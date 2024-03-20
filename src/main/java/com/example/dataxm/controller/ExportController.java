package com.example.dataxm.controller;

import com.example.dataxm.dto.exportdto.AnnualIndicatorsDTO;
import com.example.dataxm.dto.exportdto.ExportDTO;
import com.example.dataxm.dto.exportdto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.service.ExportService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/exports")
public class ExportController {

    @Autowired
    ExportService exportService;

    @GetMapping
    public ResponseDTO<PageDTO<ExportDTO>> getList(
            ExportFilterDTO dto){
        return exportService.getList(dto);
    }

    @GetMapping("annualIndicators")
    public ResponseDTO<PageDTO<AnnualIndicatorsDTO>> annualIndicatorsList(ExportFilterDTO dto){
        return exportService.annualIndicatorsList(dto);
    }

    @GetMapping("{id}")
    public ResponseDTO<ExportDTO> getById(@RequestParam String id){
        return exportService.getById(id);
    }
}
