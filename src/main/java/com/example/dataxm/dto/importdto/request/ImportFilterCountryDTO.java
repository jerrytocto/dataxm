package com.example.dataxm.dto.importdto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImportFilterCountryDTO {

    private String country ;
    private String year;
    private Integer page;
    private Integer size;
}
