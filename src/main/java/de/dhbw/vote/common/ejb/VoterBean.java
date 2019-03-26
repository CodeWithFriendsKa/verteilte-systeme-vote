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
        try{
            Voter voter = (Voter) em.createQuery("SELECT v FROM VOTER v WHERE v.username = :userName")
                 .setParameter("userName", username)
                 .getSingleResult();
        
            return voter;
        }
        catch(Exception e){
            throw new VoterNotFoundException("Voter not found",e);
        }
    }
    
    
}
