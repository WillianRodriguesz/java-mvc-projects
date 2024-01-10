<%--
    Document   : formFuncionario
    Created on : Data
    Author     : Autor
--%>
<%@page import="br.tche.ucpel.bd2.bean.Funcionario"%>
<%@page import="java.util.List"%>
<%@page errorPage="formerro.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Protótipo Cadastros JSP</title>
    </head>
    <link rel="stylesheet" href="css/layout.css" type="text/css"/>
    <script type="text/javascript">
        function limpar() {
            document.getElementById("txtCodigo").value = "";
            document.getElementById("txtNome").value = "";
            document.getElementById("txtCargo").value = "";
            document.getElementById("txtDataContratacao").value = "";
            document.getElementById("txtCodigoGerente").value = "";
            document.getElementById("txtSalario").value = "";
            document.getElementById("txtCodigoDepartamento").value = "";
            
        }

        function salvar() {
            document.getElementById("acaoCRUD").value = "salvar";
            document.formfunc.submit();
        }

        function excluir() {
            document.getElementById("acaoCRUD").value = "excluir";
            document.formfunc.submit();
        }

        function atualizar() {
            document.getElementById("acaoCRUD").value = "atualizar";
            document.formfunc.submit();
        }

        function clickTabela(cod) {
            document.getElementById("txtCodigo").value = cod;
           
            document.getElementById("acaoCRUD").value = "carregar";
            //document.formfunc.submit();
        }
    </script>
    <body>
        <div id="container2">
            <div id="header">
                <img src="imagens/ucpel.png"/>
                <div class="tituloPri">Desenvolvimento Orientado a Objetos II</div>
            </div>
            <div id="leftBar">
                <div class="menu">Cadastros</div>
                <a class="menuItem" href="principal?acao=funcionario">Funcionário</a>
                <br/>
                <br/>
                <a class="menuSair" href="principal?acao=logout">Sair</a>
            </div>
            <div id="content">
                <form id="formfunc" name="formfunc" action="principal" method="post">
                    <div class="tituloCadastros">Cadastro de Funcionário</div>
                    <%
                        Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
                    %>
                    <br/>
                    <label class="labels">Código</label><input class="inputs" id="txtCodigo" name="txtCodigo" type="text" value="<%=funcionario != null ? funcionario.getCod() : ""%>"/>
                    <br/>
                    <label class="labels" for="txtNome">Nome</label><input class="inputs" id="txtNome" name="txtNome" type="text" value="<%=funcionario != null ? funcionario.getNome() : ""%>"/>
                    <br/>
                    <label class="labels" for="txtCargo">Cargo</label><input class="inputs" id="txtCargo" name="txtCargo" type="text" value="<%=funcionario != null ? funcionario.getCargo() : ""%>"/>
                    <br/>
                    <label class="labels" for="txtDataContratacao">Data Contratação</label><input class="inputs" id="txtDataContratacao" name="txtDataContratacao" type="text" value="<%=funcionario != null ? funcionario.getDtContratacao() : ""%>"/>
                    <br/>
                    <label class="labels" for="txtCodigoGerente">Código Gerente</label><input class="inputs" id="txtCodigoGerente" name="txtCodigoGerente" type="text" value="<%=funcionario != null ? funcionario.getGerente() : ""%>"/>
                    <br/>
                    <label class="labels" for="txtSalario">Salário</label><input class="inputs" id="txtSalario" name="txtSalario" type="text" value="<%=funcionario != null ? funcionario.getSalario() : ""%>"/>
                    <br/>
                    <label class="labels" for="txtCodigoDepartamento">Código Departamento</label><input class="inputs" id="txtCodigoDepartamento" name="txtCodigoDepartamento" type="text" value="<%=funcionario != null ? funcionario.getDepartamento() : ""%>"/>
                    <br/>
                    <input class="buttons" type="button" onclick="limpar()" value="Limpar"/>
                    <input class="buttons" type="button" onclick="salvar()" value="Salvar"/>
                    <input class="buttons" type="button" onclick="excluir()" value="Excluir"/>
                    <input class="buttons" type="submit" value="Atualizar"/>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" name="acao" value="funcionario"/>
                    <br/>
                    <br/>
                    <div class="tabela">
                        <table style="width:100%" border="0">
                            <thead class="cabecalhoTabela">
                                <tr>
                                    <td>Código</td>
                                    <td>Nome</td>
                                    <td>Cargo</td>
                                    <td>Data Contratação</td>
                                    <td>Código Gerente</td>
                                    <td>Salário</td>
                                    <td>Código Departamento</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tbody>
                                    <%
                                        List<Funcionario> funcionarios = (List<Funcionario>) request.getAttribute("funcionarios");
                                        for (Funcionario funcLista : funcionarios) {
                                            out.print(String.format("<tr onClick=\"clickTabela(%d)\" class=\"linhaTabela\">", funcLista.getCod()));
                                            out.print(String.format("<td class=\"linhaTabela\">%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>",
                                                    funcLista.getCod(), funcLista.getNome(), funcLista.getCargo(), funcLista.getDtContratacao(),
                                                    funcLista.getGerente() != null ? funcLista.getGerente().getCod() : "", // Adapte conforme necessário
                                                    funcLista.getSalario(),
                                                    funcLista.getDepartamento() != null ? funcLista.getDepartamento().getCod() : "")); // Adapte conforme necessário
                                            out.print("</tr>");
                                        }
                                    %>
                                </tbody>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div id="footer">
                <div class="tituloSec">Protótipo para exemplificar conteúdos da disciplina! Prof. Luciano Edson Mertins</div>
            </div>
        </div>
    </body>
</html>
