package br.edu.ucpel.lp2.jsf.mng;

import br.edu.ucpel.lp2.dao.DepartamentoDAOLocal;
import br.edu.ucpel.lp2.jpa.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Pattern;

/**
 *
 * @author mertins
 */
@Named(value = "departamentoMNG")
@RequestScoped
public class DepartamentoMNG {

    @EJB
    DepartamentoDAOLocal dao;
    private String codigo;
    @Pattern(regexp = "(.+)", message = "{invalid.departamento.descricao}")
    private String descricao;

    /**
     * Creates a new instance of DepartamentoMNG
     */
    public DepartamentoMNG() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Departamento getDepartamento(Long cod) {
        Departamento dept = new Departamento();
        dept.setCodigo(cod);
        return dao.retrieve(dept);

    }

    public List<Departamento> getLista() {
        return dao.listaTodos();
    }

    public List<SelectItem> getListaSelectItem() {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, ""));
        for (Departamento dept : dao.listaTodos()) {
            lista.add(new SelectItem(dept, dept.getDescricao()));
        }
        return lista;
    }

    public void clear(AjaxBehaviorEvent event) {
        this.codigo = null;
        this.descricao = null;
    }

    public String save() {
        Departamento dept = new Departamento();
        dept.setDescricao(descricao);
        dao.create(dept);
        return "departamentoList";
    }

    public void remove() {
        Long index = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Departamento dept = new Departamento();
        dept.setCodigo(index);
        dao.delete(dept);
        this.clear(null);
    }

    public String prepUpdate() {
        Long index = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        System.out.println("alterar" + index);
        Departamento dept = new Departamento();
        dept.setCodigo(index);
        dept = dao.retrieve(dept);
        this.codigo = dept.getCodigo().toString();
        this.descricao = dept.getDescricao();
        return "departamentoUpdate";
    }

    public String update() {
        Departamento dept = new Departamento();
        dept.setCodigo(Long.valueOf(codigo));
        dept.setDescricao(descricao);
        dao.update(dept);
        return "departamentoList";
    }
}
