<%-- 
    Document   : index.jsp
    Created on : 12 de fev. de 2023, 19:03:47
    Author     : lucfg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="css/main.css" type="text/css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/index.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        
         <main class="container text-light mt-5">
            <div class="row">
                <div class="col-xs-7 col-sm-7 col-md-8 col-lg-8">
                    <img id="logo" src="assets/logo.png" alt="alt"/>
                </div>
                  <div class="col-xs-5 col-sm-5 col-md-2 col-lg-2">
                      <button id="buttonTop" onclick="location.href='Login.jsp'">Login</button>
                </div>
                 <div class="col-xs-5 col-sm-5 col-md-2 col-lg-2">
                      <button id="buttonTop" onclick="location.href='Cadastro.jsp'">Cadastre-se</button>
                </div>
            </div>
            <div class="row">
                    <div class="col-xs-7 col-sm-7 col-md-8 col-lg-8">
                        <div id="banner">
                            <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
                            <lottie-player src="https://assets1.lottiefiles.com/packages/lf20_gn0tojcq.json"  background="transparent"  speed="1"  style="width: 100%; height: max-400px;"  loop  autoplay></lottie-player>             
                        </div>
                    </div>
                    <div class="col-xs-5 col-sm-5 col-md-2 col-lg-2">
                        <div id="banner">
                             <h1>Sua roupa mais limpa, é conosco!</h1>  
                        </div>
                    </div>
            </div>
        
        
        
         </main>
       
<!--        <div>
            <a href="Login.jsp">Entrar</a>        
        </div>-->
    </body>
</html>
