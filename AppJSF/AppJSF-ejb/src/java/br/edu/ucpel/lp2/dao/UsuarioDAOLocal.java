package br.edu.ucpel.lp2.dao;

import br.edu.ucpel.lp2.jpa.Usuario;
import javax.ejb.Local;

/**
 *
 * @author mertins
 */
@Local
public interface UsuarioDAOLocal {

    Usuario findByLogin(String login);
}
