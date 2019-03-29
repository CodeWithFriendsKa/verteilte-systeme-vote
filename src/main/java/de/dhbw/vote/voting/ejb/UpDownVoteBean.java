package de.dhbw.vote.voting.ejb;

import de.dhbw.vote.common.ejb.EntityBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.UpDownVote;
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
    public UpDownVoteBean(){
        super(UpDownVote.class);
    }
    @EJB
    VoterBean voterBean;
    public List<UpDownVote> findVotesByUsername(String username) throws VoterNotFoundException {
        List<UpDownVote> votes = new ArrayList();
        Voter voter = voterBean.findByUserName(username);
        votes = em.createQuery("SELECT v FROM UpDownVote v "
                            + "WHERE v.creator = :voter ")
                .setParameter("voter", voter)
                .getResultList();
        return votes;
    }
    public UpDownVote findBestVoteAllTime(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }
    public UpDownVote findWorstVoteAllTime(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }
    public UpDownVote findBestVoteOfMonth(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }
    public UpDownVote findWorstVoteOfMonth(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }    
    public UpDownVote findBestVoteOfWeek(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }
    public UpDownVote findWorstVoteOfWeek(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    } 
    public UpDownVote findBestVoteOfDay(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }
    public UpDownVote findWorstVoteOfDay(){
        UpDownVote vote = (UpDownVote) em.createQuery("").getSingleResult();
               
        return new UpDownVote();
    }     
}
