package com.Msa.communityservlet.controller;

import com.Msa.communityservlet.model.Course;
import com.Msa.communityservlet.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/course")
@RestController
public class CourseController {

    @Autowired
    @Qualifier("courseServicer")
    private CourseService courseService;

    @GetMapping("/students/{studentId}")
    public List<Course> retrieveCourseListForStudent(@PathVariable Long studentId) {
        return courseService.retrieveCourseListForStudent(studentId);
    }
}
