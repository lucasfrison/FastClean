

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage = "true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Erro</title>
        <jsp:include page="sources.jsp" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    </head>
    <body class="text-white">
        <c:if test="${!empty msg}">
            <div class="container">
                <h2>${msg}</h2>
            </div>
        </c:if>
        <div class="container">
            <div class="container">
                <p><strong style="color: red;">${pageContext.exception.message}</strong></p>
            </div>
            <div class="container">
                <p>${pageContext.out.flush()}</p>
                <p>${pageContext.exception.printStackTrace(pageContext.response.writer)}</p>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                    <input type="submit" value="Sair" class="btn btn-primary active"/>
                </form>
            </div>
        </div>
        <div class="footer">
            Em caso de problemas contactar o administrador:
            <a href="mailto:${configuracao.email}">
                <c:out value="${configuracao.email}" /> </a>
        </div><br>
    </body>
</html>
