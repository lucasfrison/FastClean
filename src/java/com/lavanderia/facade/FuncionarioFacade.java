/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.model.beans.Funcionario;
import com.lavanderia.model.dao.ConnectionFactory;
import com.lavanderia.model.dao.FuncionarioDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author lucfg
 */
public class FuncionarioFacade {

    public static List<Funcionario> listarFuncionarios() {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new FuncionarioDAO(con).buscarTodos();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Funcionario buscarFuncionario(int id) {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new FuncionarioDAO(con).buscar(id);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarFuncionario(Funcionario func) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new FuncionarioDAO(con).atualizar(func);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removerFuncionario(int id) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new FuncionarioDAO(con).remover(id);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void inserirFuncionario(Funcionario func) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new FuncionarioDAO(con).inserir(func);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
