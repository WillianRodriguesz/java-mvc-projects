<%-- 
    Document   : formDepartamento
    Created on : April 4, 2017, 2:39:54 PM
    Author     : mertins
--%>
<%@page import="br.tche.ucpel.bd2.bean.Departamento"%>
<%@page import="java.util.List"%>
<%@page errorPage="formerro.jsp"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Teste">
        <title>Protótipo Cadastros Java</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.2/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/menu.css" >
        <link rel="stylesheet" href="./css/tables.css">
        <link rel="stylesheet" href="./css/default.css">
        <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.2/build/grids-responsive-min.css">
    </head>
    <script type="text/javascript">
        function openCad() {
            var elem = document.getElementById("popupCad");
            elem.style.display = "block";
            elem.style.opacity = 100;
        }

        function closeCad() {
            var elem = document.getElementById("popupCad");
            elem.style.display = "none";
            elem.style.opacity = 0;
        }

        function clickTabela(cod) {
            document.getElementById("txtCodigo").value = cod;
            document.getElementById("acaoCRUD").value = "carregar";
            document.formdept.submit();
        }

        function salvar() {
            document.getElementById("acaoCRUD").value = "salvar";
            document.formdept.submit();
        }

        function excluir() {
            document.getElementById("acaoCRUD").value = "excluir";
            document.formdept.submit();
        }

    </script>
    <body>
        <jsp:include page="header.jsp" />
        <%
            Departamento dept = (Departamento) request.getAttribute("departamento");
        %>
        <div class="popupCadButtonBox"> 
            <a class="popupCadButton" onclick="openCad()">Cadastrar</a>
        </div>
        <div id="popupCad" class="basePopupCad">
            <div class="popupCad">
                <h2>Cadastro de Departamento</h2>
                <a class="close" onclick="closeCad()">&times;</a>
                <div class="content">
                    Abaixo pode-se cadastrar, alterar ou excluir um departamento do sistema, desde que ele não tenha sido utilizado.
                </div>
                <form class="pure-form" id="formdept" name="formdept" action="principal.lp2" method="post">
                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="txtCodigo">Código</label>
                        <input id="txtCodigo" name="txtCodigo" type="text" readonly="true" value="<%=dept != null ? dept.getCod() : ""%>">
                    </div>
                    <div class="pure-u-1 pure-u-md-1-1">
                        <label for="txtDescricao">Descrição</label>
                        <input id="txtDescricao" name="txtDescricao" type="text" value="<%=dept != null ? dept.getDescricao() : ""%>">
                    </div>
                    <div class="pure-u-1 pure-u-md-1-1">
                        <label for="txtLocalizacao">Localização</label>
                        <input id="txtLocalizacao" name="txtLocalizacao" type="text" value="<%=dept != null ? dept.getLocalizacao() : ""%>">
                    </div>
                    <button onclick="salvar()" class="pure-button pure-input-1-3 pure-button-primary">Salvar</button>
                    <button onclick="excluir()" class="pure-button pure-input-1-3 pure-button-secundary">Excluir</button>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" name="acao" value="departamento"/>
                </form>
            </div>
        </div>

        <div id="table">    
            <div class="header-row row">
                <span class="cell primary">Descrição</span>
                <span class="cell">Código</span>
                <span class="cell">Localização</span>
            </div>
            <%
                List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
                for (Departamento deptLista : departamentos) {
            %>
            <div class="row">
                <input type="radio" name="expand">
                <span class="cell primary" data-label="Descrição"><%=deptLista.getDescricao()%></span>
                <span class="cell cell-click" data-label="Código" <%=String.format("onClick='clickTabela(%d)' ", deptLista.getCod())%>><%=deptLista.getCod()%></span>
                <span class="cell" data-label="Localização"><%=deptLista.getLocalizacao()%></span>
            </div>
            <%}%>
        </div>
        <%
            if (dept != null) {
                out.write("<script>openCad()</script>");
            }

        %>

    </body>
</html>