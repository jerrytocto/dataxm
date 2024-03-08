package com.example.dataxm.dto.importdto.request;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportFilterCompanyDTO {

    private String ruc ;
    private String company;
    private String year;
    private Integer page;
    private Integer size;
}
