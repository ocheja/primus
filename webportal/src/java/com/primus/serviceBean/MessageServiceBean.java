/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Message;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Student;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class MessageServiceBean extends PrimusBasePersistenceService {

    public void create(Message message) throws Exception {
        EntityManager em = super.getEntityManager();
        Student student = message.getStudent();
        if (student != null) {
            student = em.getReference(student.getClass(), student.getId());
            message.setStudent(student);
        }
        em.persist(message);
        if (student != null) {
            student.getMessages().add(message);
            student = em.merge(student);
        }
    }

    public void edit(Message message) throws Exception {
        Long id = message.getId();
        if (findMessage(id) == null) {
            throw new NonexistentEntityException("The message with id " + id + " no longer exists.");
        }
        EntityManager em = super.getEntityManager();
        Message persistentMessage = em.find(Message.class, message.getId());
        Student studentOld = persistentMessage.getStudent();
        Student studentNew = message.getStudent();
        if (studentNew != null) {
            studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
            message.setStudent(studentNew);
        }
        message = em.merge(message);
        if (studentOld != null && !studentOld.equals(studentNew)) {
            studentOld.getMessages().remove(message);
            studentOld = em.merge(studentOld);
        }
        if (studentNew != null && !studentNew.equals(studentOld)) {
            studentNew.getMessages().add(message);
            studentNew = em.merge(studentNew);
        }


    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        Message message;
        try {
            message = em.getReference(Message.class, id);
            message.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The message with id " + id + " no longer exists.", enfe);
        }
        Student student = message.getStudent();
        if (student != null) {
            student.getMessages().remove(message);
            student = em.merge(student);
        }
        em.remove(message);
    }

    public List<Message> findMessageEntities() {
        return findMessageEntities(true, -1, -1);
    }

    public List<Message> findMessageEntities(int maxResults, int firstResult) {
        return findMessageEntities(false, maxResults, firstResult);
    }

    private List<Message> findMessageEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from Message as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Message findMessage(Long id) throws Exception {
        return (Message) super.findByPrimaryKey(id, Message.class);
    }

    public int getMessageCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from Message as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
