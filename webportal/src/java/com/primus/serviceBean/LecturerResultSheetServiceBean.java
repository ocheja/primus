/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.DepartmentName;
import com.primus.data.Grade;
import com.primus.data.Lecturer;
import com.primus.data.LecturerResultGrade;
import com.primus.data.ResultGradingScheme;
import com.primus.data.ResultSheet;
import com.primus.data.Student;
import com.primus.data.StudentResultSheet;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Status;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerResultSheetService;
import com.primus.interfaces.StudentResultSheetService;
import com.primus.ireportResourses.StudentsGrade;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.util.CreatExcel;
import com.primus.util.IreportService;
import com.primus.util.ReadResultsheetExcel;
import com.primus.util.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component("lecturerResultSheetServiceBean")
public class LecturerResultSheetServiceBean extends PrimusBasePersistenceService implements LecturerResultSheetService {

    @Autowired
    public CourseService courseServiceBean;
    @Autowired
    private ReadResultsheetExcel ReadResultsheetExcel;
    @Autowired
    private DepartmentServiceBean departmentServiceBean;
    @Autowired
    StudentResultSheetService studentResultSheetServiceBean;
    @Autowired
    AcademicSessionServiceBean  academicSessionServiceBean;
      @Autowired
    ResultGradingSchemeServiceBean resultGradingSchemeServiceBean;
   
    private org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LecturerResultSheetServiceBean.class);
    private String logoRelativePath = "com/primus/ireportResourses/UNN_MainLogo.jpg";

    @Override
    public void create(ResultSheet resultSheet) throws Exception {
        EntityManager em;
        em = super.getEntityManager();
        em.persist(resultSheet);
    }

    public void edit(ResultSheet resultSheet) throws Exception {
        if (resultSheet.isApprovedByOther() || resultSheet.getHeadOfDepartmentDateStamp() != null) {
            resultSheet.setStatus(Status.IN_PROGRESS);
        }
        if ( resultSheet.getHeadOfDepartmentDateStamp() != null) {
            resultSheet.setStatus(Status.APPROVED);
            //  LOG.log(Priority.INFO_INT,"About to create resultsheets for students");
            System.out.println("About to create resultsheets for students");
            createStudentsResultsheet(resultSheet);
        }
        super.modifyEntity(resultSheet);
    }

    public void destroy(Long id) throws Exception {
        EntityManager em;

        em = super.getEntityManager();
        ResultSheet resultSheet;
        resultSheet = em.getReference(ResultSheet.class, id);
        resultSheet.getId();
        em.remove(resultSheet);
    }

    public List<ResultSheet> findResultSheetEntities() {
        return findResultSheetEntities(true, -1, -1);
    }

    public List<ResultSheet> findResultSheetEntities(int maxResults, int firstResult) {
        return findResultSheetEntities(false, maxResults, firstResult);
    }

    private List<ResultSheet> findResultSheetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();

        Query q = em.createQuery("select object(o) from ResultSheet as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public ResultSheet findResultSheet(Long id) throws PrimusServiceException {
        return (ResultSheet) super.findByPrimaryKey(id, ResultSheet.class);
    }

    public int getResultSheetCount() {
        EntityManager em = super.getEntityManager();
        Query q = em.createQuery("select count(o) from ResultSheet as o");
        return ((Long) q.getSingleResult()).intValue();

    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void develop(ResultSheet resultSheet) throws Exception {
        ResultSheet persistentResultSheet = getResultSheet(resultSheet.getCourse(), resultSheet.getDeptName(), resultSheet.getAcademicSession(), resultSheet.getAcademiclevel());
        if (persistentResultSheet == null) {
            create(resultSheet);
        } else {
            edit(resultSheet);
        }

    }

    @Override
    public Map<String, Integer> getGradeStatistics(ResultSheet resultSheet) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSheet getApprovedSheet(DepartmentName deptName, AcademicSession academicSession, Course course, AcademicLevel level) throws Exception {
        Query query = super.getEntityManager().createQuery("select rs from ResultSheet rs where rs.deptName=:deptN and rs.academicSession=:acaS and rs.academiclevel=:acaL and rs.status=:sta and rs.course=:cous");
        query.setParameter("deptN", deptName);
        query.setParameter("acaS", academicSession);
        query.setParameter("acaL", level);
        query.setParameter("sta", Status.APPROVED);
        query.setParameter("cous", course);
        return (ResultSheet) super.findEntity(query);
    }

    @Override
    public List<ResultSheet> getApprovedResultSheets(DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception {
        Query query = super.getEntityManager().createQuery("select rs from ResultSheet rs where rs.deptName=:deptN and rs.academicSession=:acaS and rs.academiclevel=:acaL and rs.status=:sta");
        query.setParameter("deptN", deptName);
        query.setParameter("acaS", academicSession);
        query.setParameter("acaL", level);
        query.setParameter("sta", Status.APPROVED);
        return (List<ResultSheet>) super.findAll(query);
    }

    @Override
    public List<ResultSheet> getNotApprovedSheet(DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception {
        Query query = super.getEntityManager().createQuery("select rs from ResultSheet rs where rs.deptName=:deptN and rs.academicSession=:acaS and rs.academiclevel=:acaL and rs.status=:sta OR rs.status=:ista");
        query.setParameter("deptN", deptName);
        query.setParameter("acaS", academicSession);
        query.setParameter("acaL", level);
        query.setParameter("sta", Status.NOT_APPROVED);
        query.setParameter("ista", Status.IN_PROGRESS);
        return (List<ResultSheet>) super.findAll(query);
    }
    @Override
    
     public ResultSheet getNotApprovedSheet(Course course,DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception {
        Query query = super.getEntityManager().createQuery("select rs from ResultSheet rs where rs.deptName=:deptN and rs.academicSession=:acaS and rs.academiclevel=:acaL and rs.status=:sta OR rs.status=:ista and rs.course=:cous");
        query.setParameter("deptN", deptName);
        query.setParameter("acaS", academicSession);
        query.setParameter("acaL", level);
        query.setParameter("cous", course);
        query.setParameter("sta", Status.NOT_APPROVED);
         query.setParameter("ista", Status.IN_PROGRESS);
        return (ResultSheet) super.findEntity(query);
    }

    @Override
    public List<ResultSheet> getResultSheets(DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception {
        Query query = super.getEntityManager().createQuery("select rs from ResultSheet rs where rs.deptName=:deptN and rs.academicSession=:acaS and rs.academiclevel=:acaL");
        query.setParameter("deptN", deptName);
        query.setParameter("acaS", academicSession);
        query.setParameter("acaL", level);
        return (List<ResultSheet>) super.findAll(query);
    }

    @Override
    public ResultSheet getResultSheet(Course course, DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception {
        Query query = super.getEntityManager().createQuery("select rs from ResultSheet rs where rs.deptName=:deptN and rs.academicSession=:acaS and rs.academiclevel=:acaL and rs.course=:cous");
        query.setParameter("deptN", deptName);
        query.setParameter("acaS", academicSession);
        query.setParameter("acaL", level);
        query.setParameter("cous", course);
        return (ResultSheet) super.findEntity(query);
    }

    @Override
    public void uploadResultSheet(byte[] bytes, String deptName, AcademicSession academicSession, AcademicLevel level, Date examDate) throws Exception {
        DepartmentName departmentName = departmentServiceBean.findDepartmentName(deptName);
        ResultSheet resultsheet = ReadResultsheetExcel.readResultSheet(bytes);
        resultsheet.setAcademiclevel(level);
        //  resultsheet.setDeptName(departmentName);
        resultsheet.setDateOfUpdate(new Date());
        resultsheet.setExaminationDate(examDate);
        create(resultsheet);

    }

    @Override
    public byte[] getResultSheetFormat(long courseId, AcademicSession academicSession, Long LecturerId) throws Exception {
        Course course = courseServiceBean.findCourse(courseId);
        return CreatExcel.create(courseServiceBean.getStudentsOfferingCourse(course, academicSession, LecturerId), course, academicSession);

    }

    @Override
    public byte[] getResultSheetFormatByDept(long courseId, AcademicSession academicSession, Long LecturerId, DepartmentName departmentName) throws Exception {
        Course course = courseServiceBean.findCourse(courseId);
        return CreatExcel.create(courseServiceBean.getStudentsOfferingCourseByDept(course, academicSession, LecturerId, departmentName), course, academicSession);
    }

    private void createStudentsResultsheet(ResultSheet resultSheet) throws Exception {
        StudentResultSheet studentResultSheet;
        Grade grade;
        List<Grade> courseGrades;
        for (LecturerResultGrade studentGrade : resultSheet.getLecturerResultGrade()) {
            studentResultSheet = studentResultSheetServiceBean.findStudentResultSheet(studentGrade.getStudent().getId(), resultSheet.getAcademiclevel(), resultSheet.getCourse().getSemester());
            if (studentResultSheet == null) {
                studentResultSheet = new StudentResultSheet();
                studentResultSheet.setStudent(studentGrade.getStudent());
                studentResultSheet.setStudentLevel(resultSheet.getAcademiclevel());
                studentResultSheet.setSemester(resultSheet.getCourse().getSemester());
                studentResultSheet.setAcademicSession(resultSheet.getAcademicSession());
                grade = new Grade();
                courseGrades = new ArrayList<>();
                grade.setCourse(resultSheet.getCourse());
                grade.setContinousAssesmentScore(studentGrade.getContinousAssesmentScore());
                grade.setExamScore(studentGrade.getExamScore());
                grade.setTotalScore(studentGrade.getTotalScore());
                courseGrades.add(grade);
                studentResultSheet.setResgisteredCoursesGrades(courseGrades);
                studentResultSheetServiceBean.develop(studentResultSheet);
            } else {
                courseGrades = studentResultSheet.getResgisteredCoursesGrades();
                for (Grade gd : courseGrades) {// remove course if it already exists
                    if (gd.getCourse().getId() == resultSheet.getCourse().getId()) {
                        courseGrades.remove(gd);
                    }
                }
                grade = new Grade();
                grade.setCourse(resultSheet.getCourse());
                grade.setContinousAssesmentScore(studentGrade.getContinousAssesmentScore());
                grade.setExamScore(studentGrade.getExamScore());
                grade.setTotalScore(studentGrade.getTotalScore());
                courseGrades.add(grade);
                studentResultSheet.setResgisteredCoursesGrades(courseGrades);
                studentResultSheetServiceBean.develop(studentResultSheet);
            }
        }
    }

    @Override
    public byte[] getLecturerResultSheetPDF(Course course,DepartmentName deptName, AcademicLevel academicLevel, AcademicSession academicSession, ServletContext servletContext) throws Exception {
        AcademicSession academicSes;
        Map<String, Object> parameters = new HashMap<>();
       Lecturer lecturer =course.getTutors().get(0);
        // DataSource dataSourse = (DataSource) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("dataSource");
        Student student;
        if (academicSession == null) {
            academicSes = academicSessionServiceBean.getCurrentAcademicSession();
        } else {
            academicSes = academicSession;
        }
        parameters.put("lecturerName", lecturer.getLecturerName().getSurname() + " " + lecturer.getLecturerName().getMiddleName().toUpperCase().charAt(0) + ". " + lecturer.getLecturerName().getFirstName().toUpperCase().charAt(0)+".");
        parameters.put("courseTitle", course.getCourseTitle());
        parameters.put("courseCode",course.getCourseCode());
        parameters.put("level", academicLevel.toString().replaceAll("l", ""));
        parameters.put("session", Integer.toString(academicSes.getStartYear()) + "/" + Integer.toString(academicSes.getEndYear()));
        parameters.put("unitLoad", String.valueOf(course.getCourseUnitLoad()));
        parameters.put("department", StringUtil.capitalizeFirstLetter(deptName.getName()));
        parameters.put("semester", StringUtil.capitalizeFirstLetter(course.getSemester().toString()));
        parameters.put("logo", servletContext.getRealPath(logoRelativePath));

        ResultSheet lecturerResultSheet = getNotApprovedSheet(course,deptName,academicSession,academicLevel);
        if (lecturerResultSheet == null) {
            throw new Exception("No lecturerResultSheet for the academic Session: " + academicSes.getStartYear() + "/" + academicSes.getEndYear() + " ( " +course.getSemester().toString() + " SEMESTER )");

        }
        List<StudentsGrade> courseGrades = new ArrayList<>();
         
       StudentsGrade studentGrade;
        ResultGradingScheme resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(academicSession);
        for (LecturerResultGrade grade : lecturerResultSheet.getLecturerResultGrade()) {
            studentGrade = new StudentsGrade();
           studentGrade.setCA(grade.getContinousAssesmentScore());
            studentGrade.setExamScore(grade.getExamScore());
            studentGrade.setTotalScore(grade.getTotalScore());
            studentGrade.setGradeLetter(resultGradingScheme.getGradeLetter(grade.getTotalScore()));
           studentGrade.setStudentName(grade.getStudent().getStudentName().getSurname()+" "+grade.getStudent().getStudentName().getFirstName()+" "+grade.getStudent().getStudentName().getMiddleName()+" ");
            studentGrade.setRegNumber(grade.getStudent().getRegNumber());
            courseGrades.add(studentGrade);
        }
        //  
        //parameters.put("status", courseForm.getStatus().toString().replaceAll("_", " ").toLowerCase());
        IreportService ireportService = new IreportService();
        byte[] pdf = ireportService.getReport("com/primus/ireportResourses/LecturerResultSheet.jrxml", parameters, courseGrades, servletContext);
        return pdf;
    }
}
