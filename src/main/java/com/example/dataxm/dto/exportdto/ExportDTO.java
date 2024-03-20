package com.example.dataxm.dto.exportdto;

import com.example.dataxm.utils.ConfigTool;
import jakarta.persistence.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ExportDTO {

    private String id;
    private String item; // Item
    private String description; //Descripción comercial
    private Double fobValue; // Valor fob de la serie
    private Double netWeight; // Peso neto
    private String sector; // Sector
    private String product; // Producto

    public static List<ExportDTO> buildDto(List<Tuple> result){
        return result.stream().map( x -> ExportDTO.builder()
                .id(x.get("id").toString())
                .item(x.get("item").toString())
                .description(x.get("description").toString())
                .fobValue(Double.valueOf(x.get("fobValue").toString()))
                .netWeight(Double.valueOf(x.get("netWeight").toString()))
                .sector(ConfigTool.validateNotNullReturn(x.get("sector"),null))
                .product(ConfigTool.validateNotNullReturn(x.get("product"),null))
                .build()).collect(Collectors.toList());
    }
}
