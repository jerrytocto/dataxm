package com.example.dataxm.dto.exportdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportFilterDTO {

    private String description; // Descripción del producto
    private String year; //Año
    private String market; // Mercado
    private String ubigeo; // Departamento
    private String company; //Empresa
    private String agentAdua; // Agente aduanas
    private Integer page;
    private Integer size;
}
