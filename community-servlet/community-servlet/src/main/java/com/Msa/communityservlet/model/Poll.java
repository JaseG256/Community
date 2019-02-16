package com.Msa.communityservlet.model;

import com.Msa.communityservlet.model.audit.UserDateAudit;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String question;
//
//    @OneToMany(
//            mappedBy = "poll",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            orphanRemoval = true
//    )
//    @Size(min = 2, max = 6)
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size = 30)
//    private List<User> choices = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
//
//    public List<User> getChoices() {
//        return choices;
//    }
//
//    public void setChoices(List<User> choices) {
//        this.choices = choices;
//    }
}

