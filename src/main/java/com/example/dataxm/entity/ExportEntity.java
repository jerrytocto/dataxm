package com.example.dataxm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "exporta")
public class ExportEntity {

    @Id
    @Column(name = "ID")
    private String id; // id

    @Column(name = "PART_NANDI")
    private String item; // Partida

    @Column(name="CADU")
    private String codeAdu;

    @Column(name = "DCOM")
    private String description; //Descripción comercial

    @Column(name = "VFOBSERDOL")
    private Double fobValue; // Valor fob de la serie

    @Column(name = "FEMB")
    private LocalDateTime shippingDate; // Fecha de embarque

    @Column(name = "VPESNET")
    private Double netWeight; // Peso neto

    @Column(name = "FANO")
    private String year;

    @Column(name = "CPAIDES")
    private String country;

    @Column(name = "CAGE")
    private String agentAdua;

    @Column(name = "DNOMPRO")
    private String company;

    @Column(name = "UBIGEO")
    private String ubigeo;

}
