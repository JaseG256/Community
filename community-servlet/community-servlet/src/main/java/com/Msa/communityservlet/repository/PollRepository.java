package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {

    Optional<Poll> findById(Long id);
}
