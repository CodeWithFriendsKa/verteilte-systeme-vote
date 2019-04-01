package de.dhbw.vote.dashboard.web;

import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.ejb.UpDownVoteNotFoundException;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chp
 */
@WebServlet(urlPatterns = {"/dashboard/"})
public class DashboardServlet extends HttpServlet {

    public static final String URL = "/dashboard/";

    @EJB
    UpDownVoteBean upDownVoteBean;

    @EJB
    VoterBean voterBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    //Find votes of voter 1
    List<UpDownVote> votes = new ArrayList();
    try {
        //ToDo: Ersetze den HardCode MaMu1 durch voterBean.getCurrentUser();
        votes = upDownVoteBean.findVotesByUsername("MaMu1");
    } catch (Exception ex) {
        Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    if(votes != null){
        //find some key values
        int myUpVotes = 0;
        int myDownVotes = 0;
        for(int i = 0; i < votes.size(); i++){
            myUpVotes += votes.get(i).getUpSize();
            myDownVotes += votes.get(i).getDownSize();
        }
        
        request.setAttribute("myUpVotes", myUpVotes);
        request.setAttribute("myDownVotes", myDownVotes);
        request.setAttribute("myVotes", votes);
    }
    
    //Find votes of all time
    List<UpDownVote> votesBestAllTime = new ArrayList();
    List<UpDownVote> votesWorstAllTime = new ArrayList();
    List<UpDownVote> votesBestMonth = new ArrayList();
    List<UpDownVote> votesWorstMonth = new ArrayList();
    List<UpDownVote> votesBestWeek = new ArrayList();
    List<UpDownVote> votesWorstWeek = new ArrayList();
    List<UpDownVote> votesBestDay = new ArrayList();
    List<UpDownVote> votesWorstDay = new ArrayList();
    try {
        votesBestAllTime = upDownVoteBean.findBestVoteAllTime();
        votesWorstAllTime = upDownVoteBean.findWorstVoteAllTime();
        votesBestMonth = upDownVoteBean.findBestVoteOfMonth();
        votesWorstMonth = upDownVoteBean.findWorstVoteOfMonth();
        votesBestWeek = upDownVoteBean.findBestVoteOfWeek();
        votesWorstWeek = upDownVoteBean.findWorstVoteOfWeek();
        votesBestDay = upDownVoteBean.findBestVoteOfDay();
        votesWorstDay = upDownVoteBean.findWorstVoteOfDay();
    } catch (UpDownVoteNotFoundException ex) {
        Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    if(votes != null){
        request.setAttribute("BestAllTimes", votesBestAllTime);
        request.setAttribute("WorstAllTimes", votesWorstAllTime);
        request.setAttribute("BestMonth", votesBestMonth);
        request.setAttribute("WorstMonth", votesWorstMonth);
        request.setAttribute("BestWeek", votesBestWeek);
        request.setAttribute("WorstWeek", votesWorstWeek);
        request.setAttribute("BestDay", votesBestDay);
        request.setAttribute("WorstDay", votesWorstDay);
    }
    
    
    // Routing to JSP
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/dashboard.jsp");
    dispatcher.forward(request, response);
    
    
    }
}
