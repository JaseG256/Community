package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Course;
import com.Msa.communityservlet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    Optional<Course> findByCourseName(String courseName);

}
