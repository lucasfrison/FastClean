/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lavanderia.model.dao;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.beans.Roupa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author goldb
 */
public class RoupasDAO implements DAO {

    private final String insert = "INSERT INTO tb_roupas (nome_roupa, custo_roupa, prazo_roupa) VALUES (?,?,?)";
    private final String delete = "DELETE FROM tb_roupas WHERE id_roupa=?";
    private final String update = "UPDATE tb_roupas SET nome_roupa=?, custo_roupa=?, prazo_roupa=? WHERE id_roupa=?";
    private final String selectId = "SELECT * FROM tb_roupas WHERE id_roupa=?";
    private final String selectAll = "SELECT * FROM tb_roupas";

    @Override
    public Roupa buscar(int id) throws DAOException {
        try ( Connection conn = ConnectionFactory.getConnection();  PreparedStatement pst = conn.prepareStatement(selectId)) {
            Roupa r = new Roupa();
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r.setId(rs.getInt("id_roupa"));
                r.setNome(rs.getString("nome_roupa"));
                r.setCustoLavagem(rs.getDouble("custo_roupa"));
                r.setPrazoLavagem(rs.getInt("prazo_roupa"));
                break;
            }
            return r;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List buscarTodos() throws DAOException {
        try ( Connection conn = ConnectionFactory.getConnection();  PreparedStatement pst = conn.prepareStatement(selectAll)) {
            List<Roupa> lista = new ArrayList();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Roupa roupa = new Roupa();
                roupa.setId(rs.getInt("id_roupa"));
                roupa.setNome(rs.getString("nome_roupa"));
                roupa.setCustoLavagem(rs.getDouble("custo_roupa"));
                roupa.setPrazoLavagem(rs.getInt("prazo_roupa"));
                lista.add(roupa);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inserir(Object roupa) throws DAOException {
        try ( Connection conn = ConnectionFactory.getConnection();  PreparedStatement pst = conn.prepareStatement(insert)) {
            if (roupa instanceof Roupa) {
                pst.setString(1, ((Roupa) roupa).getNome());
                pst.setDouble(2, ((Roupa) roupa).getCustoLavagem());
                pst.setInt(3, ((Roupa) roupa).getPrazoLavagem());
                pst.executeUpdate();
            } else {
                throw new DAOException("Erro ao tentar atualizar a roupa!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Object roupa) throws DAOException {
        try ( Connection conn = ConnectionFactory.getConnection();  PreparedStatement pst = conn.prepareStatement(update)) {
            if (roupa instanceof Roupa) {
                pst.setString(1, ((Roupa) roupa).getNome());
                pst.setDouble(2, ((Roupa) roupa).getCustoLavagem());
                pst.setInt(3, ((Roupa) roupa).getPrazoLavagem());
                pst.setInt(4, ((Roupa) roupa).getId());
                pst.executeUpdate();
            } else {
                throw new DAOException("Erro ao tentar atualizar a roupa!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remover(int id) throws DAOException {
        try ( Connection conn = ConnectionFactory.getConnection();  PreparedStatement pst = conn.prepareStatement(delete)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
