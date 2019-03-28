/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.init;

import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.Category;
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
    private void saveDemoData() {
        System.out.println("DELETE OLD DEMODATA");
        voterBean.deleteAll();
        upDownVoteBean.deleteAll();
        
        System.out.println("SAVE NEW DEMODATA IN DATABASE BEGIN");
        saveDemoVoter();
        
        try{
        saveDemoUpDownVote();
        }
        catch(Exception e){
            System.out.println("Catched VoterNotFoundException" + e.getMessage());
        }
        
        System.out.println("SAVE NEW DEMODATA IN DATABASE END");
                
        System.out.println("SHOW ALL DEMODATA IN DATABASE BEGIN");
        showDemoVoter();
        showDemoUpDownVote();
        System.out.println("SHOW ALL DEMODATA IN DATABASE END");
        
        System.out.println("FIND VOTE BY USERNAME BEGIN");
        
        try{
            upDownVoteBean.findVotesByUsername("MimiMin").forEach(v ->
                System.out.println("Vote: " + v.toString() )
            );
        }
        catch(Exception e){
            System.out.println("Exception VoterNotFound");
        }
        
        
        System.out.println("FIND VOTE BY USERNAME END");
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
        Voter voter1 = voterBean.saveNew(new Voter(
                "MimiMin",
                "def@web.de",
                "123456",
                "Mini",
                "Mustermann",
                11,
                Sex.WEIBLICH
        ));
        System.out.println("save demo voter in database: " + voter0.toString());
        System.out.println("save demo voter in database: " + voter1.toString());
    }
    
    private void saveDemoUpDownVote() throws VoterNotFoundException {
        UpDownVote vote0 = upDownVoteBean.saveNew(new UpDownVote(
                "Das erste Voting",
                voterBean.findByUserName("MaxiMax"),
                Category.AUTO
        ));
        UpDownVote vote1 = upDownVoteBean.saveNew(new UpDownVote(
                "Das zweite Voting",
                voterBean.findByUserName("MimiMin"),
                Category.SPORT
        ));
        UpDownVote vote2 = upDownVoteBean.saveNew(new UpDownVote(
                "Das dritte Voting",
                voterBean.findByUserName("MimiMin"),
                Category.HOT_OR_NOT
        ));
        System.out.println("save demo upDownVote in database: " + vote0.toString());
        System.out.println("save demo upDownVote in database: " + vote1.toString());
        System.out.println("save demo upDownVote in database: " + vote2.toString());
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