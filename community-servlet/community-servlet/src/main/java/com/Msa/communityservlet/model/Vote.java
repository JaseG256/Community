//package com.Msa.communityservlet.model;
//
//import com.Msa.communityservlet.model.audit.DateAudit;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "votes", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//                "user_id"
//        })
//})
//public class Vote extends DateAudit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
////    @ManyToOne(fetch = FetchType.LAZY, optional = false)
////    @JoinColumn(name = "poll_id", nullable = false)
////    private Poll poll;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_choice_id", nullable = false)
//    private User votableObject;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User voter;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
////    public Poll getPoll() {
////        return poll;
////    }
////
////    public void setPoll(Poll poll) {
////        this.poll = poll;
////    }
////
////    public User getVotableObject() {
////        return votableObject;
////    }
//
//    public void setVotableObject(User votableObject) {
//        this.votableObject = votableObject;
//    }
//
//    public User getVoter() {
//        return voter;
//    }
//
//    public void setVoter(User voter) {
//        this.voter = voter;
//    }
//}
