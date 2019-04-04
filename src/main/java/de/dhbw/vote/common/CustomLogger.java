package de.dhbw.vote.common;

import java.time.LocalDateTime;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 * 
 * Prämisse -> keine Änderungen in der tomee config wg. Einheitlichkeit
 * Konklusion -> eigener kleiner Logger, der mehr Infos enthält als System.out
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

    /***
     * 
     * @param genericClass 
     */
    public CustomLogger(Class<?> genericClass) {
        this.genericClass = genericClass;
    }
    
    /***
     * 
     * @param message 
     */
    public void debug(String message){
        System.out.println(
                ANSI_CYAN +
                LocalDateTime.now() + " " +
                "DEBUG:" + " " +
                this.genericClass.toString() + " " +
                message +
                ANSI_RESET
        );
    }
    
    /***
     * 
     * @param message
     * @param e 
     */
    public void error(String message, Exception e){
        System.out.println(
                ANSI_RED +
                LocalDateTime.now() + " " +
                "ERROR:" + " " +
                this.genericClass.toString() + " " +   
                message + " " +
                e.getMessage() + " " +
                e.toString() +
                ANSI_RESET
        );
        e.printStackTrace();
    }
}
