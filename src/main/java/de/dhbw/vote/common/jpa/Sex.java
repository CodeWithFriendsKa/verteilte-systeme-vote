package de.dhbw.vote.common.jpa;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public enum Sex {
    MÄNNLICH, WEIBLICH, DIVERS, UNBEKANNT;
    /**
     * Bezeichnung ermitteln
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
    public static Sex getSex(String sex){
        switch (sex) {
            case "männlich":
                return MÄNNLICH;
            case "weiblich":
                return WEIBLICH;
            case "divers":
                return DIVERS;
            default:
                return UNBEKANNT;
        }
    }
}
