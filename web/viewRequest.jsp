<%-- 
    Document   : viewRequest
    Created on : 6 de nov. de 2022, 20:48:34
    Author     : PC_Perussi
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${empty sessionScope.logado}" >
            <c:set var="mensagem" value="Precisa fazer o login" scope="request"/>
            <c:redirect url="/Login.jsp"/>
        </c:if>
        <title>Ver Pedido - FastClean</title>
        <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
        <link href="css/viewRequest.css" type="text/css" rel="stylesheet">

    </head>
    <body>
        <jsp:include page="header.jsp" /> <%-- HEADER --%>
        <main class="container text-light mt-5">
            <div class="row">
                <div class="col-xs-7 col-sm-7 col-md-10 col-lg-10">
                    <h1>Pedido < identificador ></h1>
                    <div class="breadcrumb-wrapper">
                        <ol class="breadcrumb">
                            <li id="bread" class="breadcrumb-item"><a id="bread" href="PedidoServlet?action=list">Painel Principal</a>
                            </li>
                            <li class="breadcrumb-item active" id="bread">volta
                            </li>
                        </ol>
                    </div>
                </div>
                <div class="col-xs-5 col-sm-5 col-md-2 col-lg-2">
                    <button onclick="location.href ='PedidoServlet'" id="buttonTop">Novo Pedido</button> 
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
                    <div class="card">
                        <img id="icon" src="https://cdn.pixabay.com/photo/2016/11/14/17/39/person-1824144_960_720.png" alt="icon">
                        <h3 id="name"><c:out value="${pedido.cliente.nome}"/></h3>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
                    <div class="card">
                        <h1 id="text">Detalhes do Pedido</h1>
                        <p id="text" style="margin-top:10px;">Data de abertura: 10/10/2022</p>
                        <br>
                        <h5 id="text">Roupas: <strong><c:forEach var="roupa" items="${pedido.roupas}">${roupa.nome} </c:forEach></strong></h5>
                        <br>
                        <h5 id="text">Data limite de entrega: <strong><fmt:formatDate value="${pedido.prazo}" dateStyle="short"/></strong></h5>
                        <br>
                        <h5 id="text">Status: <strong class="situacao situacao-${fn:replace(pedido.situacao,'_','')}"><c:out value="${fn:replace(pedido.situacao,'_',' ')}"/></strong></h5>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>