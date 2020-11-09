package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.FamilyEvent;
import com.Msa.communityservlet.model.When;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyEventRepository extends JpaRepository<FamilyEvent, Long> {

    Optional<FamilyEvent> findByWhen(When when);

    Optional<FamilyEvent> findByTitle(String title);
}
