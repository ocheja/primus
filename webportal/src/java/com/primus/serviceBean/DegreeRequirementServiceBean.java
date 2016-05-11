/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Degree;
import com.primus.data.Course;
import com.primus.data.DegreeRequirement;
import com.primus.enums.AcademicLevel;
import com.primus.enums.TitleOfDegree;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.util.WildSearchStringUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.jboss.weld.util.collections.ArraySet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class DegreeRequirementServiceBean extends PrimusBasePersistenceService {

    public void create(DegreeRequirement degreeRequirement) throws Exception {
        if (degreeRequirement.getCoursesForLevel() == null) {
            degreeRequirement.setCoursesForLevel(new ArraySet<Course>());
        }
        
        if(findDegreeRequirement(degreeRequirement.getDegree().getTitleOfDegree().toString(),degreeRequirement.getStudLevel()) ==null){
        EntityManager em = super.getEntityManager();
        Degree degree = degreeRequirement.getDegree();
        if (degree != null) {
            degree = em.getReference(degree.getClass(), degree.getId());
            degreeRequirement.setDegree(degree);
        }
        Set<Course> attachedCoursesForLevel = new ArraySet<>();
        for (Course coursesForLevelCourseToAttach : degreeRequirement.getCoursesForLevel()) {
            coursesForLevelCourseToAttach = em.getReference(coursesForLevelCourseToAttach.getClass(), coursesForLevelCourseToAttach.getId());
            attachedCoursesForLevel.add(coursesForLevelCourseToAttach);
        }
        degreeRequirement.setCoursesForLevel(attachedCoursesForLevel);
        em.persist(degreeRequirement);
        if (degree != null) {
            degree.getDegreeRequirement().add(degreeRequirement);
            degree = em.merge(degree);
        }
        for (Course coursesForLevelCourse : degreeRequirement.getCoursesForLevel()) {
            DegreeRequirement oldDegreeRequirementOfCoursesForLevelCourse = coursesForLevelCourse.getDegreeRequirement();
            coursesForLevelCourse.setDegreeRequirement(degreeRequirement);
            coursesForLevelCourse = em.merge(coursesForLevelCourse);
            if (oldDegreeRequirementOfCoursesForLevelCourse != null) {
                oldDegreeRequirementOfCoursesForLevelCourse.getCoursesForLevel().remove(coursesForLevelCourse);
                oldDegreeRequirementOfCoursesForLevelCourse = em.merge(oldDegreeRequirementOfCoursesForLevelCourse);
            }
        }
        }else{
            throw new Exception("Degree Requirement for "+degreeRequirement.getStudLevel()+" Level "+degreeRequirement.getDegree().getTitleOfDegree().toString()+"had already been defined");
        }
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void edit(DegreeRequirement degreeRequirement) throws Exception {
        EntityManager em = super.getEntityManager();
        DegreeRequirement persistentDegreeRequirement = em.find(DegreeRequirement.class, degreeRequirement.getId());
        Degree degreeOld = persistentDegreeRequirement.getDegree();
        Degree degreeNew = degreeRequirement.getDegree();
       Set<Course> coursesForLevelOld = persistentDegreeRequirement.getCoursesForLevel();
        Set<Course> coursesForLevelNew = degreeRequirement.getCoursesForLevel();
        if (degreeNew != null) {
            degreeNew = em.getReference(degreeNew.getClass(), degreeNew.getId());
            degreeRequirement.setDegree(degreeNew);
        }
       Set<Course> attachedCoursesForLevelNew = new ArraySet<Course>();
        for (Course coursesForLevelNewCourseToAttach : coursesForLevelNew) {
            coursesForLevelNewCourseToAttach = em.getReference(coursesForLevelNewCourseToAttach.getClass(), coursesForLevelNewCourseToAttach.getId());
            attachedCoursesForLevelNew.add(coursesForLevelNewCourseToAttach);
        }
        coursesForLevelNew = attachedCoursesForLevelNew;
        degreeRequirement.setCoursesForLevel(coursesForLevelNew);
        degreeRequirement = em.merge(degreeRequirement);
        if (degreeOld != null && !degreeOld.equals(degreeNew)) {
            degreeOld.getDegreeRequirement().remove(degreeRequirement);
            degreeOld = em.merge(degreeOld);
        }
        if (degreeNew != null && !degreeNew.equals(degreeOld)) {
            degreeNew.getDegreeRequirement().add(degreeRequirement);
            degreeNew = em.merge(degreeNew);
        }
        for (Course coursesForLevelOldCourse : coursesForLevelOld) {
            if (!coursesForLevelNew.contains(coursesForLevelOldCourse)) {
                coursesForLevelOldCourse.setDegreeRequirement(null);
                coursesForLevelOldCourse = em.merge(coursesForLevelOldCourse);
            }
        }
        for (Course coursesForLevelNewCourse : coursesForLevelNew) {
            if (!coursesForLevelOld.contains(coursesForLevelNewCourse)) {
                DegreeRequirement oldDegreeRequirementOfCoursesForLevelNewCourse = coursesForLevelNewCourse.getDegreeRequirement();
                coursesForLevelNewCourse.setDegreeRequirement(degreeRequirement);
                coursesForLevelNewCourse = em.merge(coursesForLevelNewCourse);
                if (oldDegreeRequirementOfCoursesForLevelNewCourse != null && !oldDegreeRequirementOfCoursesForLevelNewCourse.equals(degreeRequirement)) {
                    oldDegreeRequirementOfCoursesForLevelNewCourse.getCoursesForLevel().remove(coursesForLevelNewCourse);
                    oldDegreeRequirementOfCoursesForLevelNewCourse = em.merge(oldDegreeRequirementOfCoursesForLevelNewCourse);
                }
            }
        }
    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        DegreeRequirement degreeRequirement;
        try {
            degreeRequirement = em.getReference(DegreeRequirement.class, id);
            degreeRequirement.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The degreeRequirement with id " + id + " no longer exists.", enfe);
        }
        Degree degree = degreeRequirement.getDegree();
        if (degree != null) {
            degree.getDegreeRequirement().remove(degreeRequirement);
            degree = em.merge(degree);
        }
        Set<Course> coursesForLevel = degreeRequirement.getCoursesForLevel();
        for (Course coursesForLevelCourse : coursesForLevel) {
            coursesForLevelCourse.setDegreeRequirement(null);
            coursesForLevelCourse = em.merge(coursesForLevelCourse);
        }
        em.remove(degreeRequirement);
    }

    public List<DegreeRequirement> findDegreeRequirementEntities() {
        return findDegreeRequirementEntities(true, -1, -1);
    }

    public List<DegreeRequirement> findDegreeRequirementEntities(int maxResults, int firstResult) {
        return findDegreeRequirementEntities(false, maxResults, firstResult);
    }

    private List<DegreeRequirement> findDegreeRequirementEntities(boolean all, int maxResults, int firstResult) {
           Query q = super.getEntityManager().createQuery("select object(o) from DegreeRequirement as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
    }
     public DegreeRequirement findDegreeRequirement(String titleOfDegree,AcademicLevel Academiclevel) throws Exception {
           Query q = super.getEntityManager().createQuery("select d from DegreeRequirement d where d.degree.titleOfDegree =:degreeTitle and d.StudLevel =:Alevel");
           q.setParameter("degreeTitle", (TitleOfDegree)WildSearchStringUtil.checkEnum(TitleOfDegree.class, titleOfDegree));
            q.setParameter("Alevel",Academiclevel);
            return (DegreeRequirement)super.findEntity(q);
    }

    public DegreeRequirement findDegreeRequirement(Long id) throws Exception {
          return (DegreeRequirement)super.findByPrimaryKey(id, DegreeRequirement.class);
    }

    public int getDegreeRequirementCount() {
            Query q =   super.getEntityManager().createQuery("select count(o) from DegreeRequirement as o");
            return ((Long) q.getSingleResult()).intValue();
     }
}
