package com.example.dataxm.service.importservice;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelDTO;
import com.example.dataxm.repository.ImportRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportProductService implements ImportFirstLevelService{

    @Autowired
    private ImportRepository importRepository;

    @Override
    public ResponseDTO<PageDTO<ImportFirstLevelDTO>> getListProductsWithYear(ExportFilterDTO dto) {

        if(dto.getYear().isEmpty() || dto.getYear().isBlank()) dto.setYear(Year.now().toString());
        if(dto.getDescription().isEmpty() || dto.getDescription().isBlank()) dto.setDescription("laptop");

        List<Tuple> resultList = importRepository.findImportWhitProducts(dto.getDescription(),Integer.parseInt(dto.getYear()) );

        // Aplicar paginación a los resultados
        int totalResults = resultList.size();
        int pageSize = dto.getSize();
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
        int page = dto.getPage();
        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalResults);

        List<Tuple> paginatedResults = resultList.subList(startIndex, endIndex);

        PageDTO<ImportFirstLevelDTO> pageDTO = new PageDTO<>(buildDto(paginatedResults), page +1 , totalResults, totalPages);
        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, pageDTO);
    }

    @Override
    public List<ImportFirstLevelDTO> buildDto(List<Tuple> result) {

        return result.stream().map( imp -> {

                    ImportFirstLevelDTO importDto = new ImportFirstLevelDTO();

                    //Calcular el precio fob total
                    BigDecimal fobTotal = getValueAsBigDecimal(imp.get("fobValue")).add(getValueAsBigDecimal(imp.get("securityValue"))).add(getValueAsBigDecimal(imp.get("fleteValue")));
                    BigDecimal netWeight = getValueAsBigDecimal(imp.get("netWeight"));

                    importDto.setId(imp.get("id").toString());
                    importDto.setDeparture(imp.get("departure").toString());
                    importDto.setProduct(imp.get("description").toString());
                    importDto.setFobValue(fobTotal);
                    importDto.setNetWeight(netWeight);
                    importDto.setSector(imp.get("sector").toString());
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
