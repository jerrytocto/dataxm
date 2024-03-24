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
@Table(name = "agente")
public class Agent {

    @Id
    @Column(name="idcodigo")
    private String id;

    @Column(name="idagente")
    private String idAgent;

    @Column(name="agencia")
    private String agent;
}
