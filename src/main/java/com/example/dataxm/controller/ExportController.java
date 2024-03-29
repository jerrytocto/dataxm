package com.example.dataxm.controller;

import com.example.dataxm.dto.exportdto.IndicatorsDTO;
import com.example.dataxm.dto.exportdto.ExportDTO;
import com.example.dataxm.dto.exportdto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.exportdto.ResponseFilterCodeDTO;
import com.example.dataxm.dto.importdto.request.ImportSecondLevelFilterDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTOTwo;
import com.example.dataxm.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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

    @GetMapping("getByItem")
    public ResponseDTO<ImportHomeDTOTwo> findByDescriptionAndYear(ImportSecondLevelFilterDTO dto){
        return exportService.findProductsByDescriptionAndYear(dto);
    }

    @GetMapping("indicators")
    public ResponseDTO<PageDTO<IndicatorsDTO>> getIndicatorsList(ExportFilterDTO dto){
        return exportService.getIndicatorsList(dto);
    }

    @GetMapping("getByCode")
    public ResponseDTO<PageDTO<ResponseFilterCodeDTO>> getByCode(ExportFilterDTO dto){
        return exportService.getByCode(dto);
    }
}
