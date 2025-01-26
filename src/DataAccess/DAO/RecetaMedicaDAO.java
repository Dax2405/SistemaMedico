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
import DataAccess.DTO.RecetaMedicaDTO;
import Framework.PoliSaludException;

public class RecetaMedicaDAO extends SQLiteDataHelper implements IDAO<RecetaMedicaDTO> {
    @Override
    public RecetaMedicaDTO readBy(Integer id) throws Exception {
        RecetaMedicaDTO oS = new RecetaMedicaDTO();
        String query = " SELECT "
                + "  id_receta_medica, "
                + "  id_turno, "
                + "  indicaciones, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM receta_medica "
                + " WHERE estado='A' AND id_receta_medica = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new RecetaMedicaDTO(
                        rs.getInt(1), // id_receta_medica
                        rs.getInt(2), // id_turno
                        rs.getString(3), // indicaciones
                        rs.getString(4), // estado
                        rs.getString(5), // fecha_crea
                        rs.getString(6) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<RecetaMedicaDTO> readAll() throws Exception {
        List<RecetaMedicaDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_receta_medica, "
                + "  id_turno, "
                + "  indicaciones, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM receta_medica "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                RecetaMedicaDTO s = new RecetaMedicaDTO(
                        rs.getInt(1), // id_receta_medica
                        rs.getInt(2), // id_turno
                        rs.getString(3), // indicaciones
                        rs.getString(4), // estado
                        rs.getString(5), // fecha_crea
                        rs.getString(6) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(RecetaMedicaDTO entity) throws Exception {
        String query = " INSERT INTO receta_medica ("
                + "id_turno, "
                + "indicaciones"
                + ") VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdTurno());
            pstmt.setString(2, entity.getIndicaciones());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(RecetaMedicaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE receta_medica SET id_turno = ?, indicaciones = ?, fecha_modifica = ? WHERE id_receta_medica = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdTurno());
            pstmt.setString(2, entity.getIndicaciones());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdRecetaMedica());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE receta_medica SET estado = ? WHERE id_receta_medica = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM receta_medica WHERE estado ='A'";
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
