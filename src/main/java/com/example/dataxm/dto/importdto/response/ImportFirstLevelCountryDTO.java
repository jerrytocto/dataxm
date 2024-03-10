package com.example.dataxm.dto.importdto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportFirstLevelCountryDTO {

    private String country;
    private String codCountry;
    private String productName;
    private String year;
    private BigDecimal fobValue;
    private BigDecimal netWeight;
    private BigDecimal fobPrice;

}
