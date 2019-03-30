package de.dhbw.vote.init;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.VoteDate;
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
    
    private final CustomLogger logger = new CustomLogger(CreateDemo.class);
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
            
            this.testUpDownVoteEjb();
            
            logger.debug("DELETE OLD DEMODATA BEGIN");
            voterBean.deleteAll();
            upDownVoteBean.deleteAll();
            logger.debug("DELETE OLD DEMODATA END");
            logger.debug("SAVE NEW DEMODATA IN DATABASE BEGIN");
            saveDemoVoter();
            saveDemoUpDownVote();
            logger.debug("SAVE NEW DEMODATA IN DATABASE END");                
            logger.debug("SHOW ALL DEMODATA IN DATABASE BEGIN");
            showDemoVoter();
            showDemoUpDownVote();
            logger.debug("SHOW ALL DEMODATA IN DATABASE END");        
        } catch(DemoException e){
            logger.error("Error while saving demo data", e);
        }
    }
    private void saveDemoVoter() throws DemoException{
        try{
            String prename = "Max";
            String name = "Mustermann";
            String username = "MaMu";
                    
            for (int i = 0; i < 10; i++) {
                voterBean.saveNew(new Voter(
                username + i,
                username + i + "@dhbw.de",
                "123",
                prename + i,
                name + i,
                10 + i,
                Sex.MÄNNLICH
                ));
            }
        } catch (Exception e){
            throw new DemoException("DemoExeption",e);
        }
    }
    private void saveDemoUpDownVote() throws DemoException {
        try {
            List<Voter> voters = voterBean.findAll();
            String description = "Voting Nummer: ";
            int random = 0 + (int)(Math.random() * ((9 - 0) + 1));
            for (int i = 0; i < 20; i++) {
                int random0 = 0 + (int)(Math.random() * ((9 - 0) + 1));
                int random1 = random0 + (int)(Math.random() * ((9 - random0) + 1));
                int random2 = random1 + (int)(Math.random() * ((9 - random1) + 1));
                upDownVoteBean.saveNew(new UpDownVote(
                description + i,
                voters.get(0 + (int)(Math.random() * ((9 - 0) + 1))),
                voters.subList(random0, random1),
                voters.subList(random1, random2)
                ));
            }
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }
    private void showDemoVoter() throws DemoException{
        try {
            List<Voter> voters = voterBean.findAll();
            voters.forEach(voter -> logger.debug(voter.toString()));
        } catch (Exception e) {
            throw new DemoException("DemoExeption", e);
        }
    }
    private void showDemoUpDownVote() throws DemoException{
        try {
            List<UpDownVote> upDowns = upDownVoteBean.findAll();
            upDowns.forEach(upDown -> logger.debug(upDown.toString()));
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }
    private void testUpDownVoteEjb() throws DemoException{
        try {
            logger.debug("TEST");
            VoteDate voteDate = VoteDate.now();
            logger.debug(voteDate.toString());
            logger.debug(voteDate.getLastDay().toString());
            logger.debug(voteDate.getLastWeek().toString());            
            logger.debug(voteDate.getLastMonth().toString());             
            logger.debug("TEST");
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }    
}