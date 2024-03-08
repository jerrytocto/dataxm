package com.example.dataxm.service.importservice;

import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.ImportFilterCompanyDTO;
import com.example.dataxm.dto.importdto.ImportFirstLevelCompanyDTO;
import com.example.dataxm.dto.importdto.ImportFirstLevelDTO;
import com.example.dataxm.entity.Company;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.Optional;

public interface ImportFirstLevelCompanyService {

    ResponseDTO<PageDTO<ImportFirstLevelCompanyDTO>> getListProductsWithCompany(ImportFilterCompanyDTO dto);

    List<ImportFirstLevelCompanyDTO> buildDto(List<Tuple> result);

    Optional<Company> findByRuc(String ruc);

    Company findByBusinessName(String businessName);
}
