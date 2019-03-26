/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.init;

import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.common.jpa.Voter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author codekeks
 */
@Startup
@Singleton
public class CreateDemo {
    
    @EJB
    private VoterBean voterBean;
    @EJB
    private UpDownVoteBean upDownVoteBean;
    
    @PostConstruct
    private void saveDemoData(){
        System.out.println("DELETE OLD DEMODATA");
        voterBean.deleteAll();
        upDownVoteBean.deleteAll();
        
        System.out.println("SAVE NEW DEMODATA IN DATABASE BEGIN");
        saveDemoVoter();
        
        System.out.println("SAVE NEW DEMODATA IN DATABASE END");
        
        System.out.println("SHOW ALL DEMODATA IN DATABASE BEGIN");
        showDemoVoter();
        System.out.println("SHOW ALL DEMODATA IN DATABASE END");
    }
    
    private void saveDemoVoter(){
        Voter voter0 = voterBean.saveNew(new Voter(
                "MaxiMax",
                "abc@web.de",
                "123456",
                "Max",
                "Mustermann",
                12,
                Sex.MÄNNLICH
        ));
        System.out.println("save demo voter in database: " + voter0.toString());
    }
    private void saveDemoUpDownVote(){
        UpDownVote updown0 = upDownVoteBean.saveNew(new UpDownVote(
                
        ));
    }
    
    private void showDemoVoter(){
        List<Voter> voters = voterBean.findAll();
        voters.forEach(voter -> System.out.println(voter.toString()));
    }
    private void showDemoUpDownVote(){
        List<UpDownVote> upDowns = upDownVoteBean.findAll();
        upDowns.forEach(upDown -> System.out.println(upDown.toString()));
    }
}