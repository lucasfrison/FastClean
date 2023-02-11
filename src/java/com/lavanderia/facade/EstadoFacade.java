/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.model.beans.Funcionario;
import com.lavanderia.model.beans.UF;
import com.lavanderia.model.dao.ConnectionFactory;
import com.lavanderia.model.dao.EstadoDAO;
import java.sql.Connection;

/**
 *
 * @author lucfg
 */
public class EstadoFacade {
    
    public static UF buscarPorId(int id) throws EstadoException {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new EstadoDAO(con).buscarPorID(id);
        }
        catch(Exception e) {
            throw new EstadoException(e);
        }
    }
    
    public static UF buscarPorSigla(String sigla) throws EstadoException {
        try (Connection con = ConnectionFactory.getConnection()) {
            return new EstadoDAO(con).buscarPorSigla(sigla);
        }
        catch(Exception e) {
            throw new EstadoException(e);
        }
    }
    
}
