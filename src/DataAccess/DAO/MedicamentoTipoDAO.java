package DataAccess.DAO;

import DataAccess.DTO.MedicamentoTipoDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoTipoDAO extends MySQLDataHelper implements IDAO<MedicamentoTipoDTO> {
    @Override
    public MedicamentoTipoDTO readBy(Integer id) throws Exception {
        MedicamentoTipoDTO oS = new MedicamentoTipoDTO();
        String query = " SELECT "
                + "  id_medicamento_tipo, "
                + "  nombre_tipo, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medicamento_tipo "
                + " WHERE estado='A' AND id_medicamento_tipo = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new MedicamentoTipoDTO(
                        rs.getInt(1), // id_medicamento_tipo
                        rs.getString(2), // nombre_tipo
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
    public List<MedicamentoTipoDTO> readAll() throws Exception {
        List<MedicamentoTipoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_medicamento_tipo, "
                + "  nombre_tipo, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medicamento_tipo "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MedicamentoTipoDTO s = new MedicamentoTipoDTO(
                        rs.getInt(1), // id_medicamento_tipo
                        rs.getString(2), // nombre_tipo
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
    public boolean create(MedicamentoTipoDTO entity) throws Exception {
        String query = " INSERT INTO medicamento_tipo ("
                + "nombre_tipo"
                + ") VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreTipo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MedicamentoTipoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE medicamento_tipo SET nombre_tipo = ?, fecha_modifica = ? WHERE id_medicamento_tipo = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreTipo());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdMedicamentoTipo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE medicamento_tipo SET estado = ? WHERE id_medicamento_tipo = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM medicamento_tipo WHERE estado ='A'";
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
