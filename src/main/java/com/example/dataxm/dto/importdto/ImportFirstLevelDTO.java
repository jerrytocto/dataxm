package com.example.dataxm.dto.importdto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportFirstLevelDTO {

    private String id ;
    private String departure ;
    private String product ;
    private String sector;
    private BigDecimal fobValue;
    private BigDecimal netWeight;
    private BigDecimal fobPrice;
}
