/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Department;
import com.primus.data.DepartmentName;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Faculty;
import com.primus.data.Student;
import java.util.ArrayList;
import java.util.List;
import com.primus.data.Lecturer;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Named("departmentServiceBean")
public class DepartmentServiceBean extends PrimusBasePersistenceService {

    public void createDepartmentName(DepartmentName deptN) throws Exception {
        if (findDepartmentName(deptN.getName()) == null) {
            super.storeEntity(deptN);
        }
    }

    public void create(Department department) throws  Exception {
        if(department.getFaculty()== null || department.getFaculty().getId()== null){ // check if faculty field is null or faculty.ID ==null
            throw new Exception("Department must belong to an existing Faculty.  ");
        }
        if (department.getStudentsID() == null) {
            department.setStudentsID(new ArrayList<Student>());
        }
        if (department.getLecturers() == null) {
            department.setLecturers(new ArrayList<Lecturer>());
        }
        EntityManager em;
        String str = department.getDepartmentName().getName();
        DepartmentName departmentName = findDepartmentName(str);
        if (departmentName == null) { // check if department name already exists, if not create
           department.setDepartmentName((DepartmentName) super.storeEntity(department.getDepartmentName()));
        } else {
           department.setDepartmentName(departmentName);
        }
        if (findDepartment(department.getDepartmentName().getName()) == null) {  //check if faculty with Name exits if not create
          
        em = super.getEntityManager();
        Faculty faculty = department.getFaculty();
        if (faculty != null) {
            faculty = em.getReference(faculty.getClass(), faculty.getId());
            department.setFaculty(faculty);
        }
        List<Student> attachedStudentsID = new ArrayList<Student>();
        for (Student studentsIDStudentToAttach : department.getStudentsID()) {
            studentsIDStudentToAttach = em.getReference(studentsIDStudentToAttach.getClass(), studentsIDStudentToAttach.getId());
            attachedStudentsID.add(studentsIDStudentToAttach);
        }
        department.setStudentsID(attachedStudentsID);
        List<Lecturer> attachedLecturer = new ArrayList<Lecturer>();
        for (Lecturer lecturerLecturerToAttach : department.getLecturers()) {
            lecturerLecturerToAttach = em.getReference(lecturerLecturerToAttach.getClass(), lecturerLecturerToAttach.getId());
            attachedLecturer.add(lecturerLecturerToAttach);
        }
        department.setLecturers(attachedLecturer);
        em.persist(department);
        if (faculty != null) {
            faculty.getDepartments().add(department);
            faculty = em.merge(faculty);
        }
        for (Student studentsIDStudent : department.getStudentsID()) {
            Department oldDepartmentOfStudentsIDStudent = studentsIDStudent.getDepartment();
            studentsIDStudent.setDepartment(department);
            studentsIDStudent = em.merge(studentsIDStudent);
            if (oldDepartmentOfStudentsIDStudent != null) {
                oldDepartmentOfStudentsIDStudent.getStudentsID().remove(studentsIDStudent);
                oldDepartmentOfStudentsIDStudent = em.merge(oldDepartmentOfStudentsIDStudent);
            }
        }
        for (Lecturer lecturerLecturer : department.getLecturers()) {
            Department oldDepartmentOfLecturerLecturer = lecturerLecturer.getDepartment();
            lecturerLecturer.setDepartment(department);
            lecturerLecturer = em.merge(lecturerLecturer);
            if (oldDepartmentOfLecturerLecturer != null) {
                oldDepartmentOfLecturerLecturer.getLecturers().remove(lecturerLecturer);
                oldDepartmentOfLecturerLecturer = em.merge(oldDepartmentOfLecturerLecturer);
            }
        }
         } else {
            throw new Exception("The department with name : " + department.getDepartmentName().getName() + " already exists");
        }
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void edit(Department department) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;

        em = super.getEntityManager();
        Department persistentDepartment = em.find(Department.class, department.getId());
        Faculty facultyOld = persistentDepartment.getFaculty();
        Faculty facultyNew = department.getFaculty();
        List<Student> studentsIDOld = persistentDepartment.getStudentsID();
        List<Student> studentsIDNew = department.getStudentsID();
        List<Lecturer> lecturerOld = persistentDepartment.getLecturers();
        List<Lecturer> lecturerNew = department.getLecturers();
        if (facultyNew != null) {
            facultyNew = em.getReference(facultyNew.getClass(), facultyNew.getId());
            department.setFaculty(facultyNew);
        }
        List<Student> attachedStudentsIDNew = new ArrayList<Student>();
        for (Student studentsIDNewStudentToAttach : studentsIDNew) {
            studentsIDNewStudentToAttach = em.getReference(studentsIDNewStudentToAttach.getClass(), studentsIDNewStudentToAttach.getId());
            attachedStudentsIDNew.add(studentsIDNewStudentToAttach);
        }
        studentsIDNew = attachedStudentsIDNew;
        department.setStudentsID(studentsIDNew);
        List<Lecturer> attachedLecturerNew = new ArrayList<Lecturer>();
        for (Lecturer lecturerNewLecturerToAttach : lecturerNew) {
            lecturerNewLecturerToAttach = em.getReference(lecturerNewLecturerToAttach.getClass(), lecturerNewLecturerToAttach.getId());
            attachedLecturerNew.add(lecturerNewLecturerToAttach);
        }
        lecturerNew = attachedLecturerNew;
        department.setLecturers(lecturerNew);
        department = em.merge(department);
        if (facultyOld != null && !facultyOld.equals(facultyNew)) {
            facultyOld.getDepartments().remove(department);
            facultyOld = em.merge(facultyOld);
        }
        if (facultyNew != null && !facultyNew.equals(facultyOld)) {
            facultyNew.getDepartments().add(department);
            facultyNew = em.merge(facultyNew);
        }
        for (Student studentsIDOldStudent : studentsIDOld) {
            if (!studentsIDNew.contains(studentsIDOldStudent)) {
                studentsIDOldStudent.setDepartment(null);
                studentsIDOldStudent = em.merge(studentsIDOldStudent);
            }
        }
        for (Student studentsIDNewStudent : studentsIDNew) {
            if (!studentsIDOld.contains(studentsIDNewStudent)) {
                Department oldDepartmentOfStudentsIDNewStudent = studentsIDNewStudent.getDepartment();
                studentsIDNewStudent.setDepartment(department);
                studentsIDNewStudent = em.merge(studentsIDNewStudent);
                if (oldDepartmentOfStudentsIDNewStudent != null && !oldDepartmentOfStudentsIDNewStudent.equals(department)) {
                    oldDepartmentOfStudentsIDNewStudent.getStudentsID().remove(studentsIDNewStudent);
                    oldDepartmentOfStudentsIDNewStudent = em.merge(oldDepartmentOfStudentsIDNewStudent);
                }
            }
        }
        for (Lecturer lecturerOldLecturer : lecturerOld) {
            if (!lecturerNew.contains(lecturerOldLecturer)) {
                lecturerOldLecturer.setDepartment(null);
                lecturerOldLecturer = em.merge(lecturerOldLecturer);
            }
        }
        for (Lecturer lecturerNewLecturer : lecturerNew) {
            if (!lecturerOld.contains(lecturerNewLecturer)) {
                Department oldDepartmentOfLecturerNewLecturer = lecturerNewLecturer.getDepartment();
                lecturerNewLecturer.setDepartment(department);
                lecturerNewLecturer = em.merge(lecturerNewLecturer);
                if (oldDepartmentOfLecturerNewLecturer != null && !oldDepartmentOfLecturerNewLecturer.equals(department)) {
                    oldDepartmentOfLecturerNewLecturer.getLecturers().remove(lecturerNewLecturer);
                    oldDepartmentOfLecturerNewLecturer = em.merge(oldDepartmentOfLecturerNewLecturer);
                }
            }
        }

    }

    public List<?> findLikeDepartmentName(String deptName) throws Exception {
        Query query = super.getEntityManager().createQuery("select D from Department D where D.faculty.facultyName.name like :deptN"
                + " or  D.departmentName.name like :deptN");
        query.setParameter("deptN", deptName);
        return super.findAll(query);
    }
    
    public List<?> findLikeDepartmentNameName(String deptName) throws Exception {
        Query query = super.getEntityManager().createQuery("select D from DepartmentName D where"
                + " D.name like :deptN");
        query.setParameter("deptN", deptName);
        return super.findAll(query);
    }
    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        Department department;
        try {
            department = em.getReference(Department.class, id);
            department.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The department with id " + id + " no longer exists.", enfe);
        }
        Faculty faculty = department.getFaculty();
        if (faculty != null) {
            faculty.getDepartments().remove(department);
            faculty = em.merge(faculty);
        }
        List<Student> studentsID = department.getStudentsID();
        for (Student studentsIDStudent : studentsID) {
            studentsIDStudent.setDepartment(null);
            studentsIDStudent = em.merge(studentsIDStudent);
        }
        List<Lecturer> lecturer = department.getLecturers();
        for (Lecturer lecturerLecturer : lecturer) {
            lecturerLecturer.setDepartment(null);
            lecturerLecturer = em.merge(lecturerLecturer);
        }
        em.remove(department);

    }

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Department as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     private List<DepartmentName> findDepartmentNameEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(DepartmentName.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }
    public List<DepartmentName> findDepartmentNameEntities() {
       
        return findDepartmentNameEntities(true, -1, -1);
    }
    

    public Department findDepartment(Long id) throws Exception {
        return (Department) super.findByPrimaryKey(id, Department.class);
    }

    public DepartmentName findDepartmentName(String deptName) throws Exception {
        Query query = super.getEntityManager().createQuery("select DN from DepartmentName DN where DN.name=:deptN");
        query.setParameter("deptN", deptName.replaceAll("_", " "));
        return (DepartmentName) super.findEntity(query);
    }

    public Department findDepartment(String deptName) throws Exception {
        Query query = super.getEntityManager().createQuery("select D from Department D where D.departmentName.name=:deptN");
        query.setParameter("deptN", deptName);
        return (Department) super.findEntity(query);
    }

    public DepartmentName findDepartmentName(Long id) throws Exception {
        return (DepartmentName) super.findByPrimaryKey(id, DepartmentName.class);
    }

    public int getDepartmentCount() {
        EntityManager em = super.getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Department as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
