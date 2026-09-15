package com.sursalud.citasmedicas.servicio;

import com.sursalud.citasmedicas.dto.CitaRequest;
import com.sursalud.citasmedicas.modelo.Cita;
import com.sursalud.citasmedicas.modelo.EstadoCita;
import com.sursalud.citasmedicas.modelo.Medico;
import com.sursalud.citasmedicas.modelo.Paciente;
import com.sursalud.citasmedicas.repositorio.CitaRepository;
import com.sursalud.citasmedicas.repositorio.MedicoRepository;
import com.sursalud.citasmedicas.repositorio.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Lógica de negocio para citas: crear (POST /citas), consultar
 * (GET /citas), cancelar (DELETE /citas/{id}), y consultar las citas de
 * un paciente en particular (requisito "Consultar citas" del paciente).
 */
@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public CitaService(CitaRepository citaRepository, PacienteRepository pacienteRepository,
                        MedicoRepository medicoRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada con id " + id));
    }

    /** Requisito del paciente: "Solicitar citas". */
    public Cita solicitarCita(CitaRequest request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado con id " + request.getPacienteId()));
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new NoSuchElementException("Médico no encontrado con id " + request.getMedicoId()));

        Cita nuevaCita = new Cita(request.getFecha(), request.getHora(), EstadoCita.PENDIENTE, paciente, medico);
        return citaRepository.save(nuevaCita);
    }

    /** Requisito del paciente: "Consultar citas" (las de un paciente específico). */
    public List<Cita> consultarCitasDePaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado con id " + pacienteId));
        return citaRepository.findByPaciente(paciente);
    }

    /** Requisito del paciente: "Cancelar citas". Cambia el estado en vez de borrar el historial. */
    public void cancelarCita(Long id) {
        Cita cita = obtenerPorId(id);
        cita.setEstado(EstadoCita.CANCELADA);
        citaRepository.save(cita);
    }

    /** Elimina físicamente la cita (tal como pide literalmente el DELETE /citas/{id} del enunciado). */
    public void eliminarCita(Long id) {
        Cita cita = obtenerPorId(id);
        citaRepository.delete(cita);
    }
}
