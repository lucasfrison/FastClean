<%-- 
    Document   : Login
    Created on : 28 de out. de 2022, 15:50:29
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">
        <link href="css/main.css" type="text/css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="assets/favicon.png">
    </head>
    <body>
        <div class="container text-center" id="center">
            <img onclick="location.href='index.html'" src="assets/logo.png" alt="alt"/></img>
            <h1>Login</h1>
            <form action="LoginServlet" method="POST">
                <div class="form-group"> 
                    <label>Usuário</label>
                    <input name="usuario" type="text" class="form-control" id="inputUsuario" placeholder="Usuario" required>
                </div>
                <div class="form-group">
                    <label>Senha</label>
                    <input name="senha" type="password" class="form-control" id="inputSenha" placeholder="Senha" required>
                </div>
                <button type="submit" class="btn btn-default"> Login </button>    
            </form>
                <h4>Não tem cadastro?</h4>
                <button class="btn btn-default"><a href="Cadastro.jsp">Criar conta</a></button>
        </div> 
    </body>
</html>
