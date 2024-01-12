package br.tche.ucpel.tads.dooii;

import br.tche.ucpel.bd2.bean.Arquivo;
import br.tche.ucpel.bd2.dao.ArquivoDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@WebServlet(name = "DownloadFile", urlPatterns = {"/downloadFile"})
public class DownloadFile extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeArquivo = req.getParameter("nomearquivo");
        String local = req.getParameter("localarmaz");
        Logger.getLogger(DownloadFile.class.getName()).log(Level.INFO, String.format("Nome arquivo [%s]    Local[%s]", nomeArquivo, local));
        if (nomeArquivo == null || local == null) {
            resp.sendRedirect("buscarArquivo.jsp");
            return;
        } else {
            if ("ARQUIVO".equals(local)) {
                String caminhoArquivo = String.format("/home/willian/Documentos/Treinamento MM/pastaUpload/%s", nomeArquivo);
                File arquivo = new File(caminhoArquivo);

                if (arquivo.exists()) {
                    resp.setContentType("application/octet-stream");
                    resp.setHeader("Content-Disposition", "attachment;filename=" + nomeArquivo);

                    try (FileInputStream fis = new FileInputStream(arquivo); OutputStream os = resp.getOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(DownloadFile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    String errorMsg = "Arquivo não encontrado no disco.";
                    Logger.getLogger(DownloadFile.class.getName()).log(Level.INFO, errorMsg);
                    msgHtml(req, resp, errorMsg);
                }
            } else {
                Connection conn = null;
                try {
                    Context context = new InitialContext();
                    DataSource dataSource = (DataSource) context.lookup("jdbc/teste_aula");
                    conn = dataSource.getConnection();
                    ArquivoDAO dao = new ArquivoDAO(conn);

                    Arquivo arquivo = dao.retrieve(nomeArquivo);

                    if (arquivo != null) {
                        resp.setContentType(arquivo.getContentType());
                        resp.setHeader("Content-Disposition", "attachment;filename=" + nomeArquivo);

                        try (InputStream is = arquivo.getConteudo(); OutputStream os = resp.getOutputStream()) {
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = is.read(buffer)) != -1) {
                                os.write(buffer, 0, bytesRead);
                            }
                        }
                    } else {
                        String errorMsg = "Arquivo não encontrado no Banco de Dados.";
                        Logger.getLogger(DownloadFile.class.getName()).log(Level.INFO, errorMsg);
                        msgHtml(req, resp, errorMsg);
                    }
                } catch (NamingException | SQLException | IOException ex) {
                    Logger.getLogger(DownloadFile.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        conn.close();
                    } catch (Exception ex) {
                        Logger.getLogger(DownloadFile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void msgHtml(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(String.format("<html><body style='background-color: goldenrod'>%s</body></html", msg));
        out.close();
    }
}
