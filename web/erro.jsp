

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage = "true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Erro</title>
        <jsp:include page="sources.jsp" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">
        <link href="css/erro.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container text-center container-border">
        <img onclick="location.href='index.jsp'" src="assets/sadmachine.png" alt="alt"/></img>
            <c:if test="${!empty msg}">
                <div class="msg">
                    <h2>${msg}</h2>
                </div>
            </c:if>
            <p><strong style="color: red;">${pageContext.exception.message}</strong></p>
            <form action="<c:if test="${sessionScope.logado}">
                            ${pageContext.request.contextPath}/PedidoServlet?action=list
                          </c:if>
                          <c:if test="${not sessionScope.logado}">
                              ${pageContext.request.contextPath}/index.jsp
                          </c:if>"
                            method="post">
                <input type="submit" value="Sair" class="btn btn-primary active"/>
            </form>
            <div class="msg">
                Em caso de problemas contactar o administrador
            </div>
        </div>
    </body>
</html>
