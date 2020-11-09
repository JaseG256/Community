package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.FamilyEvent;
import com.Msa.communityservlet.model.When;
import com.Msa.communityservlet.repository.FamilyEventRepository;

import java.util.Optional;

public interface FamilyEventService extends CRUDService<FamilyEvent> {

    Optional<FamilyEvent> findByTitle(String title);

    Optional<FamilyEvent> findByWhen(When when);

    FamilyEventRepository getRepository();
}
