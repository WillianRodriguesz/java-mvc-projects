<%-- 
    Document   : formDepartamento
    Created on : April 4, 2017, 2:39:54 PM
    Author     : mertins
--%>
<!DOCTYPE html>
<html >
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Teste">
        <title>Protótipo Cadastros Java</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.2/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/menu.css" >
        <link rel="stylesheet" href="./css/default.css">
        <link rel="stylesheet" href="./css/error.css">
        <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.2/build/grids-responsive-min.css">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="basePopupErr">
            <div class="popupErr">
                <h2>Falha no sistema</h2>
                <div class="content">
                    <%
                        String mensagem = (String) request.getAttribute("mensagem");
                        if (mensagem != null) {
                            out.write(mensagem);
                        }
                    %>
                </div>
                <div class="popupErrButton">
                    <a  class="popupErrButtonLink" href="index.jsp">Tentar Novamente</a>
                </div>
            </div>
        </div>
    </body>
</html>