/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Hostel;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Room;
import com.primus.enums.HostelName;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.PreexistingEntityException;
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
public class HostelService implements Serializable {

    public HostelService(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hostel hostel) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (hostel.getRooms() == null) {
            hostel.setRooms(new ArrayList<Room>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Room> attachedRooms = new ArrayList<Room>();
            for (Room roomsRoomToAttach : hostel.getRooms()) {
                roomsRoomToAttach = em.getReference(roomsRoomToAttach.getClass(), roomsRoomToAttach.getId());
                attachedRooms.add(roomsRoomToAttach);
            }
            hostel.setRooms(attachedRooms);
            em.persist(hostel);
            for (Room roomsRoom : hostel.getRooms()) {
                Hostel oldHostelOfRoomsRoom = roomsRoom.getHostel();
                roomsRoom.setHostel(hostel);
                roomsRoom = em.merge(roomsRoom);
                if (oldHostelOfRoomsRoom != null) {
                    oldHostelOfRoomsRoom.getRooms().remove(roomsRoom);
                    oldHostelOfRoomsRoom = em.merge(oldHostelOfRoomsRoom);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findHostel(hostel.getHostelName()) != null) {
                throw new PreexistingEntityException("Hostel " + hostel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hostel hostel) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Hostel persistentHostel = em.find(Hostel.class, hostel.getHostelName());
            List<Room> roomsOld = persistentHostel.getRooms();
            List<Room> roomsNew = hostel.getRooms();
            List<Room> attachedRoomsNew = new ArrayList<Room>();
            for (Room roomsNewRoomToAttach : roomsNew) {
                roomsNewRoomToAttach = em.getReference(roomsNewRoomToAttach.getClass(), roomsNewRoomToAttach.getId());
                attachedRoomsNew.add(roomsNewRoomToAttach);
            }
            roomsNew = attachedRoomsNew;
            hostel.setRooms(roomsNew);
            hostel = em.merge(hostel);
            for (Room roomsOldRoom : roomsOld) {
                if (!roomsNew.contains(roomsOldRoom)) {
                    roomsOldRoom.setHostel(null);
                    roomsOldRoom = em.merge(roomsOldRoom);
                }
            }
            for (Room roomsNewRoom : roomsNew) {
                if (!roomsOld.contains(roomsNewRoom)) {
                    Hostel oldHostelOfRoomsNewRoom = roomsNewRoom.getHostel();
                    roomsNewRoom.setHostel(hostel);
                    roomsNewRoom = em.merge(roomsNewRoom);
                    if (oldHostelOfRoomsNewRoom != null && !oldHostelOfRoomsNewRoom.equals(hostel)) {
                        oldHostelOfRoomsNewRoom.getRooms().remove(roomsNewRoom);
                        oldHostelOfRoomsNewRoom = em.merge(oldHostelOfRoomsNewRoom);
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
                HostelName id = hostel.getHostelName();
                if (findHostel(id) == null) {
                    throw new NonexistentEntityException("The hostel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HostelName id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Hostel hostel;
            try {
                hostel = em.getReference(Hostel.class, id);
                hostel.getHostelName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hostel with id " + id + " no longer exists.", enfe);
            }
            List<Room> rooms = hostel.getRooms();
            for (Room roomsRoom : rooms) {
                roomsRoom.setHostel(null);
                roomsRoom = em.merge(roomsRoom);
            }
            em.remove(hostel);
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

    public List<Hostel> findHostelEntities() {
        return findHostelEntities(true, -1, -1);
    }

    public List<Hostel> findHostelEntities(int maxResults, int firstResult) {
        return findHostelEntities(false, maxResults, firstResult);
    }

    private List<Hostel> findHostelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Hostel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Hostel findHostel(HostelName id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hostel.class, id);
        } finally {
            em.close();
        }
    }

    public int getHostelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Hostel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
