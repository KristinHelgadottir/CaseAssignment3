/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.ExchangeRate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Diana
 */
public class ExchangeRateFacade {

    EntityManagerFactory emf;

    public ExchangeRateFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<ExchangeRate> getRates() {
    EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM SEED.EXCHANGE_RATE e");
            List<ExchangeRate> rates = query.getResultList();
            if (rates != null) {
                System.out.println("Got rates: " + rates.size());
            } else {
                System.out.println("Got NULL");
            }
            return rates;
        } finally {
            em.close();
        }    
    }

    public ExchangeRate getRateByCurrency(String currency) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExchangeRate.class, currency);
        } finally {
            em.close();
        }
    }
}
