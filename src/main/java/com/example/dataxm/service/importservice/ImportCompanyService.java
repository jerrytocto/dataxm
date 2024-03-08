package com.example.dataxm.service.importservice;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.ImportFilterCompanyDTO;
import com.example.dataxm.dto.importdto.ImportFirstLevelCompanyDTO;
import com.example.dataxm.dto.importdto.ImportFirstLevelDTO;
import com.example.dataxm.entity.Company;
import com.example.dataxm.repository.ImportCompanyRepository;
import com.example.dataxm.repository.ImportRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportCompanyService implements ImportFirstLevelCompanyService{

    @Autowired
    private ImportCompanyRepository importCompanyRepository;

    @Override
    public ResponseDTO<PageDTO<ImportFirstLevelCompanyDTO>> getListProductsWithCompany(ImportFilterCompanyDTO dto) {

        Optional<Company> foundCompany = Optional.of(new Company());
        Optional<List<Company>> listFountCompany =  Optional.of(new ArrayList<>());
        List<Tuple> resultList = new ArrayList<>() ;

        if(dto.getYear().isEmpty() || dto.getYear().isBlank()) dto.setYear(Year.now().toString());

        //El campo ruc no esté en blanco ni vacío
        if(!dto.getRuc().isBlank() && !dto.getRuc().isEmpty()) {

             foundCompany = findByRuc(dto.getRuc());
             if(!foundCompany.isPresent()) return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, "No existen registros");
             //Asigna el nombre de la empresa encontrada para luego filtrar en la base de datos
             dto.setCompany(foundCompany.get().getBusinessName());

             resultList = consultDB(dto);
        }

        //El campo company no esté en blanco ni vacío
        if(!dto.getCompany().isBlank() && !dto.getCompany().isEmpty()) resultList = consultDB(dto);

        // Aplicar paginación a los resultados
        int totalResults = resultList.size();
        int pageSize = dto.getSize();
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
        int page = dto.getPage();
        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalResults);

        List<Tuple> paginatedResults = resultList.subList(startIndex, endIndex);

        PageDTO<ImportFirstLevelCompanyDTO> pageDTO = new PageDTO<>(buildDto(paginatedResults), page +1 , totalResults, totalPages);
        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, pageDTO);
    }

    @Override
    public List<ImportFirstLevelCompanyDTO> buildDto(List<Tuple> result) {

        return result.stream().map( imp -> {

            ImportFirstLevelCompanyDTO importDto = new ImportFirstLevelCompanyDTO();

                    //Calcular el precio fob total
                    BigDecimal fobTotal = getValueAsBigDecimal(imp.get("fobValue")).add(getValueAsBigDecimal(imp.get("securityValue"))).add(getValueAsBigDecimal(imp.get("fleteValue")));
                    BigDecimal netWeight = getValueAsBigDecimal(imp.get("netWeight"));

                    //Consultar el ruc de la compañía pasando su nombre
                    Company company = importCompanyRepository.findByBusinessNameContaining(imp.get("company").toString());

                    importDto.setBusinessName(imp.get("company").toString());
                    importDto.setRuc(company.getRuc());
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


    //Search company only ruc in table companies
    @Override
    public Optional<Company> findByRuc(String ruc) {
        return importCompanyRepository.findByRucContaining(ruc);
    }


    //Search company only name in table companies
    @Override
    public Company findByBusinessName(String businessName) {
        return importCompanyRepository.findByBusinessNameContaining(businessName);
    }

    private List<Tuple> consultDB(ImportFilterCompanyDTO dto){
        List<Tuple> resultList = importCompanyRepository.findImportWhitCompany(dto.getCompany(),Integer.parseInt(dto.getYear()) );
        return  resultList;
    }
}
