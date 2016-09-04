/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.ventas.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findById", query = "SELECT p FROM Productos p WHERE p.id = :id"),
    @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByPrecio", query = "SELECT p FROM Productos p WHERE p.precio = :precio"),
    @NamedQuery(name = "Productos.findByCosto", query = "SELECT p FROM Productos p WHERE p.costo = :costo"),
    @NamedQuery(name = "Productos.findByStockAcual", query = "SELECT p FROM Productos p WHERE p.stockAcual = :stockAcual"),
    @NamedQuery(name = "Productos.findByStockMinimo", query = "SELECT p FROM Productos p WHERE p.stockMinimo = :stockMinimo")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_acual")
    private int stockAcual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_minimo")
    private int stockMinimo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.EAGER)
    private Collection<DetallesPedidos> detallesPedidosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.EAGER)
    private Collection<DetallesVentas> detallesVentasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.EAGER)
    private Collection<DetallesCompras> detallesComprasCollection;

    public Productos() {
    }

    public Productos(Integer id) {
        this.id = id;
    }

    public Productos(Integer id, String nombre, String descripcion, int precio, int costo, int stockAcual, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.costo = costo;
        this.stockAcual = stockAcual;
        this.stockMinimo = stockMinimo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getStockAcual() {
        return stockAcual;
    }

    public void setStockAcual(int stockAcual) {
        this.stockAcual = stockAcual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @XmlTransient
    public Collection<DetallesPedidos> getDetallesPedidosCollection() {
        return detallesPedidosCollection;
    }

    public void setDetallesPedidosCollection(Collection<DetallesPedidos> detallesPedidosCollection) {
        this.detallesPedidosCollection = detallesPedidosCollection;
    }

    @XmlTransient
    public Collection<DetallesVentas> getDetallesVentasCollection() {
        return detallesVentasCollection;
    }

    public void setDetallesVentasCollection(Collection<DetallesVentas> detallesVentasCollection) {
        this.detallesVentasCollection = detallesVentasCollection;
    }

    @XmlTransient
    public Collection<DetallesCompras> getDetallesComprasCollection() {
        return detallesComprasCollection;
    }

    public void setDetallesComprasCollection(Collection<DetallesCompras> detallesComprasCollection) {
        this.detallesComprasCollection = detallesComprasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.ventas.entities.Productos[ id=" + id + " ]";
    }
    
}
