package de.dhbw.vote.rest;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import java.util.List;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/***
 * REST Endpoint für einen Voter
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Path("Voter")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@RunAs("app-user")
public class VoterResource {
    private static final CustomLogger logger = new CustomLogger(VoterResource.class);
    @EJB
    private VoterBean voterBean;
    /*****
     * 
     * @return 
     */
    @GET
    public List<Voter> getAllVoters(){
        return voterBean.findAll();
    }
    @GET
    @Path("{username}")
    public Voter getVoterByUsername(@PathParam("username") String username){
        try {
            return voterBean.findByUserName(username);
        }
        catch(Exception e){
            logger.error("Error", e);
            return null;
        }
    }
}
