package com.sursalud.citasmedicas.controlador;

import com.sursalud.citasmedicas.modelo.Cita;
import com.sursalud.citasmedicas.modelo.Medico;
import com.sursalud.citasmedicas.servicio.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller de Medico: GET /medicos y POST /medicos, más el endpoint
 * de agenda pedido implícitamente por el requisito "Los médicos
 * necesitan: Visualizar agenda".
 *
 * NOTA: el enunciado escribe "GET /médicos" con tilde, pero las URLs no
 * deben llevar tildes (causan problemas de codificación); se usa
 * "/medicos" sin tilde, que es el estándar y sigue siendo semánticamente
 * idéntico a lo pedido.
 */
@RestController
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Registro, consulta de médicos y su agenda")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(summary = "Consultar todos los médicos")
    @GetMapping
    public List<Medico> listar() {
        return medicoService.listarTodos();
    }

    @Operation(summary = "Consultar un médico por su id")
    @GetMapping("/{id}")
    public Medico obtener(@PathVariable Long id) {
        return medicoService.obtenerPorId(id);
    }

    @Operation(summary = "Registrar un nuevo médico")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Medico registrar(@Valid @RequestBody Medico medico) {
        return medicoService.registrar(medico);
    }

    @Operation(summary = "Ver la agenda (citas asignadas) de un médico")
    @GetMapping("/{id}/agenda")
    public List<Cita> agenda(@PathVariable Long id) {
        return medicoService.obtenerAgenda(id);
    }
}
