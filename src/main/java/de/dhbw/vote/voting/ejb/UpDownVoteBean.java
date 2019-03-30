package de.dhbw.vote.voting.ejb;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.EntityBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.voting.jpa.VoteDate;
import java.sql.Date;
import java.time.LocalDateTime;
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
        VoteDate now = VoteDate.now();
        VoteDate lastMonth = now.getLastMonth();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.upSize = "
                + "(SELECT Max(x.upSize) FROM UpDownVote x) "
                + "AND v.date BETWEEN :now AND :lastMonth")
                .getResultList();
        return votes;
    }
    public List<UpDownVote> findWorstVoteOfMonth(){
        List<UpDownVote> votes = new ArrayList();
        VoteDate now = VoteDate.now();
        VoteDate lastMonth = now.getLastMonth();  
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.downSize = "
                + " (SELECT Max(x.downSize) FROM UpDownVote x) ")
                .getResultList();
        
        return votes;
    }    
    public List<UpDownVote> findBestVoteOfWeek(){     
        List<UpDownVote> votes = new ArrayList();
        VoteDate now = VoteDate.now();
        VoteDate lastMonth = now.getLastWeek();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.upSize = "
                + " (SELECT Max(x.upSize) FROM UpDownVote x) ")
                .getResultList();
        
        return votes;
    }
    public List<UpDownVote> findWorstVoteOfWeek(){
        List<UpDownVote> votes = new ArrayList();
        VoteDate now = VoteDate.now();
        VoteDate lastMonth = now.getLastWeek();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.downSize = "
                + " (SELECT Max(x.downSize) FROM UpDownVote x) ")
                .getResultList();
        
        return votes;
    } 
    public List<UpDownVote> findBestVoteOfDay(){
        List<UpDownVote> votes = new ArrayList();
        VoteDate now = VoteDate.now();
        VoteDate lastMonth = now.getLastDay();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.upSize = "
                + " (SELECT Max(x.upSize) FROM UpDownVote x) ")
                .getResultList();
        
        return votes;
    }
    public List<UpDownVote> findWorstVoteOfDay(){
        List<UpDownVote> votes = new ArrayList();
        VoteDate now = VoteDate.now();
        VoteDate lastMonth = now.getLastDay();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.downSize = "
                + " (SELECT Max(x.downSize) FROM UpDownVote x) ")
                .getResultList();
        
        return votes;
    }     
}
