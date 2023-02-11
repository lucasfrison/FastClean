/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.model.beans.Cidade;
import com.lavanderia.model.beans.UF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucfg
 */
public class CidadeDAO {
    
    private Connection con;
    
    private final String QUERY_SELECT_NOME = 
            "SELECT id_cidade, nome_cidade, id_estado_cidade" +
            " FROM tb_cidades WHERE nome_cidade = ?";
    
    private final String QUERY_SELECT_ID =
            "SELECT id_cidade, nome_cidade, id_estado_cidade" +
            " FROM tb_cidades WHERE id_cidade = ?";
    
    public CidadeDAO(Connection con) throws com.lavanderia.exceptions.DAOException {
        if (con == null)
            throw new com.lavanderia.exceptions.DAOException("A conexão passada ao DAO é nula!");
        this.con = con;
    }
    
    public Cidade buscarPorID(int id) throws com.lavanderia.exceptions.DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_ID)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(id);
                cidade.setNome(rs.getString("nome_cidade"));
                cidade.setEstado(new EstadoDAO(con).buscarPorID(
                        rs.getInt("id_estado_cidade")));
                return cidade;
            }    
            else throw new SQLException();
        } catch (SQLException e) {
            throw new com.lavanderia.exceptions.DAOException("Cidade não encontrada com id = "
                    + id + "\n", e);
        }
    }
    
    public Cidade buscarPorNome(String nome) throws com.lavanderia.exceptions.DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_NOME)) {
            st.setString(1, nome);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("id_cidade"));
                cidade.setNome(nome);
                cidade.setEstado(new EstadoDAO(con).buscarPorID(
                        rs.getInt("id_estado_cidade")));
                return cidade;
            }    
            else throw new SQLException();
        } catch (SQLException e) {
            throw new com.lavanderia.exceptions.DAOException("Cidade não encontrada com nome = "
                    + nome + "\n", e);
        }
    }
    
}
