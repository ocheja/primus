/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSessionLevel;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Department;
import com.primus.data.Room;
import com.primus.data.CourseForm;
import com.primus.data.Degree;
import java.util.HashSet;
import java.util.Set;
import com.primus.data.LecturerStudents;
import java.util.ArrayList;
import java.util.List;
import com.primus.data.Message;
import com.primus.data.Student;
import com.primus.data.StudentResultSheet;
import com.primus.enums.AcademicLevel;
import com.primus.interfaces.DegreeService;
import com.primus.interfaces.StudentService;
import com.primus.service.exceptions.NonexistentEntityException;
import java.util.HashMap;
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
public class StudentServiceBean
        extends PrimusBasePersistenceService
        implements StudentService {

    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    DegreeService degreeServiceBean;

    public void create(Student student) throws Exception {
        if (student.getAcademicSessionLevel() == null) {
            student.setAcademicSessionLevel(new ArrayList<AcademicSessionLevel>());
        }
        if (student.getCourseforms() == null) {
            student.setCourseforms(new HashSet<CourseForm>());
        }
        if (student.getLecturerStudentss() == null) {
            student.setLecturerStudentss(new ArrayList<LecturerStudents>());
        }
        if (student.getMessages() == null) {
            student.setMessages(new ArrayList<Message>());
        }
        if (student.getStudentResultSheets() == null) {
            student.setStudentResultSheets(new ArrayList<StudentResultSheet>());
        }
        EntityManager em;
        if (academicSessionServiceBean.findAcademicSession(String.valueOf(student.getYearAdmitted()), String.valueOf(student.getYearAdmitted() + 1)) == null) {
            throw new Exception("No Academic Session exist for the Admission year : " + student.getYearAdmitted());
        }
        student.setCurrentSession(academicSessionServiceBean.getCurrentAcademicSession());
        if (findStudent(student) == null) {
            em = super.getEntityManager();
            Department department = student.getDepartment();
            if (department != null) {
                department = em.getReference(department.getClass(), department.getId());
                student.setDepartment(department);
                System.out.println("logggg" + degreeServiceBean.findDegree(department.getDepartmentName()));
                Degree degree = degreeServiceBean.findDegree(department.getDepartmentName());
                if (degree == null) {
                    throw new Exception("No Degree Has been Created for the Department: " + department.getDepartmentName().getName() + " Please Create Degree First");
                }
                student.setDegree(degreeServiceBean.findDegree(department.getDepartmentName()));
            }

            Room room = student.getRoom();
            if (room != null) {
                room = em.getReference(room.getClass(), room.getId());
                student.setRoom(room);
            }
//            for (AcademicSessionLevel academicSessionLevelAcademicSessionLevel : student.getAcademicSessionLevel()) {
//                Student oldStudentOfAcademicSessionLevelAcademicSessionLevel = academicSessionLevelAcademicSessionLevel.getStudent();
//                academicSessionLevelAcademicSessionLevel.setStudent(student);
//                academicSessionLevelAcademicSessionLevel = em.merge(academicSessionLevelAcademicSessionLevel);
//                if (oldStudentOfAcademicSessionLevelAcademicSessionLevel != null) {
//                    oldStudentOfAcademicSessionLevelAcademicSessionLevel.getAcademicSessionLevel().remove(academicSessionLevelAcademicSessionLevel);
//                    oldStudentOfAcademicSessionLevelAcademicSessionLevel = em.merge(oldStudentOfAcademicSessionLevelAcademicSessionLevel);
//                }
//            }
            Set<CourseForm> attachedCourseform = new HashSet<>();
            for (CourseForm courseformCourseFormToAttach : student.getCourseforms()) {
                courseformCourseFormToAttach = em.getReference(courseformCourseFormToAttach.getClass(), courseformCourseFormToAttach.getId());
                attachedCourseform.add(courseformCourseFormToAttach);
            }
            student.setCourseforms(attachedCourseform);
            List<LecturerStudents> attachedLecturerStudentss = new ArrayList<>();
            for (LecturerStudents lecturerStudentssLecturerStudentsToAttach : student.getLecturerStudentss()) {
                lecturerStudentssLecturerStudentsToAttach = em.getReference(lecturerStudentssLecturerStudentsToAttach.getClass(), lecturerStudentssLecturerStudentsToAttach.getId());
                attachedLecturerStudentss.add(lecturerStudentssLecturerStudentsToAttach);
            }
            student.setLecturerStudentss(attachedLecturerStudentss);
            List<Message> attachedMessages = new ArrayList<>();
            for (Message messagesMessageToAttach : student.getMessages()) {
                messagesMessageToAttach = em.getReference(messagesMessageToAttach.getClass(), messagesMessageToAttach.getId());
                attachedMessages.add(messagesMessageToAttach);
            }
            student.setMessages(attachedMessages);
//            List<AcademicSessionLevel> attachedAcademicSessionLevels = new ArrayList<>();
//            for (AcademicSessionLevel academicSessionLevelsAcademicSessionLevelToAttach : student.getAcademicSessionLevel()) {
//                academicSessionLevelsAcademicSessionLevelToAttach = em.getReference(academicSessionLevelsAcademicSessionLevelToAttach.getClass(), academicSessionLevelsAcademicSessionLevelToAttach.getId());
//                attachedAcademicSessionLevels.add(academicSessionLevelsAcademicSessionLevelToAttach);
//            }
//            student.setAcademicSessionLevel(attachedAcademicSessionLevels);
            List<StudentResultSheet> attachedStudentResultSheets = new ArrayList<>();
            for (StudentResultSheet studentResultSheetsStudentResultSheetToAttach : student.getStudentResultSheets()) {
                studentResultSheetsStudentResultSheetToAttach = em.getReference(studentResultSheetsStudentResultSheetToAttach.getClass(), studentResultSheetsStudentResultSheetToAttach.getId());
                attachedStudentResultSheets.add(studentResultSheetsStudentResultSheetToAttach);
            }
            student.setStudentResultSheets(attachedStudentResultSheets);
            em.persist(student);
            if (department != null) {
                department.getStudentsID().add(student);
                department = em.merge(department);
            }
            if (room != null) {
                room.getStudents().add(student);
                room = em.merge(room);
            }
            for (CourseForm courseformCourseForm : student.getCourseforms()) {
                Student oldStudentOfCourseformCourseForm = courseformCourseForm.getStudent();
                courseformCourseForm.setStudent(student);
                courseformCourseForm = em.merge(courseformCourseForm);
                if (oldStudentOfCourseformCourseForm != null) {
                    oldStudentOfCourseformCourseForm.getCourseforms().remove(courseformCourseForm);
                    oldStudentOfCourseformCourseForm = em.merge(oldStudentOfCourseformCourseForm);
                }
            }
            for (LecturerStudents lecturerStudentssLecturerStudents : student.getLecturerStudentss()) {
                lecturerStudentssLecturerStudents.getStudents().add(student);
                lecturerStudentssLecturerStudents = em.merge(lecturerStudentssLecturerStudents);
            }
            for (Message messagesMessage : student.getMessages()) {
                Student oldStudentOfMessagesMessage = messagesMessage.getStudent();
                messagesMessage.setStudent(student);
                messagesMessage = em.merge(messagesMessage);
                if (oldStudentOfMessagesMessage != null) {
                    oldStudentOfMessagesMessage.getMessages().remove(messagesMessage);
                    oldStudentOfMessagesMessage = em.merge(oldStudentOfMessagesMessage);
                }
            }
            for (StudentResultSheet studentResultSheetsStudentResultSheet : student.getStudentResultSheets()) {
                Student oldStudentOfStudentResultSheetsStudentResultSheet = studentResultSheetsStudentResultSheet.getStudent();
                studentResultSheetsStudentResultSheet.setStudent(student);
                studentResultSheetsStudentResultSheet = em.merge(studentResultSheetsStudentResultSheet);
                if (oldStudentOfStudentResultSheetsStudentResultSheet != null) {
                    oldStudentOfStudentResultSheetsStudentResultSheet.getStudentResultSheets().remove(studentResultSheetsStudentResultSheet);
                    oldStudentOfStudentResultSheetsStudentResultSheet = em.merge(oldStudentOfStudentResultSheetsStudentResultSheet);
                }
            }

        } else {
            throw new NonexistentEntityException("The Student with Jamb registration number "
                    + "" + student.getJambRegNumber() + " already exists.");
        }
    }

    public void edit(Student student) throws Exception {
        EntityManager em = super.getEntityManager();
        Student persistentStudent = em.find(Student.class, student.getId());
        Department departmentOld = persistentStudent.getDepartment();
        Department departmentNew = student.getDepartment();
        Degree degreeNew = student.getDegree();
        Room roomOld = persistentStudent.getRoom();
        Room roomNew = student.getRoom();

        List<AcademicSessionLevel> academicSessionLevelOld = persistentStudent.getAcademicSessionLevel();
        List<AcademicSessionLevel> academicSessionLevelNew = student.getAcademicSessionLevel();
        List<AcademicSessionLevel> attachedAcademicSessionLevelNew = new ArrayList<AcademicSessionLevel>();
        Set<CourseForm> courseformOld = persistentStudent.getCourseforms();
        Set<CourseForm> courseformNew = student.getCourseforms();
        List<LecturerStudents> lecturerStudentssOld = persistentStudent.getLecturerStudentss();
        List<LecturerStudents> lecturerStudentssNew = student.getLecturerStudentss();
        List<Message> messagesOld = persistentStudent.getMessages();
        List<Message> messagesNew = student.getMessages();
        List<StudentResultSheet> studentResultSheetsOld = persistentStudent.getStudentResultSheets();
        List<StudentResultSheet> studentResultSheetsNew = student.getStudentResultSheets();
        if (departmentNew != null) {
            departmentNew = em.getReference(departmentNew.getClass(), departmentNew.getId());
            student.setDepartment(departmentNew);
        }
        if (degreeNew != null) {
            degreeNew = em.getReference(degreeNew.getClass(), degreeNew.getId());
            student.setDegree(degreeNew);
        }
        if (roomNew != null) {
            roomNew = em.getReference(roomNew.getClass(), roomNew.getId());
            student.setRoom(roomNew);
        }
        Set<CourseForm> attachedCourseformNew = new HashSet<>();
        for (CourseForm courseformNewCourseFormToAttach : courseformNew) {
            courseformNewCourseFormToAttach = em.getReference(courseformNewCourseFormToAttach.getClass(), courseformNewCourseFormToAttach.getId());
            attachedCourseformNew.add(courseformNewCourseFormToAttach);
        }
        courseformNew = attachedCourseformNew;
        student.setCourseforms(courseformNew);
        List<LecturerStudents> attachedLecturerStudentssNew = new ArrayList<>();
        for (LecturerStudents lecturerStudentssNewLecturerStudentsToAttach : lecturerStudentssNew) {
            lecturerStudentssNewLecturerStudentsToAttach = em.getReference(lecturerStudentssNewLecturerStudentsToAttach.getClass(), lecturerStudentssNewLecturerStudentsToAttach.getId());
            attachedLecturerStudentssNew.add(lecturerStudentssNewLecturerStudentsToAttach);
        }
        lecturerStudentssNew = attachedLecturerStudentssNew;
        student.setLecturerStudentss(lecturerStudentssNew);
        List<Message> attachedMessagesNew = new ArrayList<>();
        for (Message messagesNewMessageToAttach : messagesNew) {
            messagesNewMessageToAttach = em.getReference(messagesNewMessageToAttach.getClass(), messagesNewMessageToAttach.getId());
            attachedMessagesNew.add(messagesNewMessageToAttach);
        }
        messagesNew = attachedMessagesNew;
        student.setMessages(messagesNew);
         for (AcademicSessionLevel academicSessionLevelNewAcademicSessionLevelToAttach : academicSessionLevelNew) {
                academicSessionLevelNewAcademicSessionLevelToAttach = em.getReference(academicSessionLevelNewAcademicSessionLevelToAttach.getClass(), academicSessionLevelNewAcademicSessionLevelToAttach.getId());
                attachedAcademicSessionLevelNew.add(academicSessionLevelNewAcademicSessionLevelToAttach);
            }
            academicSessionLevelNew = attachedAcademicSessionLevelNew;
            student.setAcademicSessionLevel(academicSessionLevelNew);
            
        List<StudentResultSheet> attachedStudentResultSheetsNew = new ArrayList<>();
        for (StudentResultSheet studentResultSheetsNewStudentResultSheetToAttach : studentResultSheetsNew) {
            studentResultSheetsNewStudentResultSheetToAttach = em.getReference(studentResultSheetsNewStudentResultSheetToAttach.getClass(), studentResultSheetsNewStudentResultSheetToAttach.getId());
            attachedStudentResultSheetsNew.add(studentResultSheetsNewStudentResultSheetToAttach);
        }
        studentResultSheetsNew = attachedStudentResultSheetsNew;
        student.setStudentResultSheets(studentResultSheetsNew);
        student = em.merge(student);
        if (departmentOld != null && !departmentOld.equals(departmentNew)) {
            departmentOld.getStudentsID().remove(student);
            departmentOld = em.merge(departmentOld);
        }
        if (departmentNew != null && !departmentNew.equals(departmentOld)) {
            departmentNew.getStudentsID().add(student);
            departmentNew = em.merge(departmentNew);
        }
        if (roomOld != null && !roomOld.equals(roomNew)) {
            roomOld.getStudents().remove(student);
            roomOld = em.merge(roomOld);
        }
        if (roomNew != null && !roomNew.equals(roomOld)) {
            roomNew.getStudents().add(student);
            roomNew = em.merge(roomNew);
        }
        for (CourseForm courseformOldCourseForm : courseformOld) {
            if (!courseformNew.contains(courseformOldCourseForm)) {
                courseformOldCourseForm.setStudent(null);
                courseformOldCourseForm = em.merge(courseformOldCourseForm);
            }
        }
         for (AcademicSessionLevel academicSessionLevelOldAcademicSessionLevel : academicSessionLevelOld) {
                if (!academicSessionLevelNew.contains(academicSessionLevelOldAcademicSessionLevel)) {
                    academicSessionLevelOldAcademicSessionLevel.setStudent(null);
                    academicSessionLevelOldAcademicSessionLevel = em.merge(academicSessionLevelOldAcademicSessionLevel);
                }
            }
            for (AcademicSessionLevel academicSessionLevelNewAcademicSessionLevel : academicSessionLevelNew) {
                if (!academicSessionLevelOld.contains(academicSessionLevelNewAcademicSessionLevel)) {
                    Student oldStudentOfAcademicSessionLevelNewAcademicSessionLevel = academicSessionLevelNewAcademicSessionLevel.getStudent();
                    academicSessionLevelNewAcademicSessionLevel.setStudent(student);
                    academicSessionLevelNewAcademicSessionLevel = em.merge(academicSessionLevelNewAcademicSessionLevel);
                    if (oldStudentOfAcademicSessionLevelNewAcademicSessionLevel != null && !oldStudentOfAcademicSessionLevelNewAcademicSessionLevel.equals(student)) {
                        oldStudentOfAcademicSessionLevelNewAcademicSessionLevel.getAcademicSessionLevel().remove(academicSessionLevelNewAcademicSessionLevel);
                        oldStudentOfAcademicSessionLevelNewAcademicSessionLevel = em.merge(oldStudentOfAcademicSessionLevelNewAcademicSessionLevel);
                    }
                }
            }
        for (CourseForm courseformNewCourseForm : courseformNew) {
            if (!courseformOld.contains(courseformNewCourseForm)) {
                Student oldStudentOfCourseformNewCourseForm = courseformNewCourseForm.getStudent();
                courseformNewCourseForm.setStudent(student);
                courseformNewCourseForm = em.merge(courseformNewCourseForm);
                if (oldStudentOfCourseformNewCourseForm != null && !oldStudentOfCourseformNewCourseForm.equals(student)) {
                    oldStudentOfCourseformNewCourseForm.getCourseforms().remove(courseformNewCourseForm);
                    oldStudentOfCourseformNewCourseForm = em.merge(oldStudentOfCourseformNewCourseForm);
                }
            }
        }

        for (LecturerStudents lecturerStudentssOldLecturerStudents : lecturerStudentssOld) {
            if (!lecturerStudentssNew.contains(lecturerStudentssOldLecturerStudents)) {
                lecturerStudentssOldLecturerStudents.getStudents().remove(student);
                lecturerStudentssOldLecturerStudents = em.merge(lecturerStudentssOldLecturerStudents);
            }
        }
        for (LecturerStudents lecturerStudentssNewLecturerStudents : lecturerStudentssNew) {
            if (!lecturerStudentssOld.contains(lecturerStudentssNewLecturerStudents)) {
                lecturerStudentssNewLecturerStudents.getStudents().add(student);
                lecturerStudentssNewLecturerStudents = em.merge(lecturerStudentssNewLecturerStudents);
            }
        }
        for (Message messagesOldMessage : messagesOld) {
            if (!messagesNew.contains(messagesOldMessage)) {
                messagesOldMessage.setStudent(null);
                messagesOldMessage = em.merge(messagesOldMessage);
            }
        }
        for (Message messagesNewMessage : messagesNew) {
            if (!messagesOld.contains(messagesNewMessage)) {
                Student oldStudentOfMessagesNewMessage = messagesNewMessage.getStudent();
                messagesNewMessage.setStudent(student);
                messagesNewMessage = em.merge(messagesNewMessage);
                if (oldStudentOfMessagesNewMessage != null && !oldStudentOfMessagesNewMessage.equals(student)) {
                    oldStudentOfMessagesNewMessage.getMessages().remove(messagesNewMessage);
                    oldStudentOfMessagesNewMessage = em.merge(oldStudentOfMessagesNewMessage);
                }
            }
        }
        for (StudentResultSheet studentResultSheetsOldStudentResultSheet : studentResultSheetsOld) {
            if (!studentResultSheetsNew.contains(studentResultSheetsOldStudentResultSheet)) {
                studentResultSheetsOldStudentResultSheet.setStudent(null);
                studentResultSheetsOldStudentResultSheet = em.merge(studentResultSheetsOldStudentResultSheet);
            }
        }
        for (StudentResultSheet studentResultSheetsNewStudentResultSheet : studentResultSheetsNew) {
            if (!studentResultSheetsOld.contains(studentResultSheetsNewStudentResultSheet)) {
                Student oldStudentOfStudentResultSheetsNewStudentResultSheet = studentResultSheetsNewStudentResultSheet.getStudent();
                studentResultSheetsNewStudentResultSheet.setStudent(student);
                studentResultSheetsNewStudentResultSheet = em.merge(studentResultSheetsNewStudentResultSheet);
                if (oldStudentOfStudentResultSheetsNewStudentResultSheet != null && !oldStudentOfStudentResultSheetsNewStudentResultSheet.equals(student)) {
                    oldStudentOfStudentResultSheetsNewStudentResultSheet.getStudentResultSheets().remove(studentResultSheetsNewStudentResultSheet);
                    oldStudentOfStudentResultSheetsNewStudentResultSheet = em.merge(oldStudentOfStudentResultSheetsNewStudentResultSheet);
                }
            }
        }

        Long id = student.getId();
        if (findActiveStudent(id) == null) {
            throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
        }

    }

    @Override
    public Student register(Student student) throws Exception {

        if (findStudent(student) == null) {
            create(student);
        } else {
            edit(student);
        }
        return student;
    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        Student student;
        try {
            student = em.getReference(Student.class, id);
            student.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
        }
        Department department = student.getDepartment();
        if (department != null) {
            department.getStudentsID().remove(student);
            department = em.merge(department);
        }
        Room room = student.getRoom();
        if (room != null) {
            room.getStudents().remove(student);
            room = em.merge(room);
        }
        Set<CourseForm> courseform = student.getCourseforms();
        for (CourseForm courseformCourseForm : courseform) {
            courseformCourseForm.setStudent(null);
            courseformCourseForm = em.merge(courseformCourseForm);
        }
        List<LecturerStudents> lecturerStudentss = student.getLecturerStudentss();
        for (LecturerStudents lecturerStudentssLecturerStudents : lecturerStudentss) {
            lecturerStudentssLecturerStudents.getStudents().remove(student);
            lecturerStudentssLecturerStudents = em.merge(lecturerStudentssLecturerStudents);
        }
        List<Message> messages = student.getMessages();
        for (Message messagesMessage : messages) {
            messagesMessage.setStudent(null);
            messagesMessage = em.merge(messagesMessage);
        }
        List<StudentResultSheet> studentResultSheets = student.getStudentResultSheets();
        for (StudentResultSheet studentResultSheetsStudentResultSheet : studentResultSheets) {
            studentResultSheetsStudentResultSheet.setStudent(null);
            studentResultSheetsStudentResultSheet = em.merge(studentResultSheetsStudentResultSheet);
        }
        em.remove(student);
    }

    @Override
    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from Student as o where o.active =:act");
        q.setParameter("act", true);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Student findActiveAndNonactiveStudent(Long id) throws Exception {
        return (Student) super.findByPrimaryKey(id, Student.class);
    }

    @Override
    public Student findActiveStudent(Long id) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT s FROM Student s WHERE s.id =:ID  and s.active =:act");
        q.setParameter("ID", id);
        q.setParameter("act", true);
        return (Student) super.findEntity(q);
    }

    @Override
    public Student findStudent(String studentRegNumberOrMatric) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from Student s where (s.jambRegNumber =:sString or s.regNumber =:sString) and s.active =:act ");
        q.setParameter("sString", studentRegNumberOrMatric);
        q.setParameter("act", true);

        return (Student) super.findEntity(q);
    }

    @Override
    public Student findStudent(Student student) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT s FROM Student s "
                + "WHERE (s.regNumber=:studentID OR s.jambRegNumber = :studentR) and s.active =:act");
        q.setParameter("studentID", student.getRegNumber());
        q.setParameter("studentR", student.getJambRegNumber());
        q.setParameter("act", true);
        return (Student) super.findEntity(q);
    }

    @Override
    public int getStudentCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from Student as o where o.active =:act");
        q.setParameter("act", true);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Student> findStudentsInFaculty(String facultyName) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from Student s where s.department.faculty.facultyName.name =:sString and s.active =:act");
        q.setParameter("sString", facultyName);
        q.setParameter("act", true);
        return (List<Student>) super.findAll(q);
    }

    @Override
    public List<Student> findStudentsInDepartment(String departmentName) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from Student s where s.department.departmentName.name =:sString and s.active =:act ");
        q.setParameter("sString", departmentName);
        q.setParameter("act", true);
        return (List<Student>) super.findAll(q);
    }

    @Override
    public List<Student> findStudentsInLevel(String departmentName, AcademicLevel academiclevel) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from Student s where s.department.departmentName.name =:dName and s.CurrentAcademicLevel= aLevel and s.active =:act");
        q.setParameter("dName", departmentName);
        q.setParameter("aLevel", academiclevel);
        q.setParameter("act", true);
        return (List<Student>) super.findAll(q);
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> wildSearch(String searchString) throws Exception {
        Query q = super.getEntityManager().createQuery("select s from Student s where (s.regNumber like :sString "
                + "or s.jambRegNumber like :sString or s.studentName.surname like :sString or s.studentName.firstName like :sString "
                + "or s.department.departmentName.name like :sString or s.department.faculty.facultyName.name like :sString )"
                + " and s.active =:act");
        q.setParameter("sString", searchString);
        q.setParameter("act", true);
        return (List<Student>) super.findAll(q);
    }

    @Override
    public int generateMatricSerialNumber(int yearAdmitted) throws Exception {
        System.out.println("about generating matric serial number");
        int count = 0;
        int lastYearAdttimed;
        Query q = super.getEntityManager().createQuery("SELECT s FROM Student s WHERE s.yearAdmitted = :yearAdt AND s.matricSerialNumber = (SELECT MAX(s.matricSerialNumber)  FROM Student s WHERE s.yearAdmitted = :yearAdt)");
        q.setParameter("yearAdt", yearAdmitted);
        Student stud = (Student) super.findEntity(q);
        if (stud != null) { // if student not the first student that year
            return stud.getMatricSerialNumber() + 1;
        }
        while (stud == null && count <= 20) {//check if its the first student for the year
            System.out.println("checking for prevous years");
            lastYearAdttimed = yearAdmitted - count++;
            q.setParameter("yearAdt", lastYearAdttimed);
            stud = (Student) super.findEntity(q);
        }
        System.out.println("\"about generating matric serial number\"");
        if (stud == null) { // Database empty(no entry for last 20 years)
            return 000001;
        } else { // add an offset value to the new MatricSerialNumber for the current year 
            return stud.getMatricSerialNumber() + 50;
        }
    }

    @Override
    public void deactivate(Long studentId) throws Exception {
        Student student = findActiveStudent(studentId);
        student.setActive(false);
        register(student);
    }

    @Override
    public void activate(Long studentId) throws Exception {
        Student student = findActiveStudent(studentId);
        student.setActive(true);
        register(student);
    }

    @Override
    public Student studentLogin(String regNum, int password) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT s FROM Student s "
                + "WHERE (s.regNumber=:studentID OR s.jambRegNumber = :studentR) and s.password =:password");
        q.setParameter("studentID", regNum);
        q.setParameter("studentR", regNum);
        q.setParameter("password", password);
        return (Student) super.findEntity(q);
    }
}
