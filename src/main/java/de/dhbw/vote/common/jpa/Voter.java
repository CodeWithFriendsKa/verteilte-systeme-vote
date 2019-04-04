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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/***
 * Klasse für einen Voter, diese beinhaltet klassiche User- sowie
 * Profilattribute
 * 
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
    @ElementCollection(fetch = FetchType.EAGER)
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

    /***
     * 
     * @param username
     * @param mail
     * @param password
     * @param prename
     * @param name
     * @param age
     * @param sex 
     */
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
    
    /***
     * 
     */
    public Voter() {
        this.username = "";
        this.mail = "";
        this.password.password = "";
        this.prename = "";
        this.name = "";
        this.age = 0;
        this.sex = Sex.UNBEKANNT;
    }

    /***
     * 
     * @return 
     */
    public String getUsername() {return username;}   
    public void setUsername(String username) {this.username = username;}
    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
    public String getPrename() {return prename;}
    public void setPrename(String prename) {this.prename = prename;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public Sex getSex() {return sex;}
    public void setSex(Sex sex) {this.sex = sex;}
    public void setPassword(String password) {
        this.password.password = password;
        this.passwordHash = this.hashPassword(password);
    }
    public Password getPassword() {
        return this.password;
    }    
    
    /***
     * 
     * @param password
     * @return 
     */
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
    
    /***
     * 
     * @param password
     * @return 
     */
    public boolean checkPassword(String password) {
        return this.passwordHash.equals(this.hashPassword(password));
    }

    /***
     * 
     * @return 
     */
    public List<String> getGroups() {
        List<String> groupsCopy = new ArrayList<>();

        this.groups.forEach((groupname) -> {
            groupsCopy.add(groupname);
        });

        return groupsCopy;
    }

    /***
     * 
     * @param groupname 
     */
    public void addToGroup(String groupname) {
        if (!this.groups.contains(groupname)) {
            this.groups.add(groupname);
        }
    }

    /***
     * 
     * @param groupname 
     */
    public void removeFromGroup(String groupname) {
        this.groups.remove(groupname);
    }
    // <editor-fold defaultstate="collapsed" desc=" ${Override Methoden} ">
    /***
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Voter{" + "username=" + username + ", password=" + password + ", passwordHash=" + passwordHash + ", groups=" + groups + ", mail=" + mail + ", prename=" + prename + ", name=" + name + ", age=" + age + ", sex=" + sex + '}';
    }

    /***
     * 
     * @return 
     */
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

    /***
     * 
     * @param obj
     * @return 
     */
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
    // </editor-fold>
}
