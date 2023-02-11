/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.dao.ConnectionFactory;
import com.lavanderia.model.dao.AutoCadastroDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author james
 */



public class AutoCadastroFacade {
    
    public static void realizarCadastro(Cliente cliente) {
        try (Connection con = ConnectionFactory.getConnection()) {
            new AutoCadastroDAO(con).inserir(cliente);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
