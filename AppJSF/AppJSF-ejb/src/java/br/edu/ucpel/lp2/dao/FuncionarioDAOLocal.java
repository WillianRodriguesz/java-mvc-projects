package br.edu.ucpel.lp2.dao;

import br.edu.ucpel.lp2.jpa.Funcionario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mertins
 */
@Local
public interface FuncionarioDAOLocal {

    Funcionario create(Funcionario value);

    Funcionario retrieve(Funcionario value);

    void update(Funcionario value);

    void delete(Funcionario value);

    List<Funcionario> listaTodos();

    boolean valida(Funcionario value);
}
