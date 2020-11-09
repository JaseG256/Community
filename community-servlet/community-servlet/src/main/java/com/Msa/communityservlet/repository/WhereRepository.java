package com.Msa.communityservlet.repository;

import com.Msa.communityservlet.model.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhereRepository extends JpaRepository<Where, String> {

    Optional<Where> findByNameOfWhere(String nameOfWhere);
}
