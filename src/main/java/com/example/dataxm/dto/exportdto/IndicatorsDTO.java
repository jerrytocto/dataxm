package com.example.dataxm.dto.exportdto;

import com.example.dataxm.utils.ConfigTool;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndicatorsDTO {

    private String year; //año
    private String month; //Fecha de embarque
    private Double fobValue; // Valor fob USD
    private Double netWeight; // Peso neto
    private Integer recordCount; // Cantidad de registros
    private Integer companiesCount; // Cantidad de empresas
    private Integer marketsCount; // Cantidad de mercados
    private Integer departmentsCount; // Cantidad de departamentos
    private Integer customsCount; // Cantidad de aduanas
    private String countryName; //

    public static List<IndicatorsDTO> buildDto(List<Tuple> result){
        return result.stream().map( x -> IndicatorsDTO.builder()
                .year(x.getElements().stream().anyMatch(e-> e.getAlias().equals("year"))? x.get("year").toString() :null)
                .month(x.getElements().stream().anyMatch(e-> e.getAlias().equals("month"))? ConfigTool.getMonthName(Integer.parseInt(x.get("month").toString())) :null)
                .countryName(x.getElements().stream().anyMatch(e-> e.getAlias().equals("country"))? x.get("country").toString() :null)
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
