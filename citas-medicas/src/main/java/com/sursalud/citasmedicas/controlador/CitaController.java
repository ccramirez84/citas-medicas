package com.sursalud.citasmedicas.controlador;

import com.sursalud.citasmedicas.dto.CitaRequest;
import com.sursalud.citasmedicas.modelo.Cita;
import com.sursalud.citasmedicas.servicio.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller de Cita: POST /citas (crear/solicitar) y DELETE /citas/{id}
 * (cancelar), tal como exige el enunciado, más GET para consultar
 * (requisito "Los pacientes necesitan: consultar citas").
 */
@RestController
@RequestMapping("/citas")
@Tag(name = "Citas", description = "Solicitud, consulta y cancelación de citas médicas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @Operation(summary = "Consultar todas las citas (uso administrativo: control centralizado)")
    @GetMapping
    public List<Cita> listar() {
        return citaService.listarTodas();
    }

    @Operation(summary = "Consultar una cita por su id")
    @GetMapping("/{id}")
    public Cita obtener(@PathVariable Long id) {
        return citaService.obtenerPorId(id);
    }

    @Operation(summary = "Consultar las citas de un paciente específico")
    @GetMapping("/paciente/{pacienteId}")
    public List<Cita> porPaciente(@PathVariable Long pacienteId) {
        return citaService.consultarCitasDePaciente(pacienteId);
    }

    @Operation(summary = "Solicitar (crear) una nueva cita")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cita solicitar(@Valid @RequestBody CitaRequest request) {
        return citaService.solicitarCita(request);
    }

    @Operation(summary = "Cancelar una cita (cambia su estado a CANCELADA)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {
        citaService.cancelarCita(id);
    }
}
