/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lavanderia.controller;

import com.lavanderia.facade.FuncionarioFacade;
import com.lavanderia.model.beans.Funcionario;
import com.lavanderia.utils.DateUtils;
import com.lavanderia.utils.StringUtils;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucfg
 */
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        String action = request.getParameter("action");
        int id;
        Funcionario func;
        RequestDispatcher rd;
        
        if (action == null) {
            List<Funcionario> lista = FuncionarioFacade.listarFuncionarios();
            request.setAttribute("funcionarios", lista);
            rd = getServletContext().getRequestDispatcher("/ManutencaoFuncionario.jsp");
            rd.forward(request, response);
            return;
        }
        
        switch (action) {
            case "buscar":
                id = Integer.parseInt(request.getParameter("id"));
                func = FuncionarioFacade.buscarFuncionario(id);
                request.setAttribute("func", func);
                rd = getServletContext().getRequestDispatcher("FuncionarioServlet");
                rd.forward(request, response);
                break;
            case "formAlterar":
                id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("id", id);
                func = FuncionarioFacade.buscarFuncionario(id);
                request.setAttribute("func", func);
                request.setAttribute("form", "alterar");
                rd = getServletContext().getRequestDispatcher("/ManutencaoFuncionario.jsp");
                rd.forward(request, response);
                break;
            case "alterar":
                id = Integer.parseInt(request.getParameter("id"));
                func = getInstance(request);
                func.setId(id);
                FuncionarioFacade.alterarFuncionario(func);
                response.sendRedirect("FuncionarioServlet");
                break;
            case "remover":
                func = new Funcionario();
                func.setId(Integer.parseInt(request.getParameter("id")));
                FuncionarioFacade.removerFuncionario(func.getId());
                response.sendRedirect("FuncionarioServlet");
                break;
            case "inserir":
                func = getInstance(request);
                FuncionarioFacade.inserirFuncionario(func);
                response.sendRedirect("FuncionarioServlet");
                break;   
                
        }    
        
    }
    
    private Funcionario getInstance(HttpServletRequest request) {
        Funcionario func = new Funcionario();
        try {
            func.setNome(request.getParameter("nome"));
            func.setEmail(request.getParameter("email"));
            func.setSenha(StringUtils.getSha256(request.getParameter("senha")));
            String dataNasc = request.getParameter("dataNasc");
            Date dataNascFmt = DateUtils.stringToJavaDate(dataNasc);
            func.setDataNascimento(dataNascFmt);
            return func;
        } catch (NullPointerException | ParseException e) {
            throw new RuntimeException("Verifique os campos do formulário!", e);
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
