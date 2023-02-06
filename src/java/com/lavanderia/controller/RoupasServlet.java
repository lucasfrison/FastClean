/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lavanderia.controller;

import com.lavanderia.exceptions.BuscarRoupaException;
import com.lavanderia.exceptions.EditarRoupaException;
import com.lavanderia.exceptions.InserirRoupaException;
import com.lavanderia.exceptions.RemoverRoupaException;
import com.lavanderia.facade.RoupasFacade;
import com.lavanderia.model.beans.Roupa;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.ParseException;
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

        String action = request.getParameter("action");
        int id;
        Roupa roupa = null;
        RequestDispatcher rd;
        
        if (action == null) {
            List<Roupa> lista = RoupasFacade.buscarRoupas();
            request.setAttribute("roupas", lista);
            rd = getServletContext().getRequestDispatcher("/ManutencaoRoupas.jsp");
            rd.forward(request, response);
        }
        
        switch (action) {
            case "alterarform":
                try {
                    String idS = request.getParameter("id");
                    id = Integer.parseInt(idS);
                    roupa = RoupasFacade.buscarRoupa(id);
                    request.setAttribute("roupa", roupa);
                    request.setAttribute("form", "alterar");
                    rd = getServletContext().getRequestDispatcher("/FormRoupa.jsp");
                    rd.forward(request, response);
                    break;
                }  catch (NumberFormatException | IOException e) {
                    throw new RuntimeException(e);
                } catch (BuscarRoupaException e) {
                    request.setAttribute("mensagem", "ERRO: " + e.getMessage());
                    rd = request.getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
            case "alterar":
                try {
                    roupa = new Roupa();
                    roupa.setId(Integer.parseInt(request.getParameter("roupa-id")));
                    roupa.setNome(request.getParameter("roupa-nome"));
                    roupa.setPrazoLavagem(Integer.parseInt(request.getParameter("roupa-prazo")));
                    roupa.setCustoLavagem(Double.parseDouble(request.getParameter("roupa-preco")));
                    RoupasFacade.alterarRoupa(roupa);
                    response.sendRedirect("RoupasServlet");
                    break;
                } catch (NumberFormatException | IOException e) {
                    throw new RuntimeException(e);
                } catch (EditarRoupaException e) {
                    request.setAttribute("mensagem", "ERRO: " + e.getMessage());
                    rd = request.getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response); 
                }
            case "remover":
                try {
                    roupa = new Roupa();
                    roupa.setId(Integer.parseInt(request.getParameter("id")));
                    RoupasFacade.removerRoupa(roupa.getId());
                    response.sendRedirect("RoupasServlet");
                    break;
                } catch (NumberFormatException | IOException e) {
                    throw new RuntimeException(e);
                } catch (RemoverRoupaException e) {
                    request.setAttribute("mensagem", "ERRO: " + e.getMessage());
                    rd = request.getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response); 
                }
            case "incluirform":
                rd = getServletContext().getRequestDispatcher("/FormRoupa.jsp");
                rd.forward(request, response);
                break;
            case "incluir":
                try {
                    roupa = new Roupa();
                    roupa.setNome(request.getParameter("roupa-nome"));
                    roupa.setPrazoLavagem(Integer.parseInt(request.getParameter("roupa-prazo")));
                    roupa.setCustoLavagem(Double.parseDouble(request.getParameter("roupa-preco").replace(',', '.')));
                    RoupasFacade.inserirRoupa(roupa);
                    response.sendRedirect("RoupasServlet");
                    break;
                } catch (IOException | NumberFormatException e) {
                    throw new RuntimeException(e);
                } catch (InserirRoupaException e) {
                    request.setAttribute("mensagem", "ERRO: " + e.getMessage());
                    rd = request.getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response); 
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
