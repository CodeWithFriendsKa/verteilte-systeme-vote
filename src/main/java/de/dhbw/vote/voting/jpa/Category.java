package de.dhbw.vote.voting.jpa;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public enum Category {
    HOT_OR_NOT, AUTO, ESSEN, SPORT, UNBEKANNT;
    /**
     * Bezeichnung ermitteln
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
