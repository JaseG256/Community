package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.Where;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WhereRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    WhereRepository whereRepository;

    private Where where, anotherWhere;
    private Address addressOne, addressTwo;
    private String firstName, secondName, firstStreetNumber, secondStreetNumber, firstCity, secondCity, firstState,
            secondState, firstCountry, secondCountry;
    private int rightZipCode, wrongZipCode;

    @Before
    public void setUp() {
        firstName = "Black Expo";
        secondName = "Six Flags";
        firstStreetNumber = "2098";
        firstCity = "Indianapolis";
        secondCity = "Northern";
        firstState = "Indiana";
        secondState = "New Jersey";
        rightZipCode = 37883;
        wrongZipCode = 19702;

        addressOne = new Address(firstStreetNumber, firstCity, firstState, rightZipCode);
        addressTwo = new Address(secondCity, secondState);

        where = new Where(firstName);
//        where.setAddress(addressOne);
        entityManager.persist(where);
        entityManager.flush();

        anotherWhere = new Where(secondName);
//        anotherWhere.setAddress(addressTwo);
        entityManager.persist(anotherWhere);
        entityManager.flush();
    }

    @Test
    public void contextLoads() {
        assertThat(whereRepository, is(equalTo(whereRepository)));
    }

    @Test
    public void findByNameOfWhereTestOne() {
        Optional<Where> found = whereRepository.findByNameOfWhere(where.getnameOfWhere());
        Where foundWhere = found.get();
        assertThat(foundWhere.getnameOfWhere(), is(equalTo(where.getnameOfWhere())));
    }

}