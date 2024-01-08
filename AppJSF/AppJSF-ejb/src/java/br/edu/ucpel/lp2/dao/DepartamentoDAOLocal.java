package br.edu.ucpel.lp2.dao;

import br.edu.ucpel.lp2.jpa.Departamento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mertins
 */
@Local
public interface DepartamentoDAOLocal {

    Departamento create(Departamento value);

    Departamento retrieve(Departamento value);

    void update(Departamento value);

    void delete(Departamento value);

    List<Departamento> listaTodos();

    boolean valida(Departamento value);
}
