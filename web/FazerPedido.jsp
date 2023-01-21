<%-- 
    Document   : PaginaPedido
    Created on : 2 de nov. de 2022, 19:15:26
    Author     : goldb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
        <title>Fazer Pedido - FastClean</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.png">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <h1 class="text-light text-center">Novo Pedido</h1>
            <form action="#" method="post">
                <h3 class="text-light mt-3">Roupas</h3>
                <table class="table table-light">
                    <thead class="table-dark">
                        <tr>
                            <th>Selecionar</th>
                            <th class="w-50">Nome</th>
                            <th class="w-25">Valor Unidade</th>
                            <th class="w-25">Prazo</th>
                            <th>Quantidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Camisa/Camiseta</td>
                            <td class="field-valor">R$5.00</td>
                            <td class="field-prazo">5 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Calça</td>
                            <td class="field-valor">R$6.00</td>
                            <td class="field-prazo">7 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Casaco</td>
                            <td class="field-valor">R$7.00</td>
                            <td class="field-prazo">6 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                    </tbody>
                </table>
                <h3 class="text-light mt-3">Cama,Mesa e Banho</h3>
                <table class="table table-light">
                    <thead class="table-dark">
                        <tr>
                            <th>Selecionar</th>
                            <th class="w-50">Nome</th>
                            <th class="w-25">Valor Unidade</th>
                            <th class="w-25">Prazo</th>
                            <th>Quantidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Edredom/Cobertor</td>
                            <td class="field-valor">R$5.00</td>
                            <td class="field-prazo">5 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Travesseiro</td>
                            <td class="field-valor">R$6.00</td>
                            <td class="field-prazo">7 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Toalha</td>
                            <td class="field-valor">R$7.00</td>
                            <td class="field-prazo">6 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                        <tr class="row-tbody">
                            <td class="field-check text-center"><input type="checkbox" class="checkSel" name="checkSeleciona"></td>
                            <td class="field-nome">Lençol</td>
                            <td class="field-valor">R$7.00</td>
                            <td class="field-prazo">6 dias úteis</td>
                            <td class="field-qtde"><input class="inpQtde" type="number" min="1" max="10" name="quantidade" disabled></td>
                        </tr>
                    </tbody>
                </table>
                <button class="btn btn-success btnOrcamento">Solicitar Orçamento</button>
                <div class="orcamento hidden text-light mt-2 fw-bolder">
                    <p class="fs-3">Valor total do pedido: R$<spam class="orcamentoValor">####</spam>.00</p>
                    <p class="fs-3">Prazo de lavagem: <spam class="orcamentoPrazo">#</spam> dias úteis</p>
                    <div class="d-flex gap-2">
                        <button class="btn btn-success">Aceitar</button>
                        <a class="btn btn-danger" href="FazerPedido.jsp">Recusar</a>
                    </div>
                </div>
                
            </form>
        </div>
        <script src="./js/pedido.js"></script>
    </body>
</html>
