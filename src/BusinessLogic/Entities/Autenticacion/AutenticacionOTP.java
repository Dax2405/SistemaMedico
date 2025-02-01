package BusinessLogic.Entities.Autenticacion;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Usuario.Usuario;
import BusinessLogic.Entities.Utils.AutenticacionOTPUtils;
import BusinessLogic.Entities.Utils.EmailUtils;
import DataAccess.DAO.AutenticacionOTPDAO;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.AutenticacionOTPDTO;
import DataAccess.DTO.UsuarioDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AutenticacionOTP extends Autenticacion {
    static Integer idUsuario;

    public static Boolean autenticar(String email) throws Exception {

        UsuarioDAO oUsuarioDAO = new UsuarioDAO();
        UsuarioDTO usuarioDTO = oUsuarioDAO.readByEmail(email);
        if (usuarioDTO.getIdUsuario() != null) {
            idUsuario = usuarioDTO.getIdUsuario();
            String otp = AutenticacionOTPUtils.generarOTP();
            BLFactory<AutenticacionOTPDTO> oAutenticacionOTP = new BLFactory<>(AutenticacionOTPDAO::new);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fiveMinutesLater = LocalDateTime.now().plusMinutes(5);

            oAutenticacionOTP
                    .add(new AutenticacionOTPDTO(usuarioDTO.getIdUsuario(), otp,
                            dtf.format(fiveMinutesLater).toString()));
            EmailUtils.enviarEmail(email, "Codigo de acceso a Sistema Medico",
                    "Este es tu codigo para acceder al sistema: " + otp);
            System.out.println("OTP generado con exito: " + otp);
            return true;
        }
        return false;

    }

    public static Boolean validarOTP(String otp) throws Exception {
        AutenticacionOTPDAO oAutenticacionOTPDAO = new AutenticacionOTPDAO();

        AutenticacionOTPDTO autenticacionOTPDTO = oAutenticacionOTPDAO.readByOTP(otp);

        return autenticacionOTPDTO.getIdUsuario() != null;

    }

    public static Usuario obtenerUsuario() throws Exception {
        BLFactory<UsuarioDTO> oUsuario = new BLFactory<>(UsuarioDAO::new);
        UsuarioDTO usuarioDTO = oUsuario.getBy(idUsuario);
        return obtenerInstanciaPorTipoUsuario(usuarioDTO);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        AutenticacionOTP.idUsuario = idUsuario;
    }

}
