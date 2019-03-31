package de.dhbw.vote.voting.ejb;

import java.sql.Date;
import java.time.LocalDate;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public abstract class DateExtensions {
    
    public static Date now(){
        return new Date(System.currentTimeMillis());
    }
    public static Date getLastMonth(Date sqlDate){ 
        LocalDate localDate = sqlDate.toLocalDate();
        localDate = localDate.minusMonths(1);
        
        return new Date(
                Date.valueOf(localDate).getTime()
        );
    }
    public static Date getLastWeek(Date sqlDate){ 
        LocalDate localDate = sqlDate.toLocalDate();
        localDate = localDate.minusWeeks(1);
        
        return new Date(
                Date.valueOf(localDate).getTime()
        );
    }
    public static Date getLastDay(Date sqlDate){ 
        LocalDate localDate = sqlDate.toLocalDate();
        localDate = localDate.minusDays(1);
        
        return new Date(
                Date.valueOf(localDate).getTime()
        );
    }
}
