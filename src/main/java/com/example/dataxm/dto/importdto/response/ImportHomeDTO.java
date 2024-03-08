package com.example.dataxm.dto.importdto.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportHomeDTO {

    private int departure; //Número de partidas
    private int customs ;  // Número de aduanass
    private int companies; // Número de empresas
    private int markets ;  // Número de mercados
    private BigDecimal netWeight; //Total peso neto
    private BigDecimal valueFOB; //Total valor fob




}
