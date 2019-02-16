package com.Msa.communityservlet.model;

import com.Msa.communityservlet.exception.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CourseTest {

    private Course course;

    private User jason, jamie;
    private Long jasonId, jamieId;
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        course = new Course("Biology", "Learn");
        jason = new User("jason", "jason@yahoo.com", "jumpshot");
        jasonId = 1L; jason.setId(jasonId);
        jamie = new User("jamie", "jamie@gmail.com", "runner");
        jamieId = 2L; jamie.setId(jamieId);
        userList = Arrays.asList(jason, jamie);
        course.setUserList(userList);
    }

    @Test
    public void getUserTest() {
        Long foundId = 1L;
        assertEquals(jason, course.getUser(foundId));
        assertThat(course.getUser(foundId), is(sameInstance(jason)));
        assertThat(course.getUser(foundId), is(not(sameInstance(jamie))));
    }

    @Test(expected = Exception.class)
    public void getUserUserNotFoundTest() throws BadRequestException{
        Long wrongId = 4L;
        try {
            System.out.println(course.getUser(wrongId));
            course.getUser(wrongId);
            fail("Should have thrown exception");
        } catch (BadRequestException e) {
            assertThat(e, is(instanceOf(BadRequestException.class)));
        }
    }
}