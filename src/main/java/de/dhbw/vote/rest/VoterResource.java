package de.dhbw.vote.rest;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author codekeks
 */
@Path("Voter")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
}
