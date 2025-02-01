package BusinessLogic.Entities.Autenticacion;

import BusinessLogic.Entities.Usuario.Usuario;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

public class AutenticacionCredenciales extends Autenticacion {

    public static Usuario autenticar(String email, String contrasena) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO usuarioDTO = usuarioDAO.login(email, contrasena);

        return obtenerInstanciaPorTipoUsuario(usuarioDTO);
    }

}
