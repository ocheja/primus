/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Course;
import com.primus.data.Degree;
import com.primus.data.DegreeRequirement;
import com.primus.data.DepartmentName;
import com.primus.enums.AcademicLevel;
import com.primus.enums.TitleOfDegree;
import com.primus.interfaces.DegreeService;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.util.WildSearchStringUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class DegreeServiceBean
        extends PrimusBasePersistenceService
        implements DegreeService {

    @Autowired
    DegreeRequirementServiceBean degreeRequirementServiceBean;

    @Override
    public void create(Degree degree) throws Exception {
        if( findDegree(degree.getTitleOfDegree(),degree.getDepartmentName())== null){
        EntityManager em = super.getEntityManager();
        em.persist(degree);
        }else{
            throw new Exception("The Degree: "+degree.getTitleOfDegree()+" ("+degree.getDepartmentName().getName()+") has already been created");
        }
        
    }

    @Override
    public void edit(Degree degree) throws Exception {
        degree = super.getEntityManager().merge(degree);
    }

    private Degree develop(Degree degree) {
        return super.getEntityManager().merge(degree);
    }

    @Override
    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        Degree degree;
        try {
            degree = em.getReference(Degree.class, id);
            degree.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The degree with id " + id + " no longer exists.", enfe);
        }
        em.remove(degree);
    }

    @Override
    public List<Degree> findDegreeEntities() {
        return findDegreeEntities(true, -1, -1);
    }

    public List<Degree> findDegreeEntities(int maxResults, int firstResult) {
        return findDegreeEntities(false, maxResults, firstResult);
    }

    private List<Degree> findDegreeEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from Degree as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Degree findDegree(Long id) throws Exception {
        return (Degree) super.findByPrimaryKey(id, Degree.class);
    }

    @Override
    public Degree findDegree(TitleOfDegree degreeTitle, DepartmentName departmentName) {
        Query q = super.getEntityManager().createQuery("select d from Degree d where d.titleOfDegree =:sString and d.departmentName =:deptName");
        q.setParameter("sString", degreeTitle);
        q.setParameter("deptName", departmentName);
        return (Degree) super.findEntity(q);
    }
  @Override
    public Degree findDegree( DepartmentName departmentName) {
        Query q = super.getEntityManager().createQuery("select d from Degree d where d.departmentName =:deptName");
        q.setParameter("deptName", departmentName);
        return (Degree) super.findEntity(q);
    }

    @Override
    public List<Degree> wildSearch(String searchParam) throws PrimusServiceException {
        Query q = super.getEntityManager().createQuery("select d from Degree d where d.titleOfDegree like :sEnum or d.departmentName.name like :sString");
        q.setParameter("sString", searchParam);
        q.setParameter("sEnum", (TitleOfDegree)WildSearchStringUtil.checkEnum(TitleOfDegree.class, searchParam));
        return (List<Degree>) super.findAll(q);
    }

    @Override
    public int getDegreeCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from Degree as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDegreeRequirement(Degree degree, List<Course> courses, AcademicLevel academiclevel) throws Exception {
        findDegree(degree.getTitleOfDegree(), degree.getDepartmentName());
        DegreeRequirement degreerequirement;
        // degreeRequirementServiceBean
    }
}
