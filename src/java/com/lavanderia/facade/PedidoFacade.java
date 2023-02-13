/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.exceptions.InserirPedidoException;
import com.lavanderia.model.beans.Pedido;
import com.lavanderia.model.dao.PedidoDAO;
import java.util.List;

/**
 *
 * @author goldb
 */

public class PedidoFacade {

    public static PedidoDAO pDao = new PedidoDAO();
    
    public static void inserirPedido(Pedido pedido) throws InserirPedidoException {
        try {
            pDao.inserir(pedido);
        }
        catch(DAOException e) {
            throw new InserirPedidoException("Erro ao tentar inserir o pedido: " + e.getMessage());
        }
    }

    public static List<Pedido> buscarPedidos() {
        try {
            List<Pedido> pedidos = pDao.buscarTodos();
            return pedidos;
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pedido buscarPedido(int idPedido) {
        try {
            Pedido pedido = pDao.buscar(idPedido);
            return pedido;
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarEstadoPedido(Pedido pedido, String situacao) {
        try {
            switch (situacao) {
                case "aberto":
                    pDao.alterarEstadoPedido(pedido, "RECOLHIDO");
                    break;
                case "abertocli":
                    pDao.alterarEstadoPedido(pedido, "CANCELADO");
                    break;
                case "recolhido":
                    pDao.alterarEstadoPedido(pedido, "AGUARDANDO_PAGAMENTO");
                    break;
                case "aguardando":
                    pDao.alterarEstadoPedido(pedido, "PAGO");
                    break;
                case "pago":
                    pDao.alterarEstadoPedido(pedido, "FINALIZADO");
                    break;
                default:
                    throw new DAOException();
            }
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Pedido> buscarPedidosEmAberto(int id) {
        try {
            List<Pedido> pedidos = pDao.buscarTodosEmAberto(id);
            return pedidos;
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Pedido> buscarTodosPedidosEmAberto() {
        try {
            List<Pedido> pedidos = pDao.buscarTodosEmAberto();
            return pedidos;
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
