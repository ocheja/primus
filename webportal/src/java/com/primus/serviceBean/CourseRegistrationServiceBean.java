/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.DegreeRequirement;
import com.primus.data.DepartmentName;
import com.primus.data.Grade;
import com.primus.data.ResultGradingScheme;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import com.primus.enums.Status;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.StudentResultSheetService;
import com.primus.interfaces.StudentService;
import com.primus.util.IreportService;
import com.primus.util.StringUtil;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import org.jboss.weld.util.collections.ArraySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class CourseRegistrationServiceBean extends PrimusBasePersistenceService implements CourseRegistrationService {

    @Autowired
    StudentService studentServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    PrerequisiteServiceBean prerequisiteServiceBean;
    @Autowired
    StudentResultSheetService studentResultSheetServiceBean;
    @Autowired
    ResultGradingSchemeServiceBean resultGradingSchemeServiceBean;
    @Autowired
    DegreeRequirementServiceBean degreeRequirementServiceBean;
    @Autowired
    CourseFormServiceBean courseFormServiceBean;
    private String logoRelativePath = "com/primus/ireportResourses/UNN_MainLogo.jpg";

    @Override
    public List<Course> getRequiredCourses(long studentId, Semester semester) throws Exception {
        List<Course> RequiredCourses = new ArrayList<>();
        Student student = studentServiceBean.findActiveStudent(studentId);
        CourseForm CourseForm = courseFormServiceBean.getCourseForm(studentId, student.getCurrentAcademicLevel(), semester);//check if courseForm Already exists
        DegreeRequirement degreeRequirement = degreeRequirementServiceBean.findDegreeRequirement(student.getDegree().getTitleOfDegree().toString(), student.getCurrentAcademicLevel());
        RequiredCourses.addAll(getFailedCourses(studentId, semester));
        for (Course course : degreeRequirement.getCoursesForLevel()) {
            if (course.getSemester().equals(semester)) {
                RequiredCourses.add(course);
            }
        }
        // RequiredCourses.addAll(degreeRequirement.getCoursesForLevel());

        if (CourseForm != null) {//remove Already existing courses form required courses
            for (Course course : CourseForm.getCourses()) {
                RequiredCourses.remove(course);
            }
        }
        return RequiredCourses;
    }

    @Override
    public void registerCourses(long studentId, Semester semester, AcademicSession academicSession, List<Course> courses) throws Exception {
        Student student = studentServiceBean.findActiveStudent(studentId);
        ResultGradingScheme resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(academicSession);
        if (courses.isEmpty()) {
            throw new Exception("At least one Course must be selected.");
        }
        prerequisiteCoursesPassed(student, courses); //check if all prerequisite courses has been passed. else throw an exception
        if (maxUnitLoadExceeded(resultGradingScheme.getMaximumUnitLoad(), courses)) {
            throw new Exception("Maximum unit load exceeded. Please remove some courses.\n You are allowed to register a total not more than " + resultGradingScheme.getMaximumUnitLoad() + " units ");
        }
        for (Course c : courses) {/// check if semesters match
            if (!c.getSemester().equals(semester)) {
                throw new Exception("All courses must be a " + semester + " semester course");
            }
        }
        CourseForm courseForm = courseFormServiceBean.findCourseForm(studentId, academicSession, semester);
        if (courseForm != null) {
            courseForm.setAcademicSession(academicSession);
            courseForm.setSemester(semester);
            courseForm.setStudent(student);
            courseForm.getCourses().addAll(courses);
            courseFormServiceBean.edit(courseForm);
        } else {
            courseForm = new CourseForm();
            courseForm.setAcademicSession(academicSession);
            courseForm.setSemester(semester);
            courseForm.setStudent(student);
            courseForm.setCourses(courses);
            courseFormServiceBean.create(courseForm);
        }

    }

    @Override
    public byte[] getCourseFormPDF(long studentId, Semester semester, AcademicSession academicSession, ServletContext servletContext) throws Exception {
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
        parameters.put("level", student.getCurrentAcademicLevel().toString().replaceAll("l", ""));
        parameters.put("session", Integer.toString(academicSes.getStartYear()) + "/" + Integer.toString(academicSes.getEndYear()));
        parameters.put("studentImage", new ByteArrayInputStream(student.getStudentImage()));
        parameters.put("faculty", StringUtil.capitalizeFirstLetters(student.getDepartment().getFaculty().getFacultyName().getName()));
        parameters.put("department", StringUtil.capitalizeFirstLetters(student.getDepartment().getDepartmentName().getName()));
        parameters.put("programme", StringUtil.capitalizeFirstLetters(student.getProgramme()));
        parameters.put("email", student.getEmail());
        parameters.put("gsm", student.getPhoneNumber());
        parameters.put("sex", StringUtil.capitalizeFirstLetter(student.getGender().toString()));
        parameters.put("studentId", student.getId());
        parameters.put("semester", StringUtil.capitalizeFirstLetters(semester.toString().toLowerCase()));
        parameters.put("academicSessionId", academicSes.getId());
        parameters.put("logo", servletContext.getRealPath(logoRelativePath));

        CourseForm courseForm = getCourseForm(studentId, academicSes, semester);
        if (courseForm == null) {
            throw new Exception("No Approved courseForm for the academic Session: " + academicSes.getStartYear() + "/" + academicSes.getEndYear() + " ( " + semester.toString() + " SEMESTER )");

        }
        parameters.put("status", courseForm.getStatus().toString().replaceAll("_", " ").toLowerCase());
        IreportService ireportService = new IreportService();
        byte[] pdf = ireportService.getReport("com/primus/ireportResourses/UNN Courseform.jrxml", parameters, courseForm.getCourses(), servletContext);
        return pdf;
    }

    @Override
    public List<Semester> getRegisteredSemester(long studentId, AcademicSession session) throws Exception {
        List<Semester> semesters = new ArrayList<>();
        int CourseFormsSize = getCourseForms(studentId, session).size();
        if (CourseFormsSize == 1) {
            semesters.add(Semester.FIRST);
        } else if (CourseFormsSize == 2) {
            semesters.add(Semester.FIRST);
            semesters.add(Semester.SECOND);
        } else {
            return semesters; //return empty list of semesters
        }

        return semesters;
    }

    @Override
    public List<AcademicLevel> getRegisteredLevel(long studentId) throws Exception {
        List<AcademicLevel> academicLevels = new ArrayList<>();
//        Student student = studentServiceBean.findActiveStudent(studentId);
//        AcademicLevel ccademicLevel = student.getCurrentAcademicLevel();
//        int yearAdmitted = student.getYearAdmitted();
//        int yearDiff = academicSession.getStartYear() - yearAdmitted;
//        for (int i = 0; i <= yearDiff; i++) {
//            academicLevels.add(academicSessionServiceBean.findAcademicSession(String.valueOf(yearAdmitted + i), String.valueOf(yearAdmitted + (i + 1))));
//        }
        return academicLevels;
    }

    @Override
    public List<AcademicSession> getRegisteredAcademicSession(long studentId) throws Exception {
        List<AcademicSession> academicSessions = new ArrayList<>();
        Student student = studentServiceBean.findActiveStudent(studentId);
        AcademicSession academicSession = student.getCurrentSession();
        int yearAdmitted = student.getYearAdmitted();
        int yearDiff = academicSession.getStartYear() - yearAdmitted;
        for (int i = 0; i <= yearDiff; i++) {
            academicSessions.add(academicSessionServiceBean.findAcademicSession(String.valueOf(yearAdmitted + i), String.valueOf(yearAdmitted + (i + 1))));
        }
        return academicSessions;
    }

    @Override
    public List<CourseForm> getCourseForms(long studentId, AcademicSession session) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.student.currentSession =:sess");
        q.setParameter("studId", studentId);
        q.setParameter("sess", session);
        return (List<CourseForm>) super.findEntity(q);
    }

    @Override
    public CourseForm getCourseForm(long studentId, AcademicSession session, Semester semester) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.student.currentSession =:sess  and cf.semester =:sem");
        q.setParameter("studId", studentId);
        q.setParameter("sess", session);
        q.setParameter("sem", semester);
        return (CourseForm) super.findEntity(q);
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseForm getApprovedCourseForm(long studentId, AcademicSession session, Semester semester) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.student.currentSession =:sess and cf.semester =:sem and cf.status =:stat");
        q.setParameter("studId", studentId);
        q.setParameter("sess", session);
        q.setParameter("sem", semester);
        q.setParameter("stat", Status.APPROVED);
        return (CourseForm) super.findEntity(q);
    }

    @Override
    public List<CourseForm> getApprovedCourseForms(long studentId) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.status =:stat");
        q.setParameter("studId", studentId);
        q.setParameter("stat", Status.APPROVED);
        return (List<CourseForm>) super.findAll(q);
    }

    @Override
    public List<CourseForm> getNonApprovedCourseForms(long studentId) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.status =:stat");
        q.setParameter("studId", studentId);
        q.setParameter("stat", Status.NOT_APPROVED);
        return (List<CourseForm>) super.findAll(q);
    }

    @Override
    public List<CourseForm> getApprovedCourseForms(long studentId, AcademicSession session) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.student.currentSession =:sess and cf.status =:stat");
        q.setParameter("studId", studentId);
        q.setParameter("sess", session);
        q.setParameter("stat", Status.APPROVED);
        return (List<CourseForm>) super.findAll(q);
    }

    @Override
    public List<CourseForm> getNonApprovedCourseForms(long studentId, AcademicSession session) throws Exception {
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where cf.student.id =:studId and cf.student.currentSession =:sess and cf.status =:stat");
        q.setParameter("studId", studentId);
        q.setParameter("sess", session);
        q.setParameter("stat", Status.NOT_APPROVED);
        return (List<CourseForm>) super.findAll(q);
    }

    @Override
    public List<CourseForm> getCourseForms(String sString, DepartmentName departmentName, AcademicSession academicSession, Semester semester, AcademicLevel AcademicLevel,
            boolean AcademicAdviserAprroved, boolean HODAprroved, boolean FacultyOfficerAprroved) throws Exception {
System.out.println("deptname;" + departmentName.getName());
        Query q = super.getEntityManager().createQuery("select cf from CourseForm cf where (cf.student.studentName.firstName like :sString or cf.student.studentName.surname like :sString or cf.student.studentName.middleName like :sString) and (cf.student.department.departmentName=:deptname and cf.student.CurrentAcademicLevel=:lev and cf.semester=:sem and cf.academicSession=:acadSess and cf.AcademicAdviserDateStamp " + (AcademicAdviserAprroved ? " is not null" : "is null") + " and cf.facultyOfficerDateStamp " + (FacultyOfficerAprroved ? "is not null" : "is null") + " and cf.headOfDepartmentDateStamp " + (HODAprroved ? "is not null" : "is null ") + ")");
        q.setParameter("deptname", departmentName);
        q.setParameter("lev", AcademicLevel);
        q.setParameter("sem", semester);
        q.setParameter("sString", sString);
        q.setParameter("acadSess", academicSession);
        List<CourseForm> forms =(List<CourseForm>) super.findAll(q);
        if(forms!=null){
            System.out.println("Sizezzzzzzzzzzzzzzzzzz:" +forms.size()+" Semester="+semester.name()+"academicSess="+academicSession.getStartYear()+"academiclevel="+AcademicLevel.name());
        }else{
            System.out.println("FOrms nulloooooooooooooooooooooooooooo Semester="+semester.name()+"academicSess="+academicSession.getStartYear()+"academiclevel="+AcademicLevel.name());
        }
        return forms;
    }

    private boolean maxUnitLoadExceeded(int maxUnitLoad, List<Course> courses) {
        int count = 0;
        for (Course course : courses) {
            count += course.getCourseUnitLoad();
        }
        if (count > maxUnitLoad) {
            return true;
        } else {
            return false;
        }
    }

    private boolean prerequisiteCoursesPassed(Student student, List<Course> courses) throws Exception {
        ResultGradingScheme resultGradingScheme = new ResultGradingScheme();
        Grade grade = new Grade();
        List<Course> PrerequisiteCourses;
        for (Course course : courses) {
            PrerequisiteCourses = prerequisiteServiceBean.findPrerequisiteCourses(course);
            for (Course c : PrerequisiteCourses) {
                List<Object[]> academicSessionGrade = (List<Object[]>) studentResultSheetServiceBean.getGrade(student.getId(), c);
                for (Object[] obj : academicSessionGrade) { // check if he passed the course at any time (last sitting)
                    resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme((AcademicSession) obj[0]);
                    grade = (Grade) obj[1];
                }
                if (resultGradingScheme != null && grade != null) {
                    if (resultGradingScheme.getGradePoint(resultGradingScheme.getGradeLetter(grade.getTotalScore())) < resultGradingScheme.getGradePoint(resultGradingScheme.getPassGradeLetter())) { // hashcode of F > A
                        throw new Exception(c.getCourseCode().toUpperCase() + " which is a prerequisite to the course " + course.getCourseCode().toUpperCase() + " has not been passed.\n"
                                + "ALL PREREQUISITE COURSES MUST BE PASSED FIRST ");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Set<Course> getFailedCourses(Long StudentId, Semester semester) throws Exception {
        Set<Course> failedCourses = new ArraySet<>();
        ResultGradingScheme resultGradingScheme;
        float minimumPassScore;
        resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(academicSessionServiceBean.getCurrentAcademicSession());
        minimumPassScore = resultGradingScheme.getGradeLettersAndMinimumMarks().get(resultGradingScheme.getPassGradeLetter());
        /// get failed courses                        
        Query q = super.getEntityManager().createQuery("SELECT DISTINCT sg.course FROM StudentResultSheet s JOIN s.resgisteredCoursesGrades sg WHERE s.student.id =:studId AND s.semester =:sem and sg.totalScore <:minScore");
        q.setParameter("studId", StudentId);
        q.setParameter("sem", semester);
        q.setParameter("minScore", minimumPassScore);
        failedCourses.addAll((List<Course>) super.findAll(q));
        return failedCourses;
    }
}
