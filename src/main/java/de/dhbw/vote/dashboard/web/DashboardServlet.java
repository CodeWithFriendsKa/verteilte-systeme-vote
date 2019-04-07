package de.dhbw.vote.dashboard.web;

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

/***
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@WebServlet(urlPatterns = {"/app/dashboard/"})
public class DashboardServlet extends HttpServlet {
    public static final String URL = "/dashboard/";
    private static final CustomLogger logger = new CustomLogger(DashboardServlet.class);
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            logger.debug("dashboad do get ");
            //Find current Voter
            List<UpDownVote> votes = new ArrayList();
            Voter voter = new Voter();
            voter = voterBean.getCurrentUser();
            votes = upDownVoteBean.findVotesByUsername(voter.getUsername());
            //find some key values
            int myUpVotes = 0;
            int myDownVotes = 0;
            for (int i = 0; i < votes.size(); i++) {
                myUpVotes += votes.get(i).getUpVotes().size();
                myDownVotes += votes.get(i).getDownVotes().size();
            }

            //Find all votes
            List<UpDownVote> allVotes = upDownVoteBean.findAll();
            
            request.setAttribute("allVotes", allVotes);
            request.setAttribute("myUpVotes", myUpVotes);
            request.setAttribute("myDownVotes", myDownVotes);
            request.setAttribute("myVotes", votes);
            request.setAttribute("currentVoter", voter);
            
            request.setAttribute("BestAllTimes", upDownVoteBean.findBestVoteAllTime().get(0));
            request.setAttribute("WorstAllTimes", upDownVoteBean.findWorstVoteAllTime().get(0));
            request.setAttribute("BestMonth", upDownVoteBean.findBestVoteOfMonth().get(0));
            request.setAttribute("WorstMonth", upDownVoteBean.findWorstVoteOfMonth().get(0));
            request.setAttribute("BestWeek", upDownVoteBean.findBestVoteOfWeek().get(0));
            request.setAttribute("WorstWeek", upDownVoteBean.findWorstVoteOfWeek().get(0));
            request.setAttribute("BestDay", upDownVoteBean.findBestVoteOfDay().get(0));
            request.setAttribute("WorstDay", upDownVoteBean.findWorstVoteOfDay().get(0));

            // Routing to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/dashboard.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            
        }            
    }
}
