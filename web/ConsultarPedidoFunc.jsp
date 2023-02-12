<%-- 
    Document   : VisualizarPedido.jsp
    Created on : 5 de nov. de 2022, 17:11:11
    Author     : lucfg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${empty sessionScope.logado}" >
            <c:set var="mensagem" value="Precisa fazer o login" scope="request"/>
            <c:redirect url="/Login.jsp"/>
        </c:if>
        
         <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
         <link rel="stylesheet" href="css/dashboard.css"/>
        <title>Consultar Pedido</title>
    </head>
    <body>
        <jsp:include page="header.jsp" /> <%-- HEADER --%>
        <div class="container text-light mt-5">
            <form method="post">
            <div class="form-group smallTopGap">
                <label for="pedido"><h3>Pesquisar pedido</h3></label>
                <div style="width: 50%" class="input-group">
                    <input 
                        name="pedido" type="text" id="search" class="form-control"
                        placeholder="Pesquisar pedido...">
                    <button class="btn btn-secondary" type="submit" >Pesquisar</button>
                </div>
            </form>
            <h2>Consulta: Pedido 00001</h2>
            <h3>Cliente: João da Silva</h3>
            <table id="mainTable" class="table">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th>Valor</th>
                        <th>Quantidade</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Calça</td>
                        <td>R$ 6,00</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>Camisa</td>
                        <td>R$ 5,00</td>
                        <td>2</td>
                    </tr>
                </tbody>
            </table>
            <table style="background-color: white" class="table">
                <thead>
                    <tr>
                        <th>Total de itens</th>
                        <td>Data do Pedido</td>
                        <th>Prazo</th>
                        <th>Situação</th>
                        <th>Valor Total</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>3</td>
                        <td>20/11/2022 15:45</td>
                        <td>01/12/2022</td>
                        <td>Aberto</td>
                        <td>R$ 16,00</td>
                    </tr>
                </tbody>
            </table>  
            <div class="mediumTopGap">
                <a href="PesquisarPedidosFunc.jsp">
                    <button onclick='confirm("Tem ceteza?\nApós confirmar o recolhimento O pedido não poderá ser cancelado.")'
                            class="btn btn-success">
                        Confirmar Recolhimento
                    </button>  
                </a> 
                <a href="PesquisarPedidosFunc.jsp">
                    <button class="btn btn-success">
                        Confirmar Lavagem
                    </button>
                </a> 
                <a href="PesquisarPedidosFunc.jsp">
                    <button onclick='confirm("Tem ceteza que deseja encerrar o pedido?")'
                            class="btn btn-secondary">
                        Finalizar Pedido
                    </button>
                </a> 
                <a href="PesquisarPedidosFunc.jsp">
                    <button class="btn btn-secondary">
                        Voltar
                    </button>
                </a>    
            </div>
        </div>    
    </body>
</html>
