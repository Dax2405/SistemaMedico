package DataAccess.DAO;

import DataAccess.DTO.AutenticacionOTPDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AutenticacionOTPDAO extends MySQLDataHelper implements IDAO<AutenticacionOTPDTO> {
    @Override
    public AutenticacionOTPDTO readBy(Integer id) throws Exception {
        AutenticacionOTPDTO oS = new AutenticacionOTPDTO();
        String query = " SELECT "
                + "  id_autenticacion, "
                + "  id_usuario, "
                + "  otp, "
                + "  otp_expiracion, "
                + "  fecha_crea "
                + " FROM autenticacion_otp "
                + " WHERE id_autenticacion = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new AutenticacionOTPDTO(
                        rs.getInt(1), // id_autenticacion
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // otp
                        rs.getString(4), // otp_expiracion
                        rs.getString(5) // fecha_crea
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<AutenticacionOTPDTO> readAll() throws Exception {
        List<AutenticacionOTPDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_autenticacion, "
                + "  id_usuario, "
                + "  otp, "
                + "  otp_expiracion, "
                + "  fecha_crea "
                + " FROM autenticacion_otp ";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                AutenticacionOTPDTO s = new AutenticacionOTPDTO(
                        rs.getInt(1), // id_autenticacion
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // otp
                        rs.getString(4), // otp_expiracion
                        rs.getString(5) // fecha_crea
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(AutenticacionOTPDTO entity) throws Exception {
        String query = " INSERT INTO autenticacion_otp ("
                + "id_usuario, "
                + "otp, "
                + "otp_expiracion"
                + ") VALUES (?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setString(2, entity.getOtp());
            pstmt.setString(3, entity.getOtpExpiracion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(AutenticacionOTPDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE autenticacion_otp SET id_usuario = ?, otp = ?, otp_expiracion = ?, fecha_modifica = ? WHERE id_autenticacion = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUsuario());
            pstmt.setString(2, entity.getOtp());
            pstmt.setString(3, entity.getOtpExpiracion());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setInt(5, entity.getIdAutenticacionOTP());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " DELETE FROM autenticacion_otp WHERE id_autenticacion = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM autenticacion_otp";
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

    public AutenticacionOTPDTO readByOTP(String otp) throws Exception {
        AutenticacionOTPDTO oS = new AutenticacionOTPDTO();
        String query = " SELECT "
                + "  id_autenticacion, "
                + "  id_usuario, "
                + "  otp, "
                + "  otp_expiracion, "
                + "  fecha_crea "
                + " FROM autenticacion_otp "
                + " WHERE otp = " + otp;

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new AutenticacionOTPDTO(
                        rs.getInt(1), // id_autenticacion
                        rs.getInt(2), // id_usuario
                        rs.getString(3), // otp
                        rs.getString(4), // otp_expiracion
                        rs.getString(5) // fecha_crea
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;

    }
}
