/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavanderia.model.dao;


public class DAOException extends Exception{
    public DAOException() {}
    
    public DAOException(String string)
    {
        super(string);
    }
    
    public DAOException(String string, Throwable thrwbl)
    {
        super(string, thrwbl);
    }   
}
