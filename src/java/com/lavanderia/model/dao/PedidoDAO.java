/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author goldb
 */
public class PedidoDAO implements DAO<Pedido> {

    private final String insert = "INSERT INTO tb_pedidos (id_cliente_pedido,valor_pedido,prazo_pedido,estado_pedido,dt_inicio_pedido) VALUES (?,?,?,?,?)";
    private final String insertRoupaPedido = "INSERT INTO tb_roupas_pedido (id_pedido, id_roupa) VALUES (?,?)";
    
    
    @Override
    public Pedido buscar(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pedido> buscarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void inserir(Pedido pedido) throws DAOException {
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, pedido.getCliente().getId());
            pst.setDouble(2,pedido.getValorTotal());
            pst.setDate(3, new java.sql.Date(pedido.getPrazo().getTime()));
            pst.setString(4, pedido.getSituacao().toString());
            pst.setDate(5, new java.sql.Date(new Date().getTime()));
            pst.executeUpdate();
            int idPedido = lerIdPedido(pst);
            pedido.setId(idPedido);
            pedido.getRoupas().forEach( r -> {
                try {
                    inserirRoupaPedido(pedido.getId(), r.getId());
                } catch (DAOException e) {
                    throw new RuntimeException(e);
                }
            });  
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private int lerIdPedido(PreparedStatement pst) throws SQLException {
        ResultSet rs = pst.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    } 
    
    public void inserirRoupaPedido(int pedidoId, int roupaId) throws DAOException {
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(insertRoupaPedido)) {
            pst.setInt(1, pedidoId);
            pst.setInt(2, roupaId);
            pst.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Pedido pedido) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
