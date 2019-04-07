/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.rest;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.util.List;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/***
 * REST Endpoint für eine UpDownVote
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Path("UpDownVote")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@RunAs("app-user")
public class UpDownVoteResource {
    private final CustomLogger logger = new CustomLogger(UpDownVote.class);
    @EJB
    private UpDownVoteBean upDownVoteBean;
    @EJB
    private AuthBean authBean;
    
    /***
     * 
     * @param base64
     * @return 
     */
    @GET
    public List<UpDownVote> getAllUpDownVotes(@HeaderParam("Authorization") String base64){
        try {
            if(authBean.checkAuth(base64)){
                return upDownVoteBean.findAll();
            }
            else{
                return null;
            }  
        } catch (Exception e) {
            logger.error("ERROR", e);
            return null;
        }
    }
    
    /***
     * 
     * @param username
     * @param base64
     * @return 
     */
    @GET
    @Path("{username}")
    public UpDownVote getUpDownVoteByUsername(@PathParam("username") String username, @HeaderParam("Authorization") String base64){
        try {
            if(authBean.checkAuth(base64)){
                return upDownVoteBean.findVotesByUsername(username).get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            return null;
        }
    }    
}