package de.dhbw.vote.init;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.ejb.DateExtensions;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
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
@RunAs("app-user")
public class CreateDemo {
    private final CustomLogger logger = new CustomLogger(CreateDemo.class);
    @EJB
    private VoterBean voterBean;
    @EJB
    private UpDownVoteBean upDownVoteBean;  
    
    /**
     * Diese Methode führt folgende Schritte aus:
     * 1) lösche alle "alten" Daten aus der Datenbank
     * 2) speichere neue "frische" Daten in die Datenbank
     * 3) zeige die gespeicherten Daten an
     */
    @PostConstruct
    private void saveDemoData() {
        try {           
                    
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
            
            this.testUpDownVoteEjb();
        } catch(DemoException e){
            logger.error("Error while saving demo data", e);
        }
    }
    
    /***
     * 
     * @throws DemoException 
     */
    private void saveDemoVoter() throws DemoException{
        try{
            String prename = "Max";
            String name = "Mustermann";
            String username = "MaMu";
                    
            for (int i = 0; i < 10; i++) {
                voterBean.signup(
                username + i,
                "123",
                username + i + "@dhbw.de",
                (prename + Integer.toString(i)),
                (name + Integer.toString(i)),
                10 + i,
                Sex.MÄNNLICH
                );
            }
        } catch (Exception e){
            throw new DemoException("DemoExeption",e);
        }
    }
    
    /***
     * 
     * @throws DemoException 
     */
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
    
    /***
     * 
     * @throws DemoException 
     */
    private void showDemoVoter() throws DemoException{
        try {
            List<Voter> voters = voterBean.findAll();
            voters.forEach(voter -> logger.debug("DEMO: " + voter.toString()));
        } catch (Exception e) {
            throw new DemoException("DemoExeption", e);
        }
    }
    
    /***
     * 
     * @throws DemoException 
     */
    private void showDemoUpDownVote() throws DemoException{
        try {
            List<UpDownVote> upDowns = upDownVoteBean.findAll();
            upDowns.forEach(upDown -> logger.debug("DEMO: " + upDown.toString()));
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }
    
    /***
     * 
     * @throws DemoException 
     */
    private void testUpDownVoteEjb() throws DemoException{
        try {
            logger.debug("DEMO TEST");
            Date voteDate = DateExtensions.now();
            logger.debug(voteDate.toString());
            logger.debug("DEMO: " +DateExtensions.getLastDay(voteDate).toString());
            logger.debug("DEMO: " +DateExtensions.getLastWeek(voteDate).toString());
            logger.debug("DEMO: " +DateExtensions.getLastMonth(voteDate).toString());
            
            logger.debug("DEMO: Best of Month" + upDownVoteBean.findBestVoteOfMonth().get(0).toString());
            
            logger.debug("DEMO TEST");
        } catch (Exception e) {
            throw new DemoException("DemoException", e);
        }
    }    
}