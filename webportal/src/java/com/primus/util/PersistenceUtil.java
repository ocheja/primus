/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class PersistenceUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    
    static {
        try{
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PrimusPU");
        }catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        return ENTITY_MANAGER_FACTORY;
    }
}
