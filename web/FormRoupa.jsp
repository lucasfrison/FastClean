<%-- 
    Document   : EditarRoupa
    Created on : 22 de jan. de 2023, 15:06:38
    Author     : goldb
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="sources.jsp" />
        <link href="css/manRoupas.css" type="text/css" rel="stylesheet">
        <title>Editar Roupa - FastClean</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container text-white">
            <c:choose>
                <c:when test="${form.equals('alterar')}">
                    <h1 class="text-center">Editar <c:out value="${roupa.nome}"/></h1>
                </c:when>
                <c:otherwise>
                    <h1 class="text-center">Nova Roupa</h1>
                </c:otherwise>
            </c:choose>
            <form action="<c:choose>
                <c:when test="${form.equals('alterar')}">
                    RoupasServlet?action=alterar
                </c:when>
                <c:otherwise>
                    RoupasServlet?action=incluir
                </c:otherwise>
                </c:choose>" method="POST" accept-charset="ISO-8859-1">
                <c:if test="${form.equals('alterar')}">
                    <div class="form-group d-none">
                        <labe for="roupa-id">ID</labe>
                        <input class="form-control" type="number" name="roupa-id" value="<c:out value="${roupa.id}"/>"></input>
                    </div>
                </c:if>
                <div class="form-group mt-5 <c:if test="${form.equals('alterar')}">d-none</c:if>">
                    <labe for="roupa-nome">Nome</labe>
                    <input class="form-control" type="text" name="roupa-nome" <c:if test="${form.equals('alterar')}">value="<c:out value="${roupa.nome}"/>"</c:if> required></input>
                </div>
                <div class="form-group mt-2 <c:if test="${form.equals('alterar')}">mt-5</c:if>">
                    <labe for="roupa-prazo">Prazo</labe>
                    <input class="form-control" type="number" name="roupa-prazo" <c:if test="${form.equals('alterar')}">value="<c:out value="${roupa.prazoLavagem}"/>"</c:if> required></input>
                </div>
                <div class="form-group mt-2">
                    <labe for="roupa-preco">Preco</labe>
                    <input class="form-control" type="text" name="roupa-preco" <c:if test="${form.equals('alterar')}">value="<c:out value="${roupa.custoLavagem}"/>"</c:if> required></input>
                </div>
                <button class="btn btn-success mt-2" type="submit">Salvar</button>
                <a class="btn btn-danger mt-2" href="<c:url value="RoupasServlet"/>">Cancelar</a>
            </form>
        </div>
    </body>
</html>
