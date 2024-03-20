package com.example.dataxm.service;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.exportdto.AnnualIndicatorsDTO;
import com.example.dataxm.dto.exportdto.ExportDTO;
import com.example.dataxm.dto.exportdto.ExportFilterDTO;
import com.example.dataxm.dto.PageDTO;
import com.example.dataxm.dto.ResponseDTO;
import com.example.dataxm.utils.ConfigTool;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService {

    @Autowired
    private EntityManager em;
    public ResponseDTO<PageDTO<ExportDTO>> getList(ExportFilterDTO dto){

        // Generamos la consulta
        String sqlTemplate = "SELECT ex.id as id, ex.item as item, ex.description as description, ex.fobValue as fobValue, " +
                    "ex.netWeight as netWeight, p.sector as sector, p.product as product " +
                    "FROM ExportEntity ex " +
                    "LEFT JOIN ProductsEntity p ON p.item = ex.item ";

        // Agregamos las condiciones a la consulta
        List<String> predicates =new ArrayList<>();
        ConfigTool.addFilterToPredicate(predicates,"ex.description LIKE upper('%"+dto.getDescription()+"%')", dto.getDescription());
        ConfigTool.addFilterToPredicate(predicates, "ex.year = " + dto.getYear(), dto.getYear());

        if(!predicates.isEmpty()) sqlTemplate += String.format(" WHERE %s", String.join(" AND ", predicates));
        TypedQuery<Tuple> query = em.createQuery(sqlTemplate, Tuple.class);

        // Paginación
        query.setFirstResult(dto.getPage()*dto.getSize());
        query.setMaxResults(dto.getSize());

        PageDTO<ExportDTO> pageDTO =new PageDTO<>(ExportDTO.buildDto(query.getResultList()),dto.getPage(),0);
        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, pageDTO);

    }

    public ResponseDTO<PageDTO<AnnualIndicatorsDTO>> annualIndicatorsList(ExportFilterDTO dto){

        String sqlTemplate = "SELECT ex.year AS year, COUNT(ex.id) AS recordCount, SUM(ex.fobValue) as fobValue," +
                "SUM(ex.netWeight) as netWeight, COUNT(DISTINCT ex.country) as marketsCount, COUNT(DISTINCT ex.agentAdua) as customsCount," +
                "COUNT(DISTINCT ex.company) as companiesCount, COUNT(DISTINCT ex.ubigeo) as departmentsCount " +
                "FROM ExportEntity ex ";

        // Agregamos las condiciones a la consulta
        List<String> predicates =new ArrayList<>();
        ConfigTool.addFilterToPredicate(predicates, "ex.country = " + dto.getMarket(), dto.getMarket());
        ConfigTool.addFilterToPredicate(predicates, "ex.company = " + dto.getCompany(), dto.getCompany());
        ConfigTool.addFilterToPredicate(predicates, "ex.ubigeo = " + dto.getUbigeo(), dto.getUbigeo());
        ConfigTool.addFilterToPredicate(predicates, "ex.agentAdua = " + dto.getAgentAdua(), dto.getAgentAdua());

        if(!predicates.isEmpty()) sqlTemplate += String.format(" WHERE %s", String.join(" AND ", predicates));
        sqlTemplate = sqlTemplate + " GROUP BY ex.year ORDER BY ex.year ASC ";
        TypedQuery<Tuple> query = em.createQuery(sqlTemplate, Tuple.class);

        // Paginación
        query.setFirstResult(dto.getPage()*dto.getSize());
        query.setMaxResults(dto.getSize());

        PageDTO<AnnualIndicatorsDTO> pageDTO =new PageDTO<>(AnnualIndicatorsDTO.buildDto(query.getResultList()),dto.getPage(),0);
        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, pageDTO);

    }


    public ResponseDTO<ExportDTO> getById(String id){
        return null;
    }

}
