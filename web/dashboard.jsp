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
                <div class="col-xs-3 col-sm-3 col-md-2 col-lg-2">
                   <button onclick="location.href='\PesquisarPedidosFunc.jsp'" id="buttonTop">Pesquisar/Filtrar</button> 
                </div>
                <div class="col-xs-3 col-sm-3 col-md-2 col-lg-2">
                   <button onclick="location.href='\PedidoServlet'" id="buttonTop">Novo Pedido</button> 
                </div>
            </div>
            
            
            <table id="mainTable" table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Cliente</th>
                <th scope="col">Prazo</th>
                <th scope="col">Valor Total</th>
                <th scope="col">Quantidade de Peças</th>
                <th scope="col">Status</th>
                <th scope="col">Ação</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td><a id="link" href="PedidoServlet?action=view&id=${pedido.id}">${pedido.id}</a></td>
                        <td><c:out value="${pedido.cliente.nome}"/></td>
                        <td><fmt:formatDate value="${pedido.prazo}" dateStyle="short"/></td>
                        <td><fmt:formatNumber value="${pedido.valorTotal}" type="currency"/></td>
                        <td><c:out value="${fn:length(pedido.roupas)}"/></td>
                        <td class="situacao situacao-<c:out value="${fn:replace(pedido.situacao,'_','')}"/>"><c:out value="${fn:replace(pedido.situacao,'_',' ')}"/></td>
                        <c:choose>
                            <c:when test="${pedido.situacao == 'EM_ABERTO'}">
                                <td><a class="sit sit-aberto" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='loader'></i></span></a></td>  
                            </c:when>
                            <c:when test="${pedido.situacao == 'REJEITADO' || pedido.situacao == 'CANCELADO'}">
                                <td><a class="sit sit-rejeitado" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='x-octagon'></i></span></a></td>  
                            </c:when>
                            <c:when test="${pedido.situacao == 'RECOLHIDO'}">
                                <td><a class="sit sit-recolhido" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='truck'></i></span></a></td>  
                            </c:when>    
                            <c:when test="${pedido.situacao == 'AGUARDANDO_PAGAMENTO'}">
                                <td><a class="sit sit-aguardando" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='credit-card'></i></span></a></td>  
                            </c:when>   
                            <c:when test="${pedido.situacao == 'PAGO'}">
                                <td><a class="sit sit-pago" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='dollar-sign'></i></span></a></td>  
                            </c:when> 
                            <c:when test="${pedido.situacao == 'FINALIZADO'}">
                                <td><a class="sit sit-finalizado" data-code="${pedido.id}"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='check'></i></span></a></td>  
                            </c:when>     
                        </c:choose>
                    </tr>
                </c:forEach>
              <!-- 
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0001</a></th>
                <td><a id="link" href="viewRequest.jsp">Lucas Perussi</a></td>
                <td>3</td>
                <td>Escuras</td>
                <td>Calça</td>
                <td><span style="color: orange; font-weight: bolder;">PAGO</span></td>
                <td><a class="pago" data-code="1"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='x-octagon'></i></span></a></td>              
              </tr>
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0002</a></th>
                <td><a id="link" href="viewRequest.jsp">Hiron</a></td>
                <td>5</td>
                <td>Mista</td>
                <td>Camisa</td>
                <td><span style="color: gray; font-weight: bolder;">RECOLHIDO</span></td>
                <td><a class="confirma" data-code="1"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='loader'></i></span></a></td>              
              </tr>
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0003</a></th>
                <td><a id="link" href="viewRequest.jsp">Lucas Gonçalves</a></td>
                <td>10</td>
                <td>Claras</td>
                <td>Outros</td>
                <td><span style="color: blue; font-weight: bolder;">AGUARDANDO PAGAMENTO </span></td>
                <td></td>              
              </tr>
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0004</a></th>
                <td><a id="link" href="viewRequest.jsp">James Rovel</a></td>
                <td>7</td>
                <td>Mista</td>
                <td>Calça</td>
                <td><span style="color: red; font-weight: bolder;">CANCELADO</span></td>                
                <td></td>              
              </tr >
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0005</a></th>
                <td><a id="link" href="viewRequest.jsp">Steve Jobs</a></td>
                <td>2</td>
                <td>Mista</td>
                <td>Calça</td>
                <td><span style="color: #FFD700; font-weight: bolder;">ABERTO</span></td>                
                <td><a class="delete" data-code="1"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='layers'></i></span></a></td>              
              </tr >
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0006</a></th>
                <td><a id="link" href="viewRequest.jsp">Bill Gates</a></td>
                <td>3</td>
                <td>Mista</td>
                <td>Calça</td>
                <td><span style="color: green; font-weight: bolder;">FINALIZADO</span></td>                
                <td></td>              
              </tr >
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0007</a></th>
                <td><a id="link" href="viewRequest.jsp">Harry Potter</a></td>
                <td>6</td>
                <td>Mista</td>
                <td>Calça</td>
                <td><span style="color: #FFD700; font-weight: bolder;">ABERTO</span></td>                
                <td><a class="delete" data-code="1"><span style="color:mediumorchid; margin-right: 10px;"><i data-feather='layers'></i></span></a></td>              
              </tr >
              <tr>
                <th scope="row"><a id="link" href="viewRequest.jsp">0008</a></th>
                <td><a id="link" href="viewRequest.jsp">Michael Jackson</a></td>
                <td>7</td>
                <td>Mista</td>
                <td>Calça</td>
                <td><span style="color: red; font-weight: bolder;">REJEITADO </span></td>                
                <td></td>              
              </tr >
             -->
             
            </tbody>
          </table>
       
                
                
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
                
     
         
        </main>
    <script>
      feather.replace()
    </script>
<!--        <script src="./js/pedido.js"></script>-->
    </body>
</html>
