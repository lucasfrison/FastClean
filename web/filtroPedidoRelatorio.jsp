<%-- 
    Document   : filtroPedidoRelatorio
    Created on : 12 de fev. de 2023, 16:13:00
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">
        <title>Filtragem de Relatório</title>
        <script src="js/modalRelatorio.js"></script>
        <link href="css/modalRelatorio.css" type="text/css" rel="stylesheet">
        
    </head>
    <body>
        <form action="GeradorRelatorioServlet?action=relatorioPedidos" method="POST">
            <div id="myModal" class="container text-center container-border">  
                <div class="logo container text-center">
                    <img src="assets/maquinaFeliz.png" alt="alt"/></img>
                </div>
                <legend>Período para listagem de pedidos</legend>
                <div class="row">
                    <label for="dtpDataNascimento">Data Inicial</label>                     
                    <input type="date" 
                           value="${func.dataNascimento}" name="dataInicial" id="dtpDataNascimento" class="form-control">                      
                </div>
                <div class="row">
                    <label for="dtpDataNascimento">Data Final</label>                     
                    <input type="date" 
                        value="${func.dataNascimento}" name="dataFinal" id="dtpDataNascimento" class="form-control">                      
                </div>        
                <div class="botao row">
                    <button type="submit" class="btn btn-primary" href="">Enviar</button>
                </div>
            </div>
        </form>
    </body>
</html>
