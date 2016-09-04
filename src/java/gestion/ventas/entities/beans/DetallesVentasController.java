package gestion.ventas.entities.beans;

import gestion.ventas.entities.DetallesVentas;
import gestion.ventas.entities.beans.util.JsfUtil;
import gestion.ventas.entities.beans.util.JsfUtil.PersistAction;
import gestion.ventas.entities.sessions.DetallesVentasFacade;

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

@Named("detallesVentasController")
@SessionScoped
public class DetallesVentasController implements Serializable {

    @EJB
    private gestion.ventas.entities.sessions.DetallesVentasFacade ejbFacade;
    private List<DetallesVentas> items = null;
    private DetallesVentas selected;

    public DetallesVentasController() {
    }

    public DetallesVentas getSelected() {
        return selected;
    }

    public void setSelected(DetallesVentas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getDetallesVentasPK().setIdProducto(selected.getProductos().getId());
        selected.getDetallesVentasPK().setIdVentas(selected.getVentas().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setDetallesVentasPK(new gestion.ventas.entities.DetallesVentasPK());
    }

    private DetallesVentasFacade getFacade() {
        return ejbFacade;
    }

    public DetallesVentas prepareCreate() {
        selected = new DetallesVentas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetallesVentasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetallesVentasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetallesVentasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetallesVentas> getItems() {
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

    public DetallesVentas getDetallesVentas(gestion.ventas.entities.DetallesVentasPK id) {
        return getFacade().find(id);
    }

    public List<DetallesVentas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetallesVentas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetallesVentas.class)
    public static class DetallesVentasControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallesVentasController controller = (DetallesVentasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallesVentasController");
            return controller.getDetallesVentas(getKey(value));
        }

        gestion.ventas.entities.DetallesVentasPK getKey(String value) {
            gestion.ventas.entities.DetallesVentasPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new gestion.ventas.entities.DetallesVentasPK();
            key.setIdVentas(Integer.parseInt(values[0]));
            key.setIdProducto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(gestion.ventas.entities.DetallesVentasPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdVentas());
            sb.append(SEPARATOR);
            sb.append(value.getIdProducto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetallesVentas) {
                DetallesVentas o = (DetallesVentas) object;
                return getStringKey(o.getDetallesVentasPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallesVentas.class.getName()});
                return null;
            }
        }

    }

}
