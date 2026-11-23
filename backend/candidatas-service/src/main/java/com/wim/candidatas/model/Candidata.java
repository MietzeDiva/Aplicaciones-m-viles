package com.wim.candidatas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "candidatas")
public class Candidata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String correo;

    private String telefono;

    private String area;

    private int anosExperiencia;

    @Column(length = 1000)
    private String resumen;

    private String enlacePerfil;

    @Column(length = 1000)
    private String uriCv;

    private String estado = "Postulada";

    private String registradaPor;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getEnlacePerfil() { return enlacePerfil; }
    public void setEnlacePerfil(String enlacePerfil) { this.enlacePerfil = enlacePerfil; }

    public String getUriCv() { return uriCv; }
    public void setUriCv(String uriCv) { this.uriCv = uriCv; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getRegistradaPor() { return registradaPor; }
    public void setRegistradaPor(String registradaPor) { this.registradaPor = registradaPor; }
}
