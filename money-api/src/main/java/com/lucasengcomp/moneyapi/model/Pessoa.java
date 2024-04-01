package com.lucasengcomp.moneyapi.model;

import com.lucasengcomp.moneyapi.model.embededs.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String nome;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(Long codigo, String nome, Boolean ativo, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
