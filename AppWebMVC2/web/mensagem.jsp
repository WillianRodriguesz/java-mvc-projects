<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/mensagem.css">
    <title>Mensagem</title>
</head>
<body>
    <div class="wrapper">
        <div id="container">
            <h1>Mensagens</h1>
            <div class="chat-box">
                <c:forEach var="mensagem" items="${listaMsgs}">
                    <div class="message">
                        <p>${mensagem.texto}</p>
                        <c:if test="${not empty mensagem.link}">
                            <p class="link"><a href="${mensagem.link}" target="_blank">${mensagem.link}</a></p>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
