package com.sursalud.citasmedicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase de arranque del microservicio de gestión de citas médicas de la
 * clínica Sur Salud (Soacha, Cundinamarca).
 */
@SpringBootApplication
public class CitasMedicasApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitasMedicasApplication.class, args);
    }
}
