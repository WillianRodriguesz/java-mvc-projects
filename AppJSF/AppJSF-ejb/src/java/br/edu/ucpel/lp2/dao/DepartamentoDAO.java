package br.edu.ucpel.lp2.dao;

import br.edu.ucpel.lp2.jpa.Departamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mertins
 */
@Stateless
public class DepartamentoDAO implements DepartamentoDAOLocal {

    @PersistenceContext
    private EntityManager em;

    /**
     * Se o departamento for válido, este método irá fazer o INSERT no SGBD.
     * O Código será inserido pela Sequência e será colocado novamente no objeto Departamento.
     * @param value Departamento a ser inserido
     */
    @Override
    public Departamento create(Departamento value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    /**
     * Retorna o departamento do SGBD de acordo com o código do departamento recebido.
     * @param value Departamento a ser carregado do SGBD
     * @return Departamento do SGBD
     */
    @Override
    public Departamento retrieve(Departamento value) {
        Departamento valueRet = em.find(Departamento.class, value.getCodigo());
        return valueRet;
    }

    /**
     * Atualiza o departamento no SGBD.
     * @param value Departamento a ser atualizado do SGBD
     */
    @Override
    public void update(Departamento value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    /**
     * Remove o código do departamento do SGBD.
     * @param value Departamento a ser excluído. Necessita apenas do atributo COD
     */
    @Override
    public void delete(Departamento value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    /**
     * Retorna uma Lista com todos os Departamentos cadastrados no SGBD.
     * @return Lista com os departamentos.
     * @throws java.sql.SQLException Qualquer erro entre o Sistema e o Banco será devolvido nesta Exceção
     */
    @Override
    public List<Departamento> listaTodos() {
        return (List<Departamento>) em.createNamedQuery("Departamento.findAll").getResultList();
    }

    /**
     * Aplica os testes para as regras de negócio.
     * @param value Departamento a ser testado
     * @return true se o Departamento atende as regras de negócio, ou false em caso contrário.
     */
    @Override
    public boolean valida(Departamento value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }
}
