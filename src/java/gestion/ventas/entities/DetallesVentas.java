/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.ventas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "detalles_ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesVentas.findAll", query = "SELECT d FROM DetallesVentas d"),
    @NamedQuery(name = "DetallesVentas.findByIdVentas", query = "SELECT d FROM DetallesVentas d WHERE d.detallesVentasPK.idVentas = :idVentas"),
    @NamedQuery(name = "DetallesVentas.findByIdProducto", query = "SELECT d FROM DetallesVentas d WHERE d.detallesVentasPK.idProducto = :idProducto"),
    @NamedQuery(name = "DetallesVentas.findByCantidad", query = "SELECT d FROM DetallesVentas d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetallesVentas.findByPrecio", query = "SELECT d FROM DetallesVentas d WHERE d.precio = :precio")})
public class DetallesVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesVentasPK detallesVentasPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @JoinColumn(name = "id_producto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Productos productos;
    @JoinColumn(name = "id_ventas", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ventas ventas;

    public DetallesVentas() {
    }

    public DetallesVentas(DetallesVentasPK detallesVentasPK) {
        this.detallesVentasPK = detallesVentasPK;
    }

    public DetallesVentas(DetallesVentasPK detallesVentasPK, int cantidad, int precio) {
        this.detallesVentasPK = detallesVentasPK;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetallesVentas(int idVentas, int idProducto) {
        this.detallesVentasPK = new DetallesVentasPK(idVentas, idProducto);
    }

    public DetallesVentasPK getDetallesVentasPK() {
        return detallesVentasPK;
    }

    public void setDetallesVentasPK(DetallesVentasPK detallesVentasPK) {
        this.detallesVentasPK = detallesVentasPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallesVentasPK != null ? detallesVentasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesVentas)) {
            return false;
        }
        DetallesVentas other = (DetallesVentas) object;
        if ((this.detallesVentasPK == null && other.detallesVentasPK != null) || (this.detallesVentasPK != null && !this.detallesVentasPK.equals(other.detallesVentasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.ventas.entities.DetallesVentas[ detallesVentasPK=" + detallesVentasPK + " ]";
    }
    
}
