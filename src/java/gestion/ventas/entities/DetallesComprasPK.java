/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.ventas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sergio
 */
@Embeddable
public class DetallesComprasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_compra")
    private int idCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;

    public DetallesComprasPK() {
    }

    public DetallesComprasPK(int idCompra, int idProducto) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCompra;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesComprasPK)) {
            return false;
        }
        DetallesComprasPK other = (DetallesComprasPK) object;
        if (this.idCompra != other.idCompra) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.ventas.entities.DetallesComprasPK[ idCompra=" + idCompra + ", idProducto=" + idProducto + " ]";
    }
    
}
