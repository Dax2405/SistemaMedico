-- Insertar datos en la tabla usuario
INSERT INTO usuario (id_usuario, email, contrasena, tipo_usuario)
VALUES (
                1,
                'dax.navarrete@epn.edu.ec',
                'password',
                'paciente'
        ),
        (
                2,
                'emilia.moreno@epn.edu.ec',
                'password',
                'paciente'
        ),
        (
                3,
                'pat.paccha@epn.edu.ec',
                'password',
                'paciente'
        ),
        (
                4,
                'andres.martinez@epn.edu.ec',
                'password',
                'medico'
        ),
        (
                5,
                'mariana.paredes@epn.edu.ec',
                'password',
                'medico'
        ),
        (
                6,
                'carlos.gomez@epn.edu.ec',
                'password',
                'medico'
        ),
        (
                7,
                'sofia.torres@epn.edu.ec',
                'password',
                'medico'
        ),
        (
                8,
                'juan.cevallos@epn.edu.ec',
                'password',
                'medico'
        ),
        (9, 'ana.lopez@epn.edu.ec', 'password', 'medico'),
        (
                10,
                'pedro.mendez@epn.edu.ec',
                'password',
                'medico'
        );
-- Insertar datos en la tabla paciente
INSERT INTO paciente (
                id_paciente,
                id_usuario,
                nombre,
                apellido,
                codigo_unico,
                telefono,
                fecha_nacimiento,
                direccion
        )
VALUES (
                1,
                1,
                'Dax',
                'Navarrete',
                '2023201',
                '0991234567',
                '1990-05-10',
                'Quito'
        ),
        (
                2,
                2,
                'Emilia',
                'Moreno',
                '2023202',
                '0987654321',
                '1985-11-15',
                'Guayaquil'
        ),
        (
                3,
                3,
                'Patricio',
                'Paccha',
                '2023203',
                '0971122334',
                '1992-08-20',
                'Cuenca'
        );
-- Insertar datos en la tabla medico_especialidad
INSERT INTO medico_especialidad (id_medico_especialidad, nombre_especialidad)
VALUES (1, 'Cardiología'),
        (2, 'Pediatría'),
        (3, 'Neurología'),
        (4, 'Traumatología'),
        (5, 'Dermatología');
-- Insertar datos en la tabla medico_rol
INSERT INTO medico_rol (id_medico_rol, nombre_rol)
VALUES (1, 'Residente'),
        (2, 'Especialista'),
        (3, 'Jefe de Departamento');
-- Insertar datos en la tabla medico
INSERT INTO medico (
                id_medico,
                id_usuario,
                nombre,
                apellido,
                telefono,
                id_medico_especialidad,
                id_medico_rol
        )
VALUES (1, 4, 'Andrés', 'Martínez', '0981122334', 1, 2),
        (2, 5, 'Mariana', 'Paredes', '0995544332', 1, 3),
        (3, 6, 'Carlos', 'Gómez', '0978899776', 2, 1),
        (4, 7, 'Sofía', 'Torres', '0954433221', 2, 2),
        (5, 8, 'Juan', 'Cevallos', '0965544332', 3, 1),
        (6, 9, 'Ana', 'López', '0965544333', 3, 2),
        (7, 10, 'Pedro', 'Méndez', '0965544334', 4, 1);
-- Insertar datos en la tabla sala
INSERT INTO sala (id_sala, numero_sala)
VALUES (1, 101),
        (2, 102),
        (3, 201),
        (4, 202),
        (5, 301);
-- Insertar datos en la tabla turno_estado
INSERT INTO turno_estado (id_turno_estado, nombre_estado)
VALUES (1, 'Programado'),
        (2, 'Realizado'),
        (3, 'Cancelado');
-- Insertar datos en la tabla turno
INSERT INTO turno (
                id_turno,
                id_paciente,
                id_medico,
                id_sala,
                fecha_turno,
                id_turno_estado
        )
VALUES (1, 1, 1, 1, '2025-02-01 09:00:00', 1);
-- Insertar datos en la tabla paciente_historia_clinica
INSERT INTO paciente_historia_clinica (
                id_paciente_historia_clinica,
                id_paciente,
                diagnostico,
                tratamiento,
                id_medico
        )
VALUES (
                1,
                1,
                'Hipertensión',
                'Medicamentos antihipertensivos',
                1
        ),
        (
                2,
                2,
                'Asma',
                'Inhaladores y control ambiental',
                2
        ),
        (
                3,
                3,
                'Migraña',
                'Analgésicos y control del estrés',
                3
        ),
        (
                4,
                4,
                'Fractura de pierna',
                'Inmovilización y fisioterapia',
                4
        ),
        (5, 5, 'Dermatitis', 'Cremas tópicas', 5);
-- Insertar datos en la tabla medicamento_tipo
INSERT INTO medicamento_tipo (id_medicamento_tipo, nombre_tipo)
VALUES (1, 'Analgesico'),
        (2, 'Antibiotico'),
        (3, 'Antiinflamatorio');
-- Insertar datos en la tabla medicamento
INSERT INTO medicamento (
                id_medicamento,
                nombre_comercial,
                nombre_quimico,
                concentracion,
                id_medicamento_tipo
        )
VALUES (1, 'Paracetamol', 'Acetaminofén', 500, 1),
        (
                2,
                'Amoxicilina',
                'Amoxicilina Trihidrato',
                250,
                2
        ),
        (3, 'Ibuprofeno', 'Ibuprofeno', 200, 3);
-- Insertar datos en la tabla receta_medica
INSERT INTO receta_medica (id_receta_medica, id_turno, indicaciones)
VALUES (1, 1, 'Tomar 1 comprimido cada 8 horas'),
        (2, 2, 'Tomar 1 comprimido cada 12 horas');
-- Insertar datos en la tabla medicamento_recetado
INSERT INTO medicamento_recetado (
                id_medicamento_recetado,
                id_receta_medica,
                id_medicamento
        )
VALUES (1, 1, 1),
        (2, 2, 2);
-- Insertar datos en la tabla factura
INSERT INTO factura (id_factura, id_turno, monto_total, estado_pago)
VALUES (1, 1, 50.00, 'Pagado'),
        (2, 2, 75.00, 'Pendiente');
-- Insertar datos en la tabla pago_metodo
INSERT INTO pago_metodo (id_pago_metodo, nombre_metodo)
VALUES (1, 'Efectivo'),
        (2, 'Tarjeta de Crédito');
-- Insertar datos en la tabla pago
INSERT INTO pago (id_pago, id_factura, id_pago_metodo)
VALUES (1, 1, 1),
        (2, 2, 2);