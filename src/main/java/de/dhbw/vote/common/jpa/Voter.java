package de.dhbw.vote.common.jpa;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Entity
@Table(name= "VOTER")
public class Voter implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    @Id
    @Column(name = "USERNAME", length = 64)
    @Size(min = 3, max = 64, message = "Der Benutzername muss zwischen fünf und 64 Zeichen lang sein.")
    @NotNull(message = "Der Benutzername darf nicht leer sein.")
    private String username;
    @Transient
    private final Password password = new Password();
    @Column(name = "PASSWORD_HASH", length = 64)
    @NotNull(message = "Das Passwort darf nicht leer sein.")
    private String passwordHash;
    @ElementCollection
    @CollectionTable(
            name = "JTODO_USER_GROUP",
            joinColumns = @JoinColumn(name = "USERNAME")
    )
    @Column(name = "GROUPNAME")
    List<String> groups = new ArrayList<>();
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "PRENAME")
    private String prename;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;
    @Column(name = "SEX")
    private Sex sex;

    public Voter(String username, String mail, String password, String prename, String name, int age, Sex sex) {
        this.username = username;
        this.mail = mail;
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
        this.prename = prename;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public Voter() {
        this.username = "";
        this.mail = "";
        this.password.password = "";
        this.prename = "";
        this.name = "";
        this.age = 0;
        this.sex = Sex.UNBEKANNT;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
    public void setPassword(String password) {
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
    }
    public Password getPassword() {
        return this.password;
    }
    public String getPrename() {return prename;}
    public void setPrename(String prename) {this.prename = prename;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public Sex getSex() {return sex;}
    public void setSex(Sex sex) {this.sex = sex;}
    
    private String hashPassword(String password) {
        byte[] hash;

        if (password == null) {
            password = "";
        }

        // Hashwert zum Passwort berechnen
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            hash = "!".getBytes(StandardCharsets.UTF_8);
        }

        // Hashwert in einen Hex-String umwandeln
        // Vgl. https://stackoverflow.com/a/9855338
        char[] hashHex = new char[hash.length * 2];

        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            hashHex[i * 2] = HEX_ARRAY[v >>> 4];
            hashHex[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hashHex);
    }
    public boolean checkPassword(String password) {
        return this.passwordHash.equals(this.hashPassword(password));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Zuordnung zu Benutzergruppen">
    /**
     * @return Eine unveränderliche Liste aller Benutzergruppen
     */
    public List<String> getGroups() {
        List<String> groupsCopy = new ArrayList<>();

        this.groups.forEach((groupname) -> {
            groupsCopy.add(groupname);
        });

        return groupsCopy;
    }

    /**
     * Fügt den Benutzer einer weiteren Benutzergruppe hinzu.
     *
     * @param groupname Name der Benutzergruppe
     */
    public void addToGroup(String groupname) {
        if (!this.groups.contains(groupname)) {
            this.groups.add(groupname);
        }
    }

    /**
     * Entfernt den Benutzer aus der übergebenen Benutzergruppe.
     *
     * @param groupname Name der Benutzergruppe
     */
    public void removeFromGroup(String groupname) {
        this.groups.remove(groupname);
    }

    @Override
    public String toString() {
        return "Voter{" + "username=" + username + ", password=" + password + ", passwordHash=" + passwordHash + ", groups=" + groups + ", mail=" + mail + ", prename=" + prename + ", name=" + name + ", age=" + age + ", sex=" + sex + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.passwordHash);
        hash = 37 * hash + Objects.hashCode(this.groups);
        hash = 37 * hash + Objects.hashCode(this.mail);
        hash = 37 * hash + Objects.hashCode(this.prename);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.age;
        hash = 37 * hash + Objects.hashCode(this.sex);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voter other = (Voter) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.passwordHash, other.passwordHash)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.prename, other.prename)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.groups, other.groups)) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        return true;
    }
   
}
