package de.dhbw.vote.voting.ejb;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.EntityBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
/***
 * Beanklasse, welche Datenbankzugriffe für UpDownVotes ermöglicht
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Stateless
@RolesAllowed("app-user")
public class UpDownVoteBean extends EntityBean<UpDownVote, Long>{
    private static final CustomLogger logger = new CustomLogger(UpDownVoteBean.class);
    @EJB
    VoterBean voterBean;
    
    /***
     * 
     */
    public UpDownVoteBean(){
        super(UpDownVote.class);
    }    
    
    public UpDownVote updateWorAround(UpDownVote upDownVote){
        this.delete(upDownVote);
        return this.saveNew(new UpDownVote(
                upDownVote.getDescription(),
                upDownVote.getCreator(),
                upDownVote.getUpVotes(),
                upDownVote.getDownVotes(),
                upDownVote.getCategory(),
                upDownVote.getImage()
        ));
    }
    
    /***
     * 
     * @param username
     * @return
     * @throws VoterNotFoundException
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findVotesByUsername(String username) throws VoterNotFoundException, UpDownVoteNotFoundException {
        List<UpDownVote> votes = new ArrayList();
        Voter voter = voterBean.findByUserName(username);
        
        votes = em.createQuery("SELECT v FROM UpDownVote v "
                            + "WHERE v.creator = :voter ")
                .setParameter("voter", voter)
                .getResultList();
        return votes;
    }
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findBestVoteAllTime() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.upSize = "
                + " (SELECT Max(x.upSize) FROM UpDownVote x)")
                .getResultList();
        
        return votes;
    }
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findWorstVoteAllTime() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        
        votes = (List<UpDownVote>) em.createQuery(""
                + " SELECT v FROM UpDownVote v "
                + " WHERE v.downSize = "
                + " (SELECT Max(x.downSize) FROM UpDownVote x)")
                .getResultList();
        
        return votes;
    }
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findBestVoteOfMonth() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastMonth = DateExtensions.getLastMonth(now);
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MAX(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastMonth)
                .getResultList();
        return votes;
    }
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findWorstVoteOfMonth() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastMonth = DateExtensions.getLastMonth(now);
 
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MIN(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastMonth)
                .getResultList();
        return votes;
    }    
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findBestVoteOfWeek() throws UpDownVoteNotFoundException{     
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastWeek = DateExtensions.getLastWeek(now);        

        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MAX(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastWeek)
                .getResultList();
        return votes;
    }
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findWorstVoteOfWeek() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastWeek = DateExtensions.getLastWeek(now);        
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MIN(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastWeek)
                .getResultList();
        return votes;
    } 
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findBestVoteOfDay() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastDay = DateExtensions.getLastDay(now);
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MAX(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastDay)
                .getResultList();
        return votes;
    }
    
    /***
     * 
     * @return
     * @throws UpDownVoteNotFoundException 
     */
    public List<UpDownVote> findWorstVoteOfDay() throws UpDownVoteNotFoundException{
        List<UpDownVote> votes = new ArrayList();
        Date now = DateExtensions.now();
        Date lastDay = DateExtensions.getLastDay(now);
        
        votes = (List<UpDownVote>) em.createQuery(
                "SELECT u FROM UpDownVote u "
                        + "WHERE u.upSize = "
                        + "(SELECT MIN(v.upSize) FROM UpDownVote v) "
                        + "AND u.date BETWEEN :lastMonth AND :now") 
                .setParameter("now", now)
                .setParameter("lastMonth", lastDay)
                .getResultList();
        return votes;
    }
    
    public byte[] imageToByte(File f){
        byte[] res = null;
        try{
            BufferedImage image = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            res = baos.toByteArray();
        }
        catch(Exception e) { 
            e.printStackTrace();
        }
        return res;
    }


}
