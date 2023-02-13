<%-- 
    Document   : dashboard
    Created on : 6 de nov. de 2022, 19:56:47
    Author     : PC_Perussi
--%>


<%@page import="com.lavanderia.model.beans.EstadoPedido" %>
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
        <title>Dashbaord - FastClean</title>
        <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script src="https://unpkg.com/feather-icons"></script>
        <script defer src="js/dashboard.js"></script>
        <link href="css/dashboard.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> <%-- HEADER --%>
        <main class="container text-light mt-5">
            <div class="row">
                <div class="col-xs-6 col-sm-5 col-md-8 col-lg-8">
                    <h1>Lista Pedidos</h1>
                </div>
                <c:if test="${!funcionario}">
                    <div class="col-xs-3 col-sm-3 col-md-2 col-lg-2" style="margin-left:auto;">
                        <button onclick="location.href = '\PedidoServlet'" id="buttonTop">Novo Pedido</button> 
                    </div>
                </c:if>
            </div>

            <table class="mt-5">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th class="w-25" scope="col">Cliente</th>
                        <th class="text-center" scope="col">Prazo</th>
                        <th class="text-center"scope="col">Valor Total</th>
                        <th class="text-center" scope="col">Quantidade de Peças</th>
                        <th scope="col">Status</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${pedidos}">
                        <tr>
                            <td><a id="link" href="PedidoServlet?action=view&id=${pedido.id}">${pedido.id}</a></td>
                            <td><c:out value="${pedido.cliente.nome}"/></td>
                            <td class="text-center"><fmt:formatDate value="${pedido.prazo}" dateStyle="short"/></td>
                            <td class="text-center"><fmt:formatNumber value="${pedido.valorTotal}" type="currency"/></td>
                            <td class="text-center"><c:out value="${fn:length(pedido.roupas)}"/></td>
                            <td class="situacao situacao-<c:out value="${fn:replace(pedido.situacao,'_','')}"/>"><c:out value="${fn:replace(pedido.situacao,'_',' ')}"/></td>
                            <c:choose>
                                <c:when test="${pedido.situacao == 'EM_ABERTO'}">
                                    <td><c:if test="${funcionario}"><a class="sit sit-aberto" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='loader'></i></span></a></c:if> 
                                    <c:if test="${!funcionario}"><a class="sit sit-abertocli" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='loader'></i></span></a></c:if></td>
                                            </c:when>
                                            <c:when test="${pedido.situacao == 'REJEITADO' || pedido.situacao == 'CANCELADO'}">
                                    <td><c:if test="${!funcionario}"><a class="sit sit-rejeitado" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='x-octagon'></i></span></a></c:if></td>  
                                            </c:when>
                                            <c:when test="${pedido.situacao == 'RECOLHIDO'}">
                                    <td><c:if test="${funcionario}"><a class="sit sit-recolhido" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='truck'></i></span></a></c:if></td>  
                                            </c:when>    
                                            <c:when test="${pedido.situacao == 'AGUARDANDO_PAGAMENTO'}">
                                    <td><c:if test="${!funcionario}"><a class="sit sit-aguardando" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='clock'></i></span></a></c:if></td>  
                                            </c:when>   
                                            <c:when test="${pedido.situacao == 'PAGO'}">
                                    <td><c:if test="${funcionario}"><a class="sit sit-pago" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='dollar-sign'></i></span></a></c:if></td>  
                                            </c:when> 
                                            <c:when test="${pedido.situacao == 'FINALIZADO'}">
                                    <td><c:if test="${!funcionario}"><a class="sit sit-finalizado" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='check'></i></span></a></c:if></td>  
                                            </c:when>     
                                        </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


            <!--               
            <script>
                  $('.delete').on('click', function() {
                      const code = $(this).attr("data-code");
                     const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                  confirmButton: 'btn btn-success',
                                  cancelButton: 'btn btn-danger'
                                },
                                buttonsStyling: false
                              })
            
                              swalWithBootstrapButtons.fire({
                                title: 'Marcar pedido como recolhido?',
                                text: "Somente avance se o pedido já  foi recolhido pelo entregador!",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonText: 'Confirmar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                              }).then((result) => {
                                if (result.isConfirmed) {
                                  swalWithBootstrapButtons.fire(
                                    'Pedido Recolhido!',
                                    'Seu pedido foi recolhido pelo entregador com sucesso!.',
                                    'success'
                                  )
                                } else if (
                                  /* Read more about handling dismissals below */
                                  result.dismiss === Swal.DismissReason.cancel
                                ) {
                                  swalWithBootstrapButtons.fire(
                                    'Procedimento abortado',
                                    'O seu pedido continua aberto.',
                                    'error'
                                  )
                                }
                              })
                  })
            
             $('.confirma').on('click', function() {
                      const code = $(this).attr("data-code");
                     const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                  confirmButton: 'btn btn-success',
                                  cancelButton: 'btn btn-danger'
                                },
                                buttonsStyling: false
                              })
            
                              swalWithBootstrapButtons.fire({
                                title: 'Marcar pedido como lavado?',
                                text: "Somente avance se o pedido já  foi entregue!",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonText: 'Confirmar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                              }).then((result) => {
                                if (result.isConfirmed) {
                                  swalWithBootstrapButtons.fire(
                                    'Pedido Entregue!',
                                    'Este pedido foi entregue com sucesso!.',
                                    'success'
                                  )
                                } else if (
                                  /* Read more about handling dismissals below */
                                  result.dismiss === Swal.DismissReason.cancel
                                ) {
                                  swalWithBootstrapButtons.fire(
                                    'Procedimento abortado',
                                    'O seu pedido continua aberto.',
                                    'error'
                                  )
                                }
                              })
                  })
                  
                   $('.pago').on('click', function() {
                      const code = $(this).attr("data-code");
                     const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                  confirmButton: 'btn btn-success',
                                  cancelButton: 'btn btn-danger'
                                },
                                buttonsStyling: false
                              })
            
                              swalWithBootstrapButtons.fire({
                                title: 'Finalizar Pedido?',
                                text: "Somente avance se o pedido já  foi pago!",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonText: 'Confirmar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                              }).then((result) => {
                                if (result.isConfirmed) {
                                  swalWithBootstrapButtons.fire(
                                    'Pedido Finalizado!',
                                    'Seu pedido foi finalizado!.',
                                    'success'
                                  )
                                } else if (
                                  /* Read more about handling dismissals below */
                                  result.dismiss === Swal.DismissReason.cancel
                                ) {
                                  swalWithBootstrapButtons.fire(
                                    'Procedimento abortado',
                                    'O seu pedido continua aberto.',
                                    'error'
                                  )
                                }
                              })
                  })
            
              </script>
            -->                  


        </main>
        <script>
            feather.replace()
        </script>
    </body>
</html>
