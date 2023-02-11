/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.UF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucfg
 */
public class EstadoDAO {
    
    private Connection con;
    
    private final String QUERY_SELECT_SIGLA = 
            "SELECT id_estado, nome_estado, sigla_estado" +
            " FROM tb_estados WHERE sigla_estado = ?";
    
    private final String QUERY_SELECT_ID =
            "SELECT id_estado, nome_estado, sigla_estado" +
            " FROM tb_estados WHERE id_estado = ?";
    
    public EstadoDAO(Connection con) throws com.lavanderia.exceptions.DAOException {
        if (con == null)
            throw new com.lavanderia.exceptions.DAOException("A conexão passada ao DAO é nula!");
        this.con = con;
    }
    
    public UF buscarPorID(int id) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_ID)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                UF uf = new UF();
                uf.setId(id);
                uf.setNome(rs.getString("nome_estado"));
                uf.setSigla(rs.getString("sigla_estado"));
                return uf;
            }    
            else throw new SQLException();
        } catch (SQLException e) {
            throw new com.lavanderia.exceptions.DAOException("Estado não encontrado com id = "
                    + id + "\n", e);
        }
    }
    
    public UF buscarPorSigla(String sigla) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_SIGLA)) {
            st.setString(1, sigla);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                UF uf = new UF();
                uf.setId(rs.getInt("id_estado"));
                uf.setNome(rs.getString("nome_estado"));
                uf.setSigla(sigla);
                return uf;
            }    
            else throw new SQLException();
        } catch (SQLException e) {
            throw new com.lavanderia.exceptions.DAOException("Estado não encontrado com a sigla = "
                    + sigla + "\n", e);
        }
    }
    
}
