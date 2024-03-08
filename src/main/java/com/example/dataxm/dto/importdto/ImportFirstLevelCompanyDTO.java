package com.example.dataxm.dto.importdto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportFirstLevelCompanyDTO {

    private String businessName;
    private String ruc;
    private BigDecimal fobValue;
    private BigDecimal netWeight;
    private BigDecimal fobPrice;

}
