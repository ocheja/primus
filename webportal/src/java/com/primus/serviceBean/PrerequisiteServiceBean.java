/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Course;
import com.primus.data.Prerequisite;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.ArrayList;
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
@Component
@Transactional
public class PrerequisiteServiceBean extends PrimusBasePersistenceService {

    public void create(Prerequisite prerequisite) throws Exception {
        EntityManager em = super.getEntityManager();
        em.persist(prerequisite);
    }

    public void create(Course course, List<Course> courses) throws Exception {
        Prerequisite prerequisite = new Prerequisite();
        EntityManager em;
        if (course.getId() == null) { // if course does not exist
            throw new NonexistentEntityException("A prerequisite course must be an existing Course."
                    + " You must create the course first before making it a prerequisite");
        }
        for (Course PreCourse : courses) {
            prerequisite.setCourseID(course);
            if (PreCourse.getId() == null) {  // if course does not exist
                throw new NonexistentEntityException("A prerequisite  must be an existing Course");
            }
            prerequisite.setPrerequisiteCourseID(PreCourse);
            if (findPrerequisite(course.getId(), PreCourse.getId()) == null) {
                if (course.getCourseLevel().ordinal() < PreCourse.getCourseLevel().ordinal()) {
                    throw new Exception("The academic level of a course must be higher then the prerequisite course level");
                }
                if (course.getCourseCode().equals(PreCourse.getCourseCode())) {
                    throw new Exception("A course cannot be prerequisite to itself. course : " + course.getCourseCode());
                }
                if (course.getCourseLevel().ordinal() == PreCourse.getCourseLevel().ordinal() && course.getSemester().ordinal() <= PreCourse.getSemester().ordinal()) {
                    throw new Exception("The " + PreCourse.getSemester().toString() + " Semester course " + PreCourse.getCourseCode() + " cannot be a prerequisite to the " + course.getSemester().toString() + " Semester course " + course.getCourseCode());
                }
                em = super.getEntityManager();
                em.persist(prerequisite);
            } else {
                throw new Exception("The Courses " + course.getCourseCode() + " and " + PreCourse.getCourseCode() + " are prerequisites Already ");
            }
        }
    }

    public void edit(Prerequisite prerequisite) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        prerequisite = em.merge(prerequisite);

    }

    public void destroy(Long id) throws Exception {
        EntityManager em = null;
        em = getEntityManager();
        Prerequisite prerequisite;
        try {
            prerequisite = em.getReference(Prerequisite.class, id);
            prerequisite.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The prerequisite with id " + id + " no longer exists.", enfe);
        }
        em.remove(prerequisite);

    }

    public List<Prerequisite> findPrerequisiteEntities() {
        return findPrerequisiteEntities(true, -1, -1);
    }

    public List<Prerequisite> findPrerequisiteEntities(int maxResults, int firstResult) {
        return findPrerequisiteEntities(false, maxResults, firstResult);
    }

    private List<Prerequisite> findPrerequisiteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Prerequisite as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prerequisite findPrerequisite(Long id) {
        EntityManager em = super.getEntityManager();
        try {
            return em.find(Prerequisite.class, id);
        } finally {
            em.close();
        }
    }

    public Prerequisite findPrerequisite(Long courseId, Long prerequisiteId) throws Exception {
        Query query = super.getEntityManager().createQuery("SELECT s FROM Prerequisite s WHERE s.courseID.id =:ID and s.prerequisiteCourseID.id =:pID");
        query.setParameter("ID", courseId);
        query.setParameter("pID", prerequisiteId);
        return (Prerequisite) super.findEntity(query);
    }

    public List<Prerequisite> findPrerequisites(Long courseId) throws Exception {
        Query query = super.getEntityManager().createQuery("SELECT s FROM Prerequisite s WHERE s.courseID.id =:ID or s.prerequisiteCourseID.id =:ID");
        query.setParameter("ID", courseId);
        return (List<Prerequisite>) super.findAll(query);
    }

    public List<Course> findPrerequisiteCourses(Course course) {
        EntityManager em;
        List<Course> courses = new ArrayList<>();
        List<Prerequisite> foundPrerequisites;
        em = super.getEntityManager();
        foundPrerequisites = em.createQuery("SELECT s FROM Prerequisite s "
                + "WHERE s.courseID=:ID ", Prerequisite.class).
                setParameter("ID", course).getResultList();
        for (Prerequisite prerequisite : foundPrerequisites) {
            courses.add(em.find(Course.class, prerequisite.getPrerequisiteCourseID().getId()));
        }
        return courses;
    }

    public void removePrerequisite(Course course, List<Course> courses) throws Exception {
        Prerequisite foundPrerequisite;
        EntityManager em = super.getEntityManager();

        if (course.getId() == null) { // if course does not exist
            throw new Exception("A prerequisite course must be an existing Course."
                    + " You must create the course first before making it a prerequisite");
        }
        for (Course PreCourse : courses) {
            foundPrerequisite = (Prerequisite) em.createQuery("select p from Prerequisite p "
                    + "where p.courseID=:course and p.prerequisiteCourseID = :pcourse", Prerequisite.class).
                    setParameter("course", course).
                    setParameter("pcourse", PreCourse).getSingleResult();
            destroy(foundPrerequisite.getId());
        }


    }

    public int getPrerequisiteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Prerequisite as o");
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
