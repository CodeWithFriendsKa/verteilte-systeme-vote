package de.dhbw.vote.voting.ejb;

import java.sql.Date;
import java.time.LocalDate;

/***
 * Ohne Middleware wie Hibernate verwenden wir lieber das normale
 * java.sql.date
 * Da dieses aber nicht die kompfortablen Methoden für den letzen Monat, Tag...
 * anbietet sind hier einige statische Erweiterungsmehtoden für das 
 * java.sql.date
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public abstract class DateExtensions {
    
    /***
     * 
     * @return 
     */
    public static Date now(){
        return new Date(System.currentTimeMillis());
    }
    
    /***
     * 
     * @param sqlDate
     * @return 
     */
    public static Date getLastMonth(Date sqlDate){ 
        LocalDate localDate = sqlDate.toLocalDate();
        localDate = localDate.minusMonths(1);
        
        return new Date(
                Date.valueOf(localDate).getTime()
        );
    }
    
    /***
     * 
     * @param sqlDate
     * @return 
     */
    public static Date getLastWeek(Date sqlDate){ 
        LocalDate localDate = sqlDate.toLocalDate();
        localDate = localDate.minusWeeks(1);
        
        return new Date(
                Date.valueOf(localDate).getTime()
        );
    }
    
    /***
     * 
     * @param sqlDate
     * @return 
     */
    public static Date getLastDay(Date sqlDate){ 
        LocalDate localDate = sqlDate.toLocalDate();
        localDate = localDate.minusDays(1);
        
        return new Date(
                Date.valueOf(localDate).getTime()
        );
    }
}
