package com.sursalud.citasmedicas.controlador;

import com.sursalud.citasmedicas.modelo.Paciente;
import com.sursalud.citasmedicas.servicio.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller de Paciente: GET /pacientes y POST /pacientes, tal como
 * exige el enunciado en "Consumo API REST".
 */
@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Registro y consulta de pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Consultar todos los pacientes registrados")
    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listarTodos();
    }

    @Operation(summary = "Consultar un paciente por su id")
    @GetMapping("/{id}")
    public Paciente obtener(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id);
    }

    @Operation(summary = "Registrar un nuevo paciente")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Paciente registrar(@Valid @RequestBody Paciente paciente) {
        return pacienteService.registrar(paciente);
    }
}
