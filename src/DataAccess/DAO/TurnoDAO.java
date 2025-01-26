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
import DataAccess.DTO.TurnoDTO;
import Framework.PoliSaludException;

public class TurnoDAO extends SQLiteDataHelper implements IDAO<TurnoDTO> {
    @Override
    public TurnoDTO readBy(Integer id) throws Exception {
        TurnoDTO oS = new TurnoDTO();
        String query = " SELECT "
                + "  id_turno    "
                + " ,id_paciente "
                + " ,id_medico   "
                + " ,id_sala     "
                + " ,fecha_turno "
                + " ,id_turno_estado "
                + " ,estado      "
                + " ,fecha_crea  "
                + " ,fecha_modifica"
                + " FROM turno "
                + " WHERE estado='A' AND id_turno = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new TurnoDTO(
                        rs.getInt(1), // id_turno
                        rs.getInt(2), // id_paciente
                        rs.getInt(3), // id_medico
                        rs.getInt(4), // id_sala
                        rs.getString(5), // fecha_turno
                        rs.getInt(6), // id_turno_estado
                        rs.getString(7), // estado
                        rs.getString(8), // fecha_crea
                        rs.getString(9) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<TurnoDTO> readAll() throws Exception {
        List<TurnoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_turno    "
                + " ,id_paciente "
                + " ,id_medico   "
                + " ,id_sala     "
                + " ,fecha_turno "
                + " ,id_turno_estado "
                + " ,estado      "
                + " ,fecha_crea  "
                + " ,fecha_modifica"
                + " FROM turno "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                TurnoDTO s = new TurnoDTO(
                        rs.getInt(1), // id_turno
                        rs.getInt(2), // id_paciente
                        rs.getInt(3), // id_medico
                        rs.getInt(4), // id_sala
                        rs.getString(5), // fecha_turno
                        rs.getInt(6), // id_turno_estado
                        rs.getString(7), // estado
                        rs.getString(8), // fecha_crea
                        rs.getString(9) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(TurnoDTO entity) throws Exception {
        String query = " INSERT INTO turno ("
                + "id_paciente, "
                + "id_medico, "
                + "id_sala, "
                + "fecha_turno, "
                + "id_turno_estado"
                + ") VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdPaciente());
            pstmt.setInt(2, entity.getIdMedico());
            pstmt.setInt(3, entity.getIdSala());
            pstmt.setString(4, entity.getFechaTurno());
            pstmt.setInt(5, entity.getIdTurnoEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(TurnoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE turno SET id_paciente = ?, id_medico = ?, id_sala = ?, fecha_turno = ?, id_turno_estado = ?, fecha_modifica = ? WHERE id_turno = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdPaciente());
            pstmt.setInt(2, entity.getIdMedico());
            pstmt.setInt(3, entity.getIdSala());
            pstmt.setString(4, entity.getFechaTurno());
            pstmt.setInt(5, entity.getIdTurnoEstado());
            pstmt.setString(6, dtf.format(now).toString());
            pstmt.setInt(7, entity.getIdTurno());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE turno SET estado = ? WHERE id_turno = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM turno WHERE estado ='A'";
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
