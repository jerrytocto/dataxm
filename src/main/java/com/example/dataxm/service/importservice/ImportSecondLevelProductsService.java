package com.example.dataxm.service.importservice;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.request.ImportSecondFilterProductDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTOTwo;
import com.example.dataxm.repository.ImportRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Optional;

@Service
public class ImportSecondLevelProductsService {

    @Autowired
    private ImportRepository importRepository;

    //Método que busca al producto por su id y por su año
    public ResponseDTO<ImportHomeDTOTwo> findProductsByDescriptionAndUser(ImportSecondFilterProductDTO dto){

        if(dto.getProductName().isEmpty() || dto.getProductName().isBlank())
            return new ResponseDTO<>(Constants.HTTP_STATUS_WARNING, "Se debe especificar un producto");

        if(dto.getYear() > Year.now().getValue())
            return new ResponseDTO<>(Constants.HTTP_STATUS_WARNING, "El año debe ser menor que:  "+ Year.now().getValue());


        Optional<Tuple>  sqlResult = importRepository.findByProductsIdAndYear(dto.getProductName(), dto.getYear());

        if(!sqlResult.isPresent()) return new ResponseDTO<>(Constants.HTTP_STATUS_WARNING, "No se encontraron registros");

        ImportHomeDTOTwo importHomeDTO = ImportHomeDTOTwo.builder()
                .description(sqlResult.get().get("description").toString())
                .year(sqlResult.get().get("year").toString())
                .departure(getValueAsInteger(sqlResult.get().get("departure")))
                .companies(getValueAsInteger(sqlResult.get().get("companies")))
                .markets(getValueAsInteger(sqlResult.get().get("markets")))
                .valueFOB(getValueAsBigDecimal(sqlResult.get().get("valueFOB")))
                .securityValue(getValueAsBigDecimal(sqlResult.get().get("securityValue")))
                .fleteValue(getValueAsBigDecimal(sqlResult.get().get("fleteValue")))
                .netWeight(getValueAsBigDecimal(sqlResult.get().get("netWeight")))
                .build();

        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, importHomeDTO);
    }

    private Integer getValueAsInteger(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return null;
    }

    private BigDecimal getValueAsBigDecimal(Object value) {
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        return null;
    }

}
