/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lavanderia.controller;

import com.lavanderia.exceptions.BuscarRoupaException;
import com.lavanderia.exceptions.InserirPedidoException;
import com.lavanderia.facade.PedidoFacade;
import com.lavanderia.facade.RoupasFacade;
import com.lavanderia.model.beans.EstadoPedido;
import com.lavanderia.model.beans.Pedido;
import com.lavanderia.model.beans.Roupa;
import com.lavanderia.model.beans.Cliente;
import com.lavanderia.model.dao.DAOException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import static java.text.DateFormat.SHORT;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author goldb
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

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
        boolean isFuncionario = false;

        if (action == null) {
            List<Roupa> lista = RoupasFacade.buscarRoupas();
            request.setAttribute("roupas", lista);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/FazerPedido.jsp");
            rd.forward(request, response);
        } else if (action.equals("new") || action.equals("canceal")) {
            try {
                String[] ids = request.getParameterValues("checkSeleciona");
                String[] qtdes = request.getParameterValues("quantidade");
                double valorTotal = 0;
                int roupaPrazo = 0;
                List<Roupa> roupas = new ArrayList();
                for (int i = 0; i < ids.length; i++) {
                    Roupa roupa = RoupasFacade.buscarRoupa(Integer.parseInt(ids[i]));
                    if (roupa.getPrazoLavagem() > roupaPrazo) {
                        roupaPrazo = roupa.getPrazoLavagem();
                    }
                    valorTotal += roupa.getCustoLavagem() * Integer.parseInt(qtdes[i]);
                    roupas.add(roupa);
                }

                Date date = new Date();
                Locale loc = new Locale("pt", "BR");
                DateFormat df = DateFormat.getDateInstance(SHORT, loc);
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(f.parse(df.format(date)));
                    c.add(Calendar.DATE, roupaPrazo);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                date = c.getTime();

                EstadoPedido estado = null;
                if (action.equals("new")) {
                    estado = EstadoPedido.EM_ABERTO;
                } else if (action.equals("canceal")) {
                    estado = EstadoPedido.REJEITADO;
                }

                Pedido pedido = new Pedido();
                pedido.setPrazo(date);
                pedido.setValorTotal(valorTotal);
                pedido.setRoupas(roupas);
                pedido.setSituacao(estado);
                // INSERIR Cliente da session
                Cliente cliente = new Cliente();
                cliente.setId(1);
                pedido.setCliente(cliente);
                PedidoFacade.inserirPedido(pedido);

                // Redireciona pagina de pedidos, editar...
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/PesquisarPedidos.jsp");
                rd.forward(request, response);
            } catch (BuscarRoupaException | InserirPedidoException | RuntimeException e) {
                request.setAttribute("mensagem", "ERRO: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        } else if (action.equals("list") && isFuncionario) {
            List<Pedido> pedidos = PedidoFacade.buscarPedidos();
            request.setAttribute("pedidos", pedidos);
            request.setAttribute("funcionario", isFuncionario);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
            rd.forward(request, response);
        } else if (action.equals("list") && !isFuncionario) {
            int idCliente = 1;
            List<Pedido> pedidos = PedidoFacade.buscarPedidosEmAberto(idCliente);
            request.setAttribute("pedidos", pedidos);
            request.setAttribute("funcionario", isFuncionario);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
            rd.forward(request, response);
        } else if (action.equals("view")) {
            int idPedido = Integer.parseInt(request.getParameter("id"));
            Pedido pedido = PedidoFacade.buscarPedido(idPedido);
            request.setAttribute("pedido", pedido);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewRequest.jsp");
            rd.forward(request, response);
        } else if (action.equals("change")) {
            int idPedido = Integer.parseInt(request.getParameter("id"));
            Pedido pedido = PedidoFacade.buscarPedido(idPedido);
            String situacao = request.getParameter("sit");
            PedidoFacade.alterarEstadoPedido(pedido, situacao);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/PedidoServlet?action=list");
            rd.forward(request, response);
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
