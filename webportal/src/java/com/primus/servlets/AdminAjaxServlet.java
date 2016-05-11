/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.Degree;
import com.primus.data.DegreeRequirement;
import com.primus.data.Department;
import com.primus.data.DepartmentName;
import com.primus.data.Faculty;
import com.primus.data.FacultyName;
import com.primus.data.Lecturer;
import com.primus.data.LecturerType;
import com.primus.data.ResultGradingScheme;
import com.primus.data.Select2Model;
import com.primus.data.StatusMessage;
import com.primus.enums.AcademicLevel;
import com.primus.enums.LecturerPosition;
import com.primus.enums.Semester;
import com.primus.enums.TitleOfDegree;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.DegreeService;
import com.primus.interfaces.LecturerService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DegreeRequirementServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
import com.primus.serviceBean.ResultGradingSchemeServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class AdminAjaxServlet extends HttpServlet {

    @Autowired
    FacultyServiceBean facultyServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    CourseService courseServiceBean;
    @Autowired
    ResultGradingSchemeServiceBean resultGradingSchemeServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    DegreeService degreeServiceBean;
    @Autowired
    DegreeRequirementServiceBean degreeRequirementServiceBean;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAjaxServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action: " + action);
        List<Select2Model> array = new ArrayList<>();
        if (action != null) {
            String query = request.getParameter("q");
            if (action.equalsIgnoreCase("departmentNames")) {
                System.out.println("Dept: Query=" + query);
                List departments = null;
                try {
                    departments = departmentServiceBean.findLikeDepartmentNameName("%" + query + "%");
                } catch (Exception ex) {
                    Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (departments != null) {
                    System.out.println("setting dept: " + departments.size());
                    Iterator deptIterator = departments.iterator();
                    while (deptIterator.hasNext()) {
                        Select2Model select2Model = new Select2Model();
                        DepartmentName department = (DepartmentName) deptIterator.next();
                        select2Model.setId(department.getName());
                        select2Model.setText(department.getName());
                        array.add(select2Model);
                    }
                }
            } else if (action.equalsIgnoreCase("facultyNames")) {
                List faculties = null;
                try {
                    faculties = facultyServiceBean.findLikeFacultyNameName("%" + query + "%");
                } catch (Exception ex) {
                    Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (faculties != null) {
                    Iterator facIterator = faculties.iterator();
                    while (facIterator.hasNext()) {
                        Select2Model select2Model = new Select2Model();
                        FacultyName faculty = (FacultyName) facIterator.next();
                        select2Model.setId(faculty.getName());
                        select2Model.setText(faculty.getName());
                        array.add(select2Model);
                    }
                }
            }
        }

        try {
            TypeToken<List<Select2Model>> token = new TypeToken<List<Select2Model>>() {
            };
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResp = new Gson().toJson(array, token.getType());
            PrintWriter out = response.getWriter();
            System.out.println("Object: " + jsonResp);
            out.write(jsonResp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StatusMessage sm = new StatusMessage();
        String action = request.getParameter("action");
        System.out.println("Action: " + action);
        if (action != null) {
            if (action.equalsIgnoreCase("createFaculty")) {
                String facultyName = request.getParameter("facultyName");
                String description = request.getParameter("description");
                String[] departments = request.getParameterValues("departments");
                Enumeration<String> e = request.getParameterNames();
                while (e.hasMoreElements()) {
                    String ee = e.nextElement();
                    System.out.println("Parameter: " + ee);
                }
                if (facultyName == null) {
                    sm.setMessage("Faculty Name can not be null");
                } else if (description == null) {
                    sm.setMessage("Description of faculty can not be null");
                } else {
                    Faculty faculty = new Faculty();
                    faculty.setFacultyName(new FacultyName(facultyName.trim().toLowerCase()));
                    faculty.setDescription(description);
                    if (departments != null) {
                        for (String name : departments) {
                        }
                        faculty.setDepartments(null);
                    }
                    try {
                        facultyServiceBean.create(faculty);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("createDepartment")) {
                String id = request.getParameter("faculty");
                String description = request.getParameter("description");
                String departmentName = request.getParameter("departmentName");
                Faculty faculty = null;
                if (id != null) {
                    System.out.println("Got herereerrrrrrrrrrrrrr");
                    try {
                        faculty = facultyServiceBean.findFaculty(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (departmentName == null) {
                    sm.setMessage("Department name can not be null");
                } else if (description == null) {
                    sm.setMessage("Description of department can not be null");
                } else if (faculty == null) {
                    sm.setMessage("You must select a valid faculty");
                } else {
                    Department department = new Department();
                    department.setDepartmentName(new DepartmentName(departmentName.trim().toLowerCase()));
                    department.setDescription(description);
                    department.setFaculty(faculty);
                    try {
                        departmentServiceBean.create(department);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("createCourse")) {
                String deptID = request.getParameter("department");
                String description = request.getParameter("description");
                String courseTitle = request.getParameter("courseTitle");
                String courseCode = request.getParameter("courseCode");
                String courseUnit = request.getParameter("courseUnit");
                String courseLevel = request.getParameter("courseLevel");
                String semesterName = request.getParameter("semester");
                String elective = request.getParameter("elective");
                String[] preCourses = request.getParameterValues("precourses");
                Department department = null;
                Integer courseunit = null;
                com.primus.enums.AcademicLevel level = null;
                Semester semester = null;
                if (deptID != null) {
                    System.out.println("Got here dept");
                    try {
                        department = departmentServiceBean.findDepartment(Long.valueOf(deptID));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        courseunit = Integer.valueOf(courseUnit);
                    } catch (NumberFormatException nfe) {
                        courseunit = null;
                    }
                    try {
                        level = AcademicLevel.valueOf(courseLevel);
                    } catch (Exception nfe) {
                        level = null;
                    }
                    try {
                        semester = Semester.valueOf(semesterName);
                    } catch (Exception nfe) {
                        courseunit = null;
                    }

                }
                if (department == null) {
                    sm.setMessage("Course department can not be null");
                } else if (description == null) {
                    sm.setMessage("Description of course can not be null");
                } else if (courseTitle == null) {
                    sm.setMessage("Title of course can not be null");
                } else if (courseunit == null) {
                    sm.setMessage("Unit of course can not be null");
                } else if (level == null) {
                    sm.setMessage("Level of course can not be null");
                } else if (semester == null) {
                    sm.setMessage("Semester of course can not be null");
                } else {
                    List<Course> courses = new ArrayList<>();
                    Course course = new Course();
                    if (preCourses != null) {
                        for (String courseId : preCourses) {
                            try {
                                courses.add(courseServiceBean.findCourse(courseId));
                            } catch (Exception ex) {
                                Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    course.setActive(true);
                    course.setCourseCode(courseCode);
                    course.setElective(elective == null ? false : true);
                    course.setCourseUnitLoad(courseunit);
                    course.setCourseLevel(level);
                    course.setDescription(description);
                    course.setCourseTitle(courseTitle);
                    course.setRequiredLectureHours(courseunit);
                    course.setSemester(semester);
                    course.setDepartmentName(department.getDepartmentName());
                    try {
                        courseServiceBean.create(course);
                        courseServiceBean.setPrerequisites(courseServiceBean.findCourse(courseCode), courses);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("addlecturercourse")) {
                String owningLecturer = request.getParameter("owningLecturer");
                String[] courses = request.getParameterValues("courses");
                Lecturer lecturer;
                if (owningLecturer != null && courses != null) {
                    try {
                        lecturer = lecturerServiceBean.findLecturer(Long.valueOf(owningLecturer));
                        if (lecturer != null) {
                            List<Course> coursesList = new ArrayList<>();
                            for (String course : courses) {
                                Course courseToAdd = courseServiceBean.findCourse(Long.valueOf(course));
                                coursesList.add(courseToAdd);
                            }
                            courseServiceBean.assignCourseToLecturer(coursesList, lecturer);
                            sm.setMessage("Successful");
                        } else {
                            sm.setMessage("Lecturer not found");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage(ex.getLocalizedMessage());
                    }


                } else {
                    sm.setMessage("Please fill the appropriate fields.");
                }
            } else if (action.equalsIgnoreCase("lecturer_course_delete")) {
                String owningLecturer = request.getParameter("lecturerid");
                String course = request.getParameter("ownedid");
                if (owningLecturer != null && course != null) {
                    try {
                        Lecturer lecturer = lecturerServiceBean.findLecturer(Long.valueOf(owningLecturer));
                        List<Course> coursesList = new ArrayList<>();
                        Course courseToAdd = courseServiceBean.findCourse(Long.valueOf(course));
                        coursesList.add(courseToAdd);
                        courseServiceBean.removeCourseFromLecturer(coursesList, lecturer);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage(ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("Please fill the appropriate fields.");
                }
            } else if (action.equalsIgnoreCase("addprecourse")) {
                String owningCourse = request.getParameter("owningCourse");
                String[] preCourses = request.getParameterValues("precourses");
                Course course = null;
                if (owningCourse != null) {
                    System.out.println("Got here course owner not null");
                    try {
                        course = courseServiceBean.findCourse(Long.valueOf(owningCourse));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (course == null) {
                    sm.setMessage("Parent Course can not be null");
                } else if (preCourses == null) {
                    sm.setMessage("prerequisite courses can not be null");
                } else {
                    List<Course> courses = new ArrayList<>();
                    for (String courseId : preCourses) {
                        try {
                            courses.add(courseServiceBean.findCourse(Long.valueOf(courseId)));
                        } catch (Exception ex) {
                            Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        System.out.println("Number of courses: " + courses.size());
                        courseServiceBean.setPrerequisites(course, courses);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("addlecturerposition")) {
                String owningLecturer = request.getParameter("owningLecturer");
                String academicLevel = request.getParameter("academiclevel");
                String[] positions = request.getParameterValues("positions");
                Lecturer lecturer = null;
                if (owningLecturer != null) {
                    System.out.println("Got here lecturer owner not null");
                    try {
                        lecturer = lecturerServiceBean.findLecturer(Long.valueOf(owningLecturer));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (lecturer == null) {
                    sm.setMessage("Lecturer can not be null");
                } else if (positions == null) {
                    sm.setMessage("positions can not be null");
                } else {
                    if (lecturer.getLecturerType() == null) {
                        lecturer.setLecturerType(new LecturerType());
                    }
                    Set<LecturerPosition> lecturerPositions = lecturer.getLecturerType().getPositions();
                    if (lecturerPositions == null) {
                        lecturerPositions = new HashSet<>();
                    }
                    for (String position : positions) {
                        try {
                            if (position.equalsIgnoreCase("Academic_Adviser")) {
                                lecturer.getLecturerType().setAssignedStudentLevel(AcademicLevel.valueOf(academicLevel));
                            }
                            lecturerPositions.add(LecturerPosition.valueOf(position));
                        } catch (Exception ex) {
                            Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        lecturer.getLecturerType().setPosition(lecturerPositions);
                        lecturer.getLecturerType().setDepartment(lecturer.getDepartment());
                        lecturerServiceBean.edit(lecturer);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("lecturer_position_delete")) {
                String owningLecturer = request.getParameter("lecturerid");
                String position = request.getParameter("ownedid");
                Lecturer lecturer = null;
                if (owningLecturer != null) {
                    System.out.println("Got here lecturer owner not null");
                    try {
                        lecturer = lecturerServiceBean.findLecturer(Long.valueOf(owningLecturer));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (lecturer == null) {
                    sm.setMessage("Lecturer can not be null");
                } else if (position == null) {
                    sm.setMessage("positions can not be null");
                } else {
                    if (lecturer.getLecturerType() == null) {
                        lecturer.setLecturerType(new LecturerType());
                    }
                    Set<LecturerPosition> lecturerPositions = lecturer.getLecturerType().getPositions();
                    if (lecturerPositions == null) {
                        lecturerPositions = new HashSet<>();
                    }
                    lecturerPositions.remove(LecturerPosition.valueOf(position));
                    try {
                        lecturer.getLecturerType().setPosition(lecturerPositions);
                        lecturerServiceBean.edit(lecturer);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("pre_course_delete")) {
                String owningCourse = request.getParameter("owningid");
                String preCourse = request.getParameter("preid");
                Course course = null;
                if (owningCourse != null) {
                    System.out.println("Got here course owner not null");
                    try {
                        course = courseServiceBean.findCourse(Long.valueOf(owningCourse));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (course == null) {
                    sm.setMessage("Parent Course can not be null");
                } else if (preCourse == null) {
                    sm.setMessage("prerequisite course can not be null");
                } else {
                    List<Long> courses = new ArrayList<>();
                    try {
                        courses.add(Long.valueOf(preCourse));
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        courseServiceBean.removePrerequisites(course, courses);
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }
                }
            } else if (action.equalsIgnoreCase("createnewsession")) {
                String startYear = request.getParameter("startYear");
                String endYear = request.getParameter("endYear");
                String useExistingGradingScheme = request.getParameter("useExistingGradingScheme");
                String gradingScheme = request.getParameter("gradingscheme");
                String numberOfGradeLetters = request.getParameter("numberOfGradeLetters");
                String failGradeLetter = request.getParameter("failgradeletter");
                String maxUnits = request.getParameter("maximumUnits");
                boolean failGradeFound = false;
                if (endYear == null) {
                    sm.setMessage("End Year can not be null");
                } else if (startYear == null) {
                    sm.setMessage("Start Year can not be null");
                } else if (useExistingGradingScheme != null && gradingScheme == null) {
                    sm.setMessage("You must select an existing grading scheme or create a new one");
                } else if (useExistingGradingScheme == null && numberOfGradeLetters == null) {
                    sm.setMessage("You must select an existing grading scheme or create a new one");
                } else {
                    ResultGradingScheme resultGradingScheme = new ResultGradingScheme();
                    if (numberOfGradeLetters != null) {
                        int count = Integer.valueOf(numberOfGradeLetters);
                        Map<String, String> gradeLettersAndDescriptions = new LinkedHashMap<>();
                        Map<String, Float> gradeLettersAndGradePoints = new LinkedHashMap<>();
                        Map<String, Float> gradeLettersAndMinMark = new LinkedHashMap<>();
                        Map<Float, String> gradePointsDescription = new LinkedHashMap<>();
                        for (int i = 1; i <= count; i++) {
                            String gradepoint = request.getParameter("gradepoint" + i);
                            String gradeletter = request.getParameter("gradeletter" + i);
                            if (failGradeLetter != null && failGradeLetter.equalsIgnoreCase(gradeletter)) {
                                failGradeFound = true;
                            }
                            String minmark = request.getParameter("minmark" + i);
                            String gradeletterdescription = request.getParameter("gradeletterdescription" + i);
                            gradeLettersAndDescriptions.put(gradeletter, gradeletterdescription);
                            gradeLettersAndGradePoints.put(gradeletter, Float.valueOf(gradepoint));
                            gradeLettersAndMinMark.put(gradeletter, Float.valueOf(minmark));
                        }
                        resultGradingScheme.setGradeLetterDescriptions(gradeLettersAndDescriptions);
                        resultGradingScheme.setGradeLettersAndGradePoints(gradeLettersAndGradePoints);
                        resultGradingScheme.setGradeLettersAndMinimumMarks(gradeLettersAndMinMark);
                        resultGradingScheme.setGradePointsDescriptions(gradePointsDescription);
                    } else {
                        resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(Long.valueOf(gradingScheme));
                    }
                    if (failGradeFound || useExistingGradingScheme != null) {
                        AcademicSession academicSession = new AcademicSession();
                        academicSession.setStartYear(Integer.valueOf(startYear));
                        academicSession.setEndYear(Integer.valueOf(endYear));
                        resultGradingScheme.setAcademicSession(academicSession);
                        resultGradingScheme.setPassGradeLetter(failGradeLetter);
                        resultGradingScheme.setMaximumUnitLoad(Integer.valueOf(maxUnits));
                        try {
                            academicSessionServiceBean.create(academicSession);
                            AcademicSession academicSession1 = academicSessionServiceBean
                                    .findAcademicSession(startYear, endYear);
                            resultGradingScheme.setAcademicSession(academicSession1);
                            resultGradingSchemeServiceBean.create(resultGradingScheme);
                            sm.setMessage("Successful");
                        } catch (Exception ex) {
                            Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                            sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                        }
                    } else {
                        sm.setMessage("The fail grade letter provided is not valid.");
                    }
                }
            } else if (action.equalsIgnoreCase("createnewdegreerequirement")) {
                String minYear = request.getParameter("minYear");
                String deptID = request.getParameter("department");
                String maxYear = request.getParameter("maxYear");
                String titleOfDegree = request.getParameter("titleofdegree");
                String numberOfLevels = request.getParameter("numberOfLevels");
                if (minYear == null) {
                    sm.setMessage("minimum Year can not be null");
                } else if (maxYear == null) {
                    sm.setMessage("maximum Year can not be null");
                } else if (numberOfLevels == null || numberOfLevels.equals("0")) {
                    sm.setMessage("A degree can not have zero required level");
                } else {
                    System.out.println("Max:" + maxYear + " min:" + minYear);
                    Degree degree = new Degree();
                    degree.setMaxAllowableYears(Integer.valueOf(maxYear));
                    degree.setMinAllowableYears(Integer.valueOf(minYear));
                    Department department;
                    try {
                        department = departmentServiceBean.findDepartment(Long.valueOf(deptID));
                        degree.setDepartmentName(department.getDepartmentName());
                        degree.setTitleOfDegree(TitleOfDegree.valueOf(titleOfDegree));

                        degreeServiceBean.create(degree);
                        degree = degreeServiceBean.findDegree(degree.getTitleOfDegree(), degree.getDepartmentName());

                        //degreeRequirementServiceBean.create(null);
                        //  degree1.setDegreeRequirement(degreeRequirements);
                        // degreeServiceBean.edit(degree1);
                        //  sm.setMessage("Successful");

                        // List<DegreeRequirement> degreeRequirements = new ArrayList<>();
                        int count = Integer.valueOf(numberOfLevels);
                        System.out.println("About degree loop");
                        for (int i = 1; i <= count; i++) {
                            System.out.println("Degree req loop");
                            DegreeRequirement degreeRequirement = new DegreeRequirement();
                            String level = request.getParameter("level" + i);
                            String electives = request.getParameter("electivesforlevel" + i);
                            System.out.println("Level:" + level);
                            degreeRequirement.setNumberOfElectiveCourses(Integer.valueOf(electives));
                            degreeRequirement.setStudLevel(AcademicLevel.valueOf(level));
                            String courses[] = request.getParameterValues("coursesforlevel" + i);
                            Set<Course> coursesSet = new HashSet<>();
                            System.out.println("About course loop");
                            for (String course : courses) {
                                System.out.println("In course loop");
                                try {
                                    coursesSet.add(courseServiceBean.findCourse(Long.valueOf(course)));
                                } catch (Exception ex) {
                                    Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            degreeRequirement.setCoursesForLevel(coursesSet);
                            degreeRequirement.setDegree(degree);
                            degreeRequirementServiceBean.create(degreeRequirement);
                            //degreeRequirements.add(degreeRequirement);
                        }
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred. " + ex.getLocalizedMessage());
                    }

                }
            } else if (action.equalsIgnoreCase("degree_course_delete")) {
                String owningDegree = request.getParameter("degreeid");
                String course = request.getParameter("ownedid");
                if (owningDegree != null && course != null) {
                    try {
                        Degree degree = degreeServiceBean.findDegree(Long.valueOf(owningDegree));
                        if (degree != null) {
//                            degreeServiceBean.destroy(degree.getId());
                        }
                        sm.setMessage("Successful");
                    } catch (Exception ex) {
                        Logger.getLogger(AdminAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage(ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("Please fill the appropriate fields.");
                }
            }
        }

        try {
            System.out.println("Message: " + sm.getMessage());
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(sm);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
