<%-- 
    Document   : ManutencaoRoupas
    Created on : 28 de nov. de 2022, 19:04:36
    Author     : goldb
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${empty sessionScope.logado}" >
            <c:set var="mensagem" value="Precisa fazer o login" scope="request"/>
            <c:redirect url="/Login.jsp"/>
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="sources.jsp" />
        <link href="css/manRoupas.css" type="text/css" rel="stylesheet">
        <title>Manutenção Roupas - FastClean</title>
        <script defer src="js/roupas.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <h1 style="text-transform: uppercase;" class="text-center text-light">Roupas Cadastradas</h1>
            <div class="bg-nova-peca p-3 d-flex gap-3 justify-content-between mt-5">
                <a href="<c:url value="RoupasServlet?action=incluirform"/>" role="button" class="btn nova-peca">Nova Peça</a>
                <div>
                    <div class="input-group">
                        <input 
                            name="pedido" type="text" id="search" class="form-control"
                            placeholder="Pesquisar roupa...">
                        <button class="btn btn-pesquisar" type="submit" >Pesquisar</button>
                    </div>
                </div>
            </div>
            <table class="mt-4">
                <thead>
                    <tr>
                        <th class="w-25">Nome</th>
                        <th>Prazo de Lavagem</th>
                        <th>Valor por unidade</th>
                        <th class="text-center w-25">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="roupa" items="${roupas}">
                        <tr class="row-tbody">
                            <td class="field-nome"><c:out value="${roupa.nome}"/></td>
                            <td class="field-prazo"><c:out value="${roupa.prazoLavagem}"/> dias úteis</td>
                            <td class="field-valor"><fmt:formatNumber value="${roupa.custoLavagem}" type="currency" /></td>
                            <td class="text-center field-acao">
                                <a style="width: 8rem;" class="btn nova-peca acao-editar" href="<c:url value="RoupasServlet?action=alterarform&id=${roupa.id}"/>">Editar</a>
                                <a style="width: 8rem;" class="btn btn-danger acao-excluir" href="<c:url value="RoupasServlet?action=remover&id=${roupa.id}"/>">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
