/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.LecturerStudents;
import java.io.Serializable;
import javax.persistence.Query;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import com.primus.enums.Status;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class CourseFormServiceBean extends PrimusBasePersistenceService {

    @Autowired
    LecturerStudentsServiceBean lecturerStudentsServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;

    public void create(CourseForm courseForm) throws Exception {
       if(findCourseForm(courseForm.getStudent().getId(), courseForm.getAcademicSession(), courseForm.getSemester())!= null) {
           throw new Exception("Course Form already exits....");
       }
        EntityManager em = super.getEntityManager();
        Student student = courseForm.getStudent();
        if (student != null) {
            student = em.getReference(student.getClass(), student.getId());
            courseForm.setStudent(student);
        }
        em.persist(courseForm);
        if (student != null) {
            student.getCourseforms().add(courseForm);
            student = em.merge(student);
        }

    }

    public void edit(CourseForm courseForm) throws NonexistentEntityException, RollbackFailureException, Exception {

        if (courseForm.getAcademicAdviserDateStamp() != null || courseForm.getFacultyOfficerDateStamp() != null || courseForm.getHeadOfDepartmentDateStamp() != null) {
            courseForm.setStatus(Status.IN_PROGRESS);
        }
        if (courseForm.getAcademicAdviserDateStamp() != null && courseForm.getFacultyOfficerDateStamp() != null && courseForm.getHeadOfDepartmentDateStamp() != null) {
            courseForm.setStatus(Status.APPROVED);
            assignStudentToCourse(courseForm.getCourses(), courseForm.getStudent());
        }
        EntityManager em = super.getEntityManager();
        CourseForm persistentCourseForm = em.find(CourseForm.class, courseForm.getId());
        Student studentOld = persistentCourseForm.getStudent();
        Student studentNew = courseForm.getStudent();
        
         List<Course> coursesOld = persistentCourseForm.getCourses();
        List<Course> coursesNew = courseForm.getCourses();
        List<Course> attachedCourseNew = new ArrayList<>();
        
        for(Course courseOld:coursesOld){
            if(!coursesNew.contains(courseOld)){
                removeStudentFromCourse(courseOld,courseForm.getStudent());
            }
        }
        
        for (Course coursesNewCourseToAttach : coursesNew) {
            coursesNewCourseToAttach = em.getReference(coursesNewCourseToAttach.getClass(), coursesNewCourseToAttach.getId());
           attachedCourseNew.add(coursesNewCourseToAttach);
        }
        coursesNew = attachedCourseNew;
        courseForm.setCourses(coursesNew);
        courseForm = em.merge(courseForm);
         
        if (studentNew != null) {
            studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
            courseForm.setStudent(studentNew);
        }
        courseForm = em.merge(courseForm);
        if (studentOld != null && !studentOld.equals(studentNew)) {
            studentOld.getCourseforms().remove(courseForm);
            studentOld = em.merge(studentOld);
        }
        if (studentNew != null && !studentNew.equals(studentOld)) {
            studentNew.getCourseforms().add(courseForm);
            studentNew = em.merge(studentNew);
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        CourseForm courseForm = em.getReference(CourseForm.class, id);
        courseForm.getId();

        Student student = courseForm.getStudent();
        if (student != null) {
            student.getCourseforms().remove(courseForm);
            student = em.merge(student);
        }
        em.remove(courseForm);
    }

    public List<CourseForm> findCourseFormEntities() {
        return findCourseFormEntities(true, -1, -1);
    }

    public List<CourseForm> findCourseFormEntities(int maxResults, int firstResult) {
        return findCourseFormEntities(false, maxResults, firstResult);
    }

    private List<CourseForm> findCourseFormEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CourseForm.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public CourseForm findCourseForm(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CourseForm.class, id);
        } finally {
            em.close();
        }
    }

    public CourseForm findCourseForm(Long studentId, AcademicSession academicSession, Semester semester) {
        Query q = super.getEntityManager().createQuery("SELECT cf FROM CourseForm cf WHERE cf.academicSession=:aca AND cf.semester=:sem AND cf.student.id=:studId");
        q.setParameter("aca", academicSession);
        q.setParameter("sem", semester);
        q.setParameter("studId", studentId);
        return (CourseForm) super.findEntity(q);
    }
      public CourseForm getCourseForm(Long studentId, AcademicLevel academicLevel, Semester semester) {
        Query q = super.getEntityManager().createQuery("SELECT cf FROM CourseForm cf WHERE cf.student.CurrentAcademicLevel=:aca AND cf.semester=:sem AND cf.student.id=:studId");
        q.setParameter("aca", academicLevel);
        q.setParameter("sem", semester);
        q.setParameter("studId", studentId);
        return (CourseForm) super.findEntity(q);
    }

    public int getCourseFormCount() {
        EntityManager em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<CourseForm> rt = cq.from(CourseForm.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void assignStudentToCourse(List<Course> courses, Student student) throws Exception {
     int count=0;
        AcademicSession academicSession = academicSessionServiceBean.getCurrentAcademicSession();
//        for (Course course : courses) {
//            List<LecturerStudents> lecturerStudents = lecturerStudentsServiceBean.findLecturerStudents(student.getId(), academicSession, course.getCourseCode());
//            if (lecturerStudents == null || lecturerStudents.isEmpty()) { //check if student has been assigned to course for the academicSession
//                count++;
//                System.out.println("count is = "+count);
//                List<LecturerStudents> lecturerStuds = lecturerStudentsServiceBean.findLecturerStudents(academicSession, course.getCourseCode());
//                    System.out.println("number of  lecturerStuds found for course ="+course.getCourseCode()+ "and size= "+ lecturerStuds.size());
//                for (LecturerStudents lecStud : lecturerStuds) {
//                    List<Student> studs = lecStud.getStudents();
//                    studs.add(student);
//                    lecStud.setStudents(studs);
//                    lecturerStudentsServiceBean.edit(lecStud);
//                }
//            } else {
//                throw new Exception("The Student " + student.getStudentName().getSurname() + " "
//                        + student.getStudentName().getFirstName() + " has already been assigned to the course : "
//                        + course.getCourseTitle() + " for the academic Session : " + academicSession.getStartYear() + "/"
//                        + academicSession.getEndYear());
//            }
//        }
        for (Course course : courses) {
             List<LecturerStudents> lecturerStuds = lecturerStudentsServiceBean.findLecturerStudents(academicSession, course.getCourseCode());
        for (LecturerStudents lecStud : lecturerStuds) {
                    List<Student> studs = lecStud.getStudents();
                    if( lecStud.getStudents().contains(student)){//check if student has been assigned to course for the academicSession              
                          throw new Exception("The Student " + student.getStudentName().getSurname() + " "
                       + student.getStudentName().getFirstName() + " has already been assigned to the course : "
                        + course.getCourseTitle() + " for the academic Session : " + academicSession.getStartYear() + "/"
                        + academicSession.getEndYear());
                    }
                   studs.add(student);
                    lecStud.setStudents(studs);
                   lecturerStudentsServiceBean.edit(lecStud);
                }      
        }
        
    }

    public void removeStudentFromCourse(Course course, Student student) throws Exception {
        AcademicSession academicSession = academicSessionServiceBean.getCurrentAcademicSession();

        List< LecturerStudents> lecturerStudents = lecturerStudentsServiceBean.findLecturerStudents(student.getId(), academicSession, course.getCourseCode());
        if (lecturerStudents != null) { //check if student has been assigned to course for the academicSession
            for (LecturerStudents lecStud : lecturerStudents) {
                List<Student> studs = lecStud.getStudents();
                studs.remove(student);
                lecStud.setStudents(studs);
                lecturerStudentsServiceBean.edit(lecStud);
            }
        } else {
            throw new Exception("The Student " + student.getStudentName().getSurname() + " "
                    + student.getStudentName().getFirstName() + " was not assigned to the course : "
                    + course.getCourseTitle() + " for the academic Session : " + academicSession.getStartYear() + "/"
                    + academicSession.getEndYear());
        }
    }
}
