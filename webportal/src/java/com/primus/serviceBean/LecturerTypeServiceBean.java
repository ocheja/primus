/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.LecturerType;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class LecturerTypeServiceBean extends PrimusBasePersistenceService {

    public void create(LecturerType lecturerType) throws RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        em.persist(lecturerType);
    }

    public void edit(LecturerType lecturerType) throws NonexistentEntityException, RollbackFailureException, Exception {
        Long id = lecturerType.getId();
        if (findLecturerType(id) == null) {
            throw new NonexistentEntityException("The lecturerType with id " + id + " no longer exists.");
        }
        EntityManager em = super.getEntityManager();
        lecturerType = em.merge(lecturerType);
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        LecturerType lecturerType;
        try {
            lecturerType = em.getReference(LecturerType.class, id);
            lecturerType.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The lecturerType with id " + id + " no longer exists.", enfe);
        }
        em.remove(lecturerType);
    }

    public List<LecturerType> findLecturerTypeEntities() {
        return findLecturerTypeEntities(true, -1, -1);
    }

    public List<LecturerType> findLecturerTypeEntities(int maxResults, int firstResult) {
        return findLecturerTypeEntities(false, maxResults, firstResult);
    }

    private List<LecturerType> findLecturerTypeEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from LecturerType as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public LecturerType findLecturerType(Long id) throws Exception {
        return (LecturerType) super.findByPrimaryKey(id, LecturerType.class);
    }

    public int getLecturerTypeCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from LecturerType as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
