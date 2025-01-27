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
import DataAccess.DTO.MedicamentoRecetadoDTO;
import Framework.PoliSaludException;

public class MedicamentoRecetadoDAO extends MySQLDataHelper implements IDAO<MedicamentoRecetadoDTO> {
    @Override
    public MedicamentoRecetadoDTO readBy(Integer id) throws Exception {
        MedicamentoRecetadoDTO oS = new MedicamentoRecetadoDTO();
        String query = " SELECT "
                + "  id_medicamento_recetado, "
                + "  id_receta_medica, "
                + "  id_medicamento, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medicamento_recetado "
                + " WHERE estado='A' AND id_medicamento_recetado = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new MedicamentoRecetadoDTO(
                        rs.getInt(1), // id_medicamento_recetado
                        rs.getInt(2), // id_receta_medica
                        rs.getInt(3), // id_medicamento
                        rs.getString(4), // estado
                        rs.getString(5), // fecha_crea
                        rs.getString(6) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<MedicamentoRecetadoDTO> readAll() throws Exception {
        List<MedicamentoRecetadoDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_medicamento_recetado, "
                + "  id_receta_medica, "
                + "  id_medicamento, "
                + "  estado, "
                + "  fecha_crea, "
                + "  fecha_modifica "
                + " FROM medicamento_recetado "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MedicamentoRecetadoDTO s = new MedicamentoRecetadoDTO(
                        rs.getInt(1), // id_medicamento_recetado
                        rs.getInt(2), // id_receta_medica
                        rs.getInt(3), // id_medicamento
                        rs.getString(4), // estado
                        rs.getString(5), // fecha_crea
                        rs.getString(6) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(MedicamentoRecetadoDTO entity) throws Exception {
        String query = " INSERT INTO medicamento_recetado ("
                + "id_receta_medica, "
                + "id_medicamento"
                + ") VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdRecetaMedica());
            pstmt.setInt(2, entity.getIdMedicamento());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MedicamentoRecetadoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE medicamento_recetado SET id_receta_medica = ?, id_medicamento = ?, fecha_modifica = ? WHERE id_medicamento_recetado = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdRecetaMedica());
            pstmt.setInt(2, entity.getIdMedicamento());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdMedicamentoRecetado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE medicamento_recetado SET estado = ? WHERE id_medicamento_recetado = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM medicamento_recetado WHERE estado ='A'";
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
