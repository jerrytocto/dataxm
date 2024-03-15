package com.example.dataxm.controller;

import com.example.dataxm.dto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.request.ImportFilterCompanyDTO;
import com.example.dataxm.dto.importdto.request.ImportFilterCountryDTO;
import com.example.dataxm.dto.importdto.request.ImportSecondLevelFilterDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelCompanyDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelCountryDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelDTO;
import com.example.dataxm.dto.importdto.response.ImportHomeDTOTwo;
import com.example.dataxm.service.importservice.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ImportCompanyService importCompanyService;

    @Autowired
    private ImportCountryService importCountryService;

    @Autowired
    private ImportSecondLevelProductsService importSecondLevelProductsService;

    @Autowired
    private ImportSecondLevelPartidaService importSecondLevelPartidaService;

    @Autowired
    private ImportSecondLevelCountryService importSecondLevelCountryService;

    @Autowired
    private ImportSecondLevelCompanyService importSecondLevelCompanyService;

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

    @GetMapping("/partidas")
    public ResponseDTO<PageDTO<ImportFirstLevelDTO>> importsWithYearAndDescriptionOfPartida(@RequestBody ExportFilterDTO exportFilterDTO){
        return importPartidaService.getListProductsWithYear(exportFilterDTO);
    }

    @GetMapping("/companies")
    public ResponseDTO<PageDTO<ImportFirstLevelCompanyDTO>> importsWithYearAndDescriptionOfCompany(@RequestBody ImportFilterCompanyDTO exportFilterDTO){
        return importCompanyService.getListProductsWithCompany(exportFilterDTO);
    }

    @GetMapping("/country")
    public ResponseDTO<PageDTO<ImportFirstLevelCountryDTO>> importsWithYearAndDescriptionOfCountry(@RequestBody ImportFilterCountryDTO exportFilterDTO){
        return importCountryService.getListProductsWithCompany(exportFilterDTO);
    }

    @GetMapping("/products/{id}")
    public ResponseDTO<ImportHomeDTOTwo> secondLevelProducts(@PathVariable Integer id, @RequestBody ImportSecondLevelFilterDTO dto) throws SQLException {
        return importSecondLevelProductsService.findProductsByDescriptionAndUser(dto);
    }

    @GetMapping("/partidas/{numberPartida}")
    public ResponseDTO<ImportHomeDTOTwo> secondLevelPartida(@PathVariable Integer numberPartida, @RequestBody ImportSecondLevelFilterDTO dto) throws SQLException {
        return importSecondLevelPartidaService.findByPartidaAndanio(dto);
    }

    @GetMapping("/country/{codCountry}")
    public ResponseDTO<ImportHomeDTOTwo> secondLevelCountry(@PathVariable Integer codCountry, @RequestBody ImportSecondLevelFilterDTO dto){
        return importSecondLevelCountryService.findByPartidaAndanio(dto);
    }

    @GetMapping("/companies/{ruc}")
    public ResponseDTO<ImportHomeDTOTwo> secondLevelCompany(@PathVariable Integer ruc, @RequestBody ImportSecondLevelFilterDTO dto){
        return importSecondLevelCompanyService.findByCompanyAndYear(dto);
    }
}
