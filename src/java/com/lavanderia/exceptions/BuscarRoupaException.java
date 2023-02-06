/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.exceptions;

/**
 *
 * @author Will
 */
public class BuscarRoupaException extends Exception{
    
    public BuscarRoupaException() {}
    
    public BuscarRoupaException(String message) {
        super(message);
    } 
    
    public BuscarRoupaException(String message, Throwable throw1) {
        super(message, throw1);
    }
}
