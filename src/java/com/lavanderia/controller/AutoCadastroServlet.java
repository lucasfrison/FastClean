/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lavanderia.controller;

import com.lavanderia.exceptions.CidadeException;
import com.lavanderia.exceptions.EstadoException;
import com.lavanderia.exceptions.ClienteException;
import com.lavanderia.facade.AutoCadastroFacade;
import com.lavanderia.model.beans.Cidade;
import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.beans.Endereco;
import com.lavanderia.model.dao.AutoCadastroDAO;
import com.lavanderia.facade.AutoCadastroFacade;
import com.lavanderia.facade.CidadeFacade;
import com.lavanderia.facade.EstadoFacade;
import com.lavanderia.model.beans.UF;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author james
 */
@WebServlet(name = "CadastroServlet", urlPatterns = {"/CadastroServlet"})
public class AutoCadastroServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ServletContext sc = request.getServletContext();
            request.setCharacterEncoding("UTF-8");

            try {
                Endereco endereco = new Endereco();

                UF estado = EstadoFacade.buscarPorSigla(request.getParameter("uf"));
                Cidade cidade = CidadeFacade.buscarPorSigla(request.getParameter("cidade"));
                cidade.setEstado(estado);
                endereco.setCidade(cidade);
                endereco.setRua(request.getParameter("rua"));
                endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCep(request.getParameter("cep").replace(".", "").replace("-", ""));

                Cliente cliente = new Cliente();
                cliente.setEndereco(endereco);

                cliente.setNome(request.getParameter("nome"));
                cliente.setTelefone(request.getParameter("telefone").replace("(", "").replace(")", "").replace("-", ""));
                cliente.setSenha(request.getParameter("senha"));
                cliente.setCpf(request.getParameter("cpf").replace(".", "").replace("-", ""));

                /*boolean confereEmail = StringUtils.confereEmail(request.getParameter("email"));
                if (confereEmail) {
                    request.setAttribute("msg", "Email ja cadastrado na base de dados");
                    RequestDispatcher rd = request.getRequestDispatcher("/cliente/autoCadastro.jsp");
                    rd.forward(request, response);
                }*/// else {
                    cliente.setEmail(request.getParameter("email"));
                    AutoCadastroFacade.realizarCadastro(cliente);

                    if (cliente != null) {
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
                        request.setAttribute("msg", "Erro ao adicionar novo cliente");
                        RequestDispatcher rd = sc.getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }                
            } catch (Exception e) {               
                request.setAttribute("msg", "ERRO: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                throw new RuntimeException("Erro ao realizar cadastro!", e);
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
