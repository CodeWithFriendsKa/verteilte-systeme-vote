package de.dhbw.vote.init;

import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.Category;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Startup
@Singleton
public class CreateDemo {
    
    @EJB
    private VoterBean voterBean;
    @EJB
    private UpDownVoteBean upDownVoteBean;
    
    /**
     * This method makes the following steps:
     * 1) delete all demo data
     * 2) save new demo data in the database
     * 3) sow all demodata from database
     */
    @PostConstruct
    private void saveDemoData() {
        try {
            System.out.println("DELETE OLD DEMODATA BEGIN");
            voterBean.deleteAll();
            upDownVoteBean.deleteAll();
            System.out.println("DELETE OLD DEMODATA END");
            System.out.println("SAVE NEW DEMODATA IN DATABASE BEGIN");
            saveDemoVoter();
            saveDemoUpDownVote();
            System.out.println("SAVE NEW DEMODATA IN DATABASE END");                
            System.out.println("SHOW ALL DEMODATA IN DATABASE BEGIN");
            showDemoVoter();
            showDemoUpDownVote();
            System.out.println("SHOW ALL DEMODATA IN DATABASE END");        
        } catch(DemoException e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println(e.getStackTrace());
        }
    }
    private void saveDemoVoter() throws DemoException{
        try{
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
            Voter voter2 = voterBean.saveNew(new Voter(
                "TobiTorsten",
                "def@web.de",
                "123456",
                "Tobi",
                "Torsten",
                11,
                Sex.WEIBLICH
                ));            
            System.out.println("save demo voter in database: " + voter0.toString());
            System.out.println("save demo voter in database: " + voter1.toString());
        } catch (Exception e){
            throw new DemoException("DemoExeption",e);
        }
    }
    private void saveDemoUpDownVote() throws DemoException {
        try {
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
            UpDownVote vote3 = upDownVoteBean.saveNew(new UpDownVote(
                    "Das vierte Voting",
                    voterBean.findByUserName("MimiMin"),
                    voterBean.findAll(),
                    voterBean.findAll()
            ));   
            UpDownVote vote4 = upDownVoteBean.saveNew(new UpDownVote(
                    "Das fünfte Voting",
                    voterBean.findByUserName("MimiMin"),
                    voterBean.findAll().subList(0, 1),
                    voterBean.findAll().subList(0, 1)
            ));           
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }
    private void showDemoVoter() throws DemoException{
        try {
            List<Voter> voters = voterBean.findAll();
            voters.forEach(voter -> System.out.println(voter.toString()));
        } catch (Exception e) {
            throw new DemoException("DemoExeption", e);
        }
    }
    private void showDemoUpDownVote() throws DemoException{
        try {
            List<UpDownVote> upDowns = upDownVoteBean.findAll();
            upDowns.forEach(upDown -> System.out.println(upDown.toString()));
            upDownVoteBean.findVotesByUsername("MimiMin").forEach(v ->System.out.println("findVotesByUsername: " + v.toString())); 
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }
}