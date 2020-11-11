package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.FamilyEvent;
import com.Msa.communityservlet.model.When;
import com.Msa.communityservlet.repository.FamilyEventRepository;

import java.util.List;
import java.util.Optional;

public interface FamilyEventService extends CRUDService<FamilyEvent> {

    List<FamilyEvent> listAll();

    Optional<FamilyEvent> findByTitle(String title);

    Optional<FamilyEvent> findByWhen(When when);

}
