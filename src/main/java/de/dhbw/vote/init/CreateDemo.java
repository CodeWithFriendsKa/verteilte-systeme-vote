package de.dhbw.vote.init;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.voting.jpa.UpDownVote;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.ejb.DateExtensions;
import de.dhbw.vote.voting.jpa.Category;
import java.io.File;
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
    
    //TODO:
    //Bitte bei USER_PATH den eigenen absoluten Pfad angeben, bis zu dem Projekt
    private static final String PROJECT_PATH = "verteilte-systeme-vote/src/main/java/de/dhbw/vote/init/img/";
    private static final String USER_PATH = "/home/codekeks/Downloads/"; 
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
        
        //Array der Descriptions
        String[] descriptions = new String[20];
        descriptions[0] = "Wie findet Ihr meine neue Haarfarbe?";
        descriptions[1] = "Wie findet Ihr mein neues Outfit?";
        descriptions[2] = "Mein neues Make-Up!";
        descriptions[3] = "Wie findet Ihr meine neue Brille?";
        descriptions[4] = "Heute Morgen.";
        descriptions[5] = "Wie findet ihr meine neues Auto?";
        descriptions[6] = "Heute mal das Auto von meinem Vater.";
        descriptions[7] = "Würdet ihr auch gerne mit diesem Auto fahren?";
        descriptions[8] = "Soll ich mir dieses Auto kaufen?";
        descriptions[9] = "Wer fährt mit?";
        descriptions[10] = "Mein Mittagessen heute:";
        descriptions[11] = "Kann ich kochen?";
        descriptions[12] = "Wer isst auch gerne Pizze?";
        descriptions[13] = "Ist das auch Euer Lieblingsessen?";
        descriptions[14] = "Wer möchte was davon abhaben?";
        descriptions[15] = "Heute war ich mal wieder fleißig.";
        descriptions[16] = "Ich im Fitnessstudio.";
        descriptions[17] = "Wer mag auch diesen Sport";
        descriptions[18] = "Surfen, auch dein Lieblingssport?";
        descriptions[19] = "Wer mag auch das Risiko?";
        
       
        //TODO:
        //
        //byte[] Array für die Bilder;
        byte[][] bilder = new byte[20][];
        bilder[0] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Frau1.jpg"));
        bilder[1] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Frau2.jpg"));
        bilder[2] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Frau3.jpg"));
        bilder[3] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Mann1.jpg"));
        bilder[4] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Mann2.jpg"));
        bilder[5] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Auto1.jpg"));
        bilder[6] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Auto2.jpg"));
        bilder[7] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Auto3.jpg"));
        bilder[8] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Auto4.jpg"));
        bilder[9] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Auto5.jpg"));
        bilder[10] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Essen1.jpg"));
        bilder[11] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Essen2.jpg"));
        bilder[12] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Essen3.jpg"));
        bilder[13] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Essen4.jpg"));
        bilder[14] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Essen5.jpg"));
        bilder[15] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Sport1.jpg"));
        bilder[16] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Sport2.jpg"));
        bilder[17] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Sport3.jpg"));
        bilder[18] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Sport4.jpg"));
        bilder[19] = upDownVoteBean.imageToByte(new File(USER_PATH + PROJECT_PATH + "Sport5.jpg"));

        //Array der Categorys
        Category[] categorys = new Category[20];
        categorys[0] = Category.HOT_OR_NOT;
        categorys[1] = Category.HOT_OR_NOT;
        categorys[2] = Category.HOT_OR_NOT;
        categorys[3] = Category.HOT_OR_NOT;
        categorys[4] = Category.HOT_OR_NOT;
        categorys[5] = Category.AUTO;
        categorys[6] = Category.AUTO;
        categorys[7] = Category.AUTO;
        categorys[8] = Category.AUTO;
        categorys[9] = Category.AUTO;
        categorys[10] = Category.ESSEN;
        categorys[11] = Category.ESSEN;
        categorys[12] = Category.ESSEN;
        categorys[13] = Category.ESSEN;
        categorys[14] = Category.ESSEN;
        categorys[15] = Category.SPORT;
        categorys[16] = Category.SPORT;
        categorys[17] = Category.SPORT;
        categorys[18] = Category.SPORT;
        categorys[19] = Category.SPORT;
        
        try {
            List<Voter> voters = voterBean.findAll();
            String description = "Voting Nummer: ";
            int random = 0 + (int)(Math.random() * ((9 - 0) + 1));
            for (int i = 0; i < 20; i++) {
                int random0 = 0 + (int)(Math.random() * ((9 - 0) + 1));
                int random1 = random0 + (int)(Math.random() * ((9 - random0) + 1));
                int random2 = random1 + (int)(Math.random() * ((9 - random1) + 1));
                upDownVoteBean.saveNew(new UpDownVote(
                descriptions[i],
                voters.get(0 + (int)(Math.random() * ((9 - 0) + 1))),
                voters.subList(random0, random1),
                voters.subList(random1, random2),
                categorys[i],
                bilder[i]
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