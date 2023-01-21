/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.beans;

import java.io.Serializable;

/**
 *
 * @author lucfg
 */
public class Roupa implements Serializable {
    
    private int id;
    private String nome;
    private double custoLavagem;
    private int prazoLavagem;

    public Roupa() {
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

    public double getCustoLavagem() {
        return custoLavagem;
    }

    public void setCustoLavagem(double custoLavagem) {
        this.custoLavagem = custoLavagem;
    }

    public int getPrazoLavagem() {
        return prazoLavagem;
    }

    public void setPrazoLavagem(int prazoLavagem) {
        this.prazoLavagem = prazoLavagem;
    }
    
}
