/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author codekeks
 */
@Entity
@Table(name= "VOTE_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "PASSWORD")
    private String password;

    public User(String username,String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
    public User() {
        this.username = "";
        this.mail = "";
        this.password = "";
    }
    
    public static long getSerialVersionUID() {return serialVersionUID;}
    public Long getId() {return id;}
    public String getUsername() {return username;}
    public String getMail(){return mail;}
    public String getPassword() {return password;}
    public void setId(Long id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setMail(String mail) {this.mail = mail;}
    public void setPassword(String password) {this.password = password;}
    
    //to do Passwort hashen
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", mail=" + mail + ", password=" + password + '}';
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.mail);
        hash = 67 * hash + Objects.hashCode(this.password);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
}