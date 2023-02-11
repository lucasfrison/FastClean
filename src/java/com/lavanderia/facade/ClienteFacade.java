/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.dao.ConnectionFactory;
import com.lavanderia.model.dao.ClienteDAO;
import java.sql.Connection;
import java.util.List;


/**
 *
 * @author PC_Perussi
 */
public class ClienteFacade {
       public static List<Cliente> listarCliente() {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new ClienteDAO(con).buscarTodos();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Cliente buscarCliente(int id) {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new ClienteDAO(con).buscar(id);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarCliente(Cliente cli) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new ClienteDAO(con).atualizar(cli);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removerCliente(int id) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new ClienteDAO(con).remover(id);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void inserirCliente(Cliente cli) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new ClienteDAO(con).inserir(cli);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
