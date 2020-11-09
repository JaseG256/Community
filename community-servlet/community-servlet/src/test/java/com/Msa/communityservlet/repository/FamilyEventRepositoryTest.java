package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.exception.AppException;
import com.Msa.communityservlet.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FamilyEventRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    FamilyEventRepository eventRepository;

    FamilyEvent summerEvent, fallEvent;
    When summerWhen, fallWhen;
    LocalDate summerStartDate, summerEndDate, fallStartDate, fallEndDate;
    LocalTime summerStartTime, fallStartTime, fallEndTime;
    Place summerPlace, fallPlace;
    Address addressOne, addressTwo;
    String summerTitle, fallTitle, nameOne, nameTwo, firstCity, secondCity, firstState, secondState, exceptionMessage;

    @Before
    public void setUp() {
        summerTitle = "Family Reunion";
        fallTitle = "Karen's Graduation";
        nameOne = "Washington Park";
        nameTwo = "The Chase Center";
        firstCity = "Chicago";
        secondCity = "Wilmington";
        firstState = "Illinois";
        secondState = "Delaware";
        exceptionMessage = "EventNotFound";

        addressOne = new Address(firstCity, firstState);
        addressTwo = new Address(secondCity, secondState);

        summerPlace = new Place(nameOne, addressOne);
        entityManager.persist(summerPlace);
        entityManager.flush();

        fallPlace = new Place(nameTwo, addressTwo);
        entityManager.persist(fallPlace);
        entityManager.flush();

        summerStartDate = LocalDate.of(2020, Month.JULY, 21);
        summerEndDate = LocalDate.of(2020, Month.JULY, 24);
        summerStartTime = LocalTime.of(4, 0);
        summerWhen = new When(summerStartDate, summerEndDate, summerStartTime);

        summerEvent = new FamilyEvent(summerTitle, summerWhen, summerPlace);
        entityManager.persist(summerEvent);
        entityManager.flush();

        fallStartDate = LocalDate.of(2007, Month.MAY, 25);
        fallStartTime = LocalTime.of(7, 0);
        fallWhen = new When(fallStartDate, fallStartTime);

        fallEvent = new FamilyEvent(fallTitle, fallWhen, fallPlace);
        entityManager.persist(fallEvent);
        entityManager.flush();
    }

    @Test
    public void contextLoads() {
        assertNotNull(eventRepository);
    }

    @Test
    public void findByTitleWorks() {
        FamilyEvent foundEvent = eventRepository.findByTitle(summerEvent.getTitle())
                .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundEvent.getTitle(), is(equalTo(summerEvent.getTitle())));
        assertThat(foundEvent.getTitle(), is(not(equalTo(fallEvent.getTitle()))));
    }

    @Test
    public void findByWhen() {
        FamilyEvent foundEvent = eventRepository.findByWhen(fallEvent.getWhen())
                .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundEvent.getTitle(), is(equalTo(fallEvent.getTitle())));
        assertThat(foundEvent.getWhen(), is(not(equalTo(summerEvent.getWhen()))));
    }
}