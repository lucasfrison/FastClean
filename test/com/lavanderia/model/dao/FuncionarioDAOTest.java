/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.model.beans.Funcionario;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucfg
 */
public class FuncionarioDAOTest {
    
    final int ID = 15;
    
    public FuncionarioDAOTest() {
    }

    @Test
    public void testInserir() {
        System.out.println("inserir");
        try (Connection con = ConnectionFactory.getConnection()) {
            Funcionario func = new Funcionario();
            func.setNome("testeFunc");
            func.setEmail("teste@funcionarios.com");
            func.setSenha("teste1234");
            func.setDataNascimento(new Date());
            new FuncionarioDAO(con).inserir(func);
        } catch (Exception e) {
            fail("Falha ao inserir funcionario!");
        }
    }

    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        try (Connection con = ConnectionFactory.getConnection()) {
            Funcionario func = new Funcionario();
            func.setId(ID);
            func.setNome("teste2");
            func.setEmail("deucerto@funcionarios.com");
            func.setSenha("55468");
            func.setDataNascimento(new Date());
            new FuncionarioDAO(con).atualizar(func);
        } catch (Exception e) {
            fail("Falha ao atualizar funcionario!");
        }
    }
    
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        try (Connection con = ConnectionFactory.getConnection()) {
            Funcionario result = new FuncionarioDAO(con).buscar(ID);
            assertEquals("teste2", result.getNome());
        } catch(Exception e) {
            fail("Erro ao buscar os funcionário!");
        }
    }

    @Test
    public void testBuscarTodos() {
        System.out.println("buscarTodos");
        try (Connection con = ConnectionFactory.getConnection()) {
            List<Funcionario> result = new FuncionarioDAO(con).buscarTodos();
        } catch(Exception e) {
            fail("Erro ao buscar os funcionários!");
        }
    }
    
    @Test
    public void testRemover() throws Exception {
        System.out.println("remover");
        try (Connection con = ConnectionFactory.getConnection()) {
            new FuncionarioDAO(con).remover(ID);
        } catch(Exception e) {
            fail("Erro ao remover os funcionário!");
        }
    }
    
}
