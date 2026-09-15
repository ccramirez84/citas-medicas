package com.sursalud.citasmedicas.servicio;

import com.sursalud.citasmedicas.modelo.Paciente;
import com.sursalud.citasmedicas.repositorio.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Lógica de negocio para pacientes: registrarse (POST /pacientes) y
 * consultar (GET /pacientes), tal como pide el enunciado.
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente obtenerPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado con id " + id));
    }

    public Paciente registrar(Paciente paciente) {
        if (pacienteRepository.findByDocumento(paciente.getDocumento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un paciente registrado con ese documento.");
        }
        return pacienteRepository.save(paciente);
    }
}
