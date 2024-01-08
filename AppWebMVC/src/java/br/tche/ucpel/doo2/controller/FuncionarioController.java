package br.tche.ucpel.doo2.controller;

import br.tche.ucpel.bd2.dao.DepartamentoDAO;
import br.tche.ucpel.bd2.dao.FuncionarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
    
    
    private void listaTodos() throws ServletException, IOException, SQLException {
        FuncionarioDAO funDAO = new FuncionarioDAO(conn);
        req.setAttribute("funcionarios", funDAO.listaTodos());
    }
}
