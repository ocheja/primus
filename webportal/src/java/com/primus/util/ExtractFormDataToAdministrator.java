package com.primus.util;

import com.primus.data.AcademicSessionLevel;
import com.primus.data.Administrator;
import com.primus.data.Lecturer;
import com.primus.data.Name;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.enums.AdminType;
import com.primus.enums.Gender;
import com.primus.enums.Title;
import com.primus.interfaces.StudentService;
import com.primus.serviceBean.AcademicSessionLevelServiceBean;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named("extractFormDataToAdministrator")
public class ExtractFormDataToAdministrator {

    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    AcademicSessionLevelServiceBean academicSessionLevelServiceBean;
    @Autowired
    StudentService studentServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;

    public Administrator adminFormFieldData(Map<String, Object> fieldData)
            throws NumberFormatException, Exception {
        Administrator administrator = new Administrator();
        for (Entry<String, Object> entry : fieldData.entrySet()) {
            String name = entry.getKey();
            Object orgValue = entry.getValue();
            String value = null;
            byte[] byteVaue = null;
            if (orgValue instanceof byte[]) {
                byteVaue = (byte[]) entry.getValue();
            } else {
                value = (String) entry.getValue();
            }
            try {
            } catch (Exception e) {
            }
            if (name.equalsIgnoreCase("firstName")) {
                administrator.setFirstName(value);
            } else if (name.equalsIgnoreCase("surname")) {
                administrator.setLastName(value);
            } else if (name.equalsIgnoreCase("middleName")) {
                administrator.setMiddleName(value);
            } else if (name.equalsIgnoreCase("email")) {
                administrator.setEmailAddress(value);
            } else if (name.equalsIgnoreCase("password")) {
                administrator.setPassword(value.hashCode());
            } else if (name.equalsIgnoreCase("phoneNumber")) {
                administrator.setPhoneNumber(value);
            } else if (name.equalsIgnoreCase("gender")) {
//				if (value.equalsIgnoreCase("male"))
//					administrator.setGender("Male");
//                                else if (value.equalsIgnoreCase("female"))
//					administrator.setGender("Female");
            } else if (name.equalsIgnoreCase("adminType")) {
                System.out.println("Entered Dmin type: " + value);
                if (value.equalsIgnoreCase("superAdmin")) {
                    administrator.setAdminType(AdminType.Super);
                } else if (value.equalsIgnoreCase("moderatorAdmin")) {
                    administrator.setAdminType(AdminType.moderator);
                }
            } else if (name.equalsIgnoreCase("profileimage")) {
                administrator.setImage(byteVaue);
            }
        }
        return administrator;

    }

    public Lecturer lecturerFormormFieldData(Map<String, Object> fieldData)
            throws NumberFormatException, Exception {
        Lecturer lecturer = new Lecturer();
        Name lecturerName = new Name();
        List<Title> titles = new ArrayList<>();
        lecturer.setTitles(titles);
        for (Entry<String, Object> entry : fieldData.entrySet()) {
            String name = entry.getKey();
            Object orgValue = entry.getValue();
            String value = null;
            byte[] byteValue = null;
            if (orgValue instanceof byte[]) {
                byteValue = (byte[]) entry.getValue();
            } else if (orgValue instanceof String) {
                value = (String) entry.getValue();
            }
            try {
            } catch (Exception e) {
            }
            if (name.equalsIgnoreCase("firstName")) {
                lecturerName.setFirstName(value);
            } else if (name.equalsIgnoreCase("surname")) {
                lecturerName.setSurname(value);
            } else if (name.equalsIgnoreCase("middleName")) {
                lecturerName.setMiddleName(value);
            } else if (name.equalsIgnoreCase("email")) {
                lecturer.setEmail(value);
            } else if (name.equalsIgnoreCase("password")) {
                lecturer.setPassword(value.hashCode());
            } else if (name.equalsIgnoreCase("phoneNumber")) {
                lecturer.setPhoneNumber(value);
            } else if (name.equalsIgnoreCase("gender")) {
//				if (value.equalsIgnoreCase("male"))
//					administrator.setGender("Male");
//                                else if (value.equalsIgnoreCase("female"))
//					administrator.setGender("Female");
            } else if (name.contains("lecturertitles")) {
                titles.add(Title.valueOf(value));
            } else if (name.equalsIgnoreCase("department")) {
                lecturer.setDepartment(departmentServiceBean.findDepartment(Long.valueOf(value)));
            } else if (name.equalsIgnoreCase("careerdescription")) {
                lecturer.setCareerDescription(value);
            } else if (name.equalsIgnoreCase("profileimage")) {
                lecturer.setImage(byteValue);
            }
        }
        lecturer.setLecturerName(lecturerName);
        lecturer.setTitles(titles);
        return lecturer;

    }

    public Student studentFormormFieldData(Map<String, Object> fieldData)
            throws NumberFormatException, Exception {
        Student student = new Student();
        Name studentName = new Name();
        for (Entry<String, Object> entry : fieldData.entrySet()) {
            String name = entry.getKey();
            Object orgValue = entry.getValue();
            String value = null;
            byte[] byteValue = null;
            if (orgValue instanceof byte[]) {
                byteValue = (byte[]) entry.getValue();
            } else if (orgValue instanceof String) {
                value = (String) entry.getValue();
            }
            try {
            } catch (Exception e) {
            }
            if (name.equalsIgnoreCase("firstName")) {
                studentName.setFirstName(value);
            } else if (name.equalsIgnoreCase("surname")) {
                studentName.setSurname(value);
            } else if (name.equalsIgnoreCase("middleName")) {
                studentName.setMiddleName(value);
            } else if (name.equalsIgnoreCase("email")) {
                student.setEmail(value);
            } else if (name.equalsIgnoreCase("password")) {
                student.setPassword(value.hashCode());
            }else if (name.equalsIgnoreCase("jregnum")) {
                student.setJambRegNumber(value);
            }else if (name.equalsIgnoreCase("dateadmitted")) {
                int yearAdmitted = Integer.valueOf(value.split("-")[2]);
                int matricSerial = studentServiceBean.generateMatricSerialNumber(yearAdmitted);
                student.setMatricSerialNumber(matricSerial);
                student.setYearAdmitted(yearAdmitted);
            }else if (name.equalsIgnoreCase("programme")) {
                student.setProgramme(value);
            }else if (name.equalsIgnoreCase("level")) {
                student.setCurrentAcademicLevel(AcademicLevel.valueOf(value));
               List<AcademicSessionLevel> academicSessionLevels = new ArrayList<>();
               AcademicSessionLevel academicSessionLevel =new AcademicSessionLevel();
               academicSessionLevel .setAcademicLevel(AcademicLevel.valueOf(value));
               academicSessionLevel .setAcademicSession(academicSessionServiceBean.getCurrentAcademicSession());
                academicSessionLevel.setStudent(student);
//                academicSessionLevelServiceBean.create(academicSessionLevel);
//               AcademicSessionLevel academicSessionLevelnew =  academicSessionLevelServiceBean.findAcademicSessionLevel(student.getId(), AcademicLevel.valueOf(value));
                academicSessionLevels.add(academicSessionLevel);
               //map.put(academicSessionServiceBean.getCurrentAcademicSession().getId(),AcademicLevel.valueOf(value));
                if(student.getAcademicSessionLevel()==null)
                    student.setAcademicSessionLevel(academicSessionLevels);
                else
                student.getAcademicSessionLevel().addAll(academicSessionLevels);
            } else if (name.equalsIgnoreCase("phoneNumber")) {
                student.setPhoneNumber(value);
            } else if (name.equalsIgnoreCase("gender")) {
                student.setGender((value.equalsIgnoreCase("male")) ? Gender.MALE : Gender.FEMALE);
            } else if (name.equalsIgnoreCase("department")) {
                student.setDepartment(departmentServiceBean.findDepartment(Long.valueOf(value)));
            } else if (name.equalsIgnoreCase("profileimage")) {
                student.setStudentImage(byteValue);
            }
        }
        student.setStudentName(studentName);
        return student;

    }
}
