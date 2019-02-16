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
    private String username;
    private String email;

    @Before
    public void setUp() {
        id = 1L;
        username = "jason";
        email = "jason@yahoo.com";
        user = new User(username, email, "jumpshot");
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
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        userService.delete(id);
        assertThat(userRepository.findById(id), not(equalTo(user)));
    }

    @Test
    public void findByUsername() {
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);
        String foundUsername = "jason";
        User foundUser = userService.findByUsername(foundUsername);
        assertThat(foundUser.getUsername(), equalTo(foundUsername));
    }

    @Test
    public void findByEmail() {
        Mockito.when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));
        String foundEmail = "jason@yahoo.com";
        User foundUser = (userService.findByEmail(email).isPresent())
                ? userService.findByEmail(foundEmail).get() : null;
        assertThat(foundUser.getEmail(), equalTo(foundEmail));
    }

    @Test
    public void findByUsernameOrEmailTestUsername() {
        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(user);
        String foundUsername = "jason";
        User foundUser = userService.findByUsername(foundUsername);
        assertThat(foundUser.getUsername(), equalTo(foundUsername));
    }

    @Test
    public void findByUsernameOrEmailTestEmail() {
        Mockito.when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        String foundEmail = "jason@yahoo.com";
        User foundUser = userService.findByEmail(foundEmail).get();
        assertThat(foundUser.getEmail(), equalTo(foundEmail));
    }

    @Test
    public void findByIdIn() {
        List<Long> idList = Collections.singletonList(user.getId());
        Mockito.when(userRepository.findByIdIn(idList))
                .thenReturn(userList);
        Long foundId = 1L;
        List<Long> foundUserIdList = Collections.singletonList(foundId);
        List<User> foundUserList = userService.findByIdIn(foundUserIdList);
        assertThat(foundUserList, hasItems(user));
    }

    @Test
    public void existsByUsernameTrue() {
        Mockito.when(userRepository.existsByUsername(username))
                .thenReturn(true);
        String usernameToCheck = "jason";
        Boolean usernameResponse = userService.existsByUsername(usernameToCheck);
        assertTrue(usernameResponse);
    }

    @Test
    public void existsByUsernameFalse() {
        Mockito.when(userRepository.existsByUsername(username))
                .thenReturn(true);
        String usernameToCheck = "jimmy";
        Boolean usernameResponse = userService.existsByUsername(usernameToCheck);
        assertFalse(usernameResponse);
    }

    @Test
    public void existsByEmailTrue() {
        Mockito.when(userRepository.existsByEmail(email))
                .thenReturn(true);
        String emailToCheck = "jason@yahoo.com";
        Boolean usernameResponse = userService.existsByEmail(emailToCheck);
        assertTrue(usernameResponse);
    }

    @Test
    public void existsByEmailFalse() {
        Mockito.when(userRepository.existsByEmail(email))
        .thenReturn(true);
        String emailToCheck = "jason@gmail.com";
        Boolean usernameResponse = userService.existsByEmail(emailToCheck);
        assertFalse(usernameResponse);
        }
 }