/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.common.web;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.ejb.VoterNotFoundException;
import de.dhbw.vote.common.jpa.Voter;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author codekeks
 */
@WebServlet(urlPatterns = {"/login/"})
public class LoginServlet extends HttpServlet {
    
    private final CustomLogger logger = new CustomLogger(LoginServlet.class);
    @EJB
    VoterBean voterBean;
    /**
     * GET-Anfrage: Seite anzeigen
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        logger.debug("GET");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login/login.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        try {      
            String username = request.getParameter("login_username");
            String password = request.getParameter("login_password");
            
            logger.debug("username: " + username + " passwort: " + password);
            Voter voter = voterBean.findByUserName(username);
            if (voter.checkPassword(password)) {
                request.login(username, password);
                response.sendRedirect("/vote/app/dashboard/");
            }
        } catch (VoterNotFoundException ex) {
            
        }
    }
}
