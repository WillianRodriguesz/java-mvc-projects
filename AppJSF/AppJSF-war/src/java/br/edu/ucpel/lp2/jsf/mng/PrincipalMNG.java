package br.edu.ucpel.lp2.jsf.mng;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mertins
 */
@Named(value = "principalMNG")
@RequestScoped
public class PrincipalMNG {

    /** Creates a new instance of PrincipalMNG */
    public PrincipalMNG() {
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "sair";

    }
}
