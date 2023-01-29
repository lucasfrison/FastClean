/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Login;
import com.lavanderia.model.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucfg
 */
public class LoginDAO {
    
    private static final String QUERY_BUSCAR_USUARIO =
            "SELECT email_usuario, senha_usuario, " +
            "is_perfil_funcionario FROM tb_usuarios u where u.email_usuario = ?";
    
    public boolean isLoginValido(Login login) throws DAOException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_USUARIO)) {
            st.setString(1, login.getEmail());
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next())
                return rs.getString("email_usuario").equals(login.getEmail())
                       && rs.getString("senha_usuario").equals(login.getSenha());
            else throw new SQLException();
        } catch (SQLException e) {
            throw new DAOException("Usuário não encontrado! ", e);
        }
    }
    
    public boolean isFuncionario(Login login) throws DAOException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_USUARIO)) {
            st.setString(1, login.getEmail());
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next())
                return rs.getBoolean("is_perfil_funcionario");
            else throw new SQLException();
        } catch (SQLException e) {
            throw new DAOException("Usuário não encontrado! ", e);
        }
    }
    
}
