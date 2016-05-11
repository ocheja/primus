/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.primusdesktopclient.data.FingerPrintModule;
import com.primusdesktopclient.data.Location;
import com.primusdesktopclient.service.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Olisa
 */
public class LocationService implements Serializable {

    public LocationService(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Location location) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FingerPrintModule fingerPrintModule = location.getFingerPrintModule();
            if (fingerPrintModule != null) {
                fingerPrintModule = em.getReference(fingerPrintModule.getClass(), fingerPrintModule.getId());
                location.setFingerPrintModule(fingerPrintModule);
            }
            em.persist(location);
            if (fingerPrintModule != null) {
                Location oldLocationOfFingerPrintModule = fingerPrintModule.getLocation();
                if (oldLocationOfFingerPrintModule != null) {
                    oldLocationOfFingerPrintModule.setFingerPrintModule(null);
                    oldLocationOfFingerPrintModule = em.merge(oldLocationOfFingerPrintModule);
                }
                fingerPrintModule.setLocation(location);
                fingerPrintModule = em.merge(fingerPrintModule);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Location location) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Location persistentLocation = em.find(Location.class, location.getId());
            FingerPrintModule fingerPrintModuleOld = persistentLocation.getFingerPrintModule();
            FingerPrintModule fingerPrintModuleNew = location.getFingerPrintModule();
            if (fingerPrintModuleNew != null) {
                fingerPrintModuleNew = em.getReference(fingerPrintModuleNew.getClass(), fingerPrintModuleNew.getId());
                location.setFingerPrintModule(fingerPrintModuleNew);
            }
            location = em.merge(location);
            if (fingerPrintModuleOld != null && !fingerPrintModuleOld.equals(fingerPrintModuleNew)) {
                fingerPrintModuleOld.setLocation(null);
                fingerPrintModuleOld = em.merge(fingerPrintModuleOld);
            }
            if (fingerPrintModuleNew != null && !fingerPrintModuleNew.equals(fingerPrintModuleOld)) {
                Location oldLocationOfFingerPrintModule = fingerPrintModuleNew.getLocation();
                if (oldLocationOfFingerPrintModule != null) {
                    oldLocationOfFingerPrintModule.setFingerPrintModule(null);
                    oldLocationOfFingerPrintModule = em.merge(oldLocationOfFingerPrintModule);
                }
                fingerPrintModuleNew.setLocation(location);
                fingerPrintModuleNew = em.merge(fingerPrintModuleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = location.getId();
                if (findLocation(id) == null) {
                    throw new NonexistentEntityException("The location with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Location location;
            try {
                location = em.getReference(Location.class, id);
                location.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The location with id " + id + " no longer exists.", enfe);
            }
            FingerPrintModule fingerPrintModule = location.getFingerPrintModule();
            if (fingerPrintModule != null) {
                fingerPrintModule.setLocation(null);
                fingerPrintModule = em.merge(fingerPrintModule);
            }
            em.remove(location);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Location> findLocationEntities() {
        return findLocationEntities(true, -1, -1);
    }

    public List<Location> findLocationEntities(int maxResults, int firstResult) {
        return findLocationEntities(false, maxResults, firstResult);
    }

    private List<Location> findLocationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Location.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Location findLocation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Location.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Location> rt = cq.from(Location.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
