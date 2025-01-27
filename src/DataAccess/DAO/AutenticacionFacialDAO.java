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
import DataAccess.DTO.AutenticacionFacialDTO;
import Framework.PoliSaludException;

public class AutenticacionFacialDAO extends MySQLDataHelper implements IDAO<AutenticacionFacialDTO> {
    @Override
    public AutenticacionFacialDTO readBy(Integer id) throws Exception {
        AutenticacionFacialDTO oS = new AutenticacionFacialDTO();
        String query = " SELECT "
                + "  id_autenticacion, "
                + "  id_usuario, "
                + "  encoding_facial, "
                + "  fecha_crea "
                + " FROM autenticacion_facial "
                + " WHERE id_autenticacion = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new AutenticacionFacialDTO(
                        rs.getInt(1), // id_autenticacion
                        rs.getInt(2), // id_usuario
                        rs.getBytes(3), // encoding_facial
                        rs.getString(4) // fecha_crea
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<AutenticacionFacialDTO> readAll() throws Exception {
        List<AutenticacionFacialDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_autenticacion, "
                + "  id_usuario, "
                + "  encoding_facial, "
                + "  fecha_crea "
                + " FROM autenticacion_facial ";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                AutenticacionFacialDTO s = new AutenticacionFacialDTO(
                        rs.getInt(1), // id_autenticacion
                        rs.getInt(2), // id_usuario
                        rs.getBytes(3), // encoding_facial
                        rs.getString(4) // fecha_crea
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(AutenticacionFacialDTO entity) throws Exception {
        String query = " INSERT INTO autenticacion_facial ("
                + "id_usuario, "
                + "encoding_facial"
                + ") VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setBytes(2, entity.getEncodingFacial());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(AutenticacionFacialDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE autenticacion_facial SET id_usuario = ?, encoding_facial = ?, fecha_modifica = ? WHERE id_autenticacion = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setBytes(2, entity.getEncodingFacial());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdAutenticacion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " DELETE FROM autenticacion_facial WHERE id_autenticacion = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow() throws Exception {
        String query = " SELECT COUNT(*) TotalReg FROM autenticacion_facial";
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
