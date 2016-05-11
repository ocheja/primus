/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.primusdesktopclient.data.FingerPrintModule;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.primusdesktopclient.data.Location;
import com.primusdesktopclient.gsondata.HardwareMessage;
import com.primusdesktopclient.service.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Olisa
 */
public class FingerPrintModuleService implements Serializable {

    private org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(FingerPrintModuleService.class);

    public FingerPrintModuleService() {
        this.emf = Persistence.createEntityManagerFactory("PrimusDesktopClientPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FingerPrintModule fingerPrintModule) throws Exception {
        EntityManager em = null;
        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Location location = fingerPrintModule.getLocation();
            if (location.getId() != null) {
                location = em.getReference(location.getClass(), location.getId());
                fingerPrintModule.setLocation(location);
            }
            em.persist(fingerPrintModule);
            if (location != null) {
                FingerPrintModule oldFingerPrintModuleOfLocation = location.getFingerPrintModule();
                if (oldFingerPrintModuleOfLocation != null) {
                    oldFingerPrintModuleOfLocation.setLocation(null);
                    oldFingerPrintModuleOfLocation = em.merge(oldFingerPrintModuleOfLocation);
                }
                location.setFingerPrintModule(fingerPrintModule);
                location = em.merge(location);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FingerPrintModule fingerPrintModule) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FingerPrintModule persistentFingerPrintModule = em.find(FingerPrintModule.class, fingerPrintModule.getId());
            Location locationOld = persistentFingerPrintModule.getLocation();
            Location locationNew = fingerPrintModule.getLocation();
            if (locationNew != null) {
                locationNew = em.getReference(locationNew.getClass(), locationNew.getId());
                fingerPrintModule.setLocation(locationNew);
            }
            fingerPrintModule = em.merge(fingerPrintModule);
            if (locationOld != null && !locationOld.equals(locationNew)) {
                locationOld.setFingerPrintModule(null);
                locationOld = em.merge(locationOld);
            }
            if (locationNew != null && !locationNew.equals(locationOld)) {
                FingerPrintModule oldFingerPrintModuleOfLocation = locationNew.getFingerPrintModule();
                if (oldFingerPrintModuleOfLocation != null) {
                    oldFingerPrintModuleOfLocation.setLocation(null);
                    oldFingerPrintModuleOfLocation = em.merge(oldFingerPrintModuleOfLocation);
                }
                locationNew.setFingerPrintModule(fingerPrintModule);
                locationNew = em.merge(locationNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fingerPrintModule.getId();
                if (findFingerPrintModule(id) == null) {
                    throw new NonexistentEntityException("The fingerPrintModule with id " + id + " no longer exists.");
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
            FingerPrintModule fingerPrintModule;
            try {
                fingerPrintModule = em.getReference(FingerPrintModule.class, id);
                fingerPrintModule.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fingerPrintModule with id " + id + " no longer exists.", enfe);
            }
            Location location = fingerPrintModule.getLocation();
            if (location != null) {
                location.setFingerPrintModule(null);
                location = em.merge(location);
            }
            em.remove(fingerPrintModule);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FingerPrintModule> findFingerPrintModuleEntities() {
        return findFingerPrintModuleEntities(true, -1, -1);
    }

    public List<FingerPrintModule> findFingerPrintModuleEntities(int maxResults, int firstResult) {
        return findFingerPrintModuleEntities(false, maxResults, firstResult);
    }

    private List<FingerPrintModule> findFingerPrintModuleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FingerPrintModule.class));
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

    public FingerPrintModule findFingerPrintModule(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FingerPrintModule.class, id);
        } finally {
            em.close();
        }
    }

    public FingerPrintModule findFingerPrintModule(String address) {
        EntityManager em = getEntityManager();
        FingerPrintModule fingerPrintModule = null;
        boolean status = false;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select p from FingerPrintModule p where p.location.address =:add");
            q.setParameter("add", address);
            fingerPrintModule = (FingerPrintModule) q.getResultList().get(0);
            em.getTransaction().commit();
        } catch (Exception e) {
            status = true;
        } finally {
            em.close();
        }
        if (status == true) {
            return null;
        }
        return fingerPrintModule;
    }

    public FingerPrintModule findFingerPrintModuleByAdd(String macAddress) {
        EntityManager em = getEntityManager();
        FingerPrintModule fingerPrintModule = null;
        boolean status = false;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select p from FingerPrintModule p where p.macAddress =:add ");
            q.setParameter("add", macAddress);
            fingerPrintModule = (FingerPrintModule) q.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            status = true;
        } finally {
            em.close();
        }
        if (status == true) {
            return null;
        }
        return fingerPrintModule;
    }

    public FingerPrintModule findFingerPrintModule(String macAddress, String location) {
        EntityManager em = getEntityManager();
        FingerPrintModule fingerPrintModule = null;
        boolean status = false;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select p from FingerPrintModule p where p.macAddress =:add or p.location.address=:loc");
            q.setParameter("add", macAddress);
            q.setParameter("loc", location);
            fingerPrintModule = (FingerPrintModule) q.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            status = true;
        } finally {
            em.close();
        }
        if (status == true) {
            return null;
        }
        return fingerPrintModule;
    }

    public int getFingerPrintModuleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FingerPrintModule> rt = cq.from(FingerPrintModule.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void registerFingerprintModule(FingerPrintModule fingerPrintModule) throws Exception {
        FingerPrintModule oldScanner = findFingerPrintModule(fingerPrintModule.getMacAddress(), fingerPrintModule.getLocation().getAddress());
        if (oldScanner != null) {
            //  throw new Exception("Fingerprint Module already exists with MAC addresse: "+fingerPrintModule.getMacAddress()); 
            edit(fingerPrintModule);
        } else {
            create(fingerPrintModule);
        }
    }

    public void updateModule(HardwareMessage hardwareMessage) {
        if (hardwareMessage != null) {
            if (hardwareMessage.getLocation()!= null) {
                FingerPrintModule fingerPrintModule = findFingerPrintModule(hardwareMessage.getMacAdress().trim(), hardwareMessage.getLocation().trim());
                  System.out.println("finger "+fingerPrintModule);
             
                fingerPrintModule.setBatteryLevel(hardwareMessage.getBatteryLevel());
                fingerPrintModule.setActive(true);
                 fingerPrintModule.setLastCommunicationTime(new Date());
                try {
                    edit(fingerPrintModule);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(FingerPrintModuleService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FingerPrintModuleService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}