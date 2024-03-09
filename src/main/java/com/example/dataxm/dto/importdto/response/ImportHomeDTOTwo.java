package com.example.dataxm.dto.importdto.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportHomeDTOTwo {

    private String description;
    private String year ;
    private int departure; //Número de partidas
    private int customs ;  // Número de aduanass
    private int companies; // Número de empresas
    private int markets ;  // Número de mercados
    private BigDecimal netWeight; //Total peso neto
    private BigDecimal securityValue; //Total valor seguro
    private BigDecimal fleteValue; //Total valor flete
    private BigDecimal valueFOB; //Total valor fob




}
