/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lavanderia.controller;


import com.lavanderia.facade.LoginFacade;
import com.lavanderia.model.beans.Login;
import com.lavanderia.model.beans.Usuario;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;



/**
 *
 * @author PC_Perussi
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        
        request.setCharacterEncoding("UTF-8");

        try {

            String email =  request.getParameter("usuario");
            String password =  request.getParameter("senha");

            Usuario usuario = new Usuario(email, password);

            usuario = LoginFacade.login(usuario);

            if (usuario.isEmpty()) {
                throw new RuntimeException("Credenciais incorretas");
            }
            
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario",  usuario);
            session.setAttribute("logado",  true);

            RequestDispatcher rd;
            if (usuario.isFuncionario()) {
                session.setAttribute("funcionario", true);
                rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("funcionario", false);
                rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
                rd.forward(request, response);
            }
        } catch (RuntimeException e) {               
            request.setAttribute("msg", "ERRO: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
            throw new RuntimeException("Erro ao realizar cadastro!", e);
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
