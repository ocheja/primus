/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.TimeTableDailyEntries;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Transactional
@Component("timeTableDailyEntriesServiceBean")
public class TimeTableDailyEntriesServiceBean extends PrimusBasePersistenceService {

    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public TimeTableDailyEntries create(TimeTableDailyEntries timeTableDailyEntries) throws RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        timeTableDailyEntries = em.merge(timeTableDailyEntries);
        return timeTableDailyEntries;
    }

    public TimeTableDailyEntries edit(TimeTableDailyEntries timeTableDailyEntries) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        timeTableDailyEntries = em.merge(timeTableDailyEntries);

        return timeTableDailyEntries;
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        TimeTableDailyEntries timeTableDailyEntries;
        try {
            timeTableDailyEntries = em.getReference(TimeTableDailyEntries.class, id);
            timeTableDailyEntries.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The timeTableDailyEntries with id " + id + " no longer exists.", enfe);
        }
        em.remove(timeTableDailyEntries);

    }

    public List<TimeTableDailyEntries> findTimeTableDailyEntriesEntities() {
        return findTimeTableDailyEntriesEntities(true, -1, -1);
    }

    public List<TimeTableDailyEntries> findTimeTableDailyEntriesEntities(int maxResults, int firstResult) {
        return findTimeTableDailyEntriesEntities(false, maxResults, firstResult);
    }

    private List<TimeTableDailyEntries> findTimeTableDailyEntriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select object(o) from TimeTableDailyEntries as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public TimeTableDailyEntries findTimeTableDailyEntries(Long id) {
        EntityManager em = getEntityManager();
        return em.find(TimeTableDailyEntries.class, id);

    }

    public int getTimeTableDailyEntriesCount() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select count(o) from TimeTableDailyEntries as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
