/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Roupa;
import com.lavanderia.model.dao.RoupasDAO;
import java.util.List;

/**
 *
 * @author goldb
 */
public class RoupasFacade {
    
    public static RoupasDAO rDao = new RoupasDAO();

    public static List<Roupa> buscarRoupas() {
        try {
            List<Roupa> lista = rDao.buscarTodos();
            return lista;
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Roupa buscarRoupa(int id) {
        try {
            Roupa roupa = rDao.buscar(id);
            return roupa;
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarRoupa(Roupa roupa) {
        try {
            rDao.atualizar(roupa);
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removerRoupa(int id) {
        try {
            rDao.remover(id);
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void inserirRoupa(Roupa roupa) {
        try {
            rDao.inserir(roupa);
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
