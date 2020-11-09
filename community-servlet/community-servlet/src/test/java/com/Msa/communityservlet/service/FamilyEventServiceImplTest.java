package com.Msa.communityservlet.service;

import com.Msa.communityservlet.exception.AppException;
import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.FamilyEvent;
import com.Msa.communityservlet.model.Place;
import com.Msa.communityservlet.model.When;
import com.Msa.communityservlet.repository.FamilyEventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
public class FamilyEventServiceImplTest {


    @TestConfiguration
    static class FamilyEventServiceImplTestContextConfiguration {

        @Autowired
        FamilyEventRepository eventRepository;

        @Bean
        FamilyEventService familyEventService() {

            return new FamilyEventServiceImpl(eventRepository);
        }
    }

    @Autowired
    FamilyEventService eventService;

    @MockBean
    FamilyEventRepository familyEventRepository;

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

        fallPlace = new Place(nameTwo, addressTwo);

        summerStartDate = LocalDate.of(2020, Month.JULY, 21);
        summerEndDate = LocalDate.of(2020, Month.JULY, 24);
        summerStartTime = LocalTime.of(4, 0);
        summerWhen = new When(summerStartDate, summerEndDate, summerStartTime);

        summerEvent = new FamilyEvent(summerTitle, summerWhen, summerPlace);

        fallStartDate = LocalDate.of(2007, Month.MAY, 25);
        fallStartTime = LocalTime.of(7, 0);
        fallWhen = new When(fallStartDate, fallStartTime);

        fallEvent = new FamilyEvent(fallTitle, fallWhen, fallPlace);
    }

    @Test
    public void contextLoads() {
        assertNotNull(eventService);
    }

    @Test
    public void findByTitle() {
        Mockito.when(familyEventRepository.findByTitle(summerTitle))
                .thenReturn(Optional.of(summerEvent));
        FamilyEvent foundEvent = eventService.findByTitle(summerEvent.getTitle())
                .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundEvent.getTitle(), is(equalTo(summerEvent.getTitle())));
    }

    @Test
    public void findByWhen() {
        Mockito.when(familyEventRepository.findByWhen(fallWhen))
                .thenReturn(Optional.of(fallEvent));
        FamilyEvent foundEvent = eventService.findByWhen(fallEvent.getWhen())
                .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundEvent.getWhen(), is(equalTo(fallEvent.getWhen())));
    }
}