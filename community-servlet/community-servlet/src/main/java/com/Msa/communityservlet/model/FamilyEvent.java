package com.Msa.communityservlet.model;

import com.Msa.communityservlet.model.audit.DateAudit;
import com.Msa.communityservlet.model.audit.UserDateAudit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "familyEvent")
public class FamilyEvent extends UserDateAudit {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private When when;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    public FamilyEvent() {
    }

    public FamilyEvent(String title) {
        this.title = title;
    }

    public FamilyEvent(When when, Place place) {
        this.when = when;
        this.place = place;
    }

    public FamilyEvent(String title, When when, Place place) {
        this.title = title;
        this.when = when;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public When getWhen() {
        return when;
    }

    public void setWhen(When when) {
        this.when = when;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
