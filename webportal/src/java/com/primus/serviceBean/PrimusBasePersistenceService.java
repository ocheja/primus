/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.service.exceptions.PrimusServiceException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

/**
 *
 * @author Olisa
 */
@Transactional
@Component("primusBasePersistenceService")
public abstract class PrimusBasePersistenceService {

    @PersistenceContext
    private EntityManager manager;

    public EntityManager getEntityManager() {
        return this.manager;
    }

    protected Serializable createEntity(Serializable data) throws PrimusServiceException {
        return (Serializable) this.manager.merge(data);
    }

    protected Serializable storeEntity(Serializable data) throws PrimusServiceException {
        return (Serializable) this.manager.merge(data);
    }

    protected Serializable modifyEntity(Serializable data)
            throws PrimusServiceException {
        return (Serializable) this.manager.merge(data);
    }

    protected void removeEntity(Serializable data)
            throws PrimusServiceException {
        this.manager.remove(data);
    }

    public Serializable findByPrimaryKey(Long pk, Class clazz)
            throws PrimusServiceException {
        return (Serializable) this.manager.find(clazz, pk);
    }

    protected List<?> findAll(Query query)
            throws PrimusServiceException {
        List<?> results = query.getResultList();
        return results;
    }

    protected Object findEntity(Query query) {
        Object object = null;
        boolean status = false; 
        try {
            object = query.getSingleResult();
        } catch (Exception ex) {
            status = true;
        }
        if (status) {
            return null;
        }
        return object;
    }

    public List<? extends Serializable> findEntities(Class className, Field field) throws Exception {

        Query query = getEntityManager().createQuery("select c from :classN DN where c.:fieldN=:fieldV");
        query.setParameter("classN", className.getDeclaringClass().getSimpleName());
        query.setParameter(":fieldN", field.getName());
        query.setParameter(":fieldV", field.get(className.getClass()).toString());
        return query.getResultList();
    }

    protected abstract Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean);

    protected void setParameterValues(Query q, Map<String, Object> map) {
        if (!map.isEmpty()) {
            Iterator<String> keys = map.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                q.setParameter(key, map.get(key));
            }
        }
    }

    protected List<Object[]> findAllObjects(Query query)
            throws PrimusServiceException {
        return query.getResultList();
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }
}
