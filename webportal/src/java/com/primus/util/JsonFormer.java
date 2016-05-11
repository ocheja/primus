/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.primus.data.AcademicSessionLevel;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.Degree;
import com.primus.data.Department;
import com.primus.data.Faculty;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.data.Lecturer;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class JsonFormer {

    public JsonArray getDepartmentJsonForm(List<Department> departments) {
        JsonArray array = new JsonArray();
        if (departments != null) {
            for (Department department : departments) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("description", department.getDescription());
                jsonObject.addProperty("id", department.getId());
                jsonObject.addProperty("active", department.isActive());
                jsonObject.add("departmentName", new Gson().toJsonTree(department.getDepartmentName()));
                jsonObject.addProperty("facultyName", department.getFaculty().getFacultyName().getName());
                jsonObject.addProperty("lecturersSize", department.getLecturers().size());
                jsonObject.addProperty("studentsIDSize", department.getStudentsID().size());
                array.add(jsonObject);
            }
        }

        return array;
    }

    public JsonArray getCourseJsonForm(List<Course> courses) {
        JsonArray array = new JsonArray();
        if (courses != null) {
            for (Course course : courses) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("description", course.getDescription());
                jsonObject.addProperty("id", course.getId());
                jsonObject.addProperty("active", course.isActive());
                jsonObject.add("departmentName", new Gson().toJsonTree(course.getDepartmentName()));
                jsonObject.addProperty("courseTitle", course.getCourseTitle());
                jsonObject.addProperty("courseUnitLoad", course.getCourseUnitLoad());
                jsonObject.addProperty("courseCode", course.getCourseCode());
                jsonObject.add("courseLevel", new Gson().toJsonTree(course.getCourseLevel()));
                jsonObject.add("semester", new Gson().toJsonTree(course.getSemester()));
                array.add(jsonObject);
            }
        }

        return array;
    }

    public JsonArray getDegreeJsonForm(List<Degree> degrees) {
        JsonArray array = new JsonArray();
        if (degrees != null) {
            for (Degree degree : degrees) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", degree.getId());
                jsonObject.add("departmentName", new Gson().toJsonTree(degree.getDepartmentName()));
                jsonObject.add("titleOfDegree", new Gson().toJsonTree(degree.getTitleOfDegree()));
                jsonObject.addProperty("maxAllowableYears", degree.getMaxAllowableYears());
                jsonObject.addProperty("minAllowableYears", degree.getMinAllowableYears());
                array.add(jsonObject);
            }
        }

        return array;
    }

    public JsonArray getFacultyJsonForm(List<Faculty> faculties) {
        JsonArray array = new JsonArray();
        if (faculties != null) {
            for (Faculty faculty : faculties) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("Description", faculty.getDescription());
                jsonObject.addProperty("id", faculty.getId());
                jsonObject.add("facultyName", new Gson().toJsonTree(faculty.getFacultyName()));
                array.add(jsonObject);
            }
        }

        return array;
    }

    public JsonArray getLecturerJsonForm(List<Lecturer> lecturers) {
        JsonArray array = new JsonArray();
        if (lecturers != null) {
            for (Lecturer lecturer : lecturers) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("lecturerName", new Gson().toJsonTree(lecturer.getLecturerName()));
                jsonObject.addProperty("id", lecturer.getId());
                jsonObject.addProperty("active", lecturer.isActive());
                jsonObject.add("departmentName", new Gson().toJsonTree(lecturer.getDepartment().getDepartmentName()));
                jsonObject.add("title", new Gson().toJsonTree(lecturer.getTitles()));
                jsonObject.addProperty("email", lecturer.getEmail());
                array.add(jsonObject);
            }
        }

        return array;
    }

    public JsonArray getStudentJsonForm(List<Student> students) {
        JsonArray array = new JsonArray();
        if (students != null) {
            for (Student student : students) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("studentName", new Gson().toJsonTree(student.getStudentName()));
                jsonObject.addProperty("id", student.getId());
                jsonObject.addProperty("active", student.isActive());
                jsonObject.addProperty("regNumber", student.getRegNumber());
                jsonObject.addProperty("active", student.isActive());
                jsonObject.add("departmentName", new Gson().toJsonTree(student.getDepartment().getDepartmentName()));
                jsonObject.add("gender", new Gson().toJsonTree(student.getGender()));
                array.add(jsonObject);
            }
        }

        return array;
    }

    public JsonArray getCourseFormJsonForm(List<CourseForm> courseForms) {
        JsonArray array = new JsonArray();
        if (courseForms != null) {
            for (CourseForm courseForm : courseForms) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("studentName", courseForm.getStudent().getStudentName()
                        .getSurname() + " " + courseForm.getStudent().getStudentName().getFirstName()
                        + " " + courseForm.getStudent().getStudentName().getMiddleName());
                jsonObject.addProperty("id", courseForm.getId());
                jsonObject.addProperty("studentRegNum", courseForm.getStudent().getRegNumber());
                jsonObject.addProperty("academicLevel", JqueryDataTableParamModel.academicLevel.name());
                jsonObject.addProperty("academicSession", courseForm.getAcademicSession()
                        .getStartYear() + "-" + courseForm.getAcademicSession().getEndYear());
                jsonObject.addProperty("semester", courseForm.getSemester().toString());
                array.add(jsonObject);
            }
        }

        return array;
    }

    private AcademicLevel academicLevel(CourseForm courseForm) {
        AcademicLevel aLevel = null;
        List<AcademicSessionLevel> sessionLevel = courseForm.getStudent().getAcademicSessionLevel();
       for (AcademicSessionLevel academicSessionLevel :sessionLevel ) {
            if (courseForm.getAcademicSession().getId() == academicSessionLevel.getAcademicSession().getId()) {
                aLevel =academicSessionLevel.getAcademicLevel();
            }
        }
 return aLevel;
    }
}
