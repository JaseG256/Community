package com.Msa.communityservlet.model;

import com.Msa.communityservlet.model.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "places")
@Data
public class Place extends UserDateAudit {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameOfPlace;

    @Column
    private String location;

    @Column
    private Address address;

    public Place() {
    }

    public Place(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }

    public Place(String nameOfPlace, String location) {
        this.nameOfPlace = nameOfPlace;
        this.location = location;
    }

    public Place(String nameOfPlace, Address address) {
        this.nameOfPlace = nameOfPlace;
        this.address = address;
    }

    public Place(String nameOfPlace, String location, Address address) {
        this.nameOfPlace = nameOfPlace;
        this.location = location;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public void setNameOfPlace(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
