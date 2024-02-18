package com.example.dataxm.service;

import com.example.dataxm.base.Constants;
import com.example.dataxm.dto.ExportDTO;
import com.example.dataxm.dto.ExportFilterDTO;
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

        PageDTO<ExportDTO> pageDTO =new PageDTO<>(buildDto(query.getResultList()),dto.getPage(),0);
        return new ResponseDTO<>(Constants.HTTP_STATUS_SUCCESSFUL, pageDTO);

    }

    public List<ExportDTO> buildDto(List<Tuple> result){
        return result.stream().map( x -> ExportDTO.builder()
                        .id(x.get("id").toString())
                        .item(x.get("item").toString())
                        .description(x.get("description").toString())
                        .fobValue(Double.valueOf(x.get("fobValue").toString()))
                        .netWeight(Double.valueOf(x.get("netWeight").toString()))
                        .sector(ConfigTool.validateNotNullReturn(x.get("sector"),null))
                        .product(ConfigTool.validateNotNullReturn(x.get("product"),null))
                        .build()).collect(Collectors.toList());
    }
}