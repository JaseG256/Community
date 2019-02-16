package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Course;
import com.Msa.communityservlet.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
//import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CourseRepository courseRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByCourseNameTest() throws Exception {
        Course course = new Course();
        course.setCourseName("Biology");

        entityManager.persist(course);
        entityManager.flush();

        Optional<Course> found = courseRepository.findByCourseName(course.getCourseName());
        Course foundCourse = found.get();

        assertThat(foundCourse.getCourseName(), is(equalTo("Biology")));
        assertEquals(foundCourse.getCourseName(), course.getCourseName());
    }

}