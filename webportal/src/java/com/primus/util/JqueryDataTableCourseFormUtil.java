/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primus.data.CourseForm;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.data.Student;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.LecturerService;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Named("jqueryDataTableCourseFormUtil")
public class JqueryDataTableCourseFormUtil {

    @Autowired
    CourseRegistrationService courseRegistrationServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;

    public String processDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%" + searchParam + "%";
        int iTotalRecords = 0; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword
        List courseforms = null;
        try {
            courseforms = courseRegistrationServiceBean.getCourseForms(searchParam,
                    JqueryDataTableParamModel.departmentName, JqueryDataTableParamModel.academicSession, JqueryDataTableParamModel.semester,
                    JqueryDataTableParamModel.academicLevel, JqueryDataTableParamModel.academicAdviserApproved, JqueryDataTableParamModel.HODApproved, JqueryDataTableParamModel.FOApproved);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableCourseFormUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (courseforms != null) {
            iTotalRecords = courseforms.size();
            iTotalDisplayRecords = param.iTotalDisplayRecords;
            System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
            final int sortColumnIndex = param.iSortColumnIndex;
            final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

            Collections.sort(courseforms, new Comparator<CourseForm>() {
                @Override
                public int compare(CourseForm courseForm1, CourseForm courseForm2) {
                    switch (sortColumnIndex) {
                        case 0:
                            return courseForm1.getStudent().getStudentName().getSurname().compareTo(courseForm2.getStudent().getStudentName().getSurname()) * sortDirection;
                        case 1:
                            return courseForm1.getStudent().getStudentName().getFirstName().compareTo(courseForm2.getStudent().getStudentName().getFirstName()) * sortDirection;
                        case 2:
                            return courseForm1.getStudent().getStudentName().getMiddleName().compareTo(courseForm2.getStudent().getStudentName().getMiddleName()) * sortDirection;
                    }
                    return 0;
                }
            });

            if (courseforms.size() < param.iDisplayStart + param.iDisplayLength) {
                courseforms = courseforms.subList(param.iDisplayStart, courseforms.size());
            } else {
                courseforms = courseforms.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
            }
            JsonFormer former = new JsonFormer();
            TypeToken<List<?>> token = new TypeToken<List<?>>() {
            };

            param.aaData = new Gson().fromJson(former.getCourseFormJsonForm(courseforms), token.getType());
            param.iTotalDisplayRecords = iTotalDisplayRecords;
            param.iTotalRecords = iTotalRecords;
            String jSonResponse = new Gson().toJson(param);
            return jSonResponse;
        }

        return null;
//        try {
//            JsonObject jsonResponse = new JsonObject();
//            jsonResponse.addProperty("sEcho", sEcho);
//            jsonResponse.addProperty("iTotalRecords", iTotalRecords);
//            jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
//            Gson gson = new Gson();
//            jsonResponse.add("aaData", gson.toJsonTree(companies));
//
//            response.setContentType("application/Json");
//            response.getWriter().print(jsonResponse.toString());
//
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//            response.setContentType("text/html");
//            response.getWriter().print(e.getMessage());
//        }
    }

    public String processDataTableCourseStudent(JqueryDataTableParamModel param, Long lecturerId) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%" + searchParam + "%";
        int iTotalRecords = 0; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword
        List<Student> students = null;
        try {
            students = lecturerServiceBean.getRegisteredStudentsForCourse(lecturerId, JqueryDataTableParamModel.course, JqueryDataTableParamModel.academicSession);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableCourseFormUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (students != null) {
            iTotalRecords = students.size();
            iTotalDisplayRecords = param.iTotalDisplayRecords;
            System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
            final int sortColumnIndex = param.iSortColumnIndex;
            final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    switch (sortColumnIndex) {
                        case 0:
                            return student1.getDepartment().getDepartmentName().getName().compareTo(student2.getDepartment().getDepartmentName().getName()) * sortDirection;
                        case 1:
                            return student1.getStudentName().getSurname().compareTo(student2.getStudentName().getSurname()) * sortDirection;
                        case 2:
                            return student1.getStudentName().getFirstName().compareTo(student2.getStudentName().getFirstName()) * sortDirection;
                        case 3:
                            return student1.getStudentName().getMiddleName().compareTo(student2.getStudentName().getMiddleName()) * sortDirection;
                    }
                    return 0;
                }
            });

            if (students.size() < param.iDisplayStart + param.iDisplayLength) {
                students = students.subList(param.iDisplayStart, students.size());
            } else {
                students = students.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
            }
            JsonFormer former = new JsonFormer();
            TypeToken<List<?>> token = new TypeToken<List<?>>() {
            };

            param.aaData = new Gson().fromJson(former.getStudentJsonForm(students), token.getType());
            param.iTotalDisplayRecords = iTotalDisplayRecords;
            param.iTotalRecords = iTotalRecords;
            String jSonResponse = new Gson().toJson(param);
            return jSonResponse;
        }

        return null;
//        try {
//            JsonObject jsonResponse = new JsonObject();
//            jsonResponse.addProperty("sEcho", sEcho);
//            jsonResponse.addProperty("iTotalRecords", iTotalRecords);
//            jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
//            Gson gson = new Gson();
//            jsonResponse.add("aaData", gson.toJsonTree(companies));
//
//            response.setContentType("application/Json");
//            response.getWriter().print(jsonResponse.toString());
//
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//            response.setContentType("text/html");
//            response.getWriter().print(e.getMessage());
//        }
    }
}
