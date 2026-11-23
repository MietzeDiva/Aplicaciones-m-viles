package com.wim.candidatas.service;

import com.wim.candidatas.model.Candidata;
import com.wim.candidatas.repository.CandidataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidataService {

    private final CandidataRepository repo;

    public CandidataService(CandidataRepository repo) {
        this.repo = repo;
    }

    public List<Candidata> listar() {
        return repo.findAll();
    }

    public Optional<Candidata> buscar(Integer id) {
        return repo.findById(id);
    }

    public Candidata crear(Candidata c) {
        c.setId(null);
        return repo.save(c);
    }

    public Optional<Candidata> actualizar(Integer id, Candidata c) {
        if (!repo.existsById(id)) {
            return Optional.empty();
        }
        c.setId(id);
        return Optional.of(repo.save(c));
    }

    public boolean eliminar(Integer id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
