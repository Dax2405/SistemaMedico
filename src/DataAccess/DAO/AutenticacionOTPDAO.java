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
import DataAccess.DTO.AutenticacionOTPDTO;
import Framework.PoliSaludException;

public class AutenticacionOTPDAO extends MySQLDataHelper implements IDAO<AutenticacionOTPDTO> {
    @Override
    public AutenticacionOTPDTO readBy(Integer id) throws Exception {
        AutenticacionOTPDTO oS = new AutenticacionOTPDTO();
        String query = " SELECT "
                + "  id_autenticacion_otp, "
                + "  otp, "
                + "  otp_expiracion, "
                + "  fecha_crea "
                + " FROM autenticacion_otp "
                + " WHERE id_autenticacion_otp = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new AutenticacionOTPDTO(
                        rs.getInt(1), // id_autenticacion_otp
                        rs.getString(2), // otp
                        rs.getString(3), // otp_expiracion
                        rs.getString(4) // fecha_crea
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
                + "  id_autenticacion_otp, "
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
                        rs.getInt(1), // id_autenticacion_otp
                        rs.getString(2), // otp
                        rs.getString(3), // otp_expiracion
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
    public boolean create(AutenticacionOTPDTO entity) throws Exception {
        String query = " INSERT INTO autenticacion_otp ("
                + "otp, "
                + "otp_expiracion"
                + ") VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getOtp());
            pstmt.setString(2, entity.getOtpExpiracion());
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
        String query = " UPDATE autenticacion_otp SET otp = ?, otp_expiracion = ?, fecha_modifica = ? WHERE id_autenticacion_otp = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getOtp());
            pstmt.setString(2, entity.getOtpExpiracion());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdAutenticacionOTP());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " DELETE FROM autenticacion_otp WHERE id_autenticacion_otp = ?";
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
}
