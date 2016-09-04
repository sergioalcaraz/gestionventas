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
public class DetallesVentasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ventas")
    private int idVentas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;

    public DetallesVentasPK() {
    }

    public DetallesVentasPK(int idVentas, int idProducto) {
        this.idVentas = idVentas;
        this.idProducto = idProducto;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
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
        hash += (int) idVentas;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesVentasPK)) {
            return false;
        }
        DetallesVentasPK other = (DetallesVentasPK) object;
        if (this.idVentas != other.idVentas) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.ventas.entities.DetallesVentasPK[ idVentas=" + idVentas + ", idProducto=" + idProducto + " ]";
    }
    
}
