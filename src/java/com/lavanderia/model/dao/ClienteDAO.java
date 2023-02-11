/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.CidadeException;
import com.lavanderia.exceptions.DAOException;
import com.lavanderia.exceptions.EstadoException;
import com.lavanderia.facade.CidadeFacade;
import com.lavanderia.facade.EstadoFacade;
import com.lavanderia.model.beans.Cidade;
import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.beans.Endereco;
import com.lavanderia.model.beans.UF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC_Perussi
 */
public class ClienteDAO implements DAO<Cliente> {
   
    private static final String QUERY_SELECT_CLIENTE="SELECT * FROM tb_clientes JOIN tb_usuarios ON tb_usuarios.id_cliente = tb_usuarios.id_usuario WHERE tb_clientes.id_cliente = ?";
    private static final String QUERY_SELECT_CLIENTES="SELECT * FROM tb_clientes JOIN tb_usuarios ON tb_usuarios.id_cliente = tb_usuarios.id_usuario";
    private static final String QUERY_REMOVER_CLIENTE ="DELETE FROM tb_clientes WHERE id_cliente = ?";
    private static final String QUERY_REMOVER_USUARIO ="DELETE FROM tb_usuarios WHERE id_usuario = ?";
    private final String QUERY_ATUALIZAR_USUARIO ="UPDATE tb_usuarios SET email_usuario = ?, senha_usuario = ?, nome_usuario = ? WHERE id_usuario = ?";
    private final String QUERY_ATUALIZAR_CLIENTE = "UPDATE tb_clientes SET cpf_cliente = ?, fone_cliente = ?, bairro_cliente = ?, id_cidade_cliente = ?, id_estado_cliente = ?, numero_cliente = ? , rua_cliente = ?  WHERE id_cliente = ?";
    
    private Connection con;

    public ClienteDAO(Connection con) throws com.lavanderia.exceptions.DAOException {
      if (con == null)
          throw new com.lavanderia.exceptions.DAOException("A conexão passada ao DAO é nula!");
      this.con = con;
    }
    
    @Override
    public Cliente buscar(int id) throws DAOException {
       try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_CLIENTE)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next())
                return getInstance(rs);
            else throw new SQLException();
        } catch (SQLException e) {
            throw new DAOException("Cliente não encontrado com id = "
                    + id + "\n", e);
        }
    }
    
    private Cliente getInstance(ResultSet rs) throws SQLException {
        Cliente cli = new Cliente(); 
        cli.setId(rs.getInt("id_usuario"));
        cli.setNome(rs.getString("nome_usuario"));
        cli.setEmail(rs.getString("email_usuario"));
        cli.setSenha(rs.getString("senha_usuario"));
        cli.setFuncionario(rs.getBoolean("is_perfil_funcionario"));
        cli.setCpf(rs.getString("cpf_cliente"));
        cli.setTelefone(rs.getString("fone_cliente"));
        Endereco endereco = new Endereco();
        try {
            UF estado = EstadoFacade.buscarPorId(rs.getInt("id_estado_cliente"));
               endereco.setEstado(estado);

        } catch (EstadoException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Cidade cidade = CidadeFacade.buscarPorId(rs.getInt("id_cidade_cliente"));
            endereco.setCidade(cidade);   
        } catch (CidadeException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        endereco.setRua(rs.getString("rua_cliente"));
        endereco.setBairro(rs.getString("bairro_cliente"));
        endereco.setCep(rs.getString("cep_cliente"));
        endereco.setNumero(rs.getInt("numero_cliente"));
        cli.setEndereco(endereco);
        return cli;
    }
    

    @Override
    public List<Cliente> buscarTodos() throws DAOException {
          try (PreparedStatement st = con.prepareStatement(QUERY_SELECT_CLIENTES)) {
            List<Cliente> listaClientes = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
                listaClientes.add(getInstance(rs));
            return listaClientes;
        } catch (SQLException e) {
            throw new DAOException("Erro ao obter lista de funcionários!", e);
        }
    }

    @Override
    public void inserir(Cliente t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Cliente cli) throws DAOException {
    try (PreparedStatement stUsuario = 
                con.prepareStatement(QUERY_ATUALIZAR_USUARIO);
             PreparedStatement stCliente = con.prepareStatement(QUERY_ATUALIZAR_CLIENTE)) 
        {
            con.setAutoCommit(false);
            stUsuario.setString(1, cli.getEmail());
            stUsuario.setString(2, cli.getSenha());
            stUsuario.setString(3, cli.getNome());
            stUsuario.setInt(4, cli.getId());
            stUsuario.executeUpdate();          
            stCliente.setString(1, cli.getCpf());
            stCliente.setString(2, cli.getTelefone());
            stCliente.setString(3, cli.getEndereco().getBairro());
            stCliente.setInt(4, cli.getEndereco().getCidade().getId());
            stCliente.setInt(5, cli.getEndereco().getEstado().getId());
            stCliente.setInt(6, cli.getEndereco().getNumero());
            stCliente.setString(7, cli.getEndereco().getRua());
            stCliente.setInt(8, cli.getId());
            stCliente.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Erro ao atualizar o cliente: ", ex);
            }
            throw new DAOException("Erro ao atualizar o cliente: ", e);
        }
    }

    @Override
    public void remover(int id) throws DAOException {
         try (PreparedStatement stCliente = 
                con.prepareStatement(QUERY_REMOVER_CLIENTE);
             PreparedStatement stUsuario = con.prepareStatement(QUERY_REMOVER_USUARIO)) 
        {
            con.setAutoCommit(false);
            stCliente.setInt(1, id);
            stCliente.executeUpdate();
            stUsuario.setInt(1, id);
            stUsuario.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Erro ao remover o Cliente: ", ex);
            }
            throw new DAOException("Erro ao remover o Cliente: ", e);
        }
    }
    
}
