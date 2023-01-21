<%-- 
    Document   : ManutencaoFuncionario
    Created on : 28 de nov. de 2022, 15:20:19
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manutencão Funcionário</title>
<!--        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.png">
        <link href="css/manutencaoFuncionario.css" type="text/css" rel="stylesheet">
        <!--<link href="css/main.css" type="text/css" rel="stylesheet">-->
        <script src="js/manutencaoFuncionario.js"></script>
        <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
    </head>
        <jsp:include page="header.jsp" /> <%-- HEADER --%>
    <body>

        <div style="padding:30px;" class="container text-center container-border">
            <!--<img class="img" onclick="location.href='index.html'" src="assets/logo.png" alt="alt"/></img>-->
            <legend>Manutenção de Funcionários</legend>
            <form id="frmCadastro">
               <div class="form-row">
                   <div class="form-group col-md-12">
                       <label for="txtEmail">Email</label>
                       <input type="email" class="form-control" id="txtEmail" placeholder="fulano@email.com" required>
                   </div>
                   <div class="form-group col-md-12">
                       <label for="txtNome">Nome</label>
                       <input type="text" id="txtNome" class="form-control" placeholder="Digite seu nome" required>
                   </div>
                   <div class="form-group col-md-12">
                       <label for="txtSenha">Senha</label>
                       <input type="password" id="txtSenha" class="form-control" placeholder="******" required>
                   </div>
                   <div class="form-group col-md-12">
                       <label for="dtpDataNascimento">Data de Nascimento</label>                     
                       <input type="date" id="dtpDataNascimento" class="form-control" required >                      
                   </div>
                   <input type="submit" class="btn btn-primary" name="" id="btnCadastrarSalvar" value="Cadastrar"> 
               </div>               
           </form>
           <table>
                <h2 class="text-center">Lista de Funcionários</h2>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Nome</th>
                        <th>Data de Nascimento</th>
                        <th>Ultima Atualização</th>
                        <th></th>
                        <th></th>
                    </tr>
		</thead>
		<tbody id="tbodyResultados"></tbody>
		<tfoot></tfoot>
            </table>
       </div>     

    </body>
</html>
