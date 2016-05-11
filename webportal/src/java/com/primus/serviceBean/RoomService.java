/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Hostel;
import com.primus.data.Room;
import com.primus.data.Student;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Olisa
 */
public class RoomService implements Serializable {

    public RoomService(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Room room) throws RollbackFailureException, Exception {
        if (room.getStudents() == null) {
            room.setStudents(new ArrayList<Student>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Hostel hostel = room.getHostel();
            if (hostel != null) {
                hostel = em.getReference(hostel.getClass(), hostel.getHostelName());
                room.setHostel(hostel);
            }
            List<Student> attachedStudents = new ArrayList<Student>();
            for (Student studentsStudentToAttach : room.getStudents()) {
                studentsStudentToAttach = em.getReference(studentsStudentToAttach.getClass(), studentsStudentToAttach.getId());
                attachedStudents.add(studentsStudentToAttach);
            }
            room.setStudents(attachedStudents);
            em.persist(room);
            if (hostel != null) {
                hostel.getRooms().add(room);
                hostel = em.merge(hostel);
            }
            for (Student studentsStudent : room.getStudents()) {
                Room oldRoomOfStudentsStudent = studentsStudent.getRoom();
                studentsStudent.setRoom(room);
                studentsStudent = em.merge(studentsStudent);
                if (oldRoomOfStudentsStudent != null) {
                    oldRoomOfStudentsStudent.getStudents().remove(studentsStudent);
                    oldRoomOfStudentsStudent = em.merge(oldRoomOfStudentsStudent);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Room room) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Room persistentRoom = em.find(Room.class, room.getId());
            Hostel hostelOld = persistentRoom.getHostel();
            Hostel hostelNew = room.getHostel();
            List<Student> studentsOld = persistentRoom.getStudents();
            List<Student> studentsNew = room.getStudents();
            if (hostelNew != null) {
                hostelNew = em.getReference(hostelNew.getClass(), hostelNew.getHostelName());
                room.setHostel(hostelNew);
            }
            List<Student> attachedStudentsNew = new ArrayList<Student>();
            for (Student studentsNewStudentToAttach : studentsNew) {
                studentsNewStudentToAttach = em.getReference(studentsNewStudentToAttach.getClass(), studentsNewStudentToAttach.getId());
                attachedStudentsNew.add(studentsNewStudentToAttach);
            }
            studentsNew = attachedStudentsNew;
            room.setStudents(studentsNew);
            room = em.merge(room);
            if (hostelOld != null && !hostelOld.equals(hostelNew)) {
                hostelOld.getRooms().remove(room);
                hostelOld = em.merge(hostelOld);
            }
            if (hostelNew != null && !hostelNew.equals(hostelOld)) {
                hostelNew.getRooms().add(room);
                hostelNew = em.merge(hostelNew);
            }
            for (Student studentsOldStudent : studentsOld) {
                if (!studentsNew.contains(studentsOldStudent)) {
                    studentsOldStudent.setRoom(null);
                    studentsOldStudent = em.merge(studentsOldStudent);
                }
            }
            for (Student studentsNewStudent : studentsNew) {
                if (!studentsOld.contains(studentsNewStudent)) {
                    Room oldRoomOfStudentsNewStudent = studentsNewStudent.getRoom();
                    studentsNewStudent.setRoom(room);
                    studentsNewStudent = em.merge(studentsNewStudent);
                    if (oldRoomOfStudentsNewStudent != null && !oldRoomOfStudentsNewStudent.equals(room)) {
                        oldRoomOfStudentsNewStudent.getStudents().remove(studentsNewStudent);
                        oldRoomOfStudentsNewStudent = em.merge(oldRoomOfStudentsNewStudent);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = room.getId();
                if (findRoom(id) == null) {
                    throw new NonexistentEntityException("The room with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Room room;
            try {
                room = em.getReference(Room.class, id);
                room.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The room with id " + id + " no longer exists.", enfe);
            }
            Hostel hostel = room.getHostel();
            if (hostel != null) {
                hostel.getRooms().remove(room);
                hostel = em.merge(hostel);
            }
            List<Student> students = room.getStudents();
            for (Student studentsStudent : students) {
                studentsStudent.setRoom(null);
                studentsStudent = em.merge(studentsStudent);
            }
            em.remove(room);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Room> findRoomEntities() {
        return findRoomEntities(true, -1, -1);
    }

    public List<Room> findRoomEntities(int maxResults, int firstResult) {
        return findRoomEntities(false, maxResults, firstResult);
    }

    private List<Room> findRoomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Room as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Room findRoom(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Room.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Room as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
