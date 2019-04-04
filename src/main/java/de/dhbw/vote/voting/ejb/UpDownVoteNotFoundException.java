package de.dhbw.vote.voting.ejb;

/***
 * Eigene Exception welche geworfen wird, wenn das gesuchte UpDownVote
 * nicht gefunden wird
 * 
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
