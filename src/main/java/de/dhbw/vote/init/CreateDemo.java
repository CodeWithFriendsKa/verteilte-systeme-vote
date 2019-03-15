/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.init;

import de.dhbw.vote.ejb.UpDownVoteBean;
import de.dhbw.vote.ejb.UserBean;
import de.dhbw.vote.ejb.VoterBean;
import de.dhbw.vote.jpa.Sex;
import de.dhbw.vote.jpa.UpDownVote;
import de.dhbw.vote.jpa.User;
import de.dhbw.vote.jpa.Voter;
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
    private UserBean userBean;
    @EJB
    private VoterBean voterBean;
    @EJB
    private UpDownVoteBean upDownVoteBean;
    
    @PostConstruct
    private void saveDemoData(){
        System.out.println("DELETE OLD DEMODATA");
        userBean.deleteAll();
        voterBean.deleteAll();
        upDownVoteBean.deleteAll();
        
        System.out.println("SAVE NEW DEMODATA IN DATABASE BEGIN");       
        saveDemoUser();
        saveDemoVoter();
        
        System.out.println("SAVE NEW DEMODATA IN DATABASE END");
        
        System.out.println("SHOW ALL DEMODATA IN DATABASE BEGIN");
        showDemoUser();
        showDemoVoter();
        System.out.println("SHOW ALL DEMODATA IN DATABASE END");
    }
    private void saveDemoUser(){
        User user0 =  userBean.saveNew(new User(
                "maxi",
                "max@dhbw.de", 
                "123"
        ));
        System.out.println("save demo user in database: " + user0.toString());
    }
    private void saveDemoVoter(){
        Voter voter0 = voterBean.saveNew(new Voter(
                "Max",
                "Mustermann",
                12,
                Sex.MÄNNLICH,
                null,
                "maxi",
                "max@dhbw.de", 
                "123"
        ));
        System.out.println("save demo voter in database: " + voter0.toString());
    }
    private void saveDemoUpDownVote(){
        UpDownVote updown0 = upDownVoteBean.saveNew(new UpDownVote(
                
        ));
    }
    private void showDemoUser(){
        List<User> users = userBean.findAll();
        users.forEach(user -> System.out.println(user.toString()));
    }
    private void showDemoVoter(){
        List<Voter> voters = voterBean.findAll();
        voters.forEach(voter -> System.out.println("a" + voter.toString()));
    }
    private void showDemoUpDownVote(){
        List<UpDownVote> upDowns = upDownVoteBean.findAll();
        upDowns.forEach(upDown -> System.out.println(upDown.toString()));
    }
}