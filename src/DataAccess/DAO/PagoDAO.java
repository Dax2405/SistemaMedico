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
import DataAccess.DTO.PagoDTO;
import Framework.PoliSaludException;

public class PagoDAO extends SQLiteDataHelper implements IDAO<PagoDTO> {
    @Override
    public PagoDTO readBy(Integer id) throws Exception {
        PagoDTO oS = new PagoDTO();
        String query = " SELECT "
                + "  id_pago    "
                + " ,id_factura "
                + " ,estado     "
                + " ,fecha_crea "
                + " ,fecha_modifica"
                + " FROM pago "
                + " WHERE estado='A' AND id_pago = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new PagoDTO(
                        rs.getInt(1), // id_pago
                        rs.getInt(2), // id_factura
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
    public List<PagoDTO> readAll() throws Exception {
        List<PagoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_pago    "
                + " ,id_factura "
                + " ,estado     "
                + " ,fecha_crea "
                + " ,fecha_modifica"
                + " FROM pago "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                PagoDTO s = new PagoDTO(
                        rs.getInt(1), // id_pago
                        rs.getInt(2), // id_factura
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
    public boolean create(PagoDTO entity) throws Exception {
        String query = " INSERT INTO pago ("
                + "id_factura"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdFactura());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(PagoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE pago SET id_factura = ?, fecha_modifica = ? WHERE id_pago = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdFactura());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdPago());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE pago SET estado = ? WHERE id_pago = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM pago WHERE estado ='A'";
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
