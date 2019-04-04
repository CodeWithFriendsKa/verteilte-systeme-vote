package de.dhbw.vote.common.ejb;

/***
 * Eigene Exceptionklasse, welche geworfen wird, wenn beim Versuch einen
 * neuen Voter anzulegen festgestellt wird, dass der Voter bereits existiert
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class VoterAlreadyExistsException extends Exception {

    public VoterAlreadyExistsException(String message) {
        super(message);
    }
}