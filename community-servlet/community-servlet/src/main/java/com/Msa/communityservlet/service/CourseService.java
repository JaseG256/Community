package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService extends CRUDService<Course> {

    Optional<Course> findByCourseName(String courseName);

    List<Course> retrieveCourseListForStudent(Long studentId);

    Course retrieveCourseForStudent(Long studentId, Long courseId);

    Course addCourse(Long studentId, Course course);
}
