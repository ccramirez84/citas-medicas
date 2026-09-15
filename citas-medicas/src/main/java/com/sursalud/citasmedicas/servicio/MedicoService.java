package com.sursalud.citasmedicas.servicio;

import com.sursalud.citasmedicas.modelo.Cita;
import com.sursalud.citasmedicas.modelo.Medico;
import com.sursalud.citasmedicas.repositorio.CitaRepository;
import com.sursalud.citasmedicas.repositorio.MedicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Lógica de negocio para médicos: consultar médicos (GET /medicos),
 * y ver la agenda de un médico (sus citas asignadas).
 */
@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;

    public MedicoService(MedicoRepository medicoRepository, CitaRepository citaRepository) {
        this.medicoRepository = medicoRepository;
        this.citaRepository = citaRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico obtenerPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Médico no encontrado con id " + id));
    }

    public Medico registrar(Medico medico) {
        return medicoRepository.save(medico);
    }

    /** Agenda del médico: todas sus citas (requisito "Visualizar agenda"). */
    public List<Cita> obtenerAgenda(Long medicoId) {
        Medico medico = obtenerPorId(medicoId);
        return citaRepository.findByMedico(medico);
    }
}
