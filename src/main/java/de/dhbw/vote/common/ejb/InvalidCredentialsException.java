package de.dhbw.vote.common.ejb;

/***
 * Eigene Exceptionklasse, welche geworfen wird, wenn beim 
 * Versuch sich einzuloggen festgestellt wird, dass die Login-Credentials
 * nicht korrekt sind
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}