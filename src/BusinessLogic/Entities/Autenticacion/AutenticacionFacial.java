package BusinessLogic.Entities.Autenticacion;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Usuario.Usuario;
import BusinessLogic.Entities.Utils.AutenticacionFacialUtils;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

public class AutenticacionFacial extends Autenticacion {
    public static Usuario autenticar() throws Exception {
        BLFactory<UsuarioDTO> oUsuario = new BLFactory<>(UsuarioDAO::new);
        Integer idUsuario = AutenticacionFacialUtils.autenticarFacialmente();
        UsuarioDTO usuarioDTO = oUsuario.getBy(idUsuario);

        return obtenerInstanciaPorTipoUsuario(usuarioDTO);
    }

}
