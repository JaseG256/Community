package com.Msa.communityservlet.controller;

import com.Msa.communityservlet.model.Course;
import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Qualifier("courseServicer")
    CourseService courseService;

    private Course firstCourse, secondCourse;
    private List<Course> jasonCourseList, jimmyCourseList;
    private Long jasonId, jimmyId;
    private String jasonUsername, jimmyUsername, jasonEmail, jimmyEmail, jasonPassword, jimmyPassword;
    private User jason, jimmy;
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        firstCourse = new Course("Biology", "Learn Biology!");
        secondCourse = new Course("Math", "Learn to count");
        jasonCourseList = Arrays.asList(firstCourse, secondCourse);
        jasonId = 1L; jasonUsername = "jason"; jasonEmail = "jason@yahoo.com"; jasonPassword = "jumpshot";
        jason = new User(jasonUsername, jasonEmail, jasonPassword); jason.setId(jasonId);
        firstCourse.setUserList(Collections.singletonList(jason));
        jason.setCourses(jasonCourseList);
        jimmyId = 2L; jimmyUsername = "jimmy"; jimmyEmail = "jimmy@gmail.com"; jimmyPassword = "badface";
        jimmyCourseList = Collections.singletonList(secondCourse);
        jimmy = new User(jimmyUsername, jimmyEmail, jimmyPassword); jimmy.setId(jimmyId);
        secondCourse.setUserList(Arrays.asList(jason, jimmy));
        jimmy.setCourses(jimmyCourseList);
    }

    @Test
    public void initializeTest() {
        assertThat(courseService, is(notNullValue()));
    }

    @Test
    public void retrieveCourseForStudentTest() throws Exception {
        Mockito.when(courseService.retrieveCourseListForStudent(jason.getId()))
                .thenReturn(jasonCourseList);
        mockMvc.perform(get(
                "api/course/students/" + jason.getId() + "/courses"
        ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$", hasSize(equalTo(1))));
//                .andExpect(jsonPath("$[0].username", is("jason")));
    }

    @Test
    public void retrieveCourseListForStudent() {
    }
}