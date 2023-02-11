/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.exceptions;

/**
 *
 * @author james
 */
public class EstadoException extends Exception{
    public EstadoException() {}
    
    public EstadoException(String string)
    {
        super(string);
    }
    
    public EstadoException(String string, Throwable thrwbl)
    {
        super(string, thrwbl);
    }
}
