<%-- 
    Document   : Cadastro
    Created on : 28 de out. de 2022, 16:55:16
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
        <script src="js/cadastro.js"></script>
        <link href="css/main.css" type="text/css" rel="stylesheet">
        <title>Cadastro</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.png">
    </head>
    <body>
        <div class="container text-center container-border">
            <img onclick="location.href='index.html'" src="assets/logo.png" alt="alt"/></img>
            <legend>Cadastrar</legend>
            <form action="Login.jsp" method="POST">
               <div class="form-row">
                   <div class="form-group col-md-3">
                       <label for="inputCpf">CPF</label>
                       <input type="text" class="form-control" id="inputCpf" placeholder="CPF" required>
                   </div>
                   <div class="form-group col-md-6">
                        <label for="inputNome">Nome</label>
                       <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input type="text" id="inputNome" class="form-control" placeholder="Digite seu nome" required>
                       </div>
                   </div>
                   <div class="form-group col-md-3">
                       <label for="inputTelefone">Telefone</label>
                       <div class="input-group">
                           <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                           <input type="text" class="form-control" id="inputTelefone" placeholder="XX XXXXX-XXXX" maxlength="13" >
                       </div>
                   </div>
                   <div class="form-group col-md-4">
                       <label for="inputEmail">Email</label>
                       <div class="input-group">
                           <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                           <input type="email" class="form-control" id="inputEmail" placeholder="fulano@email.com" >
                       </div>
                   </div>
                    <div class="form-group col-md-4">
                       <label for="inputEmail">CEP</label>
                       <div class="input-group">
                           <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
                           <input type="text" class="form-control" id="cep" value="" size="10" maxlength="9" placeholder="00000-000" onblur="pesquisacep(this.value);">
                       </div>
                   </div>
               </div>
               <div class="form-group col-md-4">
                    <label for="inputAddress">Rua</label>
                   <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
                        <input type="text" class="form-control" id="rua" placeholder="Av Vinicius de Morais, 25">
                    </div>
               </div>
               <div class="form-group col-md-4">
                   <label for="inputAddress2">Bairro</label>
                   <input type="text" class="form-control" id="bairro" placeholder="Bairro Feliz">
               </div>
               <div class="form-group">
                   <div class="form-group col-md-2">
                        <label for="inputEstado">Número</label>
                        <input class="form-control" name="numero" type="text" id="numero" size="2" /></label><br />
                   </div>
                   <div class="form-group col-md-4">
                       <label for="inputCidade">Cidade</label>
                       <input type="text" class="form-control" id="cidade" placeholder="Cidade da Alegria">        
                   </div>
                    <div class="form-group col-md-2">
                        <label for="inputEstado">Estado</label>
                        <input class="form-control" name="uf" type="text" id="uf" size="2" /></label><br />
                   </div>
               </div>
               <div>
                   <button type="submit" class="btn btn-primary">Enviar</button>
               </div>
           </form>
       </div>     
    </body>
</html>
