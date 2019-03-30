/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.common;

import java.time.LocalDateTime;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class CustomLogger {
    private final Class<?> genericClass;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public CustomLogger(Class<?> genericClass) {
        this.genericClass = genericClass;
    }
    
    public void debug(String message){
        System.out.println(
                ANSI_CYAN +
                LocalDateTime.now() + " " +
                "DEBUG:" + " " +
                message +
                ANSI_RESET
        );
    }
    public void error(String message, Exception e){
        System.out.println(
                ANSI_CYAN +
                LocalDateTime.now() + " " +
                "ERROR:" + " " +
                message + " " +
                e.getMessage() + " " +
                e.toString() +
                ANSI_RESET
        );
        e.printStackTrace();
    }
}
