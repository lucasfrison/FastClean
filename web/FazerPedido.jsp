<%-- 
    Document   : PaginaPedido
    Created on : 2 de nov. de 2022, 19:15:26
    Author     : goldb
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
        <title>Fazer Pedido - FastClean</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.png">
        <link rel="stylesheet" href="css/fazerPedido.css" type="text/css">
        <script defer src="./js/pedido.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <h1 class="text-light text-center">Novo Pedido</h1>
            <form action="PedidoServlet?action=new" method="post">
                <h3 class="text-light mt-5">Roupas</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Selecionar</th>
                            <th class="w-50">Nome</th>
                            <th class="w-25">Valor Unidade</th>
                            <th class="w-25">Prazo</th>
                            <th>Quantidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="roupa" items="${roupas}">
                            <tr class="row-tbody">
                                <td class="field-check text-center"><input value="${roupa.id}" type="checkbox" class="checkSel" name="checkSeleciona"></td>
                                <td class="field-nome"><c:out value="${roupa.nome}"></c:out></td>
                                <td class="field-valor"><fmt:formatNumber value="${roupa.custoLavagem}" type="currency"/></td>
                                <td class="field-prazo"><c:out value="${roupa.prazoLavagem}"></c:out> dias úteis</td>
                                <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button class="btn btnOrcamento mt-3">Solicitar Orçamento</button>
                <div class="orcamento d-none text-light mt-2 fw-bolder">
                    <div class="orcamento-content">
                        <p class="fs-3">Valor total do pedido: R$<spam class="orcamentoValor">####</spam></p>
                        <p class="fs-3">Prazo de lavagem: <spam class="orcamentoPrazo">#</spam> dias úteis</p>
                        <div class="d-flex gap-2">
                            <button class="btn btn-success">Aceitar</button>
                            <button class="btn-cancelar btn btn-danger">Recusar</button>
                        </div>
                    </div>
                </div>

            </form>
        </div>

    </body>
</html>
