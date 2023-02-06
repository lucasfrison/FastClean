/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.exceptions;

/**
 *
 * @author Will
 */
public class EditarRoupaException extends Exception {
    
    public EditarRoupaException(){
        
    }
    
    public EditarRoupaException(String message) {
        super(message);
    }
    
    public EditarRoupaException(String message, Throwable throw1) {
        super(message, throw1);
    }
    
}
