/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.Grade;
import com.primus.data.ResultGradingScheme;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Student;
import com.primus.data.StudentResultSheet;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import com.primus.interfaces.StudentResultSheetService;
import com.primus.interfaces.StudentService;
import com.primus.ireportResourses.CourseGrade;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import com.primus.util.IreportService;
import com.primus.util.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class StudentResultSheetServiceBean
        extends PrimusBasePersistenceService
        implements StudentResultSheetService {

    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    ResultGradingSchemeServiceBean resultGradingSchemeServiceBean;
      @Autowired
    StudentService studentServiceBean;
      private String logoRelativePath = "com/primus/ireportResourses/UNN_MainLogo.jpg";


    public void create(StudentResultSheet studentResultSheet) throws Exception {
        if (findStudentResultSheet(studentResultSheet.getStudent().getId(), studentResultSheet.getStudentLevel(), studentResultSheet.getSemester()) != null) {
            throw new Exception(studentResultSheet.getStudentLevel().toString().replaceAll("l", "") + " Level " + studentResultSheet.getSemester()
                    + " Semester " + "Result Sheet has already been created for " + studentResultSheet.getStudent().getStudentName().getFirstName() + " "
                    + studentResultSheet.getStudent().getStudentName().getSurname());
        }
        EntityManager em = super.getEntityManager();
        Student student = studentResultSheet.getStudent();
        if (student != null) {
            student = em.getReference(student.getClass(), student.getId());
            studentResultSheet.setStudent(student);
        }
        em.persist(studentResultSheet);
        if (student != null) {
            student.getStudentResultSheets().add(studentResultSheet);
            student = em.merge(student);
        }
    }

    public void edit(StudentResultSheet studentResultSheet) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        StudentResultSheet persistentStudentResultSheet = em.find(StudentResultSheet.class, studentResultSheet.getId());
        Student studentOld = persistentStudentResultSheet.getStudent();
        Student studentNew = studentResultSheet.getStudent();
        
          List<Grade> gradeOld = persistentStudentResultSheet.getResgisteredCoursesGrades();
        List<Grade> gradesNew = studentResultSheet.getResgisteredCoursesGrades();
      List<Grade> attachedGradeNew = new ArrayList<>();
//            for (Grade coursesNewCourseToAttach : gradesNew) {
//            coursesNewCourseToAttach = em.getReference(coursesNewCourseToAttach.getClass(), coursesNewCourseToAttach.getId());
//           attachedGradeNew.add(coursesNewCourseToAttach);
//        }
//        gradesNew = attachedGradeNew;
//        studentResultSheet.setResgisteredCoursesGrades(gradesNew);
//        
      studentResultSheet = em.merge(studentResultSheet);
        

        if (studentNew != null) {
            studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
            studentResultSheet.setStudent(studentNew);
        }
        studentResultSheet = em.merge(studentResultSheet);
        if (studentOld != null && !studentOld.equals(studentNew)) {
            studentOld.getStudentResultSheets().remove(studentResultSheet);
            studentOld = em.merge(studentOld);
        }
        if (studentNew != null && !studentNew.equals(studentOld)) {
            studentNew.getStudentResultSheets().add(studentResultSheet);
            studentNew = em.merge(studentNew);
        }


        Long id = studentResultSheet.getId();
        if (findStudentResultSheet(id) == null) {
            throw new NonexistentEntityException("The studentResultSheet with id " + id + " no longer exists.");
        }

    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        StudentResultSheet studentResultSheet;
        try {
            studentResultSheet = em.getReference(StudentResultSheet.class, id);
            studentResultSheet.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The studentResultSheet with id " + id + " no longer exists.", enfe);
        }
        Student student = studentResultSheet.getStudent();
        if (student != null) {
            student.getStudentResultSheets().remove(studentResultSheet);
            student = em.merge(student);
        }
        em.remove(studentResultSheet);
    }

    public List<StudentResultSheet> findStudentResultSheetEntities() {
        return findStudentResultSheetEntities(true, -1, -1);
    }

    public List<StudentResultSheet> findStudentResultSheetEntities(int maxResults, int firstResult) {
        return findStudentResultSheetEntities(false, maxResults, firstResult);
    }

    private List<StudentResultSheet> findStudentResultSheetEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from StudentResultSheet as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public StudentResultSheet findStudentResultSheet(Long id) throws Exception {
        return (StudentResultSheet) super.findByPrimaryKey(id, StudentResultSheet.class);
    }

    public int getStudentResultSheetCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from StudentResultSheet as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void develop(StudentResultSheet studentResultSheet) throws Exception {
        if (studentResultSheet.getId() == null) {
            create(studentResultSheet);
        } else {
            edit(studentResultSheet);
        }
        //return student;
    }

    @Override
    public StudentResultSheet findStudentResultSheet(long studentId, AcademicLevel level, Semester semester) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT r FROM StudentResultSheet r WHERE r.StudentLevel=:slevel AND r.student.id=:studId AND r.semester=:sem");
        q.setParameter("sem", semester);
        q.setParameter("studId", studentId);
        q.setParameter("slevel", level);
        return (StudentResultSheet) super.findEntity(q);
    }

    @Override
    public List<StudentResultSheet> getStudentResultSheets(long studentId, AcademicLevel level) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT r FROM StudentResultSheet r WHERE r.StudentLevel=:slevel AND r.student.id=:studId");
        q.setParameter("studId", studentId);
        q.setParameter("slevel", level);
        return (List<StudentResultSheet>) super.findEntity(q);
    }

    @Override
    public List<?> getGrade(long StudentId, Course course) throws Exception {
        Query q = super.getEntityManager().createQuery("select r.AcademicSession, g from StudentResultSheet r join r.resgisteredCoursesGrades g where r.student.id=:studId and g.course =:course ");
        q.setParameter("studId", StudentId);
        q.setParameter("course", course);
        return super.findAll(q);
    }
 
    @Override
    public List<StudentResultSheet> getAllStudentResultSheets(long studentId) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT r FROM StudentResultSheet r WHERE r.student.id=:studId");
        q.setParameter("studId", studentId);
        return (List<StudentResultSheet>) super.findAll(q);
    }

    @Override
    public Float getCGPA(long studentId, AcademicSession academicSession) throws Exception {
        float gpa, point = 0, totalunitLoad = 0;
        if (academicSession == null) {
            academicSession = academicSessionServiceBean.getCurrentAcademicSession();
        }
        ResultGradingScheme resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(academicSession);
        List<StudentResultSheet> studentResultSheets = getAllStudentResultSheets(studentId);
        for (StudentResultSheet sheet : studentResultSheets) {
            for (Grade grade : sheet.getResgisteredCoursesGrades()) {
                point += resultGradingScheme.getGradePoint(grade.getTotalScore()) * grade.getCourse().getCourseUnitLoad();
                totalunitLoad += grade.getCourse().getCourseUnitLoad();
            }
        }
        gpa = point / totalunitLoad;
        return gpa;
    }

    @Override
    public Float getGPA(StudentResultSheet studentResultSheets) throws Exception {
        float gpa, point = 0, totalunitLoad = 0;
        ResultGradingScheme resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(studentResultSheets.getAcademicSession());

        for (Grade grade : studentResultSheets.getResgisteredCoursesGrades()) {
            point += resultGradingScheme.getGradePoint(grade.getTotalScore()) * grade.getCourse().getCourseUnitLoad();
            totalunitLoad += grade.getCourse().getCourseUnitLoad();
        }
        gpa = point / totalunitLoad;
        return gpa;
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getStudentResultSheetPDF(long studentId, Semester semester,AcademicLevel academicLevel, AcademicSession academicSession, ServletContext servletContext) throws Exception {
    AcademicSession academicSes;
        Map<String, Object> parameters = new HashMap<>();
        // DataSource dataSourse = (DataSource) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("dataSource");
        Student student = studentServiceBean.findActiveStudent(studentId);
        if (academicSession == null) {
            academicSes = student.getCurrentSession();
        } else {
            academicSes = academicSession;
        }
        parameters.put("name", student.getStudentName().getSurname() + " " + student.getStudentName().getMiddleName() + " " + student.getStudentName().getFirstName());
        parameters.put("matricNo", student.getRegNumber());
        parameters.put("level", academicLevel.toString().replaceAll("l", ""));
        parameters.put("session", Integer.toString(academicSes.getStartYear()) + "/" + Integer.toString(academicSes.getEndYear()));
      //  parameters.put("studentImage", new ByteArrayInputStream(student.getStudentImage()));
        parameters.put("faculty",StringUtil.capitalizeFirstLetters(student.getDepartment().getFaculty().getFacultyName().getName()));
        parameters.put("department", StringUtil.capitalizeFirstLetters(student.getDepartment().getDepartmentName().getName()));
        parameters.put("programme", student.getProgramme());
        parameters.put("email", student.getEmail());
        parameters.put("gsm", student.getPhoneNumber());
        parameters.put("sex", StringUtil.capitalizeFirstLetter(student.getGender().toString()));
        parameters.put("studentId", student.getId());
        parameters.put("semester", StringUtil.capitalizeFirstLetter(semester.toString().toLowerCase()));
        parameters.put("academicSessionId", academicSes.getId());
        parameters.put("logo", servletContext.getRealPath(logoRelativePath));
        

        StudentResultSheet studentResultSheet =  findStudentResultSheet(studentId, academicLevel,semester);
        parameters.put("GPA", getGPA(studentResultSheet));
        parameters.put("CGPA", getCGPA( student.getId(), academicSession));
        
                if (studentResultSheet == null) {
            throw new Exception("No Approved Result Sheet  for : " + academicLevel.name().replaceAll("l", "") +" Level "+ " ( " + semester.toString() + " SEMESTER )");

        }
         List<CourseGrade> courseGrades= new ArrayList<>();     
         CourseGrade courseGrade ;
           ResultGradingScheme resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(academicSession);
         for(Grade grade:studentResultSheet.getResgisteredCoursesGrades()){
             courseGrade = new CourseGrade();
             courseGrade.setCA(grade.getContinousAssesmentScore());
            courseGrade.setExamScore(grade.getExamScore());
            courseGrade.setTotalScore(grade.getTotalScore());
            courseGrade.setCourseUnitLoad(grade.getCourse().getCourseUnitLoad());
            courseGrade.setGradeLetter(resultGradingScheme.getGradeLetter(grade.getTotalScore()));
            courseGrade.setCourseCode(grade.getCourse().getCourseCode());
            courseGrade.setCourseTitle(grade.getCourse().getCourseTitle());
            courseGrades.add(courseGrade);
         }
      //  parameters.put("status", courseForm.getStatus().toString().replaceAll("_", " ").toLowerCase());
        IreportService ireportService = new IreportService();
        byte[] pdf = ireportService.getReport("com/primus/ireportResourses/StudentResultSheetTemplate.jrxml", parameters,courseGrades, servletContext);
        return pdf;
    }
}
