package BusinessLogic.Entities.Autenticacion;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.Usuario.Usuario;
import BusinessLogic.Entities.Utils.AutenticacionFacialUtils;
import DataAccess.DAO.MedicoDAO;
import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.PacienteHistoriaClinicaDAO;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.MedicoDTO;
import DataAccess.DTO.PacienteDTO;
import DataAccess.DTO.PacienteHistoriaClinicaDTO;
import DataAccess.DTO.UsuarioDTO;

import java.util.List;

public abstract class Autenticacion {

    protected static Usuario obtenerInstanciaPorTipoUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO.getTipoUsuario().equals("medico")) {
            MedicoDAO oMedico = new MedicoDAO();
            MedicoDTO medicoDTO = oMedico.readByUsuarioId(usuarioDTO.getIdUsuario());
            return new Medico(medicoDTO.getIdMedico(), medicoDTO.getNombre(), medicoDTO.getApellido(),
                    medicoDTO.getTelefono(), medicoDTO.getIdMedicoEspecialidad(),
                    medicoDTO.getIdMedicoRol());

        } else if (usuarioDTO.getTipoUsuario().equals("paciente")) {
            PacienteDAO oPaciente = new PacienteDAO();
            PacienteDTO pacienteDTO = oPaciente.readByUsuarioId(usuarioDTO.getIdUsuario());

            PacienteHistoriaClinicaDAO oHistoriaClinica = new PacienteHistoriaClinicaDAO();
            List<PacienteHistoriaClinicaDTO> historasClinica = oHistoriaClinica
                    .readAllByPacienteId(pacienteDTO.getIdPaciente());

            return new Paciente(pacienteDTO.getIdPaciente(), pacienteDTO.getNombre(), pacienteDTO.getApellido(),
                    pacienteDTO.getTelefono(), pacienteDTO.getCodigoUnico(), pacienteDTO.getDireccion(),
                    pacienteDTO.getFechaNacimiento(), historasClinica, usuarioDTO.getEmail());

        } else {
            System.out.println("Usuario no encontrado");
            return null;
        }
    }

    public static Paciente registrarPaciente(String nombre, String apellido, String codigoUnico, String telefono,
            String fechaNacimiento, String direccion, String email, String contraseña) throws Exception {
        BLFactory<UsuarioDTO> oUsuario = new BLFactory<>(UsuarioDAO::new);
        BLFactory<PacienteDTO> oPaciente = new BLFactory<>(PacienteDAO::new);

        UsuarioDTO usuarioDTO = new UsuarioDTO(email, contraseña, "paciente");
        oUsuario.add(usuarioDTO);
        PacienteDTO pacienteDTO = new PacienteDTO(usuarioDTO.getIdUsuario(), nombre, apellido, codigoUnico, telefono,
                fechaNacimiento, direccion);
        oPaciente.add(pacienteDTO);
        AutenticacionFacialUtils.registrarFacialmente(usuarioDTO.getIdUsuario());
        return new Paciente(pacienteDTO.getIdPaciente(), nombre, apellido, telefono, codigoUnico, direccion,
                fechaNacimiento, email);

    }

    public static Usuario registrarUsuario(String nombre, String apellido, String telefono,
            Integer idMedicoEspecialidad, Integer idMedicoRol, String email, String contraseña) throws Exception {
        BLFactory<UsuarioDTO> oUsuario = new BLFactory<>(UsuarioDAO::new);
        BLFactory<MedicoDTO> oMedico = new BLFactory<>(MedicoDAO::new);

        UsuarioDTO usuarioDTO = new UsuarioDTO(email, contraseña, "medico");
        oUsuario.add(usuarioDTO);
        MedicoDTO medicoDTO = new MedicoDTO(usuarioDTO.getIdUsuario(), nombre, apellido, telefono, idMedicoEspecialidad,
                idMedicoRol);
        oMedico.add(medicoDTO);
        AutenticacionFacialUtils.registrarFacialmente(usuarioDTO.getIdUsuario());
        return new Medico(medicoDTO.getIdMedico(), nombre, apellido, telefono, idMedicoEspecialidad, idMedicoRol);
    }

}
