/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavanderia.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class relConnectionFactory implements AutoCloseable {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lavanderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String SENHA = "root";
    private Connection con = null;

    public Connection getConnection() throws DAOException
    {
        if (con == null)
        {
            try
            {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, LOGIN, SENHA);
            }
            catch(ClassNotFoundException e)
            {
                throw new DAOException("Driver do banco nao encontrado: " + DRIVER, e);
            }
            catch(SQLException e)
            {
                throw new DAOException("Erro conectando ao BD: " + URL + "/" + LOGIN + "/" + SENHA, e);
            }
        }
        return con;
    }

    @Override
    public void close()
    {
        if (con!=null)
        {
            try
            {
                con.close();
            }
            catch(Exception e)
            {
                System.out.println("Erro fechando a conexao. IGNORADO");
                e.printStackTrace();
            }
            finally
            {
                con = null;
            }
        }
    }
}

