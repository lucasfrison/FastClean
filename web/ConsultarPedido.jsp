<%-- 
    Document   : VisualizarPedido.jsp
    Created on : 5 de nov. de 2022, 17:11:11
    Author     : lucfg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
         <link rel="stylesheet" href="css/dashboard.css"/>
        <title>Consultar Pedido</title>
    </head>
    <body>
        <jsp:include page="header.jsp" /> <%-- HEADER --%>   
        <div class="container text-light mt-5">
            <form method="post" action="PedidoServlet?action=view">
            <div class="form-group smallTopGap">
                <label for="pedido"><h3>Pesquisar pedido</h3></label>
                <div style="width: 50%" class="input-group">
                    <input 
                        name="id" type="text" id="search" class="form-control"
                        placeholder="Pesquisar pedido...">
                    <button class="btn btn-secondary" type="submit" >Pesquisar</button>
                </div>
            </form>
        </div>    
    </body>
</html>
