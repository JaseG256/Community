package com.Msa.communityservlet.model;

import java.io.Serializable;

public class Address implements Serializable {

    private String id;

    private String streetNumber;
    private String city;
    private String state;
    private String country;
    private int zipCode;

    public Address() {
        country = "United States";
    }

    public Address(String streetNumber, String city, String state, String country, int zipCode) {
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Address(String streetNumber, String city, String state, int zipCode) {
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}

