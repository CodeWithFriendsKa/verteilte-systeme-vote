package de.dhbw.vote.common.ejb;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
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
