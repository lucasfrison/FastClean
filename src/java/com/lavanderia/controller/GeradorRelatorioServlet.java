/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavanderia.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.lavanderia.exceptions.DAOException;
import com.lavanderia.model.dao.relConnectionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "GeradorRelatorioServlet", urlPatterns = { "/GeradorRelatorioServlet" })
public class GeradorRelatorioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     * @throws com.lavanderia.model.dao.DAOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, com.lavanderia.model.dao.DAOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        try (relConnectionFactory factory = new relConnectionFactory()) {
            String host = "http://" + request.getServerName() + ":" + request.getServerPort();
            if (action.equals("relatorioClientes")) {
                String relatorioClientes = request.getContextPath() + "/ListaClientes.jasper";
                URL relatorioClientesURL = new URL(host + relatorioClientes);
                HashMap params = new HashMap();
                byte[] bytes = JasperRunManager.runReportToPdf(relatorioClientesURL.openStream(), params,
                        factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else if (action.equals("relatorioClientesFieis")) {
                String relatorioClientesFieis = request.getContextPath() + "/ListaClientesFieis.jasper";
                URL relatorioClientesFieisURL = new URL(host + relatorioClientesFieis);
                HashMap params = new HashMap();
                byte[] bytes = JasperRunManager.runReportToPdf(relatorioClientesFieisURL.openStream(), params,
                        factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else if (action.equals("relatorioReceitas")) {
                String relatorioReceitas = request.getContextPath() + "/ListaReceitas.jasper";
                URL relatorioReceitasURL = new URL(host + relatorioReceitas);
                HashMap params = new HashMap();
                byte[] bytes = JasperRunManager.runReportToPdf(relatorioReceitasURL.openStream(), params,
                        factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else if (action.equals("relatorioPedidos")) {
                String relatorioReclamacoes = request.getContextPath() + "/Pedidos.jasper";
                URL relatorioPedidosURL = new URL(host + relatorioReclamacoes);
                HashMap params = new HashMap();
                byte[] bytes = JasperRunManager.runReportToPdf(relatorioPedidosURL.openStream(), params,
                        factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            }
        } catch (JRException e) {
            request.setAttribute("msg", "Erro no Jasper: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (com.lavanderia.model.dao.DAOException ex) {
            Logger.getLogger(GeradorRelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (com.lavanderia.model.dao.DAOException ex) {
            Logger.getLogger(GeradorRelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
