package com.example.dataxm.dto.importdto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportSecondLevelFilterDTO {

    @NotNull
    private String filter ;

    @NotNull
    private Integer year ;
}
