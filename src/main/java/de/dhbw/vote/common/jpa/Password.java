package de.dhbw.vote.common.jpa;

import javax.validation.constraints.Size;

/**
 *
 * @author codekeks
 */
public class Password {
    @Size(min = 6, max = 64, message = "Das Passwort muss zwischen sechs und 64 Zeichen lang sein.")
    public String password = ""; 
}
