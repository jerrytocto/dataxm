package com.example.dataxm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private int code;//codigo
    private String message;//mensaje
    private T data;//data

    public ResponseDTO(int code, T data){
        this.code = code;
        this.data = data;
    }

    public ResponseDTO(int code, String message){
        this.code = code;
        this.message= message;
    }
}
