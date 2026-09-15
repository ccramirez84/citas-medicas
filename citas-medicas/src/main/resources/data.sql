-- Pacientes de prueba
INSERT INTO pacientes (nombre, documento, telefono, correo) VALUES ('Laura Gómez', '1010101010', '3001234567', 'laura.gomez@correo.com');
INSERT INTO pacientes (nombre, documento, telefono, correo) VALUES ('Carlos Pérez', '2020202020', '3002345678', 'carlos.perez@correo.com');
INSERT INTO pacientes (nombre, documento, telefono, correo) VALUES ('Ana Rodríguez', '3030303030', '3003456789', 'ana.rodriguez@correo.com');

-- Médicos de prueba
INSERT INTO medicos (nombre, especialidad, consultorio) VALUES ('Dr. Andrés Martínez', 'Medicina General', 'Consultorio 101');
INSERT INTO medicos (nombre, especialidad, consultorio) VALUES ('Dra. Paula Sánchez', 'Pediatría', 'Consultorio 102');
INSERT INTO medicos (nombre, especialidad, consultorio) VALUES ('Dr. Jorge Ramírez', 'Cardiología', 'Consultorio 201');

-- Una cita de ejemplo (Laura Gómez con el Dr. Andrés Martínez)
INSERT INTO citas (fecha, hora, estado, paciente_id, medico_id) VALUES ('2026-09-20', '09:00:00', 'PENDIENTE', 1, 1);
