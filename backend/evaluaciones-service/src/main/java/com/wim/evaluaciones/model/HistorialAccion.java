package com.wim.evaluaciones.model;

import jakarta.persistence.*;

@Entity
@Table(name = "historial_acciones")
public class HistorialAccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accion;

    @Column(length = 500)
    private String descripcion;

    private String fecha;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
