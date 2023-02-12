/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;
//import com.lavanderia.model.beans.Usuario;;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Funcionario;
import com.lavanderia.model.beans.Usuario;
import com.lavanderia.utils.StringUtils;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author PC_Perussi
 */
public class UsuarioDAO {
    Connection con;
    
    private static final String QUERY_SELECT_ID = "SELECT * FROM tb_usuarios WHERE id_usuario = ?";
    private static final String QUERY_LOGIN = "SELECT * FROM tb_usuarios WHERE email_usuario = ? AND senha_usuario = ?";
    private static final String QUERY_INSERT = "INSERT INTO tb_usuarios ( email_usuario, senha_usuario, is_perfil_funcionario, nome_usuario ) VALUES ( ?, ?, ?, ? )";
    private static final String QUERY_REMOVER_USUARIO = "DELETE FROM tb_usuarios WHERE id_usuario = ?";

    public UsuarioDAO(Connection con) throws DAOException {
        if (con == null)
            throw new DAOException("A conexão passada ao DAO é nula!");
        this.con = con;
    }
    
    private Usuario alimentaUsuarioBean(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        
        usuario.setId(rs.getInt("id_usuario"));
        usuario.setEmail(rs.getString("email_usuario"));
        usuario.setFuncionario(rs.getBoolean("is_perfil_funcionario"));
        usuario.setNome(rs.getString("nome_usuario"));
        
        return usuario;
    }
      
    public Usuario buscar(int id) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_ID)) {
            st.setInt(1, id);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                return this.alimentaUsuarioBean(rs);
            } else {
                throw new DAOException("Usuário não encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DAOException("Ocorreu um erro ao processar a query.", e);
        }
    }
    
    public Usuario login(String email, String password) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_LOGIN)) {
            String passwordHash = StringUtils.getSha256(password);
            
            st.setString(1, email);
            st.setString(2, passwordHash);
            
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                return this.alimentaUsuarioBean(rs);
            } else {
                throw new DAOException("Credenciais inválidas");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DAOException("Ocorreu um erro ao processar a query.", e);
        }
    }
    
    public Usuario insert(Usuario usuario) throws DAOException {
        try (
            PreparedStatement st = con.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            String passwordHash = StringUtils.getSha256(usuario.getSenha());
            
            st.setString(1, usuario.getEmail());
            st.setString(2, passwordHash);
            st.setBoolean(3, usuario.isFuncionario());
            st.setString(4, usuario.getNome());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys(); 
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
                
                return usuario;
            }
            
            throw new DAOException("Falha ao inserir o usuário");
        } catch (SQLException e) {
            System.out.println(e);
            throw new DAOException("Ocorreu um erro ao processar a query.", e);
        } 
    }
    
    public void delete(int id) throws DAOException {
       try (
            PreparedStatement st = con.prepareStatement(QUERY_REMOVER_USUARIO)
        ) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar o funcionário: ", e);
        }
    }
}
