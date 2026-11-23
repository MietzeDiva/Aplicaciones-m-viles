package com.wim.evaluaciones.repository;

import com.wim.evaluaciones.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {

    List<Evaluacion> findAllByOrderByTotalPonderadoDesc();

    Optional<Evaluacion> findFirstByCandidataId(Integer candidataId);
}
