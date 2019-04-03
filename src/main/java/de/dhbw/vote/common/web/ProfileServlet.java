package de.dhbw.vote.common.web;

import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Sex;
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

    Sex[] sexList = Sex.values();
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
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        
        //new Voter object
        Voter v = new Voter();
        
        //get the variables and set it to the username
        v.setUsername(request.getParameter("username"));
        v.setMail(request.getParameter("mail"));
        v.setPrename(request.getParameter("prename"));
        v.setName(request.getParameter("name"));
        v.setAge(Integer.parseInt(request.getParameter("age")));
        v.setSex(Enum.valueOf(Sex.class, request.getParameter("sex")));
        
        Voter vNeu = voterBean.update(v);
        
        response.sendRedirect(request.getContextPath() + this.URL);
    }
}
