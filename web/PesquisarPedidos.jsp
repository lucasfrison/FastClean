                                                            <%-- 
    Document   : ConsultaPedido
    Created on : 5 de nov. de 2022, 15:43:21
    Author     : lucfg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${empty sessionScope.logado}" >
            <c:set var="mensagem" value="Precisa fazer o login" scope="request"/>
            <c:redirect url="/Login.jsp"/>
        </c:if>
        <title>Pesquisa de pedidos</title>
         <jsp:include page="sources.jsp" /> <%-- HEAD --%> 
         <script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
            crossorigin="anonymous"></script>
         <script src="js/search.js"></script>
         <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
         <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
         <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
         <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
         <script src="js/dateFilter.js"></script>
         <script src="js/datePicker.js"></script>
         <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
         <script defer src="https://unpkg.com/feather-icons"></script>
         <script defer src="js/dashboard.js"></script>
         <script defer src="js/datePicker.js"></script>
         <link rel="stylesheet" href="css/dashboard.css"/>
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
                    </div>
                </div>
               <c:if test="${not sessionScope.usuario.funcionario}">
                <div class="form-group smallTopGap">
                    <div class="form-check form-check-inline">
                        <input checked class="form-check-input" type="radio" name="radioState" id="radioTodos">
                        <label class="form-check-label" for="radioTodos">
                          Todos
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radioState" id="radioAberto">
                        <label class="form-check-label" for="radioAberto">
                          Em Aberto
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radioState" id="radioFinalizado">
                        <label class="form-check-label" for="radioFinalizado">
                          Finalizado
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radioState" id="radioCancelado">
                        <label class="form-check-label" for="radioCancelado">
                          Cancelado
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radioState" id="radioPago">
                        <label class="form-check-label" for="radioPago">
                          Pago
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radioState" id="radioAguardando">
                        <label class="form-check-label" for="radioAguardando">
                          Aguardando Pagamento
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radioState" id="radioRecolhido">
                        <label class="form-check-label" for="radioRecolhido">
                          Recolhido
                        </label>
                    </div>
                </div>
               </c:if>
               <c:if test="${sessionScope.usuario.funcionario}">
                   <div class="form-group smallTopGap">
                       <div>
                        <input class="form-check-input" type="checkbox" name="aa" id="checkHoje">
                        <label class="form-check-label" for="radioRecolhido">
                          Hoje
                        </label>
                        </div>
                        <label for="startDate">Data inicial:</label>
                        <input type="date" id="startDate" name="startDate">
                        <label for="endDate">Data final:</label>
                        <input type="date" id="endDate" name="endDate">
                        <button type="button" id="filter" class="btn btn-secondary">Filtrar</button>
                   </div> 
               </c:if>
            </form>
        </div>
        <div class="container text-light mt-5">
        
            <h4>Resultados:</h4>
            <table id="mainTable">
                <thead>
                    <tr>
                        <th>Número do Pedido</th>
                        <c:if test="${sessionScope.usuario.funcionario}"><th>Cliente</th></c:if>
                        <th>Valor Total</th>
                        <th>Prazo</th>
                        <th>Situação</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${pedidos}">
                        <tr>
                            <td><a id="link" href="PedidoServlet?action=view&id=${pedido.id}">${pedido.id}</a></td>
                            <c:if test="${sessionScope.usuario.funcionario}"><th style="color: grey">${pedido.cliente.nome}</th></c:if>
                            <td><fmt:formatNumber value="${pedido.valorTotal}" type="currency"/></td>
                            <td><input style="border: 0" type="date" value="${pedido.prazo}" dateStyle="short" disabled/></td>
                            <td class="situacao situacao-<c:out value="${fn:replace(pedido.situacao,'_','')}"/>"><c:out value="${fn:replace(pedido.situacao,'_',' ')}"/></td> 
                            
                         <c:choose>
                            <c:when test="${pedido.situacao == 'EM_ABERTO'}">
                                <td><c:if test="${funcionario}"><a class="sit sit-aberto" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;">Confirmar Recolhimento</i></span></a></c:if></td> 
                            </c:when>
                            <c:when test="${pedido.situacao == 'RECOLHIDO'}">
                                <td><c:if test="${funcionario}"><a class="sit sit-recolhido" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;">Confirmar Lavagem</i></span></a></c:if></td>  
                            </c:when>    
                            <c:when test="${pedido.situacao == 'AGUARDANDO_PAGAMENTO'}">
                                <td><c:if test="${!funcionario}"><a class="sit sit-aguardando" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;">Pagar pedido</span></a></c:if></td>  
                            </c:when>   
                            <c:when test="${pedido.situacao == 'PAGO'}">
                                <td><c:if test="${funcionario}"><a class="sit sit-pago" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;">Finalizar</i></span></a></c:if></td>  
                            </c:when>    
                          </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>            
    </body>
</html>
