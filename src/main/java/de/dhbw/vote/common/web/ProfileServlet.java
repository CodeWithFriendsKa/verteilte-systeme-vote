package de.dhbw.vote.common.web;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.dashboard.web.DashboardServlet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/myProfile/"})
public class ProfileServlet extends HttpServlet  {
    public static final String URL = "/myProfile/";
    private final CustomLogger logger = new CustomLogger(ProfileServlet.class);
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

    Sex[] sexList = Sex.values();
    request.setAttribute("sexList", sexList);
        
    //Find votes of all time
    Voter voter = null;
    
    try {
        voter = voterBean.getCurrentUser();

    } catch (Exception ex) {
        Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    if(voter != null){
        request.setAttribute("voter", voter);
    }
    
    logger.debug("doGet: " + voter.toString());
    
    // Routing to JSP
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/voter/myProfile.jsp");
    dispatcher.forward(request, response);
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
        
        //find actual Voter data
        Voter currentVoter = voterBean.getCurrentUser();
        
        //new Voter object
        Voter v = new Voter();
        
        //get the variables and set it to the username
        v.setUsername(request.getParameter("username"));
        v.setMail(request.getParameter("mail"));
        v.setPrename(request.getParameter("prename"));
        v.setName(request.getParameter("name"));
        v.setAge(Integer.parseInt(request.getParameter("age")));
        v.setSex(Enum.valueOf(Sex.class, request.getParameter("sex")));
        
        //update Voter data
        Voter vNeu = voterBean.update(v);
        
        logger.debug("doPost: " + currentVoter.toString() + v.toString());
        
        response.sendRedirect(request.getContextPath() + this.URL);
    }
}
