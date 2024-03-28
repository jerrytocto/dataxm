package com.example.dataxm.controller;

import com.example.dataxm.dto.exportdto.ExportFilterDTO;
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
import java.time.Year;

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
    public ResponseDTO<PageDTO<ImportFirstLevelDTO>> importsWithYearAndDescriptionOfProducts(
            @RequestParam(value = "description") String description,
            @RequestParam(value = "year", defaultValue = "2024") int year,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size
    ){
        return importProductService.getListProductsWithYear(description, year, page, size);
    }

    @GetMapping("/partidas")
    public ResponseDTO<PageDTO<ImportFirstLevelDTO>> importsWithYearAndDescriptionOfPartida(
            @RequestParam(value = "part") String part,
            @RequestParam(value = "year", defaultValue = "2024") int yearPart,
            @RequestParam(value = "page", defaultValue = "0") int pagePart,
            @RequestParam(value = "size", defaultValue = "8") int sizePart
    ){
        return importPartidaService.getListProductsWithYear(part, yearPart, pagePart, sizePart);
    }

    @GetMapping("/companies")
    public ResponseDTO<PageDTO<ImportFirstLevelCompanyDTO>> importsWithYearAndDescriptionOfCompany(
            @RequestParam(value = "ruc") String ruc,
            @RequestParam(value = "company") String company,
            @RequestParam(value = "year", defaultValue = "2024") int yearCompany,
            @RequestParam(value = "page", defaultValue = "0") int pageCompany,
            @RequestParam(value = "size", defaultValue = "8") int sizeCompany
    ){
        return importCompanyService.getListProductsWithCompany(ruc, company, yearCompany, pageCompany, sizeCompany);
    }

    @GetMapping("/country")
    public ResponseDTO<PageDTO<ImportFirstLevelCountryDTO>> importsWithYearAndDescriptionOfCountry(@RequestBody ImportFilterCountryDTO exportFilterDTO){
        return importCountryService.getListProductsWithCompany(exportFilterDTO);
    }

    @GetMapping("/products/search")
    public ResponseDTO<ImportHomeDTOTwo> secondLevelProducts(
            @RequestParam(value = "filter") String filter,
            @RequestParam(value = "year", defaultValue = "2023") int year
    ) throws SQLException {
        System.out.println("LLEGAS AQUÍ");
        return importSecondLevelProductsService.findProductsByDescriptionAndUser(filter , year);
    }

    @GetMapping("/partidas/search")
    public ResponseDTO<ImportHomeDTOTwo> secondLevelPartida(
            @RequestParam(value = "filter") String filter,
            @RequestParam(value = "year", defaultValue = "2023") int year
    ) throws SQLException {
        return importSecondLevelPartidaService.findByPartidaAndanio(filter, year);
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
