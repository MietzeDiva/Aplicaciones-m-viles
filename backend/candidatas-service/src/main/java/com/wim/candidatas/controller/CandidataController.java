package com.wim.candidatas.controller;

import com.wim.candidatas.model.Candidata;
import com.wim.candidatas.service.CandidataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatas")
public class CandidataController {

    private final CandidataService service;

    public CandidataController(CandidataService service) {
        this.service = service;
    }

    @GetMapping
    public List<Candidata> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidata> buscar(@PathVariable Integer id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Candidata crear(@Valid @RequestBody Candidata c) {
        return service.crear(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidata> actualizar(@PathVariable Integer id, @Valid @RequestBody Candidata c) {
        return service.actualizar(id, c)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
