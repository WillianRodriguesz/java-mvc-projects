package br.edu.ucpel.lp2.jsf.listener;

import br.edu.ucpel.lp2.jsf.mng.UsuarioMNG;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author mertins
 */
public class Autenticar implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();

        if (permite(fc)) {
            //usuário autorizado
            return;
        } else {
            // Verifica se esta na página principal
            boolean paginaLogin = fc.getViewRoot().getViewId().lastIndexOf("index") > -1 ? true : false;
            if (!paginaLogin) {
                NavigationHandler nh = fc.getApplication().getNavigationHandler();
                nh.handleNavigation(fc, null, "sair");
            }
        }
    }

    private boolean permite(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(UsuarioMNG.USER_SESSION_KEY));
    }
}
