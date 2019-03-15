/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.jpa;

/**
 *
 * @author chp
 */
public enum Sex {
    MÄNNLICH, WEIBLICH, DIVERS, UNBEKANNT;
    
    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case MÄNNLICH:
                return "männlich";
            case WEIBLICH:
                return "weiblich";
            case DIVERS:
                return "divers";
            default:
                return this.toString();
        }
    }
    
}
