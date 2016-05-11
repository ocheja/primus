/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Administrator;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.service.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Named("administratorServiceBean")
@Transactional
public class AdministratorServiceBean extends PrimusBasePersistenceService {

    public void create(Administrator administrator) throws Exception {
        if(findAdministrator(administrator.getEmailAddress()) == null){
        EntityManager em = super.getEntityManager();
        em.persist(administrator);
        }else{
            throw new Exception("Administrator with EmailAddress : "+administrator.getEmailAddress()+" Already exist");
        }
    }

    public void edit(Administrator administrator) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em;
        em = super.getEntityManager();
        administrator = em.merge(administrator);
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;

        em = super.getEntityManager();
        Administrator administrator;
        try {
            administrator = em.getReference(Administrator.class, id);
            administrator.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The administrator with id " + id + " no longer exists.", enfe);
        }
        em.remove(administrator);

    }

    public List<Administrator> findAdministratorEntities() {
        return findAdministratorEntities(true, -1, -1);
    }

    public List<Administrator> findAdministratorEntities(int maxResults, int firstResult) {
        return findAdministratorEntities(false, maxResults, firstResult);
    }

    private List<Administrator> findAdministratorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Administrator as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Administrator findAdministrator(Long id) throws PrimusServiceException {
        return (Administrator) super.findByPrimaryKey(id, Administrator.class);
    }

    public Administrator findAdministratorLogin(String email, int password) throws PrimusServiceException {
        Query query = super.getEntityManager().createQuery("select A from Administrator A where A.password=:passW and A.emailAddress=:emal ");
        query.setParameter("passW", password);
        query.setParameter("emal", email);
        return (Administrator) super.findEntity(query);

    }
     public Administrator findAdministrator(String email) throws PrimusServiceException {
        Query query = super.getEntityManager().createQuery("select A from Administrator A where A.emailAddress=:emal ");
        query.setParameter("emal", email);
        return (Administrator) super.findEntity(query);

    }

    public List<Administrator> findAdministrator(String email, String firstName, String surname, String phoneNumber) throws PrimusServiceException {

        Query query = super.getEntityManager().createQuery("select A from Administrator A where A.emailAddress like :emal"
                + " or A.firstName like :fName or A.LastName like :lName or A.phoneNumber like :pNum");
        query.setParameter("emal", email);
        query.setParameter("lName", surname);
        query.setParameter("fName", firstName);
        query.setParameter("pNum", phoneNumber);
        List<Administrator> administrators = (List<Administrator>) super.findAll(query);
        return administrators; 

    }

    public int getAdministratorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Administrator as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
