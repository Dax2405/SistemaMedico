package DataAccess.DAO;

import DataAccess.DTO.PagoMetodoDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PagoMetodoDAO extends MySQLDataHelper implements IDAO<PagoMetodoDTO> {
    @Override
    public PagoMetodoDTO readBy(Integer id) throws Exception {
        PagoMetodoDTO oS = new PagoMetodoDTO();
        String query = " SELECT "
                + "  id_pago_metodo    "
                + " ,nombre_metodo     "
                + " ,estado            "
                + " ,fecha_crea        "
                + " ,fecha_modifica    "
                + " FROM pago_metodo "
                + " WHERE estado='A' AND id_pago_metodo = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new PagoMetodoDTO(
                        rs.getInt(1), // id_pago_metodo
                        rs.getString(2), // nombre_metodo
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
    public List<PagoMetodoDTO> readAll() throws Exception {
        List<PagoMetodoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_pago_metodo    "
                + " ,nombre_metodo     "
                + " ,estado            "
                + " ,fecha_crea        "
                + " ,fecha_modifica    "
                + " FROM pago_metodo "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                PagoMetodoDTO s = new PagoMetodoDTO(
                        rs.getInt(1), // id_pago_metodo
                        rs.getString(2), // nombre_metodo
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
    public boolean create(PagoMetodoDTO entity) throws Exception {
        String query = " INSERT INTO pago_metodo ("
                + "nombre_metodo"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreMetodo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(PagoMetodoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE pago_metodo SET nombre_metodo = ?, fecha_modifica = ? WHERE id_pago_metodo = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreMetodo());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdPagoMetodo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE pago_metodo SET estado = ? WHERE id_pago_metodo = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM pago_metodo "
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
