/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.service.exceptions;

/**
 *
 * @author Olisa
 */
public class PrimusServiceException  extends Exception {
    
    public PrimusServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public PrimusServiceException(String message) {
        super(message);
    }
}
