/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.service.exceptions;

/**
 *
 * @author Olisa
 */
public class NullFieldException extends Exception {

    public NullFieldException(String message, Throwable cause) {
        super(message, cause);

    }

    public NullFieldException(String message) {
        super(message);

    }
}
