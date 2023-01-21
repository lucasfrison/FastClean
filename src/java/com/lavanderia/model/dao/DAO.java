/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lavanderia.model.dao;

/**
 *
 * @author lucfg
 */
import com.lavanderia.exceptions.DAOException;
import java.util.List;

public interface DAO<T> {
    public T buscar(String string) throws DAOException;
    public List<T> buscarTodos() throws DAOException;
    public void inserir(T t) throws DAOException;
    public void atualizar(T t) throws DAOException;
    public void remover(T t) throws DAOException;
}