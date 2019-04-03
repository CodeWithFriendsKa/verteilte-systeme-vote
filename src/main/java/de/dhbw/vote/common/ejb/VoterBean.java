package de.dhbw.vote.common.ejb;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.common.jpa.Voter;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Stateless
public class VoterBean{
    private final CustomLogger logger = new CustomLogger(VoterBean.class);
    @PersistenceContext
    EntityManager em;
    @Resource
    public EJBContext ctx;
    
    public Voter findByUserName(String username) throws VoterNotFoundException {
        Voter voter = (Voter) em.createQuery("SELECT v FROM Voter v WHERE v.username = :userName")
             .setParameter("userName", username)
             .getSingleResult();
        
        if(voter == null){
            throw new VoterNotFoundException("There is no user with the username: " + username);
        }
        
        logger.debug("findByUserName: " + voter.toString());
        return voter;
    }   
    
    public Voter getCurrentUser() {
        Voter voter = this.em.find(Voter.class, this.ctx.getCallerPrincipal().getName()); 
        
        logger.debug("getCurrentUser: " + voter.toString());
        return voter;
    }
    
    public void signup(String username, String password, String mail, String prename, String name, int age, Sex sex) throws UserAlreadyExistsException {
        if (em.find(Voter.class, username) != null) {
            throw new UserAlreadyExistsException("Der Benutzername $B ist bereits vergeben.".replace("$B", username));
        }

        Voter voter = new Voter(username, mail, password, prename, name, age, sex);
        voter.addToGroup("app-user");
        
        logger.debug("signup:" + voter);
        em.persist(voter);
    }
    
    @RolesAllowed("app-user")
    public void changePassword(Voter voter, String oldPassword, String newPassword) throws InvalidCredentialsException {
        if (voter == null || !voter.checkPassword(oldPassword)) {
            throw new InvalidCredentialsException("Benutzername oder Passwort sind falsch.");
        }

        logger.debug(
                "changePassword:" + " " +
                voter.toString() + " " +
                oldPassword + " " +
                newPassword
        );
        voter.setPassword(newPassword);
    }
    
    @RolesAllowed("app-user")
    public void delete(Voter voter) {
        logger.debug("delete: "+ voter);
        this.em.remove(voter);
    }
    
    @RolesAllowed("app-user")
    public Voter update(Voter voter) {
        logger.debug("update: "+ voter);
        return em.merge(voter);
    }
    
    public void deleteAll(){
        this.findAll().forEach(v -> {
            logger.debug("delete: " + v.toString());
            this.delete((Voter) v);
        });
    }
    
    public List<Voter> findAll() {
        String select = "SELECT v FROM Voter v";
        List<Voter> voters =  em.createQuery(select).getResultList();
        
        voters.forEach(v -> logger.debug("findAll: " + v.toString()));
        return voters;
    }
}