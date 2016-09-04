package gestion.ventas.entities.beans;

import gestion.ventas.entities.DetallesPedidos;
import gestion.ventas.entities.beans.util.JsfUtil;
import gestion.ventas.entities.beans.util.JsfUtil.PersistAction;
import gestion.ventas.entities.sessions.DetallesPedidosFacade;

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

@Named("detallesPedidosController")
@SessionScoped
public class DetallesPedidosController implements Serializable {

    @EJB
    private gestion.ventas.entities.sessions.DetallesPedidosFacade ejbFacade;
    private List<DetallesPedidos> items = null;
    private DetallesPedidos selected;

    public DetallesPedidosController() {
    }

    public DetallesPedidos getSelected() {
        return selected;
    }

    public void setSelected(DetallesPedidos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getDetallesPedidosPK().setIdProducto(selected.getProductos().getId());
        selected.getDetallesPedidosPK().setIdPedido(selected.getPedidos().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setDetallesPedidosPK(new gestion.ventas.entities.DetallesPedidosPK());
    }

    private DetallesPedidosFacade getFacade() {
        return ejbFacade;
    }

    public DetallesPedidos prepareCreate() {
        selected = new DetallesPedidos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetallesPedidosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetallesPedidosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetallesPedidosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetallesPedidos> getItems() {
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

    public DetallesPedidos getDetallesPedidos(gestion.ventas.entities.DetallesPedidosPK id) {
        return getFacade().find(id);
    }

    public List<DetallesPedidos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetallesPedidos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetallesPedidos.class)
    public static class DetallesPedidosControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallesPedidosController controller = (DetallesPedidosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallesPedidosController");
            return controller.getDetallesPedidos(getKey(value));
        }

        gestion.ventas.entities.DetallesPedidosPK getKey(String value) {
            gestion.ventas.entities.DetallesPedidosPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new gestion.ventas.entities.DetallesPedidosPK();
            key.setIdPedido(Integer.parseInt(values[0]));
            key.setIdProducto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(gestion.ventas.entities.DetallesPedidosPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPedido());
            sb.append(SEPARATOR);
            sb.append(value.getIdProducto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetallesPedidos) {
                DetallesPedidos o = (DetallesPedidos) object;
                return getStringKey(o.getDetallesPedidosPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallesPedidos.class.getName()});
                return null;
            }
        }

    }

}
