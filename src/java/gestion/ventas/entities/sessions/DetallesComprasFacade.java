/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.ventas.entities.sessions;

import gestion.ventas.entities.DetallesCompras;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sergio
 */
@Stateless
public class DetallesComprasFacade extends AbstractFacade<DetallesCompras> {

    @PersistenceContext(unitName = "GestionVentasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallesComprasFacade() {
        super(DetallesCompras.class);
    }
    
}
