package de.dhbw.vote.common.ejb;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.jpa.Voter;
import javax.ejb.Stateless;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Stateless
public class VoterBean extends EntityBean<Voter, Long>{
    private static final CustomLogger logger = new CustomLogger(VoterBean.class);
    public VoterBean(){
        super(Voter.class);
    }
    public Voter findByUserName(String username) throws VoterNotFoundException {
        Voter voter = (Voter) em.createQuery("SELECT v FROM Voter v WHERE v.username = :userName")
             .setParameter("userName", username)
             .getSingleResult();
        
        if(voter == null){
            throw new VoterNotFoundException("There is no user with the username: " + username);
        }
        
        return voter;
    }   
}