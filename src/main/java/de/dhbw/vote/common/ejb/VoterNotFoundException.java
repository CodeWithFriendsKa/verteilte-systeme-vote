/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.common.ejb;

/**
 *
 * @author chp
 */
public class VoterNotFoundException extends Exception {

    public VoterNotFoundException() {
        super();
    }

    public VoterNotFoundException(String message) {
        super(message);
    }

    public VoterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
}
