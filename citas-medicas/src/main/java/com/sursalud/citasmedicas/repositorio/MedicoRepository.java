package com.sursalud.citasmedicas.repositorio;

import com.sursalud.citasmedicas.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
