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
@Table(name = "aduana")
public class Customs {

    @Id
    @Column(name="codadu")
    private String id;

    @Column(name="descripcion")
    private String description;
}
