package com.example.dataxm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @Entity @AllArgsConstructor @NoArgsConstructor
@Table(name = "importa")
public class ImportEntity {

    @Id
    @Column(name = "ID")
    private String id; // id

    @Column(name = "PART_NANDI")
    private String partida; // Partida

    @Column(name = "CODI_ADUAN")
    private String customsCode;

    @Column(name = "DESC_COMER")
    private String description; //Descripción comercial

    @Column(name = "FOB_DOLPOL")
    private Double fobValue; // Valor fob de la serie

    @Column(name = "SEG_DOLAR")
    private Double securityValue; // Valor fob de la serie

    @Column(name = "FLE_DOLAR")
    private Double fleteValue; // Valor fob de la serie

    @Column(name = "PESO_NETO")
    private Double netWeight; // Peso neto

    @Column(name = "FECH_INGSI")
    private Date date;

    @Column(name = "NUME_CORRE")
    private String importCompany;

    @Column(name= "PAIS_ORIGE")
    private String originCountry;

    @Column(name = "CODI_AGENT")
    private String agentAdua;

}
