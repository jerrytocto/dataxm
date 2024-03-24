package com.example.dataxm.dto.exportdto;

import com.example.dataxm.utils.ConfigTool;
import jakarta.persistence.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ResponseFilterCodeDTO {

    private String filterHeader;
    private String year;
    private String filterData;

    public static List<ResponseFilterCodeDTO> buildDto(List<Tuple> result){
        return result.stream().map( x -> ResponseFilterCodeDTO.builder()
                .year(x.get("year").toString())
                .filterData(x.get("fobValue").toString())
                .filterHeader(ConfigTool.validateNotNullReturn(x.get("filterHeader"),null))
                .build()).collect(Collectors.toList());
    }
}
