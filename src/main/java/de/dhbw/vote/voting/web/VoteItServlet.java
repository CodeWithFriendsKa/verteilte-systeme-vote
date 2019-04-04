package de.dhbw.vote.voting.web;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***

 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@WebServlet(urlPatterns = {"/app/voteit/*"})
public class VoteItServlet extends HttpServlet {
    private final CustomLogger logger = new CustomLogger(VoteItServlet.class);
    @EJB
    UpDownVoteBean upDownVoteBean;
    @EJB
    VoterBean voterBean;

    /***
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
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
                vote = upDownVoteBean.findById(id);
                logger.debug(vote.toString());
            }
            
            
            request.setAttribute("vote", vote);

            //Variable of already voted
           
            List<Voter> upV = vote.getUpVotes();
            List<Voter> downV = vote.getDownVotes();
            boolean alreadyVoted = false;
            logger.debug(upV.toString());
            logger.debug(downV.toString());
            logger.debug(upV.size() + "");
            for(int upI = 0; upI < upV.size(); upI++){
                logger.debug(upV.get(upI).getUsername());
                logger.debug(currentVoter.getUsername());
                if((upV.get(upI).getUsername()) == null ? (currentVoter.getUsername()) == null : (upV.get(upI).getUsername()).equals(currentVoter.getUsername())){
                    alreadyVoted = true;
                    logger.debug("Inside UP true");
                    logger.debug(alreadyVoted + "");
                }
            }
            for(int downI = 0; downI < downV.size(); downI++){
                logger.debug(upV.get(downI).getUsername());
                logger.debug(currentVoter.getUsername());
                if(downV.get(downI).getUsername() == null ? (currentVoter.getUsername()) == null : downV.get(downI).getUsername().equals(currentVoter.getUsername())){
                    alreadyVoted = true;
                    logger.debug("Inside DONW true");
                    logger.debug(alreadyVoted + "");
                }
            }
            //alreadyVoted = ((upV.contains(currentVoter)) || (downV.contains(currentVoter)));
            logger.debug(Boolean.toString(alreadyVoted));
            request.setAttribute("alreadyVoted", alreadyVoted);
            logger.debug(Boolean.toString(alreadyVoted));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vote/voteIt.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {

        }
    }
    
    /***
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        
        //find current User
        Voter currentVoter = voterBean.getCurrentUser();

        long id = -1;
        String pathInfo = request.getPathInfo();

        UpDownVote vote = new UpDownVote();
        if (pathInfo != null) {
            id = Long.parseLong(pathInfo.split("/")[1]);
            vote = upDownVoteBean.findById(id);
        }
        
        //Set UP vote
        if(request.getParameter("hot") != null){
            vote.getUpVotes().add(currentVoter);
            upDownVoteBean.update(vote);
        }
        
        //Set Down vote
        if(request.getParameter("not") != null){
            vote.getDownVotes().add(currentVoter);
            upDownVoteBean.update(vote);
        }

        response.sendRedirect("/vote/app/dashboard/");
    }
    
}
