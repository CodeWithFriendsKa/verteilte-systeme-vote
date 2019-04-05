/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.voting.web;

import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chp
 */
@WebServlet(urlPatterns = {"/app/voteserv/"})
public class VoteServlet extends HttpServlet{
    @EJB
    UpDownVoteBean upDownVoteBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
             List<UpDownVote> v = upDownVoteBean.findAll();
             Long firstId = v.get(1).getId();
             Long id = firstId;   
             
             response.sendRedirect("/vote/app/voteit/" + id);
            
        } catch (Exception e) {
            
        }
    }
}
