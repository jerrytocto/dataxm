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
@Table(name = "exporta")
public class ExportEntity {

    @Id
    @Column(name = "ID")
    private String id; // id

    @Column(name = "PART_NANDI")
    private String item; // Partida

    @Column(name = "DCOM")
    private String description; //Descripción comercial

    @Column(name = "VFOBSERDOL")
    private Double fobValue; // Valor fob de la serie

    @Column(name = "VPESNET")
    private Double netWeight; // Peso neto

    @Column(name = "FANO")
    private String year;

}
