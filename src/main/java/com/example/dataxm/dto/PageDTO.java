package com.example.dataxm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO <T>{

    private List<T> content;//content
    private Integer currentPage;//currentPage
    private long totalElements;//totalItems
    private Integer totalPages;//totalPages

    public PageDTO(List<T> content,Integer currentPage,long totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
    }
}
