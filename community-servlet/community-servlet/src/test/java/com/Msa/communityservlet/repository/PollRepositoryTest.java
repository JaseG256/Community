package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Choice;
import com.Msa.communityservlet.model.Poll;
import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.model.Vote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PollRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PollRepository pollRepository;

    Poll firstPoll;
    Choice firstChoice, secondChoice;
    List<Choice> choiceList;
    User jason, johnny;
    Vote myVote, yourVote;
    Instant expirationDateTime;
    String firstQuestion, firstText, secondText;

    @Before
    public void setup() {
        jason = new User("jason", "jason@gmail.com", "jumpshot");
        johnny = new User("johnny", "johnny@yahoo.com", "leaner");
        firstPoll = new Poll();
        myVote = new Vote();
        yourVote = new Vote();

        firstText = "Hip-Hop";
        firstChoice = new Choice(firstText);
        firstChoice.setPoll(firstPoll);
        entityManager.persist(firstChoice);
        entityManager.flush();

        secondChoice = new Choice(secondText);
        secondText = "Rock";
        secondChoice.setPoll(firstPoll);
        entityManager.persist(secondChoice);
        entityManager.flush();

        choiceList = Arrays.asList(firstChoice, secondChoice);

        firstQuestion = "What is your favorite genre of music?";
        expirationDateTime = Instant.EPOCH;

        firstPoll.setQuestion(firstQuestion);
        firstPoll.setExpirationDateTime(expirationDateTime);
        firstPoll.setChoices(choiceList);
        firstPoll.addChoice(firstChoice);
        firstPoll.addChoice(secondChoice);

        entityManager.persist(firstPoll);
        entityManager.flush();

        myVote.setChoice(firstChoice);
        myVote.setPoll(firstPoll);
        myVote.setUser(jason);
        entityManager.persist(myVote);
        entityManager.flush();

        yourVote.setChoice(secondChoice);
        yourVote.setPoll(firstPoll);
        yourVote.setUser(johnny);
        entityManager.persist(yourVote);
        entityManager.flush();
    }

    @Test
    public void findByIdTest() {
        // when
        Optional<Poll> found = pollRepository.findById(firstPoll.getId());
        Poll foundPoll = found.orElse(null);
        assertEquals(firstPoll.getId(), foundPoll.getId());
    }
}