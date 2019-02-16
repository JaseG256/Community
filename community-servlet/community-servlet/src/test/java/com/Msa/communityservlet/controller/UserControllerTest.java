package com.Msa.communityservlet.controller;

import com.Msa.communityservlet.CommunityServletApplication;
import com.Msa.communityservlet.model.Role;
import com.Msa.communityservlet.model.RoleName;
import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CommunityServletApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "jason", roles = { "USER" })
//@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    @Qualifier("userServicer")
    private UserService userService;

    private User user;
    private long id;
    private List<User> userList;

    @Before
    public void setUp() {
        user = new User("jason", "jason@yahoo.com", "jumpshot");
        id = 1L;
        user.setId(id);
        user.setRoles(Collections.singleton(new Role(RoleName.ROLE_USER)));
        userList = Collections.singletonList(user);
    }

    @Test
    public void create() {
    }

    @Test
    public void getCurrentUser() {
    }

    @Test
    public void findOne() throws Exception{
        when(userService.getById(id)).thenReturn(Optional.of(user));
        mvc.perform(get("api/user/" + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$", hasSize(equalTo(1))));
//                .andExpect(jsonPath("$[0].username", is("jason")));
    }

    @Test
    public void findByUserName() {
        String username = "jason";
        User user1 = userService.findByUsername(user.getUsername());
        assertEquals(user1.getUsername(), username);
    }

    @Test
    public void findByEmail() {
    }

    @Test
    public void update() {
    }

    @Test
    public void checkUsernameAvailability() {
    }

    @Test
    public void checkEmailAvailability() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
    }
}