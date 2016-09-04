package gestion.ventas.entities.beans;

import gestion.ventas.entities.DetallesCompras;
import gestion.ventas.entities.beans.util.JsfUtil;
import gestion.ventas.entities.beans.util.JsfUtil.PersistAction;
import gestion.ventas.entities.sessions.DetallesComprasFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("detallesComprasController")
@SessionScoped
public class DetallesComprasController implements Serializable {

    @EJB
    private gestion.ventas.entities.sessions.DetallesComprasFacade ejbFacade;
    private List<DetallesCompras> items = null;
    private DetallesCompras selected;

    public DetallesComprasController() {
    }

    public DetallesCompras getSelected() {
        return selected;
    }

    public void setSelected(DetallesCompras selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getDetallesComprasPK().setIdProducto(selected.getProductos().getId());
        selected.getDetallesComprasPK().setIdCompra(selected.getCompras().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setDetallesComprasPK(new gestion.ventas.entities.DetallesComprasPK());
    }

    private DetallesComprasFacade getFacade() {
        return ejbFacade;
    }

    public DetallesCompras prepareCreate() {
        selected = new DetallesCompras();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetallesComprasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetallesComprasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetallesComprasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetallesCompras> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DetallesCompras getDetallesCompras(gestion.ventas.entities.DetallesComprasPK id) {
        return getFacade().find(id);
    }

    public List<DetallesCompras> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetallesCompras> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetallesCompras.class)
    public static class DetallesComprasControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallesComprasController controller = (DetallesComprasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallesComprasController");
            return controller.getDetallesCompras(getKey(value));
        }

        gestion.ventas.entities.DetallesComprasPK getKey(String value) {
            gestion.ventas.entities.DetallesComprasPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new gestion.ventas.entities.DetallesComprasPK();
            key.setIdCompra(Integer.parseInt(values[0]));
            key.setIdProducto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(gestion.ventas.entities.DetallesComprasPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdCompra());
            sb.append(SEPARATOR);
            sb.append(value.getIdProducto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetallesCompras) {
                DetallesCompras o = (DetallesCompras) object;
                return getStringKey(o.getDetallesComprasPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallesCompras.class.getName()});
                return null;
            }
        }

    }

}
