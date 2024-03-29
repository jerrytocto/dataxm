package com.example.dataxm.service.importservice;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.request.ImportFilterCountryDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelCountryDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelDTO;
import com.example.dataxm.entity.Country;
import com.example.dataxm.repository.ImportCountryRepository;
import com.example.dataxm.repository.ImportRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportCountryService implements ImportFirstLevelCountryService {

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private ImportCountryRepository importCountryRepository;
    @Override
    public ResponseDTO<PageDTO<ImportFirstLevelCountryDTO>> getListProductsWithCompany(ImportFilterCountryDTO dto) {

        List<Tuple> resultList = new ArrayList<>();

        if(dto.getYear().isEmpty() || dto.getYear().isBlank()) dto.setYear(Year.now().toString());

        if(dto.getCountry().isEmpty() || dto.getCountry().isBlank()) {

            resultList = importRepository.findImportWithCountryOnlyYear(Integer.parseInt(dto.getYear()));

        } else {

            //Buscar país en la tabla paises usando el campo namepais
            Optional<List<Country>> foundCounties = importCountryRepository.findByCountryNameContaining(dto.getCountry());

            //No existen registros con el pais en la base de datos
            if(!foundCounties.isPresent())
                return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, "No existen registros para el país: " + dto.getCountry());

            //Por cada uno de los registro devueltos consulta por su id
            for (Country country : foundCounties.get()){

                Optional<List<Tuple>> foundImportaciones = importRepository.findImportWithCountry(country.getId(), Integer.parseInt(dto.getYear()));
                if (!foundImportaciones.isPresent())
                    return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, "No existen registros para el país: " + dto.getCountry());

                resultList.addAll(foundImportaciones.get());
            }

        }

        // Aplicar paginación a los resultados
        int totalResults = resultList.size();
        int pageSize = dto.getSize();
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
        int page = dto.getPage();
        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalResults);

        List<Tuple> paginatedResults = resultList.subList(startIndex, endIndex);

        PageDTO<ImportFirstLevelCountryDTO> pageDTO = new PageDTO<>(buildDto(paginatedResults), page +1 , totalResults, totalPages);
        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, pageDTO);
    }

    @Override
    public List<ImportFirstLevelCountryDTO> buildDto(List<Tuple> result) {
        return result.stream().filter(Objects::nonNull).map(imp -> {

            ImportFirstLevelCountryDTO importDto = new ImportFirstLevelCountryDTO();

            //Calcular el precio fob total
            BigDecimal fobTotal = getValueAsBigDecimal(imp.get("fobValue")).add(getValueAsBigDecimal(imp.get("securityValue"))).add(getValueAsBigDecimal(imp.get("fleteValue")));
            BigDecimal netWeight = getValueAsBigDecimal(imp.get("netWeight"));

            importDto.setCodCountry(imp.get("codCountry").toString());
            importDto.setCountry(imp.get("pais").toString());
            importDto.setProductName(imp.get("productName").toString());
            importDto.setFobValue(fobTotal);
            importDto.setNetWeight(netWeight);
            if (netWeight != null) importDto.setFobPrice(fobTotal.divide(netWeight,3, RoundingMode.HALF_UP));
            return importDto;
        }).collect(Collectors.toList());
    }

    private BigDecimal getValueAsBigDecimal(Object value) {
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        return null;
    }
}
