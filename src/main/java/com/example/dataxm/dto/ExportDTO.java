package com.example.dataxm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExportDTO {

    private String id;
    private String item; // Item
    private String description; //Descripción comercial
    private Double fobValue; // Valor fob de la serie
    private Double netWeight; // Peso neto
}
