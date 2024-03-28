package com.example.dataxm.dto.exportdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportFilterDTO {

    private String item;
    private String description; // Descripción del producto
    private String year; //Año
    private String market; // Mercado
    private String ubigeo; // Departamento
    private String company; //Empresa
    private String agentAdua; // Agente aduanas
    private Boolean seasonality=false; //Para obtener estacionalidad
    private Integer page = 0;
    private Integer size = 10;
    private String codeFilter="";
}
