package com.example.dataxm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class ProductsEntity {

    @Id
    @Column(name = "idcod")
    private Integer id;

    @Column(name = "partida_nandi")
    private String item;

    @Column(name = "prod_especifico")
    private String product;

    @Column(name = "sector")
    private String sector;
}
