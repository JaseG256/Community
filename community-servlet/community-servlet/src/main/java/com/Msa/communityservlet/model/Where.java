package com.Msa.communityservlet.model;

import com.Msa.communityservlet.model.audit.DateAudit;
import com.Msa.communityservlet.model.audit.UserDateAudit;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "where")
public class Where extends UserDateAudit {

    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String nameOfWhere;

//    @Column
//    private Address address;

    public Where() {
    }

    public Where(String nameOfWhere) {
        this.nameOfWhere = nameOfWhere;
    }

//    public Where(String nameOfWhere, Address address) {
//        this.nameOfWhere = nameOfWhere;
//        this.address = address;
//    }

    public String getId() {
        return id;
    }

    public String getnameOfWhere() {
        return nameOfWhere;
    }

    public void setnameOfWhere(String nameOfWhere) {
        this.nameOfWhere = nameOfWhere;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
