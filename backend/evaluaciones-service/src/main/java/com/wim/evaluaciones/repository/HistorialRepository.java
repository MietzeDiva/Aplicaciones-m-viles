package com.wim.evaluaciones.repository;

import com.wim.evaluaciones.model.HistorialAccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialRepository extends JpaRepository<HistorialAccion, Integer> {

    List<HistorialAccion> findAllByOrderByIdDesc();
}
