/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Department;
import com.primus.data.Faculty;
import com.primus.data.FacultyName;
import com.primus.service.exceptions.RollbackFailureException;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Named("facultyServiceBean")
public class FacultyServiceBean extends PrimusBasePersistenceService {

    public void createFacultyName(FacultyName facultyN) throws Exception {
        if (findFacultyName(facultyN.getName()) == null) {
            super.storeEntity(facultyN);
        }
    }

    public void create(Faculty faculty) throws Exception {

        if (faculty.getDepartments() == null) {
            faculty.setDepartments(new ArrayList<Department>());
        }
        EntityManager em;
        String str = faculty.getFacultyName().getName();
        FacultyName facultyName = findFacultyName(str);
        if (facultyName == null) {
            faculty.setFacultyName((FacultyName) super.storeEntity(faculty.getFacultyName()));
        } else {
            faculty.setFacultyName(facultyName);
        }
        if (findFaculty(faculty.getFacultyName().getName()) == null) {  //check if faculty with Name exits if not create
            em = super.getEntityManager();
            List<Department> attachedDepartments = new ArrayList<>();
            for (Department departmentsDepartmentToAttach : faculty.getDepartments()) {
                departmentsDepartmentToAttach = em.getReference(departmentsDepartmentToAttach.getClass(), departmentsDepartmentToAttach.getId());
                attachedDepartments.add(departmentsDepartmentToAttach);
            }
            faculty.setDepartments(attachedDepartments);
            em.persist(faculty);
            for (Department departmentsDepartment : faculty.getDepartments()) {
                Faculty oldFacultyOfDepartmentsDepartment = departmentsDepartment.getFaculty();
                departmentsDepartment.setFaculty(faculty);
                departmentsDepartment = em.merge(departmentsDepartment);
                if (oldFacultyOfDepartmentsDepartment != null) {
                    oldFacultyOfDepartmentsDepartment.getDepartments().remove(departmentsDepartment);
                    oldFacultyOfDepartmentsDepartment = em.merge(oldFacultyOfDepartmentsDepartment);
                }
            }
        } else {
            throw new Exception("The faculty with name " + faculty.getFacultyName().getName() + " already exists");
        }

    }

    public void edit(Faculty faculty) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em;

        em = super.getEntityManager();
        Faculty persistentFaculty = em.find(Faculty.class, faculty.getId());
        List<Department> departmentsOld = persistentFaculty.getDepartments();
        List<Department> departmentsNew = faculty.getDepartments();
        List<Department> attachedDepartmentsNew = new ArrayList<Department>();
        for (Department departmentsNewDepartmentToAttach : departmentsNew) {
            departmentsNewDepartmentToAttach = em.getReference(departmentsNewDepartmentToAttach.getClass(), departmentsNewDepartmentToAttach.getId());
            attachedDepartmentsNew.add(departmentsNewDepartmentToAttach);
        }
        departmentsNew = attachedDepartmentsNew;
        faculty.setDepartments(departmentsNew);
        faculty = em.merge(faculty);
        for (Department departmentsOldDepartment : departmentsOld) {
            if (!departmentsNew.contains(departmentsOldDepartment)) {
                departmentsOldDepartment.setFaculty(null);
                departmentsOldDepartment = em.merge(departmentsOldDepartment);
            }
        }
        for (Department departmentsNewDepartment : departmentsNew) {
            if (!departmentsOld.contains(departmentsNewDepartment)) {
                Faculty oldFacultyOfDepartmentsNewDepartment = departmentsNewDepartment.getFaculty();
                departmentsNewDepartment.setFaculty(faculty);
                departmentsNewDepartment = em.merge(departmentsNewDepartment);
                if (oldFacultyOfDepartmentsNewDepartment != null && !oldFacultyOfDepartmentsNewDepartment.equals(faculty)) {
                    oldFacultyOfDepartmentsNewDepartment.getDepartments().remove(departmentsNewDepartment);
                    oldFacultyOfDepartmentsNewDepartment = em.merge(oldFacultyOfDepartmentsNewDepartment);
                }
            }
        }

    }

    public void destroy(Long id) throws Exception {
        EntityManager em;

        em = super.getEntityManager();
        Faculty faculty;
        try {
            faculty = em.getReference(Faculty.class, id);
            faculty.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The faculty with id " + id + " no longer exists.", enfe);
        }
        List<Department> departments = faculty.getDepartments();
        for (Department departmentsDepartment : departments) {
            departmentsDepartment.setFaculty(null);
            departmentsDepartment = em.merge(departmentsDepartment);
        }
        em.remove(faculty);

    }

    public List<Faculty> findFacultyEntities() {
        return findFacultyEntities(true, -1, -1);
    }

    public List<Faculty> findFacultyEntities(int maxResults, int firstResult) {
        return findFacultyEntities(false, maxResults, firstResult);
    }

    private List<Faculty> findFacultyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Faculty.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }
    private List<FacultyName> findFacultyNameEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(FacultyName.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }
    public List<FacultyName> findFacultyNameEntities() {
       
        return findFacultyNameEntities(true, -1, -1);
    }

    public Faculty findFaculty(Long id) throws Exception {
        return (Faculty) super.findByPrimaryKey(id, Faculty.class);
    }

    public FacultyName findFacultyName(Long id) throws Exception {
        return (FacultyName) super.findByPrimaryKey(id, FacultyName.class);
    }

    public int getFacultyCount() {
        EntityManager em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Faculty> rt = cq.from(Faculty.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public FacultyName findFacultyName(String FacName) throws Exception {
        Query query = super.getEntityManager().createQuery("select FN from FacultyName FN where FN.name=:facN");
        query.setParameter("facN", FacName);
        return (FacultyName) super.findEntity(query);
    }
    
    public List<?> findLikeFacultyName(String FacName) throws Exception {
        Query query = super.getEntityManager().createQuery("select F from Faculty F where F.facultyName.name like :facN");
        query.setParameter("facN", FacName);
        return super.findAll(query);
    }

    public List<?> findLikeFacultyNameName(String FacName) throws Exception {
        Query query = super.getEntityManager().createQuery("select F from FacultyName F where F.name like :facN");
        query.setParameter("facN", FacName);
        return super.findAll(query);
    }
    public Faculty findFaculty(String FacName) throws Exception {
        Query query = super.getEntityManager().createQuery("select F from Faculty F where F.facultyName.name=:facN");
        query.setParameter("facN", FacName);
        return (Faculty) super.findEntity(query);
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
