/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.beans.EstadoPedido;
import com.lavanderia.model.beans.Pedido;
import com.lavanderia.model.beans.Roupa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author goldb
 */
public class PedidoDAO implements DAO<Pedido> {

    private final String insert = "INSERT INTO tb_pedidos (id_cliente_pedido,valor_pedido,prazo_pedido,estado_pedido,dt_inicio_pedido) VALUES (?,?,?,?,?)";
    private final String insertRoupaPedido = "INSERT INTO tb_roupas_pedido (id_pedido, id_roupa) VALUES (?,?)";
    private final String selectAll = "SELECT * FROM tb_pedidos";
    private final String select = "SELECT * FROM tb_pedidos WHERE id_pedido=?";
    private final String selectIdRoupas = "SELECT * FROM tb_roupas_pedido WHERE id_pedido=?";
    private static RoupasDAO rDao = new RoupasDAO();
    
    
    @Override
    public Pedido buscar(int id) throws DAOException {
        /*
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(select)) {
            
            
            Pedido pedido = new Pedido();
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setPrazo(rs.getDate("prazo_pedido"));
                pedido.setValorTotal(rs.getDouble("valor_pedido"));
                pedido.setSituacao(EstadoPedido.valueOf(rs.getString("estado_pedido")));
                List<Roupa> roupas = buscarRoupas(rs.getInt("id_pedido")); 
                pedido.setRoupas(roupas);
                pedidos.add(p);
            }
            
            return pedidos;
            
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        */
    }

    @Override
    public List<Pedido> buscarTodos() throws DAOException {
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(selectAll)) {
            
            List<Pedido> pedidos = new ArrayList();
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id_pedido"));
                p.setPrazo(rs.getDate("prazo_pedido"));
                p.setValorTotal(rs.getDouble("valor_pedido"));
                p.setSituacao(EstadoPedido.valueOf(rs.getString("estado_pedido")));
                List<Roupa> roupas = buscarRoupas(rs.getInt("id_pedido")); 
                p.setRoupas(roupas);
                pedidos.add(p);
            }
            
            return pedidos;
            
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
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

    private List<Roupa> buscarRoupas(int idPedido) {
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(selectIdRoupas)) {
            List<Roupa> roupas = new ArrayList();
            pst.setInt(1, idPedido);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Roupa r = rDao.buscar(rs.getInt("id_roupa"));
                roupas.add(r);
            }
            return roupas; 
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    
    
}
