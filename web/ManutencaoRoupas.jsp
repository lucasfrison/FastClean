<%-- 
    Document   : ManutencaoRoupas
    Created on : 28 de nov. de 2022, 19:04:36
    Author     : goldb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="sources.jsp" />
        <link href="css/manRoupas.css" type="text/css" rel="stylesheet">
        <title>Manutenção Roupas - FastClean</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <h1 style="text-transform: uppercase;" class="text-center text-light">Roupas Cadastradas</h1>
            <div class="bg-primary p-3 d-flex gap-3 justify-content-between mt-4">
                <button class="btn btn-success nova-peca">Nova Peça</button>
                <div>
                    <div class="input-group">
                        <input 
                            name="pedido" type="text" id="search" class="form-control"
                            placeholder="Pesquisar roupa...">
                        <button class="btn btn-warning" type="submit" >Pesquisar</button>
                    </div>
                </div>
            </div>
            <table class="table table-hover bg-light mt-4">
                <thead class="table-dark">
                    <tr>
                        <th class="w-25">Nome</th>
                        <th>Prazo de Lavagem</th>
                        <th>Valor por unidade</th>
                        <th class="text-center">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="row-tbody">
                        <td class="field-nome">Calça Jeans</td>
                        <td class="field-prazo">5-6 dias úteis</td>
                        <td class="field-valor">R$4.00</td>
                        <td class="text-center field-acao">
                            <button class="btn btn-info w-25 acao-editar">Editar</button>
                            <button class="btn btn-danger w-25 acao-excluir">Excluir</button>
                        </td>
                    </tr>
                    <tr class="row-tbody">
                        <td class="field-nome">Camiseta</td>
                        <td class="field-prazo">4-5 dias úteis</td>
                        <td class="field-valor">R$3.00</td>
                        <td class="text-center field-acao">
                            <button class="btn btn-info w-25 acao-editar">Editar</button>
                            <button class="btn btn-danger w-25 acao-excluir">Excluir</button>
                        </td>
                    </tr>
                    <tr class="row-tbody">
                        <td class="field-nome">Jaqueta</td>
                        <td class="field-prazo">5-6 dias úteis</td>
                        <td class="field-valor">R$5.00</td>
                        <td class="text-center field-acao">
                            <button class="btn btn-info w-25 acao-editar">Editar</button>
                            <button class="btn btn-danger w-25 acao-excluir">Excluir</button>
                        </td>
                    </tr>
                    <tr class="row-tbody">
                        <td class="field-nome">Travesseiro</td>
                        <td class="field-prazo">6-7 dias úteis</td>
                        <td class="field-valor">R$8.00</td>
                        <td class="text-center field-acao">
                            <button class="btn btn-info w-25 acao-editar">Editar</button>
                            <button class="btn btn-danger w-25 acao-excluir">Excluir</button>
                        </td>
                    </tr>
                    <tr class="row-tbody"> 
                        <td class="field-nome">Toalha</td>
                        <td class="field-prazo">3-4 dias úteis</td>
                        <td class="field-valor">R$4.00</td>
                        <td class="text-center field-acao">
                            <button class="btn btn-info w-25 acao-editar">Editar</button>
                            <button class="btn btn-danger w-25 acao-excluir">Excluir</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="modal-editar hidden">
                <div class="modal-content">
                    <div class="modal-title">
                        <h2 class="peca-nome"></h2>
                        <button class="btn btn-danger close-modal">X</button>
                    </div>
                    <form action="#" method="post">
                        <div class="form-group">
                            <label for="peca-prazo">Prazo de lavagem</label>
                            <input class="form-control" type="number" name="peca-prazo">
                        </div>
                        <div class="form-group">
                            <label for="peca-valor">Preço por unidade</label>
                            <input class="form-control" type="number" min="1" step="any" name="peca-valor">
                        </div>
                        <button class="btn btn-success mt-2" type="submit">Salvar</button>
                    </form>
                </div>
            </div>
            <div class="modal-nova-peca hidden">
                <div class="modal-content-nova">
                    <div class="modal-title">
                        <h2>Nova Peça</h2>
                        <button class="btn btn-danger close-modal-nova">X</button>
                    </div>
                    <form action="#" method="post" class="mt-3">
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input class="form-control form-nome" type="text" name="nome" required>
                        </div>
                        <div class="form-group">
                            <label for="prazo">Prazo</label>
                            <input class="form-control form-prazo" type="number" min="1" name="prazo" required>
                        </div>
                        <div class="form-group">
                            <label for="valor">Valor por Unidade</label>
                            <input class="form-control form-valor" type="number" min="1" step="any" name="valor" required>
                        </div>
                        <button class="btn btn-success mt-3 nova-salvar" type="submit">Salvar</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="./js/manRoupas.js"></script>
    </body>
</html>
