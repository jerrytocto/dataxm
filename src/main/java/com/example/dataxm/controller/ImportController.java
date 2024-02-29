package com.example.dataxm.controller;

import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.ImportHomeDTO;
import com.example.dataxm.dto.importdto.ImportHomeDTOTwo;
import com.example.dataxm.service.importservice.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("v1/imports")
public class ImportController {

    @Autowired
    private HomeService homeService;

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
}
