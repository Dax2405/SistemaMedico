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
import DataAccess.DTO.TurnoEstadoDTO;
import Framework.PoliSaludException;

public class TurnoEstadoDAO extends MySQLDataHelper implements IDAO<TurnoEstadoDTO> {
    @Override
    public TurnoEstadoDTO readBy(Integer id) throws Exception {
        TurnoEstadoDTO oS = new TurnoEstadoDTO();
        String query = " SELECT "
                + "  id_turno_estado, "
                + "  nombre_estado, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM turno_estado "
                + " WHERE estado='A' AND id_turno_estado = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new TurnoEstadoDTO(
                        rs.getInt(1), // id_turno_estado
                        rs.getString(2), // nombre_estado
                        rs.getString(3), // estado
                        rs.getString(4), // fecha_crea
                        rs.getString(5) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<TurnoEstadoDTO> readAll() throws Exception {
        List<TurnoEstadoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_turno_estado, "
                + "  nombre_estado, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM turno_estado "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TurnoEstadoDTO s = new TurnoEstadoDTO(
                        rs.getInt(1), // id_turno_estado
                        rs.getString(2), // nombre_estado
                        rs.getString(3), // estado
                        rs.getString(4), // fecha_crea
                        rs.getString(5) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(TurnoEstadoDTO entity) throws Exception {
        String query = " INSERT INTO turno_estado ("
                + "nombre_estado"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(TurnoEstadoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE turno_estado SET nombre_estado = ?, fecha_modifica = ? WHERE id_turno_estado = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreEstado());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdTurnoEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE turno_estado SET estado = ? WHERE id_turno_estado = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM turno_estado WHERE estado ='A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1); // TotalReg
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }
}
