/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.ejb;

import de.dhbw.vote.ejb.common.EntityBean;
import de.dhbw.vote.jpa.Voter;
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
}
