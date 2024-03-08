package com.example.dataxm.service.importservice;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTOTwo;
import com.example.dataxm.repository.ImportRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private ImportRepository importRepository;

    private static final int SCALE = 2; // Escala para la precisión de los cálculos BigDecimal

    public ResponseDTO<ImportHomeDTO> listValuesHome(Integer year){

        if (year==null || year.equals("")) year = Year.now().getValue();
        List<Tuple> result = importRepository.findImportHomeDataByYear(year);

        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, processResult(result));
    }

    public ResponseDTO<ImportHomeDTOTwo> listValuesHomeTwo(Integer year) throws SQLException {

        if (year==null || year.equals("")) year = Year.now().getValue();

        Tuple rs = importRepository.findImportHomeDataByYeartwo(year);

        ImportHomeDTOTwo importHomeDTO = ImportHomeDTOTwo.builder()
                .departure(getValueAsInteger(rs.get("departure")))
                .customs(getValueAsInteger(rs.get("customs")))
                .companies(getValueAsInteger(rs.get("companies")))
                .markets(getValueAsInteger(rs.get("markets")))
                .valueFOB(getValueAsBigDecimal(rs.get("valueFOB")))
                .securityValue(getValueAsBigDecimal(rs.get("securityValue")))
                .fleteValue(getValueAsBigDecimal(rs.get("fleteValue")))
                .netWeight(getValueAsBigDecimal(rs.get("netWeight")))
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

    //Método para procesar resultado de la consulta
    private ImportHomeDTO processResult(List<Tuple> result){

        ImportHomeDTO importHomeDTO = new ImportHomeDTO();

        //Contamos cada uno de los campos del importHomeDTO
        importHomeDTO.setDeparture(countUniqueFields(result,"partida" ));
        importHomeDTO.setCustoms(countUniqueFields(result,"customsCode" ));
        importHomeDTO.setCompanies(countUniqueFields(result,"importCompany" ));
        importHomeDTO.setMarkets(countUniqueFields(result,"originCountry" ));
        importHomeDTO.setDeparture(countUniqueFields(result,"partida" ));

        importHomeDTO.setNetWeight(sumNetWeight(result));

        BigDecimal totalFOB = sumTotalFOB(result);
        importHomeDTO.setValueFOB(totalFOB);

        return importHomeDTO;
    }

    // Método para sumar el peso neto
    private BigDecimal sumNetWeight(List<Tuple> result) {
        BigDecimal totalNetWeight = result.stream()
                .map(tuple -> BigDecimal.valueOf(tuple.get("netWeight", Double.class)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalNetWeight.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
    }

    // Método para sumar el valor FOB
    private BigDecimal sumTotalFOB(List<Tuple> result) {
        return result.stream()
                .map(tuple -> {
                    BigDecimal securityValue = BigDecimal.valueOf(tuple.get("securityValue", Double.class));
                    BigDecimal fleteValue = BigDecimal.valueOf(tuple.get("fleteValue", Double.class));
                    BigDecimal fobValue = BigDecimal.valueOf(tuple.get("fobValue", Double.class));
                    return securityValue.add(fleteValue).add(fobValue);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Método para contar cantidades de diferentes campos
    private int countUniqueFields(List<Tuple> result, String fieldName){
        return (int) result.stream()
                .map(tuple-> tuple.get(fieldName, String.class))
                .distinct()
                .count();
    }
}
