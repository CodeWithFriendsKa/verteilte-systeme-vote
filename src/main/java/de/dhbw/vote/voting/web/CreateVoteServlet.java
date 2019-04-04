package de.dhbw.vote.voting.web;

import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.jpa.Category;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.io.IOException;
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
@WebServlet(urlPatterns = {"/createvote/"})
public class CreateVoteServlet extends HttpServlet {
    public static final String URL = "/createvote/";
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
        
        Category[] categoryList = Category.values();
        request.setAttribute("categoryList", categoryList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/vote/createVote.jsp");
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
        
        //find current User
        Voter creator = voterBean.getCurrentUser();

        
        Category c = Enum.valueOf(Category.class, request.getParameter("category"));
        String d = request.getParameter("description");

       // vote.setImage(request.getParameter("image"));

               
        UpDownVote vote = new UpDownVote(d,creator,c);
       
        UpDownVote newVote = upDownVoteBean.saveNew(vote);
        
        response.sendRedirect("/vote/app/dashboard/");
    }
}

