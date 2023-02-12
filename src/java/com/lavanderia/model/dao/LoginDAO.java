///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.lavanderia.model.dao;
//
//import com.lavanderia.exceptions.DAOException;
//import com.lavanderia.model.beans.Login;
//import com.lavanderia.model.beans.Usuario;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// *
// * MARCADA PARA MORRER
// * 
// * 
// * @author lucfg
// */
//public class LoginDAO {
//    
//    private Connection con;
//
//    private static final String QUERY_BUSCAR_USUARIO =
//            "SELECT email_usuario, "
//            + ""
//            + ""
//            + ""
//            + "" +
//            "is_perfil_funcionario FROM tb_usuarios u where u.email_usuario = ? AND u.senha_usuario";
//
//    public LoginDAO(Connection con) throws DAOException {
//        if (con == null)
//            throw new DAOException("A conexão passada ao DAO é nula!");
//        this.con = con;
//    }
//    
//    public boolean isLoginValido(Login login) throws DAOException {
//        try {
//            PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_USUARIO);
//            st.setString(1, login.getEmail());
//            
//            st.executeQuery();
//            ResultSet rs = st.getResultSet();
//            
//            if (rs.next()) {
//                
//                
//            } else {
//                throw new DAOException("Usuário não encontrado! ");
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Ocorreu um problema", e);
//        }
//    }
//               
//    public boolean isFuncionario(Login login) throws DAOException {
//        try {
//            PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_USUARIO); 
//            st.setString(1, login.getEmail());
//            st.executeQuery();
//            ResultSet rs = st.getResultSet();
//            if (rs.next())
//                return rs.getBoolean("is_perfil_funcionario");
//            else throw new SQLException();
//        } catch (SQLException e) {
//            throw new DAOException("Usuário não encontrado! ", e);
//        }
//    }
//    
//}
