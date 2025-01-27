-- MySQL
DROP TABLE IF EXISTS pago;
DROP TABLE IF EXISTS pago_metodo;
DROP TABLE IF EXISTS factura;
DROP TABLE IF EXISTS medicamento_recetado;
DROP TABLE IF EXISTS medicamento;
DROP TABLE IF EXISTS medicamento_tipo;
DROP TABLE IF EXISTS receta_medica;
DROP TABLE IF EXISTS turno;
DROP TABLE IF EXISTS turno_estado;
DROP TABLE IF EXISTS sala;
DROP TABLE IF EXISTS paciente_historia_clinica;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS medico_rol;
DROP TABLE IF EXISTS medico_especialidad;
DROP TABLE IF EXISTS autenticacion_facial;
DROP TABLE IF EXISTS autenticacion_otp;
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email CHAR(255) UNIQUE NOT NULL,
    contrasena CHAR(255) NOT NULL,
    tipo_usuario CHAR(10) NOT NULL CHECK (tipo_usuario IN ('medico', 'paciente')),
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre CHAR(255) NOT NULL,
    apellido CHAR(255) NOT NULL,
    codigo_unico CHAR(255) UNIQUE NOT NULL,
    telefono CHAR(20),
    fecha_nacimiento DATE,
    direccion CHAR(255),
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE medico_especialidad (
    id_medico_especialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre_especialidad CHAR(255) UNIQUE NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE medico_rol (
    id_medico_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol CHAR(255) UNIQUE NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre CHAR(255) NOT NULL,
    apellido CHAR(255) NOT NULL,
    telefono CHAR(20),
    id_medico_especialidad INT NOT NULL,
    id_medico_rol INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_medico_especialidad) REFERENCES medico_especialidad(id_medico_especialidad),
    FOREIGN KEY (id_medico_rol) REFERENCES medico_rol(id_medico_rol)
);
CREATE TABLE autenticacion_facial (
    id_autenticacion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    encoding_facial BLOB NOT NULL,
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE autenticacion_otp (
    id_autenticacion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    otp CHAR(255) NOT NULL,
    otp_expiracion TIMESTAMP NOT NULL,
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE paciente_historia_clinica (
    id_paciente_historia_clinica INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    diagnostico CHAR(255),
    tratamiento CHAR(255),
    id_medico INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico)
);
CREATE TABLE turno_estado (
    id_turno_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado CHAR(255) UNIQUE NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE sala (
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    numero_sala INT UNIQUE NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE turno (
    id_turno INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    id_sala INT NOT NULL,
    fecha_turno TIMESTAMP NOT NULL,
    id_turno_estado INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico),
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala),
    FOREIGN KEY (id_turno_estado) REFERENCES turno_estado(id_turno_estado)
);
CREATE TABLE receta_medica (
    id_receta_medica INT AUTO_INCREMENT PRIMARY KEY,
    id_turno INT NOT NULL,
    indicaciones CHAR(255),
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_turno) REFERENCES turno(id_turno)
);
CREATE TABLE medicamento_tipo (
    id_medicamento_tipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo CHAR(255) UNIQUE NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE medicamento (
    id_medicamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_comercial CHAR(255) NOT NULL,
    nombre_quimico CHAR(255),
    concentracion FLOAT,
    id_medicamento_tipo INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_medicamento_tipo) REFERENCES medicamento_tipo(id_medicamento_tipo)
);
CREATE TABLE medicamento_recetado (
    id_medicamento_recetado INT AUTO_INCREMENT PRIMARY KEY,
    id_receta_medica INT NOT NULL,
    id_medicamento INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_receta_medica) REFERENCES receta_medica(id_receta_medica),
    FOREIGN KEY (id_medicamento) REFERENCES medicamento(id_medicamento)
);
CREATE TABLE factura (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    id_turno INT NOT NULL,
    monto_total FLOAT NOT NULL,
    estado_pago CHAR(255) NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_turno) REFERENCES turno(id_turno)
);
CREATE TABLE pago_metodo (
    id_pago_metodo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo CHAR(255) UNIQUE NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT NOT NULL,
    id_pago_metodo INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    fecha_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_factura) REFERENCES factura(id_factura),
    FOREIGN KEY (id_pago_metodo) REFERENCES pago_metodo(id_pago_metodo)
);