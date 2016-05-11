/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Department;
import com.primus.data.Course;
import com.primus.data.DepartmentName;
import com.primus.data.Lecturer;
import java.util.ArrayList;
import java.util.List;
import com.primus.data.LecturerStudents;
import com.primus.data.Student;
import com.primus.enums.Semester;
import com.primus.interfaces.LecturerService;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Component
@Transactional
public class LecturerServiceBean
        extends PrimusBasePersistenceService
        implements LecturerService {

    @Autowired
    LecturerStudentsServiceBean lecturerStudentsServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;

    @Override
    public void create(Lecturer lecturer) throws Exception {
        if (lecturer.getCourses() == null) {
            lecturer.setCourses(new ArrayList<Course>());
        }
        if (lecturer.getLecturerStudents() == null) {
            lecturer.setLecturerStudents(new ArrayList<LecturerStudents>());
        }
        Lecturer Flecturer = findLecturer(lecturer.getEmail()); ///check if Lecturer already exists with the same eMail add.
        if (Flecturer == null) {
            EntityManager em = super.getEntityManager();
            Department department = lecturer.getDepartment();
            if (department != null) {
                department = em.getReference(department.getClass(), department.getId());
                lecturer.setDepartment(department);
            }
            List<Course> attachedCourses = new ArrayList<Course>();
            for (Course coursesCourseToAttach : lecturer.getCourses()) {
                coursesCourseToAttach = em.getReference(coursesCourseToAttach.getClass(), coursesCourseToAttach.getId());
                attachedCourses.add(coursesCourseToAttach);
            }
            lecturer.setCourses(attachedCourses);
            List<LecturerStudents> attachedLecturerStudents = new ArrayList<LecturerStudents>();
            for (LecturerStudents lecturerStudentsLecturerStudentsToAttach : lecturer.getLecturerStudents()) {
                lecturerStudentsLecturerStudentsToAttach = em.getReference(lecturerStudentsLecturerStudentsToAttach.getClass(), lecturerStudentsLecturerStudentsToAttach.getId());
                attachedLecturerStudents.add(lecturerStudentsLecturerStudentsToAttach);
            }
            lecturer.setLecturerStudents(attachedLecturerStudents);
            em.persist(lecturer);
            if (department != null) {
                department.getLecturers().add(lecturer);
                department = em.merge(department);
            }
            for (Course coursesCourse : lecturer.getCourses()) {
                coursesCourse.getTutors().add(lecturer);
                coursesCourse = em.merge(coursesCourse);
            }
            for (LecturerStudents lecturerStudentsLecturerStudents : lecturer.getLecturerStudents()) {
                Lecturer oldLecturerOfLecturerStudentsLecturerStudents = lecturerStudentsLecturerStudents.getLecturer();
                lecturerStudentsLecturerStudents.setLecturer(lecturer);
                lecturerStudentsLecturerStudents = em.merge(lecturerStudentsLecturerStudents);
                if (oldLecturerOfLecturerStudentsLecturerStudents != null) {
                    oldLecturerOfLecturerStudentsLecturerStudents.getLecturerStudents().remove(lecturerStudentsLecturerStudents);
                    oldLecturerOfLecturerStudentsLecturerStudents = em.merge(oldLecturerOfLecturerStudentsLecturerStudents);
                }
            }
        } else {
            throw new NonexistentEntityException("The Lecturer with email address "
                    + "" + lecturer.getEmail() + " already exists.");
        }
    }

    @Override
    public void edit(Lecturer lecturer) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        Lecturer persistentLecturer = em.find(Lecturer.class, lecturer.getId());
        Department departmentOld = persistentLecturer.getDepartment();
        Department departmentNew = lecturer.getDepartment();
        List<Course> coursesOld = persistentLecturer.getCourses();
        List<Course> coursesNew = lecturer.getCourses();
        List<LecturerStudents> lecturerStudentsOld = persistentLecturer.getLecturerStudents();
        List<LecturerStudents> lecturerStudentsNew = lecturer.getLecturerStudents();
        if (departmentNew != null) {
            departmentNew = em.getReference(departmentNew.getClass(), departmentNew.getId());
            lecturer.setDepartment(departmentNew);
        }
        List<Course> attachedCoursesNew = new ArrayList<Course>();
        for (Course coursesNewCourseToAttach : coursesNew) {
            coursesNewCourseToAttach = em.getReference(coursesNewCourseToAttach.getClass(), coursesNewCourseToAttach.getId());
            attachedCoursesNew.add(coursesNewCourseToAttach);
        }
        coursesNew = attachedCoursesNew;
        lecturer.setCourses(coursesNew);
        List<LecturerStudents> attachedLecturerStudentsNew = new ArrayList<>();
        for (LecturerStudents lecturerStudentsNewLecturerStudentsToAttach : lecturerStudentsNew) {
            lecturerStudentsNewLecturerStudentsToAttach = em.getReference(lecturerStudentsNewLecturerStudentsToAttach.getClass(), lecturerStudentsNewLecturerStudentsToAttach.getId());
            attachedLecturerStudentsNew.add(lecturerStudentsNewLecturerStudentsToAttach);
        }
        lecturerStudentsNew = attachedLecturerStudentsNew;
        lecturer.setLecturerStudents(lecturerStudentsNew);
        lecturer = em.merge(lecturer);
        if (departmentOld != null && !departmentOld.equals(departmentNew)) {
            departmentOld.getLecturers().remove(lecturer);
            departmentOld = em.merge(departmentOld);
        }
        if (departmentNew != null && !departmentNew.equals(departmentOld)) {
            departmentNew.getLecturers().add(lecturer);
            departmentNew = em.merge(departmentNew);
        }
        for (Course coursesOldCourse : coursesOld) {
            if (!coursesNew.contains(coursesOldCourse)) {
                coursesOldCourse.getTutors().remove(lecturer);
                coursesOldCourse = em.merge(coursesOldCourse);
            }
        }
        for (Course coursesNewCourse : coursesNew) {
            if (!coursesOld.contains(coursesNewCourse)) {
                coursesNewCourse.getTutors().add(lecturer);
                coursesNewCourse = em.merge(coursesNewCourse);
            }
        }
        for (LecturerStudents lecturerStudentsOldLecturerStudents : lecturerStudentsOld) {
            if (!lecturerStudentsNew.contains(lecturerStudentsOldLecturerStudents)) {
                lecturerStudentsOldLecturerStudents.setLecturer(null);
                lecturerStudentsOldLecturerStudents = em.merge(lecturerStudentsOldLecturerStudents);
            }
        }
        for (LecturerStudents lecturerStudentsNewLecturerStudents : lecturerStudentsNew) {
            if (!lecturerStudentsOld.contains(lecturerStudentsNewLecturerStudents)) {
                Lecturer oldLecturerOfLecturerStudentsNewLecturerStudents = lecturerStudentsNewLecturerStudents.getLecturer();
                lecturerStudentsNewLecturerStudents.setLecturer(lecturer);
                lecturerStudentsNewLecturerStudents = em.merge(lecturerStudentsNewLecturerStudents);
                if (oldLecturerOfLecturerStudentsNewLecturerStudents != null && !oldLecturerOfLecturerStudentsNewLecturerStudents.equals(lecturer)) {
                    oldLecturerOfLecturerStudentsNewLecturerStudents.getLecturerStudents().remove(lecturerStudentsNewLecturerStudents);
                    oldLecturerOfLecturerStudentsNewLecturerStudents = em.merge(oldLecturerOfLecturerStudentsNewLecturerStudents);
                }
            }
        }

        Long id = lecturer.getId();
        if (findLecturer(id) == null) {
            throw new NonexistentEntityException("The lecturer with id " + id + " no longer exists.");
        }


    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        Lecturer lecturer;
        try {
            lecturer = em.getReference(Lecturer.class, id);
            lecturer.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The lecturer with id " + id + " no longer exists.", enfe);
        }
        Department department = lecturer.getDepartment();
        if (department != null) {
            department.getLecturers().remove(lecturer);
            department = em.merge(department);
        }
        List<Course> courses = lecturer.getCourses();
        for (Course coursesCourse : courses) {
            coursesCourse.getTutors().remove(lecturer);
            coursesCourse = em.merge(coursesCourse);
        }
        List<LecturerStudents> lecturerStudents = lecturer.getLecturerStudents();
        for (LecturerStudents lecturerStudentsLecturerStudents : lecturerStudents) {
            lecturerStudentsLecturerStudents.setLecturer(null);
            lecturerStudentsLecturerStudents = em.merge(lecturerStudentsLecturerStudents);
        }
        em.remove(lecturer);

    }

    @Override
    public List<Lecturer> findLecturerEntities() {
        return findLecturerEntities(true, -1, -1);
    }

    @Override
    public List<Lecturer> findLecturerEntities(int maxResults, int firstResult) {
        return findLecturerEntities(false, maxResults, firstResult);
    }

    private List<Lecturer> findLecturerEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from Lecturer as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Lecturer findLecturer(Long id) throws Exception {
        return (Lecturer) super.findByPrimaryKey(id, Lecturer.class);
    }
    @Override
    public Lecturer findLecturerByFingerPrintId(Long fingerPrintID,DepartmentName departmentName) throws Exception{
        Query query = super.getEntityManager().createQuery("select L from Lecturer L where L.fingerPrintId = :fid and l.department.departmentName=:deptName");
        query.setParameter("fid", fingerPrintID);
         query.setParameter("deptName", departmentName);
        return (Lecturer) super.findEntity(query);
    }


    @Override
    public Lecturer findLecturer(String email) throws Exception {
        Query query = super.getEntityManager().createQuery("select L from Lecturer L where L.email = :lEmail ");
        query.setParameter("lEmail", email);
        return (Lecturer) super.findEntity(query);
    }

    @Override
    public Lecturer findLecturerLogin(String userId, int password) throws Exception {
        Query query = super.getEntityManager().createQuery("select L from Lecturer L where L.email = :lEmail and L.password= :lPassword");
        query.setParameter("lEmail", userId);
        query.setParameter("lPassword", password);
        return (Lecturer) super.findEntity(query);
    }
    
    @Override
    public int getLecturerCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from Lecturer as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Lecturer> wildSearch(String searchString) throws Exception {
        Query query = super.getEntityManager().createQuery("select l from Lecturer l where l.email like :cString or l.lecturerName.firstName like :cString or l.lecturerName.middleName like :cString or l.lecturerName.surname like :cString or l.phoneNumber like :cString or l.department.departmentName.name like :cString or l.department.faculty.facultyName.name like :cString ");
        query.setParameter("cString", searchString);
        return (List<Lecturer>) super.findAll(query);
    }

    @Override
    public List<Lecturer> findLecturers(String DepartmentName) throws Exception {
        Query query = super.getEntityManager().createQuery("select l from Lecturer l where l.department.departmentName.name=:cString");
        query.setParameter("cString", DepartmentName);
        return (List<Lecturer>) super.findAll(query);
    }

    @Override
    public void deactivateLecturer() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> getRegisteredStudentsForCourse(long lecturerId, Course course, AcademicSession academicSession) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from LecturerStudents ls join ls.students s where ls.lecturer.id=:lecId and ls.academicSession=:acaSess and ls.course=:cos");
        q.setParameter("lecId", lecturerId);
        q.setParameter("cos", course);
        q.setParameter("acaSess", academicSession);
        return (List<Student>) super.findAll(q);
    }
    
    @Override
     public List<Student> getRegisteredStudentsForCourseByDept(long lecturerId, Course course, AcademicSession academicSession,DepartmentName departmentName) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from LecturerStudents ls join ls.students s where ls.lecturer.id=:lecId and ls.academicSession=:acaSess and ls.course=:cos and s.department.departmentName=:deptname");
        q.setParameter("lecId", lecturerId);
        q.setParameter("cos", course);
        q.setParameter("acaSess", academicSession);
        q.setParameter("deptname", departmentName);
        return (List<Student>) super.findAll(q);
    }

    @Override
    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSess) throws Exception {
        AcademicSession academicSession;
        if (academicSess == null) {
            academicSession = academicSessionServiceBean.getCurrentAcademicSession();
        } else {
            academicSession = academicSess;
        }
        Query q = super.getEntityManager().createQuery("select LS.course from LecturerStudents LS where LS.academicSession =:Aca and LS.lecturer.id =:Lec");
        q.setParameter("Aca", academicSession);
        q.setParameter("Lec", lecturerId);
        return (List<Course>) super.findAll(q);
    }

    @Override
    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSess, Semester semester) throws Exception {
        AcademicSession academicSession;
        if (academicSess == null) {
            academicSession = academicSessionServiceBean.getCurrentAcademicSession();
        } else {
            academicSession = academicSess;
        }
        Query q = super.getEntityManager().createQuery("select LS.course from LecturerStudents LS where LS.academicSession =:Aca and LS.lecturer.id =:Lec  and LS.semster=:sem ");
        q.setParameter("Aca", academicSession);
        q.setParameter("Lec", lecturerId);
        q.setParameter("sem", semester);
        return (List<Course>) super.findAll(q);
    }
}
