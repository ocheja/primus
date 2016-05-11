/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Lecturer;
import com.primus.data.Course;
import com.primus.data.LecturerStudents;
import com.primus.data.Student;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class LecturerStudentsServiceBean extends PrimusBasePersistenceService {

    public void create(LecturerStudents lecturerStudents) throws Exception {
        if (lecturerStudents.getStudents() == null) {
            lecturerStudents.setStudents(new ArrayList<Student>());
        }
        EntityManager em = super.getEntityManager();
        Lecturer lecturer = lecturerStudents.getLecturer();
        if (lecturer != null) {
            lecturer = em.getReference(lecturer.getClass(), lecturer.getId());
            lecturerStudents.setLecturer(lecturer);
        }
        Course course = lecturerStudents.getCourse();
        if (course != null) {
            course = em.getReference(course.getClass(), course.getId());
            lecturerStudents.setCourse(course);
        }
        List<Student> attachedStudents = new ArrayList<Student>();
        for (Student studentsStudentToAttach : lecturerStudents.getStudents()) {
            studentsStudentToAttach = em.getReference(studentsStudentToAttach.getClass(), studentsStudentToAttach.getId());
            attachedStudents.add(studentsStudentToAttach);
        }
        lecturerStudents.setStudents(attachedStudents);
        em.persist(lecturerStudents);
        if (lecturer != null) {
            lecturer.getLecturerStudents().add(lecturerStudents);
            lecturer = em.merge(lecturer);
        }
        if (course != null) {
            course.getLecturerStudents().add(lecturerStudents);
            course = em.merge(course);
        }
        for (Student studentsStudent : lecturerStudents.getStudents()) {
            studentsStudent.getLecturerStudentss().add(lecturerStudents);
            studentsStudent = em.merge(studentsStudent);
        }

    }

    public void edit(LecturerStudents lecturerStudents) throws Exception {
        EntityManager em = super.getEntityManager();
        LecturerStudents persistentLecturerStudents = em.find(LecturerStudents.class, lecturerStudents.getId());
        Lecturer lecturerOld = persistentLecturerStudents.getLecturer();
        Lecturer lecturerNew = lecturerStudents.getLecturer();
        Course courseOld = persistentLecturerStudents.getCourse();
        Course courseNew = lecturerStudents.getCourse();
        List<Student> studentsOld = persistentLecturerStudents.getStudents();
        List<Student> studentsNew = lecturerStudents.getStudents();
        if (lecturerNew != null) {
            lecturerNew = em.getReference(lecturerNew.getClass(), lecturerNew.getId());
            lecturerStudents.setLecturer(lecturerNew);
        }
        if (courseNew != null) {
            courseNew = em.getReference(courseNew.getClass(), courseNew.getId());
            lecturerStudents.setCourse(courseNew);
        }
        List<Student> attachedStudentsNew = new ArrayList<Student>();
        for (Student studentsNewStudentToAttach : studentsNew) {
            studentsNewStudentToAttach = em.getReference(studentsNewStudentToAttach.getClass(), studentsNewStudentToAttach.getId());
            attachedStudentsNew.add(studentsNewStudentToAttach);
        }
        studentsNew = attachedStudentsNew;
        lecturerStudents.setStudents(studentsNew);
        lecturerStudents = em.merge(lecturerStudents);
        if (lecturerOld != null && !lecturerOld.equals(lecturerNew)) {
            lecturerOld.getLecturerStudents().remove(lecturerStudents);
            lecturerOld = em.merge(lecturerOld);
        }
        if (lecturerNew != null && !lecturerNew.equals(lecturerOld)) {
            lecturerNew.getLecturerStudents().add(lecturerStudents);
            lecturerNew = em.merge(lecturerNew);
        }
        if (courseOld != null && !courseOld.equals(courseNew)) {
            courseOld.getLecturerStudents().remove(lecturerStudents);
            courseOld = em.merge(courseOld);
        }
        if (courseNew != null && !courseNew.equals(courseOld)) {
            courseNew.getLecturerStudents().add(lecturerStudents);
            courseNew = em.merge(courseNew);
        }
        for (Student studentsOldStudent : studentsOld) {
            if (!studentsNew.contains(studentsOldStudent)) {
                studentsOldStudent.getLecturerStudentss().remove(lecturerStudents);
                studentsOldStudent = em.merge(studentsOldStudent);
            }
        }
        for (Student studentsNewStudent : studentsNew) {
            if (!studentsOld.contains(studentsNewStudent)) {
                studentsNewStudent.getLecturerStudentss().add(lecturerStudents);
                studentsNewStudent = em.merge(studentsNewStudent);
            }
        }

        Long id = lecturerStudents.getId();
        if (findLecturerStudents(id) == null) {
            throw new NonexistentEntityException("The lecturerStudents with id " + id + " no longer exists.");
        }

    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        LecturerStudents lecturerStudents;
        try {
            lecturerStudents = em.getReference(LecturerStudents.class, id);
            lecturerStudents.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The lecturerStudents with id " + id + " no longer exists.", enfe);
        }
        Lecturer lecturer = lecturerStudents.getLecturer();
        if (lecturer != null) {
            lecturer.getLecturerStudents().remove(lecturerStudents);
            lecturer = em.merge(lecturer);
        }
        Course course = lecturerStudents.getCourse();
        if (course != null) {
            course.getLecturerStudents().remove(lecturerStudents);
            course = em.merge(course);
        }
        List<Student> students = lecturerStudents.getStudents();
        for (Student studentsStudent : students) {
            studentsStudent.getLecturerStudentss().remove(lecturerStudents);
            studentsStudent = em.merge(studentsStudent);
        }
        em.remove(lecturerStudents);

    }

    public List<LecturerStudents> findLecturerStudentsEntities() {
        return findLecturerStudentsEntities(true, -1, -1);
    }

    public List<LecturerStudents> findLecturerStudentsEntities(int maxResults, int firstResult) {
        return findLecturerStudentsEntities(false, maxResults, firstResult);
    }

    private List<LecturerStudents> findLecturerStudentsEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from LecturerStudents as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public LecturerStudents findLecturerStudents(Long id) throws Exception {
        return (LecturerStudents) super.findByPrimaryKey(id, LecturerStudents.class);
    }

    public LecturerStudents findLecturerStudents(String lecturerEmail, AcademicSession academicSession, String courseCode) throws Exception {
        Query q = super.getEntityManager().createQuery("select l from LecturerStudents l where l.lecturer.email =:lEmail and"
                + " l.academicSession=:acaSession and l.course.courseCode=:code");
        q.setParameter("lEmail", lecturerEmail);
        q.setParameter("acaSession", academicSession);
        q.setParameter("code", courseCode);
        return (LecturerStudents) super.findEntity(q);
    }

    public List<LecturerStudents> findLecturerStudents(long studentId, AcademicSession academicSession, String courseCode) throws Exception {
        Query q = super.getEntityManager().createQuery("select l from LecturerStudents l where l.academicSession=:acaSession and l.course.courseCode=:code and :studId in (select s.id from LecturerStudents ls join ls.students s )");
        q.setParameter("studId", studentId);
        q.setParameter("acaSession", academicSession);
        q.setParameter("code", courseCode);
        return (List<LecturerStudents>) super.findAll(q);
    }

    public List<LecturerStudents> findLecturerStudents(AcademicSession academicSession, String courseCode) throws Exception {
         Query q = super.getEntityManager().createQuery("select l from LecturerStudents l where l.academicSession=:acaSession and l.course.courseCode=:code");
      q.setParameter("acaSession", academicSession);
        q.setParameter("code", courseCode);
        return (List<LecturerStudents>) super.findAll(q);
    }
    
    public List<LecturerStudents> findLecturerStudents(String lecturerEmail) throws Exception {
        Query q = super.getEntityManager().createQuery("select l from LecturerStudents l where l.lecturer.email =:lEmail ");
        q.setParameter("lEmail", lecturerEmail);
        return (List<LecturerStudents>) super.findAll(q);
    }

    public int getLecturerStudentsCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from LecturerStudents as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
