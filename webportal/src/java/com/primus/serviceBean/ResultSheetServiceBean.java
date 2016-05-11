/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.ResultSheet;
import com.primus.service.exceptions.PrimusServiceException;
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
 * @author Olisa
 */
@Transactional
@Component("resultSheetServiceBean")
public class ResultSheetServiceBean extends PrimusBasePersistenceService {

    public void create(ResultSheet resultSheet) throws RollbackFailureException, Exception {
        EntityManager em;
        em = super.getEntityManager();
        em.persist(resultSheet);

    }

    public void edit(ResultSheet resultSheet) throws Exception {
        super.modifyEntity(resultSheet);
    }

    public void destroy(Long id) throws Exception {
        EntityManager em;
        
        em = super.getEntityManager();
        ResultSheet resultSheet;
        resultSheet = em.getReference(ResultSheet.class, id);
        resultSheet.getId();
        em.remove(resultSheet);
  }

    public List<ResultSheet> findResultSheetEntities() {
        return findResultSheetEntities(true, -1, -1);
    }

    public List<ResultSheet> findResultSheetEntities(int maxResults, int firstResult) {
        return findResultSheetEntities(false, maxResults, firstResult);
    }

    private List<ResultSheet> findResultSheetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();

        Query q = em.createQuery("select object(o) from ResultSheet as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public ResultSheet findResultSheet(Long id) throws PrimusServiceException {
        return (ResultSheet) super.findByPrimaryKey(id, ResultSheet.class);
    }

    public int getResultSheetCount() {
        EntityManager em = super.getEntityManager();
        Query q = em.createQuery("select count(o) from ResultSheet as o");
        return ((Long) q.getSingleResult()).intValue();

    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
