/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.lavanderia.model.dao;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucfg
 */
public class ConnectionFactoryTest {
    
    public ConnectionFactoryTest() {
    }

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = ConnectionFactory.getConnection();
        assertNotNull(result);
        result.close();
    }
    
}
