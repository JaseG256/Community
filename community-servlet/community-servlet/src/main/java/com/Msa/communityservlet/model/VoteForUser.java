//package com.Msa.communityservlet.model;
//
//import com.Msa.communityservlet.model.audit.DateAudit;
//import com.Msa.communityservlet.model.audit.UserDateAudit;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "votes", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//                "user_id"
//        })
//})
//public class VoteForUser extends UserDateAudit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
