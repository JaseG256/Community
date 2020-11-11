package com.Msa.communityservlet.service;

import com.Msa.communityservlet.config.ServiceUnitTestConfig;
import com.Msa.communityservlet.model.Course;
import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.repository.CourseRepository;
import com.Msa.communityservlet.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceUnitTestConfig.class)
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    private Course firstCourse, secondCourse;
    private String firstCourseName, secondCourseName;
    private Long firstId, secondId;
    private User jason;
    private List<Course> courseList;
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        firstCourseName = "Biology"; secondCourseName = "Chemistry";
        firstId = 1L; secondId = 2L;
        firstCourse = new Course(firstCourseName, "Learn the animal kingdom!");
        firstCourse.setId(firstId);
        secondCourse = new Course(secondCourseName, "Learn about the periodic table!");
        secondCourse.setId(secondId);
        courseList = Arrays.asList(firstCourse, secondCourse);
        jason = new User("jason", "jason@yahoo.com", "jumpshot");
        userList = Collections.singletonList(jason);
        firstCourse.setUserList(userList);
        secondCourse.setUserList(userList);

    }

    @Test
    public void findByCourseName() {
        Mockito.when(courseService.findByCourseName(firstCourse.getCourseName()))
                .thenReturn(Optional.of(firstCourse));
        String foundCourseName = "Biology";
        Course foundCourse = courseService.findByCourseName(foundCourseName).get();
        assertThat(foundCourse.getCourseName(), is(equalTo(foundCourseName)));
        assertThat(foundCourse.getCourseName(), is(equalTo(firstCourse.getCourseName())));
    }

    @Test
    public void listAll() {
        mockCourseList();
        List<Course> courseServiceList = (List<Course>) courseService.listAll();
        assertThat(courseServiceList, hasItems(firstCourse, secondCourse));
    }

    @Test
    public void retrieveCoursesForStudentTest() {
        mockJasonAndHisCourseList();
        mockCourseList();
        List<Course> foundCoursesForJasonList = courseService.retrieveCourseListForStudent(jason.getId());
        assertThat(foundCoursesForJasonList, hasItem(secondCourse));
        assertThat(foundCoursesForJasonList, is(not(equalTo(courseList))));
    }

    @Test
    public void retrieveCourseForStudentTest() {
        mockJasonAndHisCourseList();
        mockCourseList();
        Course foundCourseForJason = courseService.retrieveCourseForStudent(jason.getId(),
                secondCourse.getId());
        assertThat(foundCourseForJason, is(equalTo(secondCourse)));
        assertThat(foundCourseForJason, is(not(equalTo(firstCourse))));
    }

    @Test
    public void addCourseTest() {
        mockJasonAndHisCourseList();
        Course addedCourse = courseService.addCourse(jason.getId(), firstCourse);
        assertThat(jason.getCourses(), hasItem(firstCourse));
        assertThat(jason.getCourses(), hasItem(addedCourse));
        assertThat(jason.getCourses(), hasSize(2));
    }

    @Test
    public void findById() {
        Mockito.when(courseRepository.findById(firstCourse.getId()))
                .thenReturn(Optional.of(firstCourse));
        Long foundId = 1L;
        Course foundCourse = courseService.getById(foundId).get();
        assertThat(foundCourse.getId(), is(equalTo(foundId)));
    }

    @Test
    public void saveOrUpdate() {
        Mockito.when(courseRepository.save(secondCourse))
                .thenReturn(secondCourse);
        Course savedCourse = courseService.saveOrUpdate(secondCourse);
        assertThat(savedCourse.getCourseName(), is(equalTo(secondCourse.getCourseName())));
    }

    private void mockCourseList() {
        Mockito.when(courseRepository.findAll())
                .thenReturn(courseList);
    }

    private void mockJasonAndHisCourseList() {
        mockJason();
        makeJasonsCourseList();
    }

    private void mockJason() {
        Mockito.when(userRepository.findById(jason.getId()))
                .thenReturn(Optional.of(jason));
    }

    private void makeJasonsCourseList() {
        List<Course> jasonCourses = new ArrayList<>();
        jasonCourses.add(secondCourse);
        jason.setCourses(jasonCourses);
    }
}