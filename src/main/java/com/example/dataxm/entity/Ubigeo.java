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
@Table(name = "ubigeo")
public class Ubigeo {

    @Id
    @Column(name="cubigeo")
    private String id;
    @Column(name="nombredepartamento")
    private String department;

}

