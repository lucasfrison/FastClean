/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;

import com.lavanderia.exceptions.BuscarRoupaException;
import com.lavanderia.exceptions.DAOException;
import com.lavanderia.exceptions.EditarRoupaException;
import com.lavanderia.exceptions.InserirRoupaException;
import com.lavanderia.exceptions.RemoverRoupaException;
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

    public static Roupa buscarRoupa(int id) throws BuscarRoupaException {
        try {
            Roupa roupa = rDao.buscar(id);
            return roupa;
        }
        catch(DAOException e) {
            throw new BuscarRoupaException("Erro ao buscar roupa: id = " + id + e.getMessage());
        }
    }

    public static void alterarRoupa(Roupa roupa) throws EditarRoupaException {
        try {
            rDao.atualizar(roupa);
        }
        catch(DAOException e) {
            throw new EditarRoupaException("Erro ao editar a roupa = " + e.getMessage());
        }
    }

    public static void removerRoupa(int id) throws RemoverRoupaException {
        try {
            rDao.remover(id);
        }
        catch(DAOException e) {
            throw new RemoverRoupaException("Erro ao remover roupa: id = " + id + e.getMessage());
        }
    }

    public static void inserirRoupa(Roupa roupa) throws InserirRoupaException {
        try {
            rDao.inserir(roupa);
        }
        catch(DAOException e) {
            throw new InserirRoupaException("Erro ao inserir roupa = "+ e.getMessage());
        }
    }
    
    
}
