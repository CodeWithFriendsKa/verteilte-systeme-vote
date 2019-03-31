package de.dhbw.vote.voting.ejb;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.EntityBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Stateless
public class UpDownVoteBean extends EntityBean<UpDownVote, Long>{
    private static final CustomLogger logger = new CustomLogger(UpDownVoteBean.class);
    @EJB
    VoterBean voterBean;
    public UpDownVoteBean(){
        super(UpDownVote.class);
    }    
    public List<UpDownVote> findVotesByUsername(String username) throws VoterNotFoundException {
        List<UpDownVote> votes = new ArrayList();
        Voter voter = voterBean.findByUserName(username);
        
        votes = em.createQuery("SELECT v FROM UpDownVote v "
                            + "WHERE v.creator = :voter ")
                .setParameter("voter", voter)
                .getResultList();
        return votes;
    }
    public List<UpDownVote> findBestVoteAllTime(){
        List<UpDownVote> votes = new ArrayList();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.upSize = "
                + " (SELECT Max(x.upSize) FROM UpDownVote x)")
                .getResultList();
        
        return votes;
    }
    public List<UpDownVote> findWorstVoteAllTime(){
        List<UpDownVote> votes = new ArrayList();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.downSize = "
                + " (SELECT Max(x.downSize) FROM UpDownVote x)")
                .getResultList();
        
        return votes;
    }
    public List<UpDownVote> findBestVoteOfMonth(){
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastMonth = DateExtensions.getLastMonth(now);
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MAX(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastMonth)
                .getResultList();
        return votes;
    }
    public List<UpDownVote> findWorstVoteOfMonth(){
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastMonth = DateExtensions.getLastMonth(now);
 
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MIN(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastMonth)
                .getResultList();
        return votes;
    }    
    public List<UpDownVote> findBestVoteOfWeek(){     
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastWeek = DateExtensions.getLastWeek(now);        

        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MAX(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastWeek)
                .getResultList();
        return votes;
    }
    public List<UpDownVote> findWorstVoteOfWeek(){
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastWeek = DateExtensions.getLastWeek(now);        
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MIN(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastWeek)
                .getResultList();
        return votes;
    } 
    public List<UpDownVote> findBestVoteOfDay(){
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastDay = DateExtensions.getLastDay(now);
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MAX(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastDay)
                .getResultList();
        return votes;
    }
    public List<UpDownVote> findWorstVoteOfDay(){
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastDay = DateExtensions.getLastDay(now);
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MIN(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastDay)
                .getResultList();
        return votes;
    }
}
