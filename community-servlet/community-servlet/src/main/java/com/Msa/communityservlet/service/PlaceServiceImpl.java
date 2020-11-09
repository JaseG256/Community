package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.Address;
import com.Msa.communityservlet.model.Place;
import com.Msa.communityservlet.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> listAll() {
        List<Place> placeList = new ArrayList<>();
        placeRepository.findAll().forEach(placeList::add);
        return placeList;
    }

    @Override
    public Optional<Place> getById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public Place saveOrUpdate(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public Optional<Place> findByNameOfPlace(String nameOfPlace) {
        return null;
    }

    @Override
    public Optional<Place> findByAddress(Address address) {
        return null;
    }
}
