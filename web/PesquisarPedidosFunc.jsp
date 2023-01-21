                                                            <%-- 
    Document   : ConsultaPedido
    Created on : 5 de nov. de 2022, 15:43:21
    Author     : lucfg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pesquisa de pedidos</title>
        <jsp:include page="sources.jsp" /> <%-- HEAD --%> 
        <script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
            crossorigin="anonymous"></script>
        <script src="js/search.js"></script>  
        <script src="js/statusPedidoColor.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <script src="js/dateFilter.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
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
                        <button class="btn btn-secondary" type="submit" >Pesquisar</button>
                    </div>
                </div>
                <div class="form-group smallTopGap">
                    <input type='text' name='dataFiltro' value='Período...'>
                </div>
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
                          Aberto
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
            </form>
        </div>
        <div class="container text-light mt-5">
        
            <h4>Resultados:</h4>
            <table id="mainTable" class="table table-hover">
                <thead>
                    <tr>
                        <th>Número do Pedido</th>
                        <th>Nome do Cliente</th>
                        <th>Valor Total</th>
                        <th>Data do Pedido</th>
                        <th>Prazo</th>
                        <th>Situação</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><a href="ConsultarPedidoFunc.jsp">0001</a></td>
                        <td>Joao da Silva</td>
                        <td>R$ 250,00</td>
                        <td>10/12/2022</td>
                        <td>-</td>
                        <td>Aberto</td>
                    </tr>
                    <tr>
                        <td><a href="ConsultarPedidoFunc.jsp">0002</a></td>
                        <td>Maria</td>
                        <td>R$ 250,00</td>
                        <td>01/12/2022</td>
                        <td>01/12/2022</td>
                        <td>Finalizado</td>
                    </tr>
                    <tr>
                        <td><a href="ConsultarPedidoFunc.jsp">0003</a></td>
                        <td>Lucas</td>
                        <td>R$ 250,00</td>
                        <td>01/12/2022</td>
                        <td>01/12/2022</td>
                        <td>Aguardando Pagamento</td>
                    </tr>
                    <tr>
                        <td><a href="ConsultarPedidoFunc.jsp">0004</a></td>
                        <td>James</td>
                        <td>R$ 125,00</td>
                        <td>01/12/2022</td>
                        <td>01/12/2022</td>
                        <td>Cancelado</td>
                    </tr>
                    <tr>
                        <td><a href="ConsultarPedidoFunc.jsp">0005</a></td>
                        <td>Jõao da Silva</td>
                        <td>R$ 250,00</td>
                        <td>10/12/2022</td>
                        <td>25/12/2022</td>
                        <td>Recolhido</td>
                    </tr>
                </tbody>
            </table>
        </div>            
    </body>
</html>
