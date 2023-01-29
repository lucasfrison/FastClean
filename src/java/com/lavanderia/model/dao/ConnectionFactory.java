/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;


import com.lavanderia.exceptions.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author lucfg
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lavanderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String SENHA = "root";
    //private static final String SENHA = "mysql";
    
    public static Connection getConnection() throws DAOException {  
        try {
            Class.forName(DRIVER);  
            Connection con = DriverManager.getConnection(URL, LOGIN, SENHA);
            return con;
        } catch(ClassNotFoundException e) {
            throw new DAOException("Driver do banco não encontrado: "+ DRIVER, e);
        } catch(SQLException e) {
            throw new DAOException("Erro conectando ao BD: " + URL + "/"+ LOGIN + "/" + SENHA, e);
        }
    }   
}    
 