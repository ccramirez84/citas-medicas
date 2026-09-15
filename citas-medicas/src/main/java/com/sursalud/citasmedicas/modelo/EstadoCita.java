package com.sursalud.citasmedicas.modelo;

/**
 * Estados posibles de una cita médica, usados para el campo "estado"
 * de la entidad Cita pedido por el enunciado.
 */
public enum EstadoCita {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA,
    ATENDIDA
}
