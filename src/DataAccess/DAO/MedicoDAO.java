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
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.MedicoDTO;
import Framework.PoliSaludException;

public class MedicoDAO extends SQLiteDataHelper implements IDAO<MedicoDTO> {
    @Override
    public MedicoDTO readBy(Integer id) throws Exception {
        MedicoDTO oS = new MedicoDTO();
        String query = " SELECT "
                + "  id_medico    "
                + " ,id_usuario   "
                + " ,nombre        "
                + " ,apellido      "
                + " ,telefono      "
                + " ,id_medico_especialidad "
                + " ,id_medico_rol "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM medico "
                + " WHERE estado='A' AND id_medico = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new MedicoDTO(
                        rs.getInt(1), // id_medico
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // nombre
                        rs.getString(4), // apellido
                        rs.getString(5), // telefono
                        rs.getString(6), // id_medico_especialidad
                        rs.getString(7), // id_medico_rol
                        rs.getString(8), // estado
                        rs.getString(9), // fecha_crea
                        rs.getString(10) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<MedicoDTO> readAll() throws Exception {
        List<MedicoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_medico    "
                + " ,id_usuario   "
                + " ,nombre        "
                + " ,apellido      "
                + " ,telefono      "
                + " ,id_medico_especialidad "
                + " ,id_medico_rol "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM medico "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                MedicoDTO s = new MedicoDTO(
                        rs.getInt(1), // id_medico
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // nombre
                        rs.getString(4), // apellido
                        rs.getString(5), // telefono
                        rs.getString(6), // id_medico_especialidad
                        rs.getString(7), // id_medico_rol
                        rs.getString(8), // estado
                        rs.getString(9), // fecha_crea
                        rs.getString(10) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(MedicoDTO entity) throws Exception {
        String query = " INSERT INTO medico ("
                + "id_usuario, "
                + "nombre, "
                + "apellido, "
                + "email, "
                + "telefono, "
                + "id_medico_especialidad, "
                + "id_medico_rol"
                + ") VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getApellido());
            pstmt.setString(4, entity.getTelefono());
            pstmt.setString(5, entity.getIdMedicoEspecialidad());
            pstmt.setString(6, entity.getIdMedicoRol());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MedicoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE medico SET id_usuario = ?, nombre = ?, apellido = ?, email = ?, telefono = ?, id_medico_especialidad = ?, id_medico_rol = ?, fecha_modifica = ? WHERE id_medico = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getApellido());
            pstmt.setString(4, entity.getTelefono());
            pstmt.setString(5, entity.getIdMedicoEspecialidad());
            pstmt.setString(6, entity.getIdMedicoRol());
            pstmt.setString(7, dtf.format(now).toString());
            pstmt.setInt(8, entity.getIdMedico());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE medico SET estado = ? WHERE id_medico = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM medico "
                + " WHERE estado ='A' ";
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
