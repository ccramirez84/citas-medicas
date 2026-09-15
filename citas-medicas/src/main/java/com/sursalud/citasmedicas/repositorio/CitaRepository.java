package com.sursalud.citasmedicas.repositorio;

import com.sursalud.citasmedicas.modelo.Cita;
import com.sursalud.citasmedicas.modelo.Medico;
import com.sursalud.citasmedicas.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    /** Para que el paciente pueda "consultar sus citas". */
    List<Cita> findByPaciente(Paciente paciente);

    /** Para que el médico pueda "visualizar su agenda". */
    List<Cita> findByMedico(Medico medico);
}
