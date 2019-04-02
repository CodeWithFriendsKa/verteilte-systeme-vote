package de.dhbw.vote.common.web;

import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.dashboard.web.DashboardServlet;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/myProfile/"})
public class ProfileServlet extends HttpServlet  {
    
    public static final String URL = "/myProfile/";

    @EJB
    VoterBean voterBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    ArrayList sexList = new ArrayList();
    sexList.add("MÄNNLICH");
    sexList.add("WEIBLICH");
    sexList.add("DIVERS");
    request.setAttribute("sexList", sexList);
        
    //Find votes of all time
    Voter voter = null;
    
    try {
        voter = voterBean.findByUserName("MaMu1");

    } catch (VoterNotFoundException ex) {
        Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    if(voter != null){
        request.setAttribute("voter", voter);
    }
    
    
    // Routing to JSP
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/voter/myProfile.jsp");
    dispatcher.forward(request, response);
    
    
    }
}
