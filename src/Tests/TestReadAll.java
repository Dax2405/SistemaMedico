package Tests;

import DataAccess.DAO.*;
import DataAccess.DTO.*;

import java.util.List;

public class TestReadAll {
    public static void main(String[] args) {
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            MedicoEspecialidadDAO medicoEspecialidadDAO = new MedicoEspecialidadDAO();
            MedicoRolDAO medicoRolDAO = new MedicoRolDAO();
            MedicoDAO medicoDAO = new MedicoDAO();
            PacienteHistoriaClinicaDAO pacienteHistoriaClinicaDAO = new PacienteHistoriaClinicaDAO();
            RecetaMedicaDAO recetaMedicaDAO = new RecetaMedicaDAO();
            MedicamentoRecetadoDAO medicamentoRecetadoDAO = new MedicamentoRecetadoDAO();
            MedicamentoTipoDAO medicamentoTipoDAO = new MedicamentoTipoDAO();
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            FacturaDAO facturaDAO = new FacturaDAO();
            PagoDAO pagoDAO = new PagoDAO();
            PagoMetodoDAO pagoMetodoDAO = new PagoMetodoDAO();
            SalaDAO salaDAO = new SalaDAO();
            TurnoEstadoDAO turnoEstadoDAO = new TurnoEstadoDAO();
            TurnoDAO turnoDAO = new TurnoDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            AutenticacionFacialDAO autenticacionFacialDAO = new AutenticacionFacialDAO();
            AutenticacionOTPDAO autenticacionOTPDAO = new AutenticacionOTPDAO();

            // List<PacienteDTO> pacientes = pacienteDAO.readAll();
            // System.out.println("Pacientes:");
            // for (PacienteDTO paciente : pacientes) {
            // System.out.println(paciente);
            // }

            // List<MedicoEspecialidadDTO> especialidades = medicoEspecialidadDAO.readAll();
            // System.out.println("Especialidades Médicas:");
            // for (MedicoEspecialidadDTO especialidad : especialidades) {
            // System.out.println(especialidad);
            // }

            // List<MedicoRolDTO> roles = medicoRolDAO.readAll();
            // System.out.println("Roles Médicos:");
            // for (MedicoRolDTO rol : roles) {
            // System.out.println(rol);
            // }

            // List<MedicoDTO> medicos = medicoDAO.readAll();
            // System.out.println("Médicos:");
            // for (MedicoDTO medico : medicos) {
            // System.out.println(medico);
            // }

            // List<PacienteHistoriaClinicaDTO> historiasClinicas =
            // pacienteHistoriaClinicaDAO.readAll();
            // System.out.println("Historias Clínicas de Pacientes:");
            // for (PacienteHistoriaClinicaDTO historiaClinica : historiasClinicas) {
            // System.out.println(historiaClinica);
            // }

            // List<RecetaMedicaDTO> recetasMedicas = recetaMedicaDAO.readAll();
            // System.out.println("Recetas Médicas:");
            // for (RecetaMedicaDTO recetaMedica : recetasMedicas) {
            // System.out.println(recetaMedica);
            // }

            // List<MedicamentoRecetadoDTO> medicamentosRecetados =
            // medicamentoRecetadoDAO.readAll();
            // System.out.println("Medicamentos Recetados:");
            // for (MedicamentoRecetadoDTO medicamentoRecetado : medicamentosRecetados) {
            // System.out.println(medicamentoRecetado);
            // }

            // List<MedicamentoTipoDTO> tiposMedicamentos = medicamentoTipoDAO.readAll();
            // System.out.println("Tipos de Medicamentos:");
            // for (MedicamentoTipoDTO tipoMedicamento : tiposMedicamentos) {
            // System.out.println(tipoMedicamento);
            // }

            // List<MedicamentoDTO> medicamentos = medicamentoDAO.readAll();
            // System.out.println("Medicamentos:");
            // for (MedicamentoDTO medicamento : medicamentos) {
            // System.out.println(medicamento);
            // }

            // List<FacturaDTO> facturas = facturaDAO.readAll();
            // System.out.println("Facturas:");
            // for (FacturaDTO factura : facturas) {
            // System.out.println(factura);
            // }

            List<PagoDTO> pagos = pagoDAO.readAll();
            System.out.println("Pagos:");
            for (PagoDTO pago : pagos) {
                System.out.println(pago);
            }

            List<PagoMetodoDTO> metodosPago = pagoMetodoDAO.readAll();
            System.out.println("Métodos de Pago:");
            for (PagoMetodoDTO metodoPago : metodosPago) {
                System.out.println(metodoPago);
            }

            List<SalaDTO> salas = salaDAO.readAll();
            System.out.println("Salas:");
            for (SalaDTO sala : salas) {
                System.out.println(sala);
            }

            List<TurnoEstadoDTO> estadosTurno = turnoEstadoDAO.readAll();
            System.out.println("Estados de Turno:");
            for (TurnoEstadoDTO estadoTurno : estadosTurno) {
                System.out.println(estadoTurno);
            }

            List<TurnoDTO> turnos = turnoDAO.readAll();
            System.out.println("Turnos:");
            for (TurnoDTO turno : turnos) {
                System.out.println(turno);
            }

            List<UsuarioDTO> usuarios = usuarioDAO.readAll();
            System.out.println("Usuarios:");
            for (UsuarioDTO usuario : usuarios) {
                System.out.println(usuario);
            }

            List<AutenticacionFacialDTO> autenticacionesFacial = autenticacionFacialDAO.readAll();
            System.out.println("Autenticaciones Faciales:");
            for (AutenticacionFacialDTO autenticacionFacial : autenticacionesFacial) {
                System.out.println(autenticacionFacial);
            }

            List<AutenticacionOTPDTO> autenticacionesOTP = autenticacionOTPDAO.readAll();
            System.out.println("Autenticaciones OTP:");
            for (AutenticacionOTPDTO autenticacionOTP : autenticacionesOTP) {
                System.out.println(autenticacionOTP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}