package com.example.dataxm.dto.importdto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportSecondFilterProductDTO {

    @NotNull
    private String productName ;

    @NotNull
    private Integer year ;
}
