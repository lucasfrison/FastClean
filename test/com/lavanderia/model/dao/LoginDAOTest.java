/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.model.beans.Login;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucfg
 */
public class LoginDAOTest {
    
    public LoginDAOTest() {
    }

    /**
     * Test of isLoginValido method, of class LoginDAO.
     */
    @Test
    public void testIsLoginValido() throws Exception {
        Login login = new Login();
        login.setEmail("teste@funcionarios.com");
        login.setSenha("teste1234");
        assertTrue(new LoginDAO()
                                .isLoginValido(login));
    }
    
    @Test
    public void testIsFuncionario() throws Exception {
        Login login = new Login();
        login.setEmail("teste@funcionarios.com");
        assertTrue(new LoginDAO()
                                .isFuncionario(login));
    }
    
}
