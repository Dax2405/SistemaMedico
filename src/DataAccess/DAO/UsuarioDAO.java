package DataAccess.DAO;

import DataAccess.DTO.UsuarioDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getContrasena());
            pstmt.setString(3, entity.getTipoUsuario());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setIdUsuario(rs.getInt(1));
            }
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

    public UsuarioDTO login(String email, String contrasena) throws Exception {
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
                + " WHERE estado='A' AND email = ? AND contrasena = ?";

        try {
            Connection conn = openConnection(); // conectar a DB
            PreparedStatement pstmt = conn.prepareStatement(query); // CRUD : select * ...
            pstmt.setString(1, email);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();
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
            throw new PoliSaludException(e.getMessage(), UsuarioDAO.class.getName(), "login()");
        }
        return oS;
    }

    public UsuarioDTO readByEmail(String email) throws Exception {
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
                + " WHERE estado='A' AND email = '" + email + "'";

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
}
