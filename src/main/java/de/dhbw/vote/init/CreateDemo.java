/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.init;

import de.dhbw.vote.ejb.UserBean;
import de.dhbw.vote.jpa.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author codekeks
 */
@Startup
@Singleton
public class CreateDemo {
    
    private final Logger logger = LoggerFactory.getLogger(CreateDemo.class);
    @EJB
    private UserBean userBean;
    
    @PostConstruct
    private void saveDemoData(){
        System.out.println("SAVE DEMODATA IN DATABASE BEGIN");
        //var user = userBean.saveNew(new User("maxi","max@dhbw.de", "123"));
        //System.out.println("save demo user in database: " + user.toString());
        
        userBean.deleteAll();
        
        List<User> users = userBean.findAll();
        
        System.out.println("user size" + users.size());
        
        users.forEach(u -> System.out.println("find all demo users in database: " + u.toString()));
        System.out.println("SAVE DEMODATA IN DATABASE END");
    }
}