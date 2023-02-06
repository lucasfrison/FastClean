/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.exceptions.InserirPedidoException;
import com.lavanderia.model.beans.Pedido;
import com.lavanderia.model.dao.PedidoDAO;

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
    
}
