/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.rest;

/**
 *
 * @author codekeks
 */
public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Exception e) {
        super(message, e);
    }

    AuthorizationException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}