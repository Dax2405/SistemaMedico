package DataAccess.DAO;

import DataAccess.DTO.MedicamentoDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO extends MySQLDataHelper implements IDAO<MedicamentoDTO> {
    @Override
    public MedicamentoDTO readBy(Integer id) throws Exception {
        MedicamentoDTO medicamento = new MedicamentoDTO();
        String query = " SELECT "
                + "  id_medicamento, "
                + "  nombre_comercial, "
                + "  nombre_quimico, "
                + "  concentracion, "
                + "  id_medicamento_tipo, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medicamento "
                + " WHERE estado='A' AND id_medicamento = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                medicamento = new MedicamentoDTO(
                        rs.getInt(1), // id_medicamento
                        rs.getString(2), // nombre_comercial
                        rs.getString(3), // nombre_quimico
                        rs.getFloat(4), // concentracion
                        rs.getInt(5), // id_medicamento_tipo
                        rs.getString(6), // estado
                        rs.getString(7), // fecha_crea
                        rs.getString(8) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return medicamento;
    }

    @Override
    public List<MedicamentoDTO> readAll() throws Exception {
        List<MedicamentoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_medicamento, "
                + "  nombre_comercial, "
                + "  nombre_quimico, "
                + "  concentracion, "
                + "  id_medicamento_tipo, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medicamento "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MedicamentoDTO medicamento = new MedicamentoDTO(
                        rs.getInt(1), // id_medicamento
                        rs.getString(2), // nombre_comercial
                        rs.getString(3), // nombre_quimico
                        rs.getFloat(4), // concentracion
                        rs.getInt(5), // id_medicamento_tipo
                        rs.getString(6), // estado
                        rs.getString(7), // fecha_crea
                        rs.getString(8) // fecha_modifica
                );
                lst.add(medicamento);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(MedicamentoDTO entity) throws Exception {
        String query = " INSERT INTO medicamento ("
                + "nombre_comercial, "
                + "nombre_quimico, "
                + "concentracion, "
                + "id_medicamento_tipo"
                + ") VALUES (?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreComercial());
            pstmt.setString(2, entity.getNombreQuimico());
            pstmt.setFloat(3, entity.getConcentracion());
            pstmt.setInt(4, entity.getIdMedicamentoTipo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MedicamentoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE medicamento SET nombre_comercial = ?, nombre_quimico = ?, concentracion = ?, id_medicamento_tipo = ?, fecha_modifica = ? WHERE id_medicamento = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreComercial());
            pstmt.setString(2, entity.getNombreQuimico());
            pstmt.setFloat(3, entity.getConcentracion());
            pstmt.setInt(4, entity.getIdMedicamentoTipo());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.setInt(6, entity.getIdMedicamento());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE medicamento SET estado = ? WHERE id_medicamento = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM medicamento WHERE estado ='A'";
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