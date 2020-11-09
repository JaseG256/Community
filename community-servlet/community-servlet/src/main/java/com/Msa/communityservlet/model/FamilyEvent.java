package com.Msa.communityservlet.model;

import com.Msa.communityservlet.model.audit.DateAudit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "familyEvent")
public class FamilyEvent extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String title;

    @Column
    private When when;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "where_id", nullable = false)
    private Where where;

    public FamilyEvent() {
    }

    public FamilyEvent(When when, Where where) {
        this.when = when;
        this.where = where;
    }

    public FamilyEvent(String title, When when, Where where) {
        this.title = title;
        this.when = when;
        this.where = where;
    }

    public String getId() {
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

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }
}
