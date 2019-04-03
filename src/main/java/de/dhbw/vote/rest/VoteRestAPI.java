/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.rest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author codekeks
 */
@ApplicationPath("api")
public class VoteRestAPI extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        // Hier für jede Webservice-Klasse eine Zeile hinzufügen

        return resources;
    }
}
