/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lavanderia.controller;

import com.lavanderia.facade.RoupasFacade;
import com.lavanderia.model.beans.Roupa;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author goldb
 */
@WebServlet(name = "RoupasServlet", urlPatterns = {"/RoupasServlet"})
public class RoupasServlet extends HttpServlet {

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
        
        // Acesso pelo NAV
        
        // Esboço Inicial 
        String action = request.getParameter("action");
        if (action == null) {
            List<Roupa> lista = RoupasFacade.buscarRoupas();
            request.setAttribute("roupas", lista);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ManutencaoRoupas.jsp");
            rd.forward(request, response);
        }
        
        int id;
        Roupa roupa = null;
        RequestDispatcher rd;
        
        switch (action) {
            case "alterarform":
                id = Integer.parseInt(request.getParameter("id"));
                roupa = RoupasFacade.buscarRoupa(id);
                request.setAttribute("roupa", roupa);
                request.setAttribute("form", "alterar");
                rd = getServletContext().getRequestDispatcher("/FormRoupa.jsp");
                rd.forward(request, response);
                break;
            case "alterar":
                roupa = new Roupa();
                roupa.setId(Integer.parseInt(request.getParameter("roupa-id")));
                roupa.setNome(request.getParameter("roupa-nome"));
                roupa.setPrazoLavagem(Integer.parseInt(request.getParameter("roupa-prazo")));
                roupa.setCustoLavagem(Double.parseDouble(request.getParameter("roupa-preco")));
                RoupasFacade.alterarRoupa(roupa);
                response.sendRedirect("RoupasServlet");
                break;
            case "remover":
                roupa = new Roupa();
                roupa.setId(Integer.parseInt(request.getParameter("id")));
                RoupasFacade.removerRoupa(roupa.getId());
                response.sendRedirect("RoupasServlet");
                break;
            case "incluirform":
                rd = getServletContext().getRequestDispatcher("/FormRoupa.jsp");
                rd.forward(request, response);
                break;
            case "incluir":
                roupa = new Roupa();
                roupa.setNome(request.getParameter("roupa-nome"));
                roupa.setPrazoLavagem(Integer.parseInt(request.getParameter("roupa-prazo")));
                roupa.setCustoLavagem(Double.parseDouble(request.getParameter("roupa-preco")));
                RoupasFacade.inserirRoupa(roupa);
                response.sendRedirect("RoupasServlet");
                break;
            default:
                System.out.println("DEFAULT");
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
