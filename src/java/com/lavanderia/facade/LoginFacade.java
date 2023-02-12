/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.facade;
import com.lavanderia.model.beans.Usuario;
import com.lavanderia.model.dao.ConnectionFactory;
import com.lavanderia.model.dao.UsuarioDAO;
import java.sql.Connection;

/**
 *
 * @author PC_Perussi
 */
public class LoginFacade {
    public static Usuario login(Usuario usuario) {
        try (Connection con = ConnectionFactory.getConnection()) {
            return (new UsuarioDAO(con)).login(usuario.getEmail(), usuario.getSenha());
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
