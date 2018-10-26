package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
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


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;
    private List<User> userList;
    private Long id;

    @Before
    public void setUp() {
        user = new User("jason", "jason@yahoo.com", "jumpshot");
        id = 1L;
        user.setId(id);
        userList = Collections.singletonList(user);
    }

    @Test
    public void listAll() {
        Mockito.when(userRepository.findAll())
                .thenReturn(userList);
        List<User> userServiceList = userService.listAll();
        assertThat(userServiceList, hasItems(user));
    }

    @Test
    public void getById() {
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        User foundUser = (userService.getById(id).isPresent())
                ? userService.getById(id).get() : null;
        assertThat(foundUser.getId(), equalTo(user.getId()));
    }

    @Test
    public void saveOrUpdate() {
        Mockito.when(userRepository.save(user))
                .thenReturn(user);
        User savedUser = userService.saveOrUpdate(user);
        assertThat(savedUser, equalTo(user));
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findByEmail() {
    }

    @Test
    public void findByUsernameOrEmail() {
    }

    @Test
    public void findByIdIn() {
    }

    @Test
    public void existsByUsername() {
    }

    @Test
    public void existsByEmail() {
    }
}