<%-- 
    Document   : ManutencaoFuncionario
    Created on : 28 de nov. de 2022, 15:20:19
    Author     : james
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${empty sessionScope.logado}" >
            <c:set var="mensagem" value="Precisa fazer o login" scope="request"/>
            <c:redirect url="/Login.jsp"/>
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manutencão Funcionário</title>
<!--        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.png">
        <link href="css/manutencaoFuncionario.css" type="text/css" rel="stylesheet">
        <link href="css/erro.css" type="text/css" rel="stylesheet">
        <!--<link href="css/main.css" type="text/css" rel="stylesheet">-->
        <jsp:include page="sources.jsp" /> <%-- HEAD --%>  
        <script src="js/validarFormText.js"></script>
        <script defer src="js/excluirFuncionario.js"></script>
    </head>
        <jsp:include page="header.jsp" /> <%-- HEADER --%>
    <body>
        <div style="padding:30px;" class="container text-center container-border">
            <!--<img class="img" onclick="location.href='index.html'" src="assets/logo.png" alt="alt"/></img>-->
            <legend>Manutenção de Funcionários</legend>
            <form id="frmCadastro" accept-charset="ISO-8859-1" method="post" action=
                "<c:choose>
                    <c:when test="${form.equals('alterar')}">
                        FuncionarioServlet?action=alterar&id=${param.id}
                    </c:when>
                    <c:otherwise>
                        FuncionarioServlet?action=inserir
                    </c:otherwise>
                </c:choose>">
               <div class="form-row">
                   <div class="form-group col-md-12">
                       <label for="txtEmail">Email</label>
                       <input type="email" class="form-control" 
                              value="${func.email}" name="email" id="txtEmail" placeholder="fulano@email.com" required>
                   </div>
                   <div class="form-group col-md-12">
                       <label for="txtNome">Nome</label>
                       <input type="text" id="txtNome" 
                              value="${func.nome}" name="nome" class="form-control" placeholder="Digite seu nome" required>
                   </div>
                   <div class="form-group col-md-12">
                       <label for="txtSenha">Senha</label>
                       <input type="password" id="txtSenha" name="senha" class="form-control" placeholder="******" required>
                   </div>
                   <div class="form-group col-md-12">
                       <label for="dtpDataNascimento">Data de Nascimento</label>                     
                       <input type="date" 
                              value="${func.dataNascimento}" name="dataNasc" id="dtpDataNascimento" class="form-control" required >                      
                   </div>
                   <input type="submit" class="btn btn-primary" name="" id="btnCadastrarSalvar" value="Confirmar">
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
                        <th>Ações</th>
                    </tr>
		</thead>
                <tbody>
                    <c:forEach var="funcionario" items="${funcionarios}">
                        <tr class="row-tbody">
                            <td>${funcionario.id}</td>
                            <td>${funcionario.nome}</td>
                            <td>${funcionario.email}</td>
                            <td><fmt:formatDate value="${funcionario.dataNascimento}" 
                                            pattern="dd/MM/yyyy" /></td>
                            <td>
                                <a style="width: 8rem;" class="btn btn-success" 
                                   href="FuncionarioServlet?action=formAlterar&id=${funcionario.id}">Editar</a>
                                <a style="width: 8rem;" class="btn btn-danger" id="acao-excluir" 
                                   href="FuncionarioServlet?action=remover&id=${funcionario.id}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
		<tfoot></tfoot>
            </table>
       </div>     

    </body>
</html>
