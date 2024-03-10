package com.example.dataxm.service.importservice;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.request.ImportSecondLevelFilterDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTOTwo;
import com.example.dataxm.entity.Country;
import com.example.dataxm.repository.ImportCountryRepository;
import com.example.dataxm.repository.ImportRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportSecondLevelCountryService {

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private ImportCountryRepository importCountryRepository;

    //Método que buscar el país por su código
    public ResponseDTO<ImportHomeDTOTwo> findByPartidaAndanio(ImportSecondLevelFilterDTO dto){

        if(dto.getFilter().isEmpty() || dto.getFilter().isBlank())
            return new ResponseDTO<>(Constants.HTTP_STATUS_WARNING, "Se debe especificar el país");

        if(dto.getYear() > Year.now().getValue())
            return new ResponseDTO<>(Constants.HTTP_STATUS_WARNING, "El año debe ser menor que:  "+ Year.now().getValue());

        //Consulta a la base de datos por país y año(se debe pasar como parámetro el id del país a buscar)
        Optional<Tuple>  sqlResult = importRepository.secondLevelCountry(dto.getYear(), dto.getFilter());

        if(!sqlResult.isPresent()) return new ResponseDTO<>(Constants.HTTP_STATUS_WARNING, "No se encontraron registros con el país: "+ dto.getFilter());


        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, builDTO(sqlResult.get()));
    }

    public ImportHomeDTOTwo builDTO(Tuple result){

        ImportHomeDTOTwo importHomeDTO = ImportHomeDTOTwo.builder()
                    .departure(getValueAsInteger(result.get("departure")))
                    .companies(getValueAsInteger(result.get("companies")))
                    .agentAdua(getValueAsInteger(result.get("customs")))
                    .valueFOB(getValueAsBigDecimal(result.get("valueFOB")))
                    .securityValue(getValueAsBigDecimal(result.get("securityValue")))
                    .fleteValue(getValueAsBigDecimal(result.get("fleteValue")))
                    .netWeight(getValueAsBigDecimal(result.get("netWeight")))
                    .build();

            return importHomeDTO;
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
