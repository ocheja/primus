/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Lecturer;
import com.primus.data.PortalActivity;
import com.primus.data.Student;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Transactional
@Component("portalActivityServiceBean")
public class PortalActivityServiceBean extends PrimusBasePersistenceService {

    
    
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public void create(PortalActivity portalActivity) throws RollbackFailureException, Exception {
        EntityManager em = null;
            em = getEntityManager();
            em.persist(portalActivity); 
    }

    public void edit(PortalActivity portalActivity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
            em = getEntityManager();
            portalActivity = em.merge(portalActivity);
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
            em = getEntityManager();
            PortalActivity portalActivity;
            try {
                portalActivity = em.getReference(PortalActivity.class, id);
                portalActivity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The portalActivity with id " + id + " no longer exists.", enfe);
            }
            em.remove(portalActivity);
    }

    public List<PortalActivity> findPortalActivityEntities() {
        return findPortalActivityEntities(true, -1, -1);
    }

    public List<PortalActivity> findPortalActivityEntities(int maxResults, int firstResult) {
        return findPortalActivityEntities(false, maxResults, firstResult);
    }

    private List<PortalActivity> findPortalActivityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
            Query q = em.createQuery("select o from PortalActivity  o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
    }
    
    public List<PortalActivity> findPortalActivityEntities(Lecturer lecturer) {
        EntityManager em = getEntityManager();
            Query q = em.createQuery("select o from PortalActivity as o where o.lecturer.email=:lec");
            q.setParameter("lec", lecturer.getEmail());
            return q.getResultList();
    }
    public List<PortalActivity> findPortalActivityEntities(Student student) {
        EntityManager em = getEntityManager();
            Query q = em.createQuery("select o from PortalActivity as o where o.student.matricSerialNumber=:stud");
            q.setParameter("stud", student.getMatricSerialNumber());
            return q.getResultList();
    }
    public PortalActivity findPortalActivity(Long id) {
        EntityManager em = getEntityManager();
            return em.find(PortalActivity.class, id);
    }

    public int getPortalActivityCount() {
        EntityManager em = getEntityManager();
            Query q = em.createQuery("select count(o) from PortalActivity as o");
            return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
