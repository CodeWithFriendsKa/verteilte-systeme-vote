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
    private static final CustomLogger logger = new CustomLogger(VoterBean.class);
    @PersistenceContext
    EntityManager em;
    @Resource
    EJBContext ctx;
    
    public Voter findByUserName(String username) throws VoterNotFoundException {
        Voter voter = (Voter) em.createQuery("SELECT v FROM Voter v WHERE v.username = :userName")
             .setParameter("userName", username)
             .getSingleResult();
        
        if(voter == null){
            throw new VoterNotFoundException("There is no user with the username: " + username);
        }
        
        return voter;
    }   
    public Voter getCurrentUser() {
        return this.em.find(Voter.class, this.ctx.getCallerPrincipal().getName());
    }
    public void signup(String username, String password, String mail, String prename, String name, int age, Sex sex) throws UserAlreadyExistsException {
        if (em.find(Voter.class, username) != null) {
            throw new UserAlreadyExistsException("Der Benutzername $B ist bereits vergeben.".replace("$B", username));
        }

        Voter user = new Voter(username, mail, password, prename, name, age, sex);
        user.addToGroup("app-user");
        em.persist(user);
    }
    @RolesAllowed("app-user")
    public void changePassword(Voter user, String oldPassword, String newPassword) throws InvalidCredentialsException {
        if (user == null || !user.checkPassword(oldPassword)) {
            throw new InvalidCredentialsException("Benutzername oder Passwort sind falsch.");
        }

        user.setPassword(newPassword);
    }
    @RolesAllowed("app-user")
    public void delete(Voter user) {
        this.em.remove(user);
    }
    @RolesAllowed("app-user")
    public Voter update(Voter user) {
        return em.merge(user);
    }
    public void deleteAll(){
        this.findAll().forEach(v -> {
            logger.debug("delete: " + v.toString());
            this.delete((Voter) v);
        });
    }
    public List<Voter> findAll() {
        String select = "SELECT v FROM Voter v";
        return em.createQuery(select).getResultList();
    }
}