package com.example.dataxm.controller;

import com.example.dataxm.dto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.ImportFirstLevelDTO;
import com.example.dataxm.dto.importdto.ImportHomeDTO;
import com.example.dataxm.dto.importdto.ImportHomeDTOTwo;
import com.example.dataxm.service.importservice.HomeService;
import com.example.dataxm.service.importservice.ImportPartidaService;
import com.example.dataxm.service.importservice.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("v1/imports")
public class ImportController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private ImportProductService importProductService;

    @Autowired
    private ImportPartidaService importPartidaService;

    /*
    @GetMapping("/{year}")
    public ResponseDTO<ImportHomeDTO> homeImport(@PathVariable Integer year) {
        return homeService.listValuesHome(year);
    }

     */


    @GetMapping("/{year}")
    public ResponseDTO<ImportHomeDTOTwo> homeImportTwo(@PathVariable Integer year) throws SQLException {
        return homeService.listValuesHomeTwo(year);
    }

    @GetMapping("/products")
    public ResponseDTO<PageDTO<ImportFirstLevelDTO>> importsWithYearAndDescriptionOfProducts(@RequestBody ExportFilterDTO exportFilterDTO){
        return importProductService.getListProductsWithYear(exportFilterDTO);
    }

    @GetMapping("/partida")
    public ResponseDTO<PageDTO<ImportFirstLevelDTO>> importsWithYearAndDescriptionOfPartida(@RequestBody ExportFilterDTO exportFilterDTO){
        return importPartidaService.getListProductsWithYear(exportFilterDTO);
    }
}
