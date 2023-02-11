/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.model.beans.Cidade;
import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.beans.Endereco;
import com.lavanderia.model.beans.Funcionario;
import com.lavanderia.model.beans.UF;
import java.sql.Connection;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author james
 */
public class AutoCadastroDAOTest {
    
    public AutoCadastroDAOTest() {
    }
    
    @Test
    public void testInserir() {
        System.out.println("inserir");
        try (Connection con = ConnectionFactory.getConnection()) {
            
            Cliente c = new Cliente();
            
            c.setNome("testeFunc");
            c.setEmail("legal@funcionarios.com");
            c.setSenha("testelegal123");
            c.setCpf("45689632578");
            c.setTelefone("41995005555");
            
            Endereco end = new Endereco();
            end.setBairro("Caiua");
            end.setCep("81260300");
            
            Cidade city = new Cidade();
            city.setNome("Curitiba");
            city.setId(1);
            
            end.setCidade(city);
            
            UF est = new UF();
            est.setId(18);

            end.setEstado(est);
            end.setNumero(126);
            end.setRua("Rua José Sebastião Baltazar");


            c.setEndereco(end);
            city.setEstado(est);

            new AutoCadastroDAO(con).inserir(c);
        } catch (Exception e) {
            fail("Falha ao inserir cliente!");
        }
    }
    
}
