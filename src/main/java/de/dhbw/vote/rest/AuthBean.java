/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.rest;

import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import java.util.Base64;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author codekeks
 */
@Stateless
@RunAs("app-user")
public class AuthBean {
    
    @EJB
    private VoterBean voterBean;
    
    public boolean checkAuth(String base64) throws VoterNotFoundException, AuthorizationException{
        try {
            String[] userAndPasswort = getUsernamePasswort(base64);
            Voter voter = voterBean.findByUserName(userAndPasswort[0]);
        
            if(voter.checkPassword(userAndPasswort[1])){
                return true;
            }
            else return false;
        } 
        catch (Exception e) {
            throw new AuthorizationException("Falscher Username oder Passwort", e);
        }
    }

    private String[] getUsernamePasswort(String base64) {
        String decodedString = new String(Base64.getDecoder().decode(base64.split(" ")[1].getBytes()));
        return decodedString.split(":");
    }    
}
