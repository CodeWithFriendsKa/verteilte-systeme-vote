package de.dhbw.vote.init;

/***
 * Eigene Exceptionklasse, welche geworfen wird, wenn beim anlegen der
 * Demodaten ein Fehler passiert 
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class DemoException extends Exception{
    public DemoException() {
    }
    public DemoException(String message) {
        super(message);
    }
    public DemoException(String message, Throwable cause) {
        super(message, cause);
    }
}
