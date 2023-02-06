/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.exceptions;

/**
 *
 * @author Will
 */
public class InserirPedidoException extends Exception{
    
    public InserirPedidoException() {
        
    }
    
    public InserirPedidoException(String message) {
        super(message);
    }
    
    public InserirPedidoException(String message, Throwable throw1) {
        super(message, throw1);
    }
}
