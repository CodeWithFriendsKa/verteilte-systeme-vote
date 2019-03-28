/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author codekeks
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
}
