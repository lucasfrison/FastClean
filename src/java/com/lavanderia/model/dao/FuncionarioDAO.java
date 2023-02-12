/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Funcionario;
import com.lavanderia.model.beans.Usuario;
import com.lavanderia.utils.DateUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucfg
 */
public class FuncionarioDAO implements DAO<Funcionario> {
    
    private Connection con;
    
    private final String QUERY_INSERIR_FUNCIONARIO = 
            "INSERT INTO tb_funcionarios(id_funcionario, nasc_funcionario) " +
            "VALUES (?, ?)";
    
    private final String QUERY_ATUALIZAR_USUARIO =
            "UPDATE tb_usuarios SET email_usuario = ?, " +
            "senha_usuario = ?, nome_usuario = ? " +
            "WHERE id_usuario = ?";
    
    private final String QUERY_ATUALIZAR_FUNCIONARIO =
            "UPDATE tb_funcionarios SET nasc_funcionario = ? " +
            "WHERE id_funcionario = ?";
    
    private final String QUERY_BUSCAR =
            "SELECT id_usuario, email_usuario, senha_usuario, " +
            "is_perfil_funcionario, nome_usuario, " +
            "nasc_funcionario FROM tb_usuarios u " +
            "JOIN tb_funcionarios f ON u.id_usuario = f.id_funcionario " +
            "AND u.id_usuario = ?";  
    
    private final String QUERY_REMOVER_FUNCIONARIO =
            "DELETE FROM tb_funcionarios WHERE id_funcionario = ?";
 
    private final String QUERY_BUSCAR_TODOS =
            "SELECT id_usuario, email_usuario, senha_usuario, " +
            "is_perfil_funcionario, nome_usuario, nasc_funcionario " +
            "FROM tb_usuarios u JOIN tb_funcionarios f " +
            "ON u.id_usuario = f.id_funcionario";

    public FuncionarioDAO(Connection con) throws DAOException {
        if (con == null)
            throw new DAOException("A conexão passada ao DAO é nula!");
        this.con = con;
    }

    @Override
    public Funcionario buscar(int id) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next())
                return getInstance(rs);
            else throw new SQLException();
        } catch (SQLException e) {
            throw new DAOException("Funcionario não encontrado com id = "
                    + id + "\n", e);
        }
    }

    @Override
    public List<Funcionario> buscarTodos() throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            List<Funcionario> listaFunc = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
                listaFunc.add(getInstance(rs));
            return listaFunc;
        } catch (SQLException e) {
            throw new DAOException("Erro ao obter lista de funcionários!", e);
        }
    }

    @Override
    public void inserir(Funcionario func) throws DAOException {
        try (
            PreparedStatement stFuncionario = con.prepareStatement(QUERY_INSERIR_FUNCIONARIO))
        {
            con.setAutoCommit(false);
            
            Usuario usuario = new Usuario();
            
            usuario.setEmail(func.getEmail());
            usuario.setSenha(func.getSenha());
            usuario.setNome(func.getNome());
            usuario.setFuncionario(true);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(con);
            usuario = usuarioDAO.insert(usuario);
            
            stFuncionario.setInt(1, usuario.getId());
            stFuncionario.setDate(2, DateUtils.
                    javaDateToSQLDate(func.getDataNascimento()));
            
            stFuncionario.executeUpdate();
            
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Erro ao inserir o funcionário: ", ex);
            }
            throw new DAOException("Erro ao inserir o funcionário: ", e);
        } 
    }

    @Override
    public void atualizar(Funcionario func) throws DAOException {
        try (PreparedStatement stUsuario = 
                con.prepareStatement(QUERY_ATUALIZAR_USUARIO);
             PreparedStatement stFuncionario = con.prepareStatement(QUERY_ATUALIZAR_FUNCIONARIO)) 
        {
            con.setAutoCommit(false);
            // LUCAS: modificar para utilizar o UsuarioDAO
            stUsuario.setString(1, func.getEmail());
            stUsuario.setString(2, func.getSenha());
            stUsuario.setString(3, func.getNome());
            stUsuario.setInt(4, func.getId());
            stUsuario.executeUpdate();
            stFuncionario.setDate(1, DateUtils.
                    javaDateToSQLDate(func.getDataNascimento()));
            stFuncionario.setInt(2, func.getId());
            stFuncionario.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Erro ao atualizar o funcionário: ", ex);
            }
            throw new DAOException("Erro ao atualizar o funcionário: ", e);
        }
    }

    @Override
    public void remover(int id) throws DAOException {
        try (PreparedStatement stFuncionario = con.prepareStatement(QUERY_REMOVER_FUNCIONARIO)) {
            con.setAutoCommit(false);
            stFuncionario.setInt(1, id);
            stFuncionario.executeUpdate();
            
            (new UsuarioDAO(con)).delete(id);
            
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Erro ao atualizar o funcionário: ", ex);
            }
            throw new DAOException("Erro ao atualizar o funcionário: ", e);
        }
    }
    
    private Funcionario getInstance(ResultSet rs) throws SQLException {
        Funcionario func = new Funcionario(); 
        func.setId(rs.getInt("id_usuario"));
        func.setNome(rs.getString("nome_usuario"));
        func.setEmail(rs.getString("email_usuario"));
        func.setSenha(rs.getString("senha_usuario"));
        func.setFuncionario(rs.getBoolean("is_perfil_funcionario"));
        func.setDataNascimento(rs.getDate("nasc_funcionario"));
        return func;
    }
    
}
