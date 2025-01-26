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
import DataAccess.DTO.SalaDTO;
import Framework.PoliSaludException;

public class SalaDAO extends SQLiteDataHelper implements IDAO<SalaDTO> {
    @Override
    public SalaDTO readBy(Integer id) throws Exception {
        SalaDTO oS = new SalaDTO();
        String query = " SELECT "
                + "  id_sala    "
                + " ,numero_sala "
                + " ,estado      "
                + " ,fecha_crea  "
                + " ,fecha_modifica"
                + " FROM sala "
                + " WHERE estado='A' AND id_sala = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new SalaDTO(
                        rs.getInt(1), // id_sala
                        rs.getInt(2), // numero_sala
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
    public List<SalaDTO> readAll() throws Exception {
        List<SalaDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_sala    "
                + " ,numero_sala "
                + " ,estado      "
                + " ,fecha_crea  "
                + " ,fecha_modifica"
                + " FROM sala "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                SalaDTO s = new SalaDTO(
                        rs.getInt(1), // id_sala
                        rs.getInt(2), // numero_sala
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
    public boolean create(SalaDTO entity) throws Exception {
        String query = " INSERT INTO sala ("
                + "numero_sala"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getNumeroSala());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(SalaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE sala SET numero_sala = ?, fecha_modifica = ? WHERE id_sala = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getNumeroSala());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdSala());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE sala SET estado = ? WHERE id_sala = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM sala "
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
