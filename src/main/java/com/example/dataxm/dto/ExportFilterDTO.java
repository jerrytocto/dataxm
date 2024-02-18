package com.example.dataxm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportFilterDTO {

    private String description;
    private String year;
    private Integer page;
    private Integer size;
}
