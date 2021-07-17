package com.southsystem.voting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "ASSOCIATES")
public class Associate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    public Associate(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

}
