package de.dhbw.vote.rest;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import java.util.List;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
    @EJB
    private AuthBean authBean;
    /*****
     * 
     * @return 
     */
    @GET
    public List<Voter> getAllVoters(@HeaderParam("Authorization") String base64){
        try {
            if(authBean.checkAuth(base64)){
                return voterBean.findAll();
            }
            else{
                return null;
            }        
        }
        catch(Exception e){
            logger.error("Error", e);
            return null;        
        }
    }
    @GET
    @Path("{username}")
    public Voter getVoterByUsername(@PathParam("username") String username, @HeaderParam("Authorization") String base64){
        try {
            if(authBean.checkAuth(base64)){
                return voterBean.findByUserName(username);
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            logger.error("Error", e);
            return null;
        }
    }
}
