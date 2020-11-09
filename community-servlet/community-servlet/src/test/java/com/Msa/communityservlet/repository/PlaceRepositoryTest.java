package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.exception.AppException;
import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.Place;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaceRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PlaceRepository placeRepository;

    Place rightPlace, wrongPlace;
    Address addressOne, addressTwo;
    String firstName, secondName, firstStreetNumber, secondStreetNumber, firstCity, secondCity, firstState,
            secondState, firstCountry, secondCountry, exceptionMessage;
    int rightZipCode, wrongZipCode;

    @Before
    public void setUp() {
        firstName = "Black Expo";
        secondName = "Six Flags";
        firstStreetNumber = "2098";
        firstCity = "Indianapolis";
        secondCity = "Northern";
        firstState = "Indiana";
        secondState = "New Jersey";
        exceptionMessage = "Place not Found";
        rightZipCode = 37883;
        wrongZipCode = 19702;

        addressOne = new Address(firstStreetNumber, firstCity, firstState, rightZipCode);
        addressTwo = new Address(secondCity, secondState);

        rightPlace = new Place(firstName, addressOne);
        entityManager.persist(rightPlace);
        entityManager.flush();

        wrongPlace = new Place(secondName, addressTwo);
        entityManager.persist(wrongPlace);
        entityManager.flush();
    }

    @Test
    public void contextLoads() {
        assertNotNull(placeRepository);
    }

    @Test
    public void findByNameOfPlaceWorks() {
        String nameOfPlace = "Navy Pier";
        String wrongName = "Disney Land";
        Place place = new Place(nameOfPlace);
        entityManager.persist(place);
        entityManager.flush();
        Optional<Place> found = placeRepository.findByNameOfPlace(place.getNameOfPlace());
        Place foundPlace = found.orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundPlace.getNameOfPlace(), is(equalTo(place.getNameOfPlace())));
        assertThat(foundPlace.getNameOfPlace(), is(equalTo(nameOfPlace)));
        assertThat(foundPlace.getNameOfPlace(), is(not(equalTo(wrongName))));
    }

//    @Test
//    public void findByAddress_CityWorks() {
//        Optional<Place> found = placeRepository.findByAddress_City(secondCity);
//        Place foundPlace = found.orElseThrow(() -> new AppException(exceptionMessage));
//        assertThat(foundPlace.getAddress().getCity(), is(equalTo(wrongPlace.getAddress().getCity())));
//    }

    @Test
    public void findByAddressWorks() {
        Place foundPlace =
                placeRepository.findByAddress(rightPlace.getAddress())
                        .orElseThrow(() -> new AppException(exceptionMessage));
        assertThat(foundPlace.getAddress(), is(equalTo(rightPlace.getAddress())));
        assertThat(foundPlace.getNameOfPlace(), is(equalTo(rightPlace.getNameOfPlace())));
        assertThat(foundPlace.getAddress(), is(not(equalTo(wrongPlace.getAddress()))));
    }

}