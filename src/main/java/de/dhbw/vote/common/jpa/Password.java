package de.dhbw.vote.common.jpa;

import javax.validation.constraints.Size;

/***
 * Einfache Klasse für ein Passwort
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class Password {
    @Size(min = 6, max = 64, message = "Das Passwort muss zwischen sechs und 64 Zeichen lang sein.")
    public String password = ""; 
}
