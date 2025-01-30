package DataAccess.DAO;

import DataAccess.DTO.MedicoRolDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicoRolDAO extends MySQLDataHelper implements IDAO<MedicoRolDTO> {
    @Override
    public MedicoRolDTO readBy(Integer id) throws Exception {
        MedicoRolDTO oS = new MedicoRolDTO();
        String query = " SELECT "
                + "  id_medico_rol    "
                + " ,nombre_rol       "
                + " ,estado           "
                + " ,fecha_crea       "
                + " ,fecha_modifica   "
                + " FROM medico_rol "
                + " WHERE estado='A' AND id_medico_rol = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new MedicoRolDTO(
                        rs.getInt(1), // id_medico_rol
                        rs.getString(2), // nombre_rol
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
    public List<MedicoRolDTO> readAll() throws Exception {
        List<MedicoRolDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_medico_rol    "
                + " ,nombre_rol       "
                + " ,estado           "
                + " ,fecha_crea       "
                + " ,fecha_modifica   "
                + " FROM medico_rol "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MedicoRolDTO s = new MedicoRolDTO(
                        rs.getInt(1), // id_medico_rol
                        rs.getString(2), // nombre_rol
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
    public boolean create(MedicoRolDTO entity) throws Exception {
        String query = " INSERT INTO medico_rol ("
                + "nombre_rol"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreRol());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MedicoRolDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE medico_rol SET nombre_rol = ?, fecha_modifica = ? WHERE id_medico_rol = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreRol());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdMedicoRol());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE medico_rol SET estado = ? WHERE id_medico_rol = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM medico_rol WHERE estado ='A'";
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