/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucfg
 */
public class Pedido implements Serializable {
    
    private int id;
    private Cliente cliente;
    private Funcionario atendente;
    private List<Roupa> roupas;
    private double valorTotal;
    private Date prazo;
    private EstadoPedido situacao;

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public List<Roupa> getRoupas() {
        return roupas;
    }

    public void setRoupas(List<Roupa> roupas) {
        this.roupas = roupas;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal() {
        double valorTotal = 0;
        for (Roupa r : roupas) {
            valorTotal += r.getCustoLavagem();
        }
        this.valorTotal = valorTotal;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public EstadoPedido getSituacao() {
        return situacao;
    }

    public void setSituacao(EstadoPedido situacao) {
        this.situacao = situacao;
    }
    
}
