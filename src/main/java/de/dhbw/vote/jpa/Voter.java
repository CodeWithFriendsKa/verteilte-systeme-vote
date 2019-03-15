/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.jpa;

import java.awt.Image;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author chp
 */
@Entity
public class Voter extends User implements Serializable {
    
    @Column(name = "PRENAME")
    private String prename;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;
    @Column(name = "SEX")
    private Sex sex;
    @Column(name = "AVATAR")
    private Image avatar;

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Voter(String prename, String name, int age, Sex sex, Image avatar, String username, String mail, String password) {
        super(username, mail, password);
        this.prename = prename;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.avatar = avatar;
    }
    
    public Voter() {
        super();
        this.prename = "";
        this.name = "";
        this.age = 0;
        this.sex = Sex.UNBEKANNT;
        this.avatar = null;
    }
//</editor-fold>

    public String getPrename() {return prename;}
    public void setPrename(String prename) {this.prename = prename;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public Sex getSex() {return sex;}
    public void setSex(Sex sex) {this.sex = sex;}
    public Image getAvatar() {return avatar;}
    public void setAvatar(Image avatar) {this.avatar = avatar;}

    @Override
    public String toString() {
        return "Voter{" + "prename=" + prename + ", name=" + name + ", age=" + age + ", sex=" + sex + ", avatar=" + avatar +", " + super.toString() +"}";
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.prename);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.age;
        hash = 29 * hash + Objects.hashCode(this.sex);
        hash = 29 * hash + Objects.hashCode(this.avatar);
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
        if (!Objects.equals(this.prename, other.prename)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        return true;
    }
    
    
}
