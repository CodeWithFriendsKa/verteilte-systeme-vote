package de.dhbw.vote.voting.ejb;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class UpDownVoteNotFoundException extends Exception {
    public UpDownVoteNotFoundException() {
    }
    public UpDownVoteNotFoundException(String message) {
        super(message);
    }
    public UpDownVoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
