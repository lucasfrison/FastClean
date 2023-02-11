/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.model.beans.Cliente;
import com.lavanderia.utils.DateUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author james
 */
public class AutoCadastroDAO {
    
    private Connection con;

    private final String QUERY_REALIZAR_CADASTRO = "";
    
    public AutoCadastroDAO(Connection con) throws com.lavanderia.exceptions.DAOException {
        if (con == null)
            throw new com.lavanderia.exceptions.DAOException("A conexão passada ao DAO é nula!");
        this.con = con;
    }
    
    public void inserir(Cliente cliente) throws com.lavanderia.exceptions.DAOException {
        try (PreparedStatement stCliente = 
                con.prepareStatement(QUERY_REALIZAR_CADASTRO, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stFuncionario = con.prepareStatement(QUERY_REALIZAR_CADASTRO)) 
        {
            con.setAutoCommit(false);
            stCliente.setString(1, cliente.getEmail());
            stCliente.setString(2, cliente.getSenha());
            stCliente.setBoolean(3, true);
            stCliente.setString(4, cliente.getNome());
            stCliente.executeUpdate();
            ResultSet rs = stCliente.getGeneratedKeys(); 
            if (rs.next())
                stCliente.setInt(1, rs.getInt(1));
            stCliente.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new com.lavanderia.exceptions.DAOException("Erro ao realizar cadastro: ", ex);
            }
            throw new com.lavanderia.exceptions.DAOException("Erro ao realizar cadastro: ", e);
        } 
    }
}
