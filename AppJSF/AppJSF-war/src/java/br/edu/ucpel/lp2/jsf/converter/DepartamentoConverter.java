package br.edu.ucpel.lp2.jsf.converter;

import br.edu.ucpel.lp2.jpa.Departamento;
import br.edu.ucpel.lp2.jsf.mng.DepartamentoMNG;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author mertins
 */
@FacesConverter(value = "departamentoConverter")
public class DepartamentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vExp = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{departamentoMNG}", DepartamentoMNG.class);
        DepartamentoMNG departamentoMNG = (DepartamentoMNG) vExp.getValue(ctx.getELContext());
        Departamento dept = departamentoMNG.getDepartamento(Long.valueOf(value));
        if (dept == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor desconhecido", "Departamento não foi encontrado");
            throw new ConverterException(msg);
        }
        return dept;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "0";
        } else {
            return ((Departamento) value).getCodigo().toString();
        }
    }
}
