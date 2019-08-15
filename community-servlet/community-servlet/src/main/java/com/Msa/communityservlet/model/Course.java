package com.Msa.communityservlet.model;


import com.Msa.communityservlet.exception.BadRequestException;
import com.Msa.communityservlet.model.audit.UserDateAudit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    private String description;

    @ElementCollection
    private List<String> steps = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<User> userList = new ArrayList<>();

    public Course() { }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser(Long userId) {
        return userList.stream()
                .filter(user -> user.getId().equals(userId))
                .reduce((user, user2) -> (user.getId().equals(userId)) ? user : user2)
                .orElseThrow(() -> new BadRequestException("This user does not exist"));
    }

    @Override
    public String toString() {
        return String.format(
                "Course [courseName=%s, description=%s]", courseName, description
        );
    }
}
