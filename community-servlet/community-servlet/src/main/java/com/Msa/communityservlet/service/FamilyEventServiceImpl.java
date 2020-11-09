package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.FamilyEvent;
import com.Msa.communityservlet.model.When;
import com.Msa.communityservlet.repository.FamilyEventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "eventServicer")
public class FamilyEventServiceImpl implements FamilyEventService {

    private final FamilyEventRepository eventRepository;

    public FamilyEventServiceImpl(FamilyEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<FamilyEvent> listAll() {
        List<FamilyEvent> eventList = new ArrayList<>();
        eventRepository.findAll().forEach(eventList::add);
        return eventList;
    }

    @Override
    public Optional<FamilyEvent> getById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public FamilyEvent saveOrUpdate(FamilyEvent domainObject) {
        return eventRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<FamilyEvent> findByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    @Override
    public Optional<FamilyEvent> findByWhen(When when) {
        return eventRepository.findByWhen(when);
    }

    @Override
    public FamilyEventRepository getRepository() {
        return eventRepository;
    }
}
