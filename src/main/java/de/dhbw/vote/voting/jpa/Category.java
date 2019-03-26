/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.voting.jpa;

/**
 *
 * @author chp
 */
public enum Category {
    HOT_OR_NOT, AUTO, ESSEN, SPORT, UNBEKANNT;
    
    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case HOT_OR_NOT:
                return "Hot or Not";
            case AUTO:
                return "Auto";
            case ESSEN:
                return "Essen";
            case SPORT:
                return "Sport";
            default:
                return this.toString();
        }
    }
}
