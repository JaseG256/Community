package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Poll;
import com.Msa.communityservlet.model.User;
import org.junit.Before;
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
public class PollRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PollRepository pollRepository;

    @Test
    public void findById() {
        // given
        Poll poll = new Poll();
//        poll.setId(1L);
//        poll.setQuestion("Please Cast Your Vote!");
//        List<User> choices = new ArrayList<>();
//        poll.setChoices(choices);
        entityManager.persist(poll);
        entityManager.flush();
        // when
        Optional<Poll> found = pollRepository.findById(poll.getId());
        Poll foundPoll = found.get();
        // then
        assertEquals(foundPoll.getId(), poll.getId());
    }

    @Test
    public void savePollTest() {
        Poll poll = new Poll();
        poll.setId(1L);
        poll.setQuestion("What's the deal?");
        entityManager.persist(poll);
        entityManager.flush();
        assertNotNull(poll.getId());
    }
}