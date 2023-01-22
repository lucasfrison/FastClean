package com.lavanderia.model.beans;

import java.io.Serializable;

/**
 *
 * @author lucfg
 */
public class Cidade implements Serializable {
    
    private int id;
    private String nome;
    private UF estado;

    public Cidade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UF getEstado() {
        return estado;
    }

    public void setEstado(UF estado) {
        this.estado = estado;
    }
    
}
