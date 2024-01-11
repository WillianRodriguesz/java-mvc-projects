package br.tche.ucpel.tads.doo2.filter;

import br.tche.ucpel.bd2.bean.Mensagem;
import br.tche.ucpel.bd2.bean.Usuario;
import br.tche.ucpel.bd2.dao.MensagemDAO;
import br.tche.ucpel.bd2.dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author willian
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

     @Resource(name = "jdbc/teste_aula")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            UsuarioDAO userDAO = new UsuarioDAO(conn);

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Usuario usuario = userDAO.retrieve(username);

            if (username.equals(usuario.getLogin()) && password.equals(usuario.getSenha())) {
                request.getSession().setAttribute("UsuarioLogado", true);
                
                MensagemDAO msgDAO = new MensagemDAO(conn);
                List<Mensagem> lista = msgDAO.listaUltimasPublicas(3);
                request.setAttribute("listaMsgs", lista);
         
                RequestDispatcher dispatcher = request.getRequestDispatcher("mensagem.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("formerro.jsp");
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
