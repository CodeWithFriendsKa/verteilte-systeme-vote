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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author chp
 */
@Entity
@Table(name= "VOTER")
public class Voter extends User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PRENAME")
    private String prename;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;
    @Column(name = "ADDRESS")
    private Address address;
    @Column(name = "SEX")
    private Sex sex;
    @Column(name = "AVATAR")
    private Image avatar;

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Voter(String prename, String name, int age, Address address, Sex sex, Image avatar, String username, String mail, String password) {
        super(username, mail, password);
        this.prename = prename;
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.avatar = avatar;
    }
    
    public Voter() {
        super();
        this.prename = "";
        this.name = "";
        this.age = 0;
        this.address = null;
        this.sex = Sex.UNBEKANNT;
        this.avatar = null;
    }
//</editor-fold>

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getPrename() {return prename;}
    public void setPrename(String prename) {this.prename = prename;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public Address getAddress() {return address;}
    public void setAddress(Address address) {this.address = address;}
    public Sex getSex() {return sex;}
    public void setSex(Sex sex) {this.sex = sex;}
    public Image getAvatar() {return avatar;}
    public void setAvatar(Image avatar) {this.avatar = avatar;}

    @Override
    public String toString() {
        return "Voter{" + "id=" + id + ", prename=" + prename + ", name=" + name + ", age=" + age + ", address=" + address + ", sex=" + sex + ", avatar=" + avatar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.prename);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.age;
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.sex);
        hash = 89 * hash + Objects.hashCode(this.avatar);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.sex, other.sex)) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        return true;
    }
    
    
    
}
