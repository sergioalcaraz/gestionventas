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
@Table(name = "detalles_compras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesCompras.findAll", query = "SELECT d FROM DetallesCompras d"),
    @NamedQuery(name = "DetallesCompras.findByIdCompra", query = "SELECT d FROM DetallesCompras d WHERE d.detallesComprasPK.idCompra = :idCompra"),
    @NamedQuery(name = "DetallesCompras.findByIdProducto", query = "SELECT d FROM DetallesCompras d WHERE d.detallesComprasPK.idProducto = :idProducto"),
    @NamedQuery(name = "DetallesCompras.findByCantidad", query = "SELECT d FROM DetallesCompras d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetallesCompras.findByPrecio", query = "SELECT d FROM DetallesCompras d WHERE d.precio = :precio")})
public class DetallesCompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesComprasPK detallesComprasPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @JoinColumn(name = "id_compra", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Compras compras;
    @JoinColumn(name = "id_producto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Productos productos;

    public DetallesCompras() {
    }

    public DetallesCompras(DetallesComprasPK detallesComprasPK) {
        this.detallesComprasPK = detallesComprasPK;
    }

    public DetallesCompras(DetallesComprasPK detallesComprasPK, int cantidad, int precio) {
        this.detallesComprasPK = detallesComprasPK;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetallesCompras(int idCompra, int idProducto) {
        this.detallesComprasPK = new DetallesComprasPK(idCompra, idProducto);
    }

    public DetallesComprasPK getDetallesComprasPK() {
        return detallesComprasPK;
    }

    public void setDetallesComprasPK(DetallesComprasPK detallesComprasPK) {
        this.detallesComprasPK = detallesComprasPK;
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

    public Compras getCompras() {
        return compras;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallesComprasPK != null ? detallesComprasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesCompras)) {
            return false;
        }
        DetallesCompras other = (DetallesCompras) object;
        if ((this.detallesComprasPK == null && other.detallesComprasPK != null) || (this.detallesComprasPK != null && !this.detallesComprasPK.equals(other.detallesComprasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.ventas.entities.DetallesCompras[ detallesComprasPK=" + detallesComprasPK + " ]";
    }
    
}
