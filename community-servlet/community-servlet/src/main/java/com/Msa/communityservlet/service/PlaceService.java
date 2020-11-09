package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.Place;

import java.util.Optional;

public interface PlaceService extends CRUDService<Place> {

    Optional<Place> findByNameOfPlace(String nameOfPlace);

    Optional<Place> findByAddress(Address address);
}
