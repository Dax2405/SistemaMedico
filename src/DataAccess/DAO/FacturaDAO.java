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
import DataAccess.DTO.FacturaDTO;
import Framework.PoliSaludException;

public class FacturaDAO extends MySQLDataHelper implements IDAO<FacturaDTO> {
    @Override
    public FacturaDTO readBy(Integer id) throws Exception {
        FacturaDTO oS = new FacturaDTO();
        String query = " SELECT "
                + "  id_factura    "
                + " ,id_turno      "
                + " ,monto_total   "
                + " ,estado_pago   "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM factura "
                + " WHERE estado='A' AND id_factura = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new FacturaDTO(
                        rs.getInt(1), // id_factura
                        rs.getInt(2), // id_turno
                        rs.getFloat(3), // monto_total
                        rs.getString(4), // estado_pago
                        rs.getString(5), // estado
                        rs.getString(6), // fecha_crea
                        rs.getString(7) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<FacturaDTO> readAll() throws Exception {
        List<FacturaDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_factura    "
                + " ,id_turno      "
                + " ,monto_total   "
                + " ,estado_pago   "
                + " ,estado        "
                + " ,fecha_crea    "
                + " ,fecha_modifica"
                + " FROM factura "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                FacturaDTO s = new FacturaDTO(
                        rs.getInt(1), // id_factura
                        rs.getInt(2), // id_turno
                        rs.getFloat(3), // monto_total
                        rs.getString(4), // estado_pago
                        rs.getString(5), // estado
                        rs.getString(6), // fecha_crea
                        rs.getString(7) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(FacturaDTO entity) throws Exception {
        String query = " INSERT INTO factura ("
                + "id_turno, "
                + "monto_total, "
                + "estado_pago"
                + ") VALUES (?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdTurno());
            pstmt.setFloat(2, entity.getMontoTotal());
            pstmt.setString(3, entity.getEstadoPago());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(FacturaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE factura SET id_turno = ?, monto_total = ?, estado_pago = ?, fecha_modifica = ? WHERE id_factura = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdTurno());
            pstmt.setFloat(2, entity.getMontoTotal());
            pstmt.setString(3, entity.getEstadoPago());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setInt(5, entity.getIdFactura());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE factura SET estado = ? WHERE id_factura = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM factura "
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
