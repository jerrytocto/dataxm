package com.example.dataxm.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresas")
public class Company {

    @Id
    @Column(name = "idemp")
    private int id ;

    @Column(name = "ruc")
    private String ruc ;

    @Column(name = "razonsocial")
    private String businessName ;
}
