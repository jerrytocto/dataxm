package com.example.dataxm.service.importservice;

import com.example.dataxm.dto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.dto.importdto.response.ImportFirstLevelDTO;
import jakarta.persistence.Tuple;

import java.util.List;

public interface ImportFirstLevelService {

    ResponseDTO<PageDTO<ImportFirstLevelDTO>> getListProductsWithYear(ExportFilterDTO dto);

    List<ImportFirstLevelDTO> buildDto(List<Tuple> result);
}
