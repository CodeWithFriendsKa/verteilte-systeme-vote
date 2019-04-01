package de.dhbw.vote.common.ejb;

/**
 *
 * @author codekeks
 */
public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
