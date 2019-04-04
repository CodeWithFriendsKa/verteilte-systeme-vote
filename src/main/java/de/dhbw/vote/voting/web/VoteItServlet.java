/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.voting.web;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rouven Brost
 */
@WebServlet(urlPatterns = {"/app/voteit/*"})
public class VoteItServlet extends HttpServlet {

    private final CustomLogger logger = new CustomLogger(VoteItServlet.class);
    
    @EJB
    UpDownVoteBean upDownVoteBean;

    @EJB
    VoterBean voterBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            long id = -1;
            String pathInfo = request.getPathInfo();

            //current user
            Voter currentVoter = voterBean.getCurrentUser();

            UpDownVote vote = new UpDownVote();
            if (pathInfo != null) {
                logger.debug("TEST123");
                logger.debug(pathInfo);
                logger.debug(pathInfo.split("/")[1]);
                id = Long.parseLong(pathInfo.split("/")[1]);
                logger.debug(Long.toString(id));
            }
            vote = upDownVoteBean.findById(id);
            logger.debug(vote.toString());
            
            request.setAttribute("vote", vote);

            //Variable of already voted
            
            
            
            List<Voter> upV = vote.getUpVotes();
            List<Voter> downV = vote.getDownVotes();
            boolean alreadyVoted = ((upV.contains(currentVoter)) || (downV.contains(currentVoter)));
            logger.debug(Boolean.toString(alreadyVoted));
            request.setAttribute("alreadyVoted", alreadyVoted);
            logger.debug(Boolean.toString(alreadyVoted));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vote/voteIt.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {

        }
    }

}
