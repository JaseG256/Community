package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByNameOfPlace(String nameOfPlace);

    Optional<Place> findByAddress(Address address);

//    Optional<Place> findByAddress_City(String city);
//
//    Optional<Place> findByAddress_State(String state);
//
//    Optional<Place> findByAddress_Country(String country);
}
