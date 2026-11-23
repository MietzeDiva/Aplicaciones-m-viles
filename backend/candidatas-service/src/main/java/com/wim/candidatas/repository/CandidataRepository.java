package com.wim.candidatas.repository;

import com.wim.candidatas.model.Candidata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidataRepository extends JpaRepository<Candidata, Integer> {
}
