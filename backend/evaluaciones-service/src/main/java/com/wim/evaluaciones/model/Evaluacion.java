package com.wim.evaluaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer candidataId;

    private String candidataNombre;

    @Min(1)
    @Max(5)
    private int puntajeExperiencia;

    @Min(1)
    @Max(5)
    private int puntajeFormacion;

    @Min(1)
    @Max(5)
    private int puntajeEntrevista;

    private double totalPonderado;

    private String evaluadora = "Evaluadora WIM";

    private String fecha;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCandidataId() { return candidataId; }
    public void setCandidataId(Integer candidataId) { this.candidataId = candidataId; }

    public String getCandidataNombre() { return candidataNombre; }
    public void setCandidataNombre(String candidataNombre) { this.candidataNombre = candidataNombre; }

    public int getPuntajeExperiencia() { return puntajeExperiencia; }
    public void setPuntajeExperiencia(int puntajeExperiencia) { this.puntajeExperiencia = puntajeExperiencia; }

    public int getPuntajeFormacion() { return puntajeFormacion; }
    public void setPuntajeFormacion(int puntajeFormacion) { this.puntajeFormacion = puntajeFormacion; }

    public int getPuntajeEntrevista() { return puntajeEntrevista; }
    public void setPuntajeEntrevista(int puntajeEntrevista) { this.puntajeEntrevista = puntajeEntrevista; }

    public double getTotalPonderado() { return totalPonderado; }
    public void setTotalPonderado(double totalPonderado) { this.totalPonderado = totalPonderado; }

    public String getEvaluadora() { return evaluadora; }
    public void setEvaluadora(String evaluadora) { this.evaluadora = evaluadora; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
