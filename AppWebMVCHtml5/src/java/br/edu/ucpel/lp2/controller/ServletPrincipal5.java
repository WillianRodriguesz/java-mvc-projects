package br.edu.ucpel.lp2.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author mertins
 */
@WebServlet(name = "ServletPrincipal", urlPatterns = {"/principal.lp2"})
public class ServletPrincipal5 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = null;
        Connection conn = null;
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("jdbc/BaseUCPel");
            conn = dataSource.getConnection();
            String acao = req.getParameter("acao");
            switch (acao) {
                case "login":
                    LoginController5 lc = new LoginController5(req, resp, conn);
                    lc.processo();
                    break;
                case "logout":
                    req.getSession().invalidate();
                    RequestDispatcher logout = req.getRequestDispatcher("index.jsp");
                    logout.forward(req, resp);
                    break;
                case "departamento":
                    DepartamentoController dc = new DepartamentoController(req, resp, conn);
                    dc.processo();
                    break;
                default:
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req, resp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ServletPrincipal5.dispatcherErro(req, resp, ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }

    public static void dispatcherErro(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("mensagem", msg);
        RequestDispatcher dispatcher = req.getRequestDispatcher("formerro.jsp");
        dispatcher.forward(req, resp);

    }
}
