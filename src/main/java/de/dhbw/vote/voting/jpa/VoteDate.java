package de.dhbw.vote.voting.jpa;

import java.sql.Date;
import java.time.LocalDate;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class VoteDate extends Date {

    public VoteDate(int year, int month, int day) {
        super(year, month, day);
    }

    public VoteDate(long date) {
        super(date);
    }
    
    public static VoteDate now(){
        return new VoteDate(System.currentTimeMillis());
    }
    
    public VoteDate getLastMonth(){ 
        LocalDate date = this.toLocalDate();
        date = date.minusMonths(1);
        
        return new VoteDate(
                Date.valueOf(date).getTime()
        );
    }
    public VoteDate getLastWeek(){
        LocalDate date = this.toLocalDate();
        date = date.minusWeeks(1);
        
        return new VoteDate(
                Date.valueOf(date).getTime()
        );
    }
    public VoteDate getLastDay(){
        LocalDate date = this.toLocalDate(); 
        date = date.minusDays(1);
        
        return new VoteDate(
                Date.valueOf(date).getTime()
        );
    }    
}
