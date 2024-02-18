package com.example.dataxm.controller;

import com.example.dataxm.dto.ExportDTO;
import com.example.dataxm.dto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.service.ExportService;
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
}
