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
import DataAccess.DTO.MedicoEspecialidadDTO;
import Framework.PoliSaludException;

public class MedicoEspecialidadDAO extends MySQLDataHelper implements IDAO<MedicoEspecialidadDTO> {

    @Override
    public MedicoEspecialidadDTO readBy(Integer id) throws Exception {
        MedicoEspecialidadDTO oS = new MedicoEspecialidadDTO();
        String query = " SELECT "
                + "  id_medico_especialidad, "
                + "  nombre_especialidad, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medico_especialidad "
                + " WHERE estado='A' AND id_medico_especialidad = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new MedicoEspecialidadDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<MedicoEspecialidadDTO> readAll() throws Exception {
        List<MedicoEspecialidadDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_medico_especialidad, "
                + "  nombre_especialidad, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medico_especialidad "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MedicoEspecialidadDTO s = new MedicoEspecialidadDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(MedicoEspecialidadDTO entity) throws Exception {
        String query = " INSERT INTO medico_especialidad ("
                + "nombre_especialidad"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreEspecialidad());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MedicoEspecialidadDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE medico_especialidad SET nombre_especialidad = ?, fecha_modifica = ? WHERE id_medico_especialidad = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreEspecialidad());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdMedicoEspecialidad());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE medico_especialidad SET estado = ? WHERE id_medico_especialidad = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM medico_especialidad WHERE estado ='A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }
}