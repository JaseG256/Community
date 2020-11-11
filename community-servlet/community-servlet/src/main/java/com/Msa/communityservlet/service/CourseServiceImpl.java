package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.Course;
import com.Msa.communityservlet.repository.CourseRepository;
import com.Msa.communityservlet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "courseServicer")
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Course> findByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public List<Course> listAll() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public List<Course> retrieveCourseListForStudent(Long studentId) {
        return getUserCourseList(studentId);
    }

    @Override
    public Course retrieveCourseForStudent(Long studentId, Long courseId) {
        List<Course> courseList = getUserCourseList(studentId);
        return courseList.stream().filter(course -> course.getId().equals(courseId))
                .findAny().get();
    }

    @Override
    public Course addCourse(Long studentId, Course course) {
        return (userRepository.findById(studentId).get().getCourses().add(course))
                ? course : null;
    }

    @Override
    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course saveOrUpdate(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private List<Course> getUserCourseList(Long studentId) {
        return userRepository.findById(studentId).get().getCourses();
    }
}
