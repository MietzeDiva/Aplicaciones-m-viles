package com.wim.evaluaciones.controller;

import com.wim.evaluaciones.model.HistorialAccion;
import com.wim.evaluaciones.repository.HistorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final HistorialRepository repo;

    public HistorialController(HistorialRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<HistorialAccion> listar() {
        return repo.findAllByOrderByIdDesc();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistorialAccion crear(@RequestBody HistorialAccion h) {
        h.setId(null);
        return repo.save(h);
    }
}
