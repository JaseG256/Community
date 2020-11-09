package com.Msa.communityservlet.service;

import com.Msa.communityservlet.exception.AppException;
import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.Place;
import com.Msa.communityservlet.repository.PlaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
public class PlaceServiceImplTest {

    @TestConfiguration
    static class PlaceServiceImplTestContextConfiguration {

        @Autowired
        PlaceRepository placeRepository;

        @Bean
        PlaceService placeService() {
            return new PlaceServiceImpl(placeRepository);
        }
    }

    @Autowired
    PlaceService placeService;

    @MockBean
    PlaceRepository placeRepositoryMock;

    String nameOfPlace, anotherName, exceptionMessage, firstName, secondName, firstStreetNumber, secondStreetNumber,
            firstCity, secondCity, firstState, secondState, firstCountry, secondCountry;
    int rightZipCode, wrongZipCode;;
    Long firstId, secondId;
    Address rightAddress, wrongAddress;
    Place rightPlace, wrongPlace;

    @Before
    public void setUp() {
        nameOfPlace = "Six Flags";
        anotherName = "Disney World";
        exceptionMessage = "Place not found";
        firstName = "Black Expo";
        firstStreetNumber = "2098";
        secondStreetNumber = "3702";
        firstCity = "Indianapolis";
        secondCity = "Northern";
        firstState = "Indiana";
        secondState = "New Jersey";
        rightZipCode = 37883;
        wrongZipCode = 19702;

        firstId = 1L;
        secondId = 2L;

        rightAddress = new Address(firstStreetNumber, firstCity, firstState, rightZipCode);
        wrongAddress = new Address(secondCity, secondState);

        rightPlace = new Place(nameOfPlace);
        rightPlace.setId(firstId);

        wrongPlace = new Place(anotherName);
        wrongPlace.setId(secondId);
    }

    @Test
    public void contextLoads() {
        assertNotNull(placeService);
        assertNotNull(placeService.getRepository());
    }

    @Test
    public void findByNameOfPlace() {
        Mockito.when(placeRepositoryMock.findByNameOfPlace(nameOfPlace))
                .thenReturn(Optional.of(rightPlace));
        Place foundPlace = placeService.findByNameOfPlace(nameOfPlace)
                .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundPlace.getNameOfPlace(), is(equalTo(nameOfPlace)));
        assertThat(foundPlace.getNameOfPlace(), is(not(equalTo(anotherName))));
    }

    @Test
    public void findByAddress() {
        Mockito.when(placeRepositoryMock.findByAddress(rightAddress))
                .thenReturn(Optional.of(rightPlace));
        Place foundPlace = placeService.findByAddress(rightAddress)
                .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundPlace.getAddress(), is(equalTo(rightPlace.getAddress())));
        assertThat(foundPlace.getNameOfPlace(), is(equalTo(rightPlace.getNameOfPlace())));
        assertThat(foundPlace.getNameOfPlace(), is(not(equalTo(wrongPlace.getNameOfPlace()))));
    }
}