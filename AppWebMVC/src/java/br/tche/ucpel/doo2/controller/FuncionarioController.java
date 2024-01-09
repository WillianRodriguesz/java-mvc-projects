package br.tche.ucpel.doo2.controller;

import br.tche.ucpel.bd2.bean.Departamento;
import br.tche.ucpel.bd2.bean.Funcionario;
import br.tche.ucpel.bd2.dao.DepartamentoDAO;
import br.tche.ucpel.bd2.dao.FuncionarioDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FuncionarioController {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final Connection conn;

    public FuncionarioController(HttpServletRequest req, HttpServletResponse resp, Connection conn) {
        this.req = req;
        this.resp = resp;
        this.conn = conn;
    }

    public void processo() throws ServletException, IOException, SQLException {

        listaTodos();
        RequestDispatcher dispatcher = req.getRequestDispatcher("formdefuncionario.jsp");
        dispatcher.forward(req, resp);
    }

    private void salvar() throws ServletException, IOException, ParseException, SQLException {
        int codigo = Integer.parseInt(req.getParameter("txtCodigo"));
        String nome = req.getParameter("txtNome");
        String cargo = req.getParameter("Cargo");
        Date dataContratacao = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("txtDataContratacao"));
        int codGerente = Integer.parseInt(req.getParameter("txtCodigoGerente"));
        BigDecimal salario = new BigDecimal(req.getParameter("txtSalario"));
        int codDepartamento = Integer.parseInt("txtCodigoDepartamento");

        Funcionario funcionario = new Funcionario(codGerente);
        Departamento departamento = new Departamento(codDepartamento);
        FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
        DepartamentoDAO depDAO = new DepartamentoDAO(conn);

        Funcionario gerente = funcDAO.retrieve(funcionario);
        departamento = depDAO.retrieve(departamento);

        funcionario.setCod(codigo);
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setDtContratacao(dataContratacao);
        funcionario.setGerente(gerente);
        funcionario.setSalario(salario);
        funcionario.setDepartamento(departamento);
        
        
        //adicionar controle de cod auto //
        try {
            funcDAO.create(funcionario);
        } catch (SQLException ex) {
            ServletPrincipal.dispatcherErro(req, resp, String.format("Não foi possível inserir Funcionario.[%s]", ex.getMessage()));
        }
    }

    private void listaTodos() throws ServletException, IOException, SQLException {
        FuncionarioDAO funDAO = new FuncionarioDAO(conn);
        req.setAttribute("funcionarios", funDAO.listaTodos());
    }
}
