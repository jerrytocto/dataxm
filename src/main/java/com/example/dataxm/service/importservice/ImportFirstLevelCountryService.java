package com.example.dataxm.service.importservice;

import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.request.ImportFilterCompanyDTO;
import com.example.dataxm.dto.importdto.request.ImportFilterCountryDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelCompanyDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelCountryDTO;
import jakarta.persistence.Tuple;

import java.util.List;

public interface ImportFirstLevelCountryService {

    ResponseDTO<PageDTO<ImportFirstLevelCountryDTO>> getListProductsWithCompany(ImportFilterCountryDTO dto);

    List<ImportFirstLevelCountryDTO> buildDto(List<Tuple> result);
}
