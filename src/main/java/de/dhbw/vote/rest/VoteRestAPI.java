package de.dhbw.vote.rest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/***
 * REST api Applicationklasse
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@ApplicationPath("api")
public class VoteRestAPI extends Application{
    
    /***
     * 
     * @return 
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(VoterResource.class);
        resources.add(UpDownVoteResource.class);

        return resources;
    }
}
