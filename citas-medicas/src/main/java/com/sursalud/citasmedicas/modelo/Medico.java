package com.sursalud.citasmedicas.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad Medico: representa a un médico de la clínica, que necesita
 * visualizar su agenda, consultar pacientes y administrar su
 * disponibilidad (requisitos del enunciado).
 */
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    private String consultorio;

    public Medico() {
    }

    public Medico(String nombre, String especialidad, String consultorio) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
    }

    // ===================== Getters y Setters =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }
}
