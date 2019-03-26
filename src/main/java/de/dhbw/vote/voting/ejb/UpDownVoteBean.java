/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.voting.ejb;

import de.dhbw.vote.common.ejb.EntityBean;
import de.dhbw.vote.voting.jpa.UpDownVote;
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
    
    
}
