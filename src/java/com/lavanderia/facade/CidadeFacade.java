/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.model.beans.Cidade;
import com.lavanderia.model.beans.UF;
import com.lavanderia.model.dao.CidadeDAO;
import com.lavanderia.model.dao.ConnectionFactory;
import com.lavanderia.model.dao.EstadoDAO;
import java.sql.Connection;

/**
 *
 * @author lucfg
 */
public class CidadeFacade {
    
    public static UF buscarPorId(int id) throws CidadeException {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new EstadoDAO(con).buscarPorID(id);
        }
        catch(Exception e) {
            throw new CidadeException(e);
        }
    }
    
    public static Cidade buscarPorSigla(String nome) throws CidadeException {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new CidadeDAO(con).buscarPorNome(nome);
        }
        catch(Exception e) {
            throw new CidadeException(e);
        }
    }
    
}
