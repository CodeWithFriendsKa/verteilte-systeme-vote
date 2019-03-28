/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.common.ejb;

import de.dhbw.vote.common.jpa.Voter;
import javax.ejb.Stateless;


/**
 *
 * @author codekeks
 */
@Stateless
public class VoterBean extends EntityBean<Voter, Long>{
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
