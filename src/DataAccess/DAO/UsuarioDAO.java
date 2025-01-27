package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import DataAccess.MySQLDataHelper;
import DataAccess.DTO.UsuarioDTO;
import Framework.PoliSaludException;

public class UsuarioDAO extends MySQLDataHelper implements IDAO<UsuarioDTO> {
    @Override
    public UsuarioDTO readBy(Integer id) throws Exception {
        UsuarioDTO oS = new UsuarioDTO();
        String query = " SELECT "
                + "  id_usuario    "
                + " ,email         "
                + " ,contrasena    "
                + " ,tipo_usuario  "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM usuario "
                + " WHERE estado='A' AND id_usuario = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new UsuarioDTO(
                        rs.getInt(1), // id_usuario
                        rs.getString(2), // email
                        rs.getString(3), // contrasena
                        rs.getString(4), // tipo_usuario
                        rs.getString(5), // estado
                        rs.getString(6), // fecha_crea
                        rs.getString(7) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<UsuarioDTO> readAll() throws Exception {
        List<UsuarioDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_usuario    "
                + " ,email         "
                + " ,contrasena    "
                + " ,tipo_usuario  "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM usuario "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                UsuarioDTO s = new UsuarioDTO(
                        rs.getInt(1), // id_usuario
                        rs.getString(2), // email
                        rs.getString(3), // contrasena
                        rs.getString(4), // tipo_usuario
                        rs.getString(5), // estado
                        rs.getString(6), // fecha_crea
                        rs.getString(7) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(UsuarioDTO entity) throws Exception {
        String query = " INSERT INTO usuario ("
                + "email, "
                + "contrasena, "
                + "tipo_usuario"
                + ") VALUES (?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getContrasena());
            pstmt.setString(3, entity.getTipoUsuario());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(UsuarioDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE usuario SET email = ?, contrasena = ?, tipo_usuario = ?, fecha_modifica = ? WHERE id_usuario = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getContrasena());
            pstmt.setString(3, entity.getTipoUsuario());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setInt(5, entity.getIdUsuario());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE usuario SET estado = ? WHERE id_usuario = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow() throws Exception {
        String query = " SELECT COUNT(*) TotalReg FROM usuario WHERE estado ='A'";
        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                return rs.getInt(1); // TotalReg
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }
}
