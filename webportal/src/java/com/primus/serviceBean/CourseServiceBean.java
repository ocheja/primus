/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.DegreeRequirement;
import com.primus.data.DepartmentName;
import com.primus.data.Lecturer;
import java.util.ArrayList;
import java.util.List;
import com.primus.data.LecturerStudents;
import com.primus.data.Prerequisite;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerService;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import com.primus.util.WildSearchStringUtil;
import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class CourseServiceBean
        extends PrimusBasePersistenceService
        implements CourseService {

    @Autowired
    PrerequisiteServiceBean prerequisiteServiceBean;
    @Autowired
    LecturerStudentsServiceBean lecturerStudentsServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;

    @Override
    public void create(Course course) throws RollbackFailureException, Exception {
        if (course.getTutors() == null) {
            course.setTutors(new ArrayList<Lecturer>());
        }
        if (course.getLecturerStudents() == null) {
            course.setLecturerStudents(new ArrayList<LecturerStudents>());
        }
        Course c = findCourse(course.getCourseCode());
//        System.out.println(" the found course is " +c);
        if (c == null) {
            EntityManager em = super.getEntityManager();
            DegreeRequirement degreeRequirement = course.getDegreeRequirement();
            if (degreeRequirement != null) {
                degreeRequirement = em.getReference(degreeRequirement.getClass(), degreeRequirement.getId());
                course.setDegreeRequirement(degreeRequirement);
            }
            List<Lecturer> attachedTutors = new ArrayList<Lecturer>();
            for (Lecturer tutorsLecturerToAttach : course.getTutors()) {
                tutorsLecturerToAttach = em.getReference(tutorsLecturerToAttach.getClass(), tutorsLecturerToAttach.getId());
                attachedTutors.add(tutorsLecturerToAttach);
            }
            course.setTutors(attachedTutors);
            List<LecturerStudents> attachedLecturerStudents = new ArrayList<LecturerStudents>();
            for (LecturerStudents lecturerStudentsLecturerStudentsToAttach : course.getLecturerStudents()) {
                lecturerStudentsLecturerStudentsToAttach = em.getReference(lecturerStudentsLecturerStudentsToAttach.getClass(), lecturerStudentsLecturerStudentsToAttach.getId());
                attachedLecturerStudents.add(lecturerStudentsLecturerStudentsToAttach);
            }
            course.setLecturerStudents(attachedLecturerStudents);
            em.persist(course);
            if (degreeRequirement != null) {
                degreeRequirement.getCoursesForLevel().add(course);
                degreeRequirement = em.merge(degreeRequirement);
            }
            for (Lecturer tutorsLecturer : course.getTutors()) {
                tutorsLecturer.getCourses().add(course);
                tutorsLecturer = em.merge(tutorsLecturer);
            }
            for (LecturerStudents lecturerStudentsLecturerStudents : course.getLecturerStudents()) {
                Course oldCourseOfLecturerStudentsLecturerStudents = lecturerStudentsLecturerStudents.getCourse();
                lecturerStudentsLecturerStudents.setCourse(course);
                lecturerStudentsLecturerStudents = em.merge(lecturerStudentsLecturerStudents);
                if (oldCourseOfLecturerStudentsLecturerStudents != null) {
                    oldCourseOfLecturerStudentsLecturerStudents.getLecturerStudents().remove(lecturerStudentsLecturerStudents);
                    oldCourseOfLecturerStudentsLecturerStudents = em.merge(oldCourseOfLecturerStudentsLecturerStudents);
                }
            }
        } else {
            throw new Exception("Course with code : " + course.getCourseCode() + " Already exists");
        }
    }

    @Override
    public void edit(Course course) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        Course persistentCourse = em.find(Course.class, course.getId());
        DegreeRequirement degreeRequirementOld = persistentCourse.getDegreeRequirement();
        DegreeRequirement degreeRequirementNew = course.getDegreeRequirement();
        List<Lecturer> tutorsOld = persistentCourse.getTutors();
        List<Lecturer> tutorsNew = course.getTutors();
        List<LecturerStudents> lecturerStudentsOld = persistentCourse.getLecturerStudents();
        List<LecturerStudents> lecturerStudentsNew = course.getLecturerStudents();
        if (degreeRequirementNew != null) {
            degreeRequirementNew = em.getReference(degreeRequirementNew.getClass(), degreeRequirementNew.getId());
            course.setDegreeRequirement(degreeRequirementNew);
        }
        List<Lecturer> attachedTutorsNew = new ArrayList<Lecturer>();
        for (Lecturer tutorsNewLecturerToAttach : tutorsNew) {
            tutorsNewLecturerToAttach = em.getReference(tutorsNewLecturerToAttach.getClass(), tutorsNewLecturerToAttach.getId());
            attachedTutorsNew.add(tutorsNewLecturerToAttach);
        }
        tutorsNew = attachedTutorsNew;
        course.setTutors(tutorsNew);
        List<LecturerStudents> attachedLecturerStudentsNew = new ArrayList<LecturerStudents>();
        for (LecturerStudents lecturerStudentsNewLecturerStudentsToAttach : lecturerStudentsNew) {
            lecturerStudentsNewLecturerStudentsToAttach = em.getReference(lecturerStudentsNewLecturerStudentsToAttach.getClass(), lecturerStudentsNewLecturerStudentsToAttach.getId());
            attachedLecturerStudentsNew.add(lecturerStudentsNewLecturerStudentsToAttach);
        }
        lecturerStudentsNew = attachedLecturerStudentsNew;
        course.setLecturerStudents(lecturerStudentsNew);
        course = em.merge(course);
        if (degreeRequirementOld != null && !degreeRequirementOld.equals(degreeRequirementNew)) {
            degreeRequirementOld.getCoursesForLevel().remove(course);
            degreeRequirementOld = em.merge(degreeRequirementOld);
        }
        if (degreeRequirementNew != null && !degreeRequirementNew.equals(degreeRequirementOld)) {
            degreeRequirementNew.getCoursesForLevel().add(course);
            degreeRequirementNew = em.merge(degreeRequirementNew);
        }
        for (Lecturer tutorsOldLecturer : tutorsOld) {
            if (!tutorsNew.contains(tutorsOldLecturer)) {
                tutorsOldLecturer.getCourses().remove(course);
                tutorsOldLecturer = em.merge(tutorsOldLecturer);
            }
        }
        for (Lecturer tutorsNewLecturer : tutorsNew) {
            if (!tutorsOld.contains(tutorsNewLecturer)) {
                tutorsNewLecturer.getCourses().add(course);
                tutorsNewLecturer = em.merge(tutorsNewLecturer);
            }
        }
        for (LecturerStudents lecturerStudentsOldLecturerStudents : lecturerStudentsOld) {
            if (!lecturerStudentsNew.contains(lecturerStudentsOldLecturerStudents)) {
                lecturerStudentsOldLecturerStudents.setCourse(null);
                lecturerStudentsOldLecturerStudents = em.merge(lecturerStudentsOldLecturerStudents);
            }
        }
        for (LecturerStudents lecturerStudentsNewLecturerStudents : lecturerStudentsNew) {
            if (!lecturerStudentsOld.contains(lecturerStudentsNewLecturerStudents)) {
                Course oldCourseOfLecturerStudentsNewLecturerStudents = lecturerStudentsNewLecturerStudents.getCourse();
                lecturerStudentsNewLecturerStudents.setCourse(course);
                lecturerStudentsNewLecturerStudents = em.merge(lecturerStudentsNewLecturerStudents);
                if (oldCourseOfLecturerStudentsNewLecturerStudents != null && !oldCourseOfLecturerStudentsNewLecturerStudents.equals(course)) {
                    oldCourseOfLecturerStudentsNewLecturerStudents.getLecturerStudents().remove(lecturerStudentsNewLecturerStudents);
                    oldCourseOfLecturerStudentsNewLecturerStudents = em.merge(oldCourseOfLecturerStudentsNewLecturerStudents);
                }
            }
        }

        Long id = course.getId();
        if (findCourse(id) == null) {
            throw new NonexistentEntityException("The course with id " + id + " no longer exists.");
        }

    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        Course course;
        try {
            course = em.getReference(Course.class, id);
            course.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The course with id " + id + " no longer exists.", enfe);
        }
        DegreeRequirement degreeRequirement = course.getDegreeRequirement();
        if (degreeRequirement != null) {
            degreeRequirement.getCoursesForLevel().remove(course);
            degreeRequirement = em.merge(degreeRequirement);
        }
        List<Lecturer> tutors = course.getTutors();
        for (Lecturer tutorsLecturer : tutors) {
            tutorsLecturer.getCourses().remove(course);
            tutorsLecturer = em.merge(tutorsLecturer);
        }
        List<LecturerStudents> lecturerStudents = course.getLecturerStudents();
        for (LecturerStudents lecturerStudentsLecturerStudents : lecturerStudents) {
            lecturerStudentsLecturerStudents.setCourse(null);
            lecturerStudentsLecturerStudents = em.merge(lecturerStudentsLecturerStudents);
        }
        List<Prerequisite> prerequisites = prerequisiteServiceBean.findPrerequisites(id);
        System.out.println("th found courses : " + prerequisites);
        for (Prerequisite prerequisite : prerequisites) {
            prerequisiteServiceBean.destroy(prerequisite.getId());
        }
        em.remove(course);

    }

    @Override
    public List<Course> findCourseEntities() {
        return findCourseEntities(true, -1, -1);
    }

    public List<Course> findCourseEntities(int maxResults, int firstResult) {
        return findCourseEntities(false, maxResults, firstResult);
    }

    private List<Course> findCourseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Course as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Course findCourse(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Course findCourse(String courseCode) throws Exception {
        Query query = super.getEntityManager().createQuery("select C from Course C where C.courseCode = :courseC");
        query.setParameter("courseC", courseCode.trim());
        return (Course) super.findEntity(query);
    }

    @Override
    public int getCourseCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Course as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void develop(Course course) throws Exception {
        if (findCourse(course.getCourseCode()) == null) {
            create(course); ///check if student already exists
        } else {
            edit(course);
        }
    }

    @Override
    public List<Course> getCoursesForLevel(DepartmentName departmentName, AcademicLevel level, Semester sems) throws Exception {
        Query query = super.getEntityManager().createQuery("select c from Course c where c.departmentName.name = :deptN"
                + " and c.courseLevel =:clevel and c.semester=: cSem");
        query.setParameter("deptN", departmentName.getName());
        query.setParameter("clevel", level);
        query.setParameter("cSem", sems);
        return (List<Course>) super.findAll(query);
    }

    @Override
    public List<Course> getAllCourses(DepartmentName departmentName) throws Exception {
        Query query = super.getEntityManager().createQuery("select c from Course c where c.departmentName.name = :deptN");
        query.setParameter("deptN", departmentName.getName());
        return (List<Course>) super.findAll(query);
    }

    @Override
    public List<Course> getCoursesForSemester(DepartmentName departmentName, Semester sems) throws Exception {
        Query query = super.getEntityManager().createQuery("select c from Course c where c.departmentName.name = :deptN"
                + " and c.semester=: cSem");
        query.setParameter("deptN", departmentName.getName());
        query.setParameter("cSem", sems);
        return (List<Course>) super.findAll(query);
    }

    @Override
    public List<Student> getStudentsOfferingCourse(Course course, AcademicSession academicSession,Long LecturerId) throws Exception {
        Query query = super.getEntityManager().createQuery("select s from LecturerStudents LS join LS.students s where LS.course.courseCode = :cCode and LS.academicSession=:acaSession and LS.lecturer.id=:lecId");
        query.setParameter("cCode", course.getCourseCode());
        query.setParameter("acaSession", academicSession);
         query.setParameter("lecId", LecturerId);
        return (List<Student>) super.findAll(query);
    }
    @Override
     public List<Student> getStudentsOfferingCourseByDept(Course course, AcademicSession academicSession,Long LecturerId, DepartmentName departmentName) throws Exception {
       Query query = super.getEntityManager().createQuery("select s from LecturerStudents LS join LS.students s where LS.course.courseCode = :cCode and LS.academicSession=:acaSession and LS.lecturer.id=:lecId and s.department.departmentName=:deptname");
       query.setParameter("cCode", course.getCourseCode());
        query.setParameter("acaSession", academicSession);
         query.setParameter("lecId", LecturerId);
        query.setParameter("deptname", departmentName);
        return (List<Student>) super.findAll( query);
    }

    @Override
    public void setPrerequisites(Course course, List<Course> prerequisites) throws Exception {
        prerequisiteServiceBean.create(course, prerequisites);
    }

    @Override
    public List<Course> getPrerequisites(Long courseId) throws Exception {
        return prerequisiteServiceBean.findPrerequisiteCourses(findCourse(courseId));
    }

    @Override
    public void removePrerequisites(Course course, List<Long> prerequisitesID) throws Exception {
        List<Course> prerequisites = new ArrayList();
        for (Long coursID : prerequisitesID) {
            prerequisites.add(findCourse(coursID));
        }
        prerequisiteServiceBean.removePrerequisite(course, prerequisites);
    }

    @Override
    public List<Course> wildSearch(String searchString) throws Exception {
        Query query = super.getEntityManager().createQuery("select c from Course c where c.courseCode like :cString or c.departmentName.name like :cString or c.courseTitle like :cString or c.courseLevel like :cLevel");
        query.setParameter("cString", searchString);
        query.setParameter("cLevel", (AcademicLevel) WildSearchStringUtil.checkEnum(AcademicLevel.class, searchString));
//       query.setParameter("cInt", (Integer)WildSearchStringUtil.checkInteger(searchString));
//       query.setParameter("sem", (Semester)WildSearchStringUtil.checkEnum(Semester.class, searchString));
        return (List<Course>) super.findAll(query);
    }

    @Override
    public void assignCourseToLecturer(List<Course> courses, Lecturer lecturer) throws Exception {
        int n = 0;
        AcademicSession academicSession = academicSessionServiceBean.getCurrentAcademicSession();
        for (Course course : courses) {
            LecturerStudents LecturerStudents = lecturerStudentsServiceBean.findLecturerStudents(lecturer.getEmail(), academicSession, course.getCourseCode());
            if (LecturerStudents == null) { //check if lecturer has been assigned to course for the academicSession
                LecturerStudents = new LecturerStudents();
                LecturerStudents.setLecturer(lecturer);
                LecturerStudents.setCourse(course);
                LecturerStudents.setAcademicSession(academicSession);
                LecturerStudents.setSemster(course.getSemester());
                lecturerStudentsServiceBean.create(LecturerStudents);
                List<LecturerStudents> LS = lecturer.getLecturerStudents();
                LecturerStudents lecturerStudents = lecturerStudentsServiceBean.findLecturerStudents(lecturer.getEmail(), academicSession, course.getCourseCode());
                LS.add(lecturerStudents);
                lecturer.setLecturerStudents(LS);
                List<Course> Courses = lecturer.getCourses();
                if (!Courses.contains(course)) {
                    System.out.println(" this is the " + n);
                    Courses.add(course);
                    lecturer.setCourses(Courses);
                    System.out.println(" this is the " + n);
                }
                lecturerServiceBean.edit(lecturer);
            } else {
                throw new Exception("The lecturer " + lecturer.getLecturerName().getSurname() + " "
                        + lecturer.getLecturerName().getFirstName() + " has already been assigned to the course : "
                        + course.getCourseTitle() + " for the academic Session : " + academicSession.getStartYear() + "/"
                        + academicSession.getEndYear());
            }

//            List<Lecturer> tutors = course.getTutors();
//            if (!tutors.contains(lecturer)) {
//                System.out.println(" this is the " + n);
//                tutors.add(lecturer);
//                course.setTutors(tutors);
//                List<LecturerStudents> LS = course.getLecturerStudents();
//                LS.add(lecturerStudentsServiceBean.findLecturerStudents(lecturer.getEmail(), academicSession, course.getCourseCode()));
//                course.setLecturerStudents(LS);
//                this.edit(course);
//                System.out.println(" this is the " + n);
//            }
            n++;
        }
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
    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSess,Semester semester) throws Exception {
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

    @Override
    public void removeCourseFromLecturer(List<Course> courses, Lecturer lecturer) throws Exception {
        AcademicSession academicSession = academicSessionServiceBean.getCurrentAcademicSession();

        for (Course course : courses) {
            LecturerStudents LecturerStudents = lecturerStudentsServiceBean.findLecturerStudents(lecturer.getEmail(), academicSession, course.getCourseCode());
            if (course.getId() != null) {
                List<Lecturer> tutors = course.getTutors();
                if (tutors.contains(lecturer)) {
                    tutors.remove(lecturer);
                    course.setTutors(tutors);
                    edit(course);
                }
            }
            if (LecturerStudents != null) { //check if lecturer has been assigned to course for the academicSession
                lecturerStudentsServiceBean.destroy(LecturerStudents.getId());
            } else {
                throw new Exception("The lecturer " + lecturer.getLecturerName().getSurname() + " "
                        + lecturer.getLecturerName().getFirstName() + " was not assigned to the course : "
                        + course.getCourseTitle() + " for the academic Session : " + academicSession.getStartYear() + "/"
                        + academicSession.getEndYear());
            }

        }
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> findByDepartment(DepartmentName departmentName) throws Exception {
        Query q = super.getEntityManager().createQuery("select c from Course c where c.departmentName =:deptName");
        q.setParameter("deptName", departmentName);
        return (List<Course>) super.findAll(q);
    }
}
