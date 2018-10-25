package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        // given
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        // when
        User found = userRepository.findByUsername(jason.getUsername());
        // then
        assertEquals(found.getUsername(), jason.getUsername());

    }

    @Test
    public void findByEmailTest() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        // when
        Optional<User> found = userRepository.findByEmail(jason.getEmail());
        User foundUser = found.orElse(null);
        // then
        assertEquals(foundUser.getEmail(), jason.getEmail());
    }

    @Test
    public void findByUsernameOrEmailTestUsername() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        // when
        Optional<User> found = userRepository.findByUsernameOrEmail(jason.getUsername(), null);
        User foundUser = found.orElse(null);
        // then
        assertEquals(foundUser.getUsername(), jason.getUsername());
    }

    @Test
    public void findByUsernameOrEmailTestEmail() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        // when
        Optional<User> found = userRepository.findByUsernameOrEmail(null, jason.getEmail());
        User foundUser = found.orElse(null);
        // then
        assertEquals(foundUser.getEmail(), jason.getEmail());
    }

    @Test
    public void findByIdIn() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        User jamie = new User("jamie", "jamie@gmail.com", "runner");
        entityManager.persist(jamie);
        entityManager.flush();
        List<User> userList = userRepository.findAll();
        List<Long> userIds = new ArrayList<>();
        userList.forEach((user -> userIds.add(user.getId())));
        List<User> userListFromIds = userRepository.findByIdIn(userIds);

        assertEquals(userList, userListFromIds);
    }


    @Test
    public void existsByUsernameTestTrue() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        User jamie = new User("jamie", "jamie@gmail.com", "runner");
        entityManager.persist(jamie);
        entityManager.flush();
        assertEquals(userRepository.existsByUsername(jamie.getUsername()),
                userRepository.existsByUsername(jason.getUsername()));
    }

    @Test
    public void existsByUsernameTestFalse() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        User jamie = new User("jamie", "jamie@gmail.com", "runner");

        assertNotEquals(userRepository.existsByUsername(jason.getUsername()),
                userRepository.existsByUsername(jamie.getUsername()));
    }

    @Test
    public void existsByEmailTestTrue() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        User jamie = new User("jamie", "jamie@gmail.com", "runner");
        entityManager.persist(jamie);
        entityManager.flush();
        assertEquals(userRepository.existsByEmail(jamie.getEmail()),
                userRepository.existsByEmail(jason.getEmail()));
    }

    @Test
    public void existsByEmailFalse() {
        User jason = new User("jason", "jason@yahoo.com", "jumpshot");
        entityManager.persist(jason);
        entityManager.flush();
        User jamie = new User("jamie", "jamie@gmail.com", "runner");

        assertNotEquals(userRepository.existsByEmail(jamie.getEmail()),
                userRepository.existsByEmail(jason.getEmail()));
    }
}