package com.example.dataxm.dto.exportdto;

import jakarta.persistence.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class AnnualIndicatorsDTO {

    private String year; //año
    private Double fobValue; // Valor fob USD
    private Double netWeight; // Peso neto
    private Integer recordCount; // Cantidad de registros
    private Integer companiesCount; // Cantidad de empresas
    private Integer marketsCount; // Cantidad de mercados
    private Integer departmentsCount; // Cantidad de departamentos
    private Integer customsCount; // Cantidad de aduanas

    public static List<AnnualIndicatorsDTO> buildDto(List<Tuple> result){
        return result.stream().map( x -> AnnualIndicatorsDTO.builder()
                .year(x.get("year").toString())
                .fobValue(Double.valueOf(x.get("fobValue").toString()))
                .netWeight(Double.valueOf(x.get("netWeight").toString()))
                .recordCount(Integer.parseInt(x.get("recordCount").toString()))
                .marketsCount(Integer.parseInt(x.get("marketsCount").toString()))
                .companiesCount(Integer.parseInt(x.get("companiesCount").toString()))
                .departmentsCount(Integer.parseInt(x.get("departmentsCount").toString()))
                .customsCount(Integer.parseInt(x.get("customsCount").toString()))
                .build()).collect(Collectors.toList());
    }

}
