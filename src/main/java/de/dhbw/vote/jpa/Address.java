/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.jpa;

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
@Table(name= "ADDRESS")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CITY")
    private String city;
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "STREET")
    private String street;
    @Column(name = "HOUSENO")
    private String houseNo;
    @Column(name = "COUNTRY")
    private String country;

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Address(String city, String postcode, String street, String houseNo, String country) {
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.houseNo = houseNo;
        this.country = country;
    }
    
    public Address() {
        this.city = "";
        this.postcode = "";
        this.street = "";
        this.houseNo = "";
        this.country = "";
    }
//</editor-fold>

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getPostcode() {return postcode;}
    public void setPostcode(String postcode) {this.postcode = postcode;}
    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}
    public String getHouseNo() {return houseNo;}
    public void setHouseNo(String houseNo) {this.houseNo = houseNo;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", city=" + city + ", postcode=" + postcode + ", street=" + street + ", houseNo=" + houseNo + ", country=" + country + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.postcode);
        hash = 89 * hash + Objects.hashCode(this.street);
        hash = 89 * hash + Objects.hashCode(this.houseNo);
        hash = 89 * hash + Objects.hashCode(this.country);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.houseNo, other.houseNo)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
