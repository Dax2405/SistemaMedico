package DataAccess.DAO;

import DataAccess.DTO.PacienteDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends MySQLDataHelper implements IDAO<PacienteDTO> {
    @Override
    public PacienteDTO readBy(Integer id) throws Exception {
        PacienteDTO oS = new PacienteDTO();
        String query = " SELECT "
                + "  id_paciente    "
                + " ,id_usuario    "
                + " ,nombre        "
                + " ,apellido      "
                + " ,codigo_unico  "
                + " ,telefono      "
                + " ,fecha_nacimiento "
                + " ,direccion     "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM paciente "
                + " WHERE estado='A' AND id_paciente = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new PacienteDTO(
                        rs.getInt(1), // id_paciente
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // nombre
                        rs.getString(4), // apellido
                        rs.getString(5), // codigo_unico
                        rs.getString(6), // telefono
                        rs.getString(7), // fecha_nacimiento
                        rs.getString(8), // direccion
                        rs.getString(9), // estado
                        rs.getString(10), // fecha_crea
                        rs.getString(11) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<PacienteDTO> readAll() throws Exception {
        List<PacienteDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_paciente    "
                + " ,id_usuario    "
                + " ,nombre        "
                + " ,apellido      "
                + " ,codigo_unico  "
                + " ,telefono      "
                + " ,fecha_nacimiento "
                + " ,direccion     "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM paciente "
                + " WHERE estado='A' ";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la
            while (rs.next()) {
                PacienteDTO s = new PacienteDTO(
                        rs.getInt(1), // id_paciente
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // nombre
                        rs.getString(4), // apellido
                        rs.getString(5), // codigo_unico
                        rs.getString(6), // telefono
                        rs.getString(7), // fecha_nacimiento
                        rs.getString(8), // direccion
                        rs.getString(9), // estado
                        rs.getString(10), // fecha_crea
                        rs.getString(11) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(PacienteDTO entity) throws Exception {
        String query = " INSERT INTO paciente ("
                + "id_usuario, "
                + "nombre, "
                + "apellido, "
                + "codigo_unico, "
                + "telefono, "
                + "fecha_nacimiento, "
                + "direccion"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getApellido());
            pstmt.setString(4, entity.getCodigoUnico());
            pstmt.setString(5, entity.getTelefono());
            pstmt.setString(6, entity.getFechaNacimiento());
            pstmt.setString(7, entity.getDireccion());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setIdPaciente(rs.getInt(1));

            }
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(PacienteDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE paciente SET id_usuario = ?, nombre = ?, apellido = ?, codigo_unico = ?, telefono = ?, fecha_nacimiento = ?, direccion = ?, fecha_modifica = ? WHERE id_paciente = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getApellido());
            pstmt.setString(4, entity.getCodigoUnico());
            pstmt.setString(5, entity.getTelefono());
            pstmt.setString(6, entity.getFechaNacimiento());
            pstmt.setString(7, entity.getDireccion());
            pstmt.setString(8, dtf.format(now).toString());
            pstmt.setInt(9, entity.getIdPaciente());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE paciente SET estado = ? WHERE id_paciente = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM  "
                + " WHERE   Estado ='A' ";
        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la
            while (rs.next()) {
                return rs.getInt(1); // TotalReg
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }

    public PacienteDTO readByUsuarioId(Integer id) throws Exception {
        PacienteDTO oS = new PacienteDTO();
        String query = " SELECT "
                + "  id_paciente    "
                + " ,id_usuario    "
                + " ,nombre        "
                + " ,apellido      "
                + " ,codigo_unico  "
                + " ,telefono      "
                + " ,fecha_nacimiento "
                + " ,direccion     "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM paciente "
                + " WHERE estado='A' AND id_usuario = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new PacienteDTO(
                        rs.getInt(1), // id_paciente
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // nombre
                        rs.getString(4), // apellido
                        rs.getString(5), // codigo_unico
                        rs.getString(6), // telefono
                        rs.getString(7), // fecha_nacimiento
                        rs.getString(8), // direccion
                        rs.getString(9), // estado
                        rs.getString(10), // fecha_crea
                        rs.getString(11) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }
}
