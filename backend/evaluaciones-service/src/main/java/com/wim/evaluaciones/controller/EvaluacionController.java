package com.wim.evaluaciones.controller;

import com.wim.evaluaciones.model.Evaluacion;
import com.wim.evaluaciones.repository.EvaluacionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionRepository repo;

    public EvaluacionController(EvaluacionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Evaluacion> listar() {
        return repo.findAll();
    }

    @GetMapping("/ranking")
    public List<Evaluacion> ranking() {
        return repo.findAllByOrderByTotalPonderadoDesc();
    }

    @GetMapping("/candidata/{candidataId}")
    public ResponseEntity<Evaluacion> porCandidata(@PathVariable Integer candidataId) {
        return repo.findFirstByCandidataId(candidataId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evaluacion crear(@Valid @RequestBody Evaluacion e) {
        if (repo.findFirstByCandidataId(e.getCandidataId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La candidata ya tiene una evaluacion");
        }
        e.setId(null);
        e.setTotalPonderado(e.getPuntajeExperiencia() * 0.35
                + e.getPuntajeFormacion() * 0.35
                + e.getPuntajeEntrevista() * 0.30);
        if (e.getFecha() == null || e.getFecha().isBlank()) {
            e.setFecha(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }
        return repo.save(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
