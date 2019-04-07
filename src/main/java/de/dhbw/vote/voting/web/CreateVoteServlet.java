package de.dhbw.vote.voting.web;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Voter;
import de.dhbw.vote.voting.ejb.UpDownVoteBean;
import de.dhbw.vote.voting.jpa.Category;
import de.dhbw.vote.voting.jpa.UpDownVote;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@WebServlet(urlPatterns = {"/createvote/"})
@MultipartConfig
public class CreateVoteServlet extends HttpServlet {
    private final CustomLogger logger = new CustomLogger(CreateVoteServlet.class);
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        request.setCharacterEncoding("utf-8");
        //Hole alle relevanten Parameter aus dem Request für ein neues UpDownVote
        Voter creator = voterBean.getCurrentUser();
        
        Category category = Category.AUTO;
        //Category category = Enum.valueOf(Category.class, request.getParameter("category"));
        String description = "test";
        //String description = request.getParameter("description");        
        logger.debug(creator.toString());
        logger.debug(description);
        logger.debug(category.getLabel());
        
        Part myFile = request.getPart("myFile");
        InputStream myFileStream = myFile.getInputStream();
        byte[] filecontent = new byte[(int) myFile.getSize()];
        int i = 0, b;

        while ((b = myFileStream.read()) != -1) {
            filecontent[i++] = (byte) b;
        }        
        

        logger.debug(filecontent.toString());
        
        //Erzeuge und speichere das neu erstellte Voting
        UpDownVote upDownVote = 
                upDownVoteBean.saveNew(
                    new UpDownVote(
                        description,
                        creator,
                        category,
                        filecontent                
                    )
                );
        
/*        logger.debug(
                "doPost:" + " " +
                "UpDownVote: " + upDownVote.toString()
        );
  */      
        //Zurück zum Dashboard
        response.sendRedirect("/vote/app/dashboard/");            
        } catch (Exception e) {
            logger.error("ERROR", e);
        }
    }
}

