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
@Table(name = "detalles_pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesPedidos.findAll", query = "SELECT d FROM DetallesPedidos d"),
    @NamedQuery(name = "DetallesPedidos.findByIdPedido", query = "SELECT d FROM DetallesPedidos d WHERE d.detallesPedidosPK.idPedido = :idPedido"),
    @NamedQuery(name = "DetallesPedidos.findByIdProducto", query = "SELECT d FROM DetallesPedidos d WHERE d.detallesPedidosPK.idProducto = :idProducto"),
    @NamedQuery(name = "DetallesPedidos.findByCantidad", query = "SELECT d FROM DetallesPedidos d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetallesPedidos.findByPrecio", query = "SELECT d FROM DetallesPedidos d WHERE d.precio = :precio")})
public class DetallesPedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesPedidosPK detallesPedidosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pedidos pedidos;
    @JoinColumn(name = "id_producto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Productos productos;

    public DetallesPedidos() {
    }

    public DetallesPedidos(DetallesPedidosPK detallesPedidosPK) {
        this.detallesPedidosPK = detallesPedidosPK;
    }

    public DetallesPedidos(DetallesPedidosPK detallesPedidosPK, int cantidad, int precio) {
        this.detallesPedidosPK = detallesPedidosPK;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetallesPedidos(int idPedido, int idProducto) {
        this.detallesPedidosPK = new DetallesPedidosPK(idPedido, idProducto);
    }

    public DetallesPedidosPK getDetallesPedidosPK() {
        return detallesPedidosPK;
    }

    public void setDetallesPedidosPK(DetallesPedidosPK detallesPedidosPK) {
        this.detallesPedidosPK = detallesPedidosPK;
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

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
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
        hash += (detallesPedidosPK != null ? detallesPedidosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesPedidos)) {
            return false;
        }
        DetallesPedidos other = (DetallesPedidos) object;
        if ((this.detallesPedidosPK == null && other.detallesPedidosPK != null) || (this.detallesPedidosPK != null && !this.detallesPedidosPK.equals(other.detallesPedidosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.ventas.entities.DetallesPedidos[ detallesPedidosPK=" + detallesPedidosPK + " ]";
    }
    
}
