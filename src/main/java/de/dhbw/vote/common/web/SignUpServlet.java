package de.dhbw.vote.common.web;

import de.dhbw.vote.common.CustomLogger;
import de.dhbw.vote.common.ejb.VoterAlreadyExistsException;
import de.dhbw.vote.common.ejb.ValidationBean;
import de.dhbw.vote.common.ejb.VoterBean;
import de.dhbw.vote.common.jpa.Sex;
import de.dhbw.vote.common.jpa.Voter;
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
import javax.servlet.http.HttpSession;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@WebServlet(urlPatterns = {"/signup/"})
public class SignUpServlet extends HttpServlet {
    private static final CustomLogger logger = new CustomLogger(SignUpServlet.class);
    @EJB
    private ValidationBean validationBean;
    @EJB
    private VoterBean voterBean;
    
    /***
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login/signup.jsp");
        dispatcher.forward(request, response);
        
        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("signup_form");
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
            //Error liste für die Fehlermeldungen anlegen
            List<String> errors = new ArrayList<>();
            
            // Formulareingaben auslesen        
            String username = request.getParameter("signup_username");
            String password1 = request.getParameter("signup_password1");
            String password2 = request.getParameter("signup_password2");
            String mail = request.getParameter("signup_mail");
            String prename = request.getParameter("signup_prename");
            String name = request.getParameter("signup_name");
            int age =  Integer.parseInt(request.getParameter("signup_age"));
            Sex sex = Sex.getSex(request.getParameter("signup_sex"));
            logger.debug(
                    "do POST ausgelesene Formulardaten:" + " " + 
                    username + " " + 
                    password1 + " " + 
                    password2 + " " + 
                    mail + " " + 
                    prename + " " + 
                    name + " " + 
                    age + " " + 
                    sex
            );
            
            //Voter validieren
            if (!password1.equals(password2)){
                errors.add("Die Passwörter stimmen nicht überein!");
            }
            errors = validationBean.validate(new Voter(), errors);
            
            //Wenn keine Exception geworfen wurde registroere den Voter und gehe auf die nächste Siete 
            if (true) {
                this.voterBean.signup(
                    username,
                    password1,
                    mail,
                    prename,
                    name,
                    age,
                    sex
            );
            request.login(username, password1);
            response.sendRedirect("/vote/app/dashboard/");
            } 
        } 
        catch (VoterAlreadyExistsException ex) {
            logger.error("Der Voter existiert bereits", ex);
            request.getRequestDispatcher("/WEB-INF/login/error.jsp").forward(request, response);
        }
    }
}
