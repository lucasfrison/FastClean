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

    private final String QUERY_REALIZAR_CADASTRO_USUARIO = "INSERT INTO tb_usuarios(email_usuario, senha_usuario, " + 
            "is_perfil_funcionario, nome_usuario) " +
            "VALUES (?, ?, ?, ?)";
    
    private final String QUERY_REALIZAR_CADASTRO_CLIENTE = "INSERT INTO tb_clientes " + 
            "(id_cliente, cpf_cliente, fone_cliente, bairro_cliente, id_cidade_cliente, id_estado_cliente, numero_cliente, rua_cliente, cep_cliente) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public AutoCadastroDAO(Connection con) throws com.lavanderia.exceptions.DAOException {
        if (con == null)
            throw new com.lavanderia.exceptions.DAOException("A conexão passada ao DAO é nula!");
        this.con = con;
    }
    
    public void inserir(Cliente cliente) throws com.lavanderia.exceptions.DAOException {
        try (PreparedStatement stUsuario = 
                con.prepareStatement(QUERY_REALIZAR_CADASTRO_USUARIO, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stCliente = con.prepareStatement(QUERY_REALIZAR_CADASTRO_CLIENTE)) 
        {
            con.setAutoCommit(false);
            stUsuario.setString(1, cliente.getEmail());
            stUsuario.setString(2, cliente.getSenha());
            stUsuario.setBoolean(3, false);
            stUsuario.setString(4, cliente.getNome());
            stUsuario.executeUpdate();
            ResultSet rs = stUsuario.getGeneratedKeys(); 
            if (rs.next()){                  
                stCliente.setInt(1, rs.getInt(1));
                stCliente.setString(2, cliente.getCpf());
                stCliente.setString(3, cliente.getTelefone());
                stCliente.setString(4, cliente.getEndereco().getBairro());
                stCliente.setInt(5, cliente.getEndereco().getCidade().getId());
                stCliente.setInt(6, cliente.getEndereco().getEstado().getId());
                stCliente.setInt(7, cliente.getEndereco().getNumero());
                stCliente.setString(8, cliente.getEndereco().getRua());
                stCliente.setString(9, cliente.getEndereco().getCep());
            }
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
