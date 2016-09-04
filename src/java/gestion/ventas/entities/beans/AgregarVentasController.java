package gestion.ventas.entities.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import gestion.ventas.entities.Clientes;
import gestion.ventas.entities.Empleados;
import gestion.ventas.entities.Productos;
import gestion.ventas.entities.beans.util.DataConnect;
import gestion.ventas.entities.sessions.ClientesFacade;
import gestion.ventas.entities.sessions.EmpleadosFacade;
import gestion.ventas.entities.sessions.ProductosFacade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("agregarVentasController")
@SessionScoped
public class AgregarVentasController implements Serializable {

    private String nroVenta;
    private String fechaVenta;
    private Connection con = null;
    private int cantidad;

    private List<Clientes> clientesList = new ArrayList<>(); //comboClientes
    private List<Empleados> empleadosList = new ArrayList<>(); //comboEmpleados

    private List<Productos> productosList = new ArrayList<>();
    private Productos producto;

    private ArrayList<DetallesVentasController> listaDetalle = new ArrayList<>();

    @EJB
    private ClientesFacade clientesFacade = new ClientesFacade();
    @EJB
    private EmpleadosFacade empleadosFacade = new EmpleadosFacade();

    @EJB
    private ProductosFacade productosFacade = new ProductosFacade();

    private Integer idCliente;
    private Empleados emplado;

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Empleados getEmpleado() {
        return emplado;
    }

    public void setEmpleado(Empleados empleado) {
        this.emplado = empleado;
    }

    public String getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(String nroVenta) {
        this.nroVenta = nroVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<DetallesVentasController> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(ArrayList<DetallesVentasController> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    

    @PostConstruct
    void initialiseSession() {
        con = DataConnect.getConnection();
        this.cargarVista();
    }

    public void cargarVista() {
        this.clientesList = clientesFacade.findAll();
        this.empleadosList = empleadosFacade.findAll();
        this.productosList = productosFacade.findAll();
        int nuevaSeq = obtenerNroVenta();
        if (nuevaSeq < 10) {
            this.nroVenta = "000" + String.valueOf(nuevaSeq);
        } else if (nuevaSeq < 100) {
            this.nroVenta = "0" + String.valueOf(nuevaSeq);
        } else {
            this.nroVenta = String.valueOf(nuevaSeq);
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        this.fechaVenta = today;

    }

    public int obtenerNroVenta() {
        int ultimoValor = 0;
        try {
            PreparedStatement ps
                    = con.prepareStatement("SELECT last_value FROM ventas_id_seq");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal uv = rs.getBigDecimal("last_value");
                ultimoValor = uv.toBigInteger().intValue();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener secuencia ­­>" + ex.getMessage());
        }
        return ultimoValor;
    }

    public void agregarProducto() {
    }

}
