package DataAccess.DAO;

import DataAccess.DTO.PacienteHistoriaClinicaDTO;
import DataAccess.MySQLDataHelper;
import Framework.PoliSaludException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PacienteHistoriaClinicaDAO extends MySQLDataHelper implements IDAO<PacienteHistoriaClinicaDTO> {
    @Override
    public PacienteHistoriaClinicaDTO readBy(Integer id) throws Exception {
        PacienteHistoriaClinicaDTO oS = new PacienteHistoriaClinicaDTO();
        String query = " SELECT "
                + "  id_paciente_historia_clinica "
                + " ,id_paciente "
                + " ,diagnostico "
                + " ,tratamiento "
                + " ,id_medico "
                + " ,estado "
                + " ,fecha_crea "
                + " ,fecha_modifica "
                + " FROM paciente_historia_clinica "
                + " WHERE estado='A' AND id_paciente_historia_clinica = " + id.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new PacienteHistoriaClinicaDTO(
                        rs.getInt(1), // id_paciente_historia_clinica
                        rs.getInt(2), // id_paciente
                        rs.getString(3), // diagnostico
                        rs.getString(4), // tratamiento
                        rs.getInt(5), // id_medico
                        rs.getString(6), // estado
                        rs.getString(7), // fecha_crea
                        rs.getString(8) // fecha_modifica
                );
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<PacienteHistoriaClinicaDTO> readAll() throws Exception {
        List<PacienteHistoriaClinicaDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_paciente_historia_clinica "
                + " ,id_paciente "
                + " ,diagnostico "
                + " ,tratamiento "
                + " ,id_medico "
                + " ,estado "
                + " ,fecha_crea "
                + " ,fecha_modifica "
                + " FROM paciente_historia_clinica "
                + " WHERE estado='A'";

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la
            while (rs.next()) {
                PacienteHistoriaClinicaDTO s = new PacienteHistoriaClinicaDTO(
                        rs.getInt(1), // id_paciente_historia_clinica
                        rs.getInt(2), // id_paciente
                        rs.getString(3), // diagnostico
                        rs.getString(4), // tratamiento
                        rs.getInt(5), // id_medico
                        rs.getString(6), // estado
                        rs.getString(7), // fecha_crea
                        rs.getString(8) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(PacienteHistoriaClinicaDTO entity) throws Exception {
        String query = " INSERT INTO paciente_historia_clinica ("
                + "id_paciente, "
                + "diagnostico, "
                + "tratamiento, "
                + "id_medico"
                + ") VALUES (?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdPaciente());
            pstmt.setString(2, entity.getDiagnostico());
            pstmt.setString(3, entity.getTratamiento());
            pstmt.setInt(4, entity.getIdMedico());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(PacienteHistoriaClinicaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE paciente_historia_clinica SET id_paciente = ?, diagnostico = ?, tratamiento = ?, id_medico = ?, fecha_modifica = ? WHERE id_paciente_historia_clinica = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdPaciente());
            pstmt.setString(2, entity.getDiagnostico());
            pstmt.setString(3, entity.getTratamiento());
            pstmt.setInt(4, entity.getIdMedico());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.setInt(6, entity.getIdPacienteHistoriaClinica());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE paciente_historia_clinica SET estado = ? WHERE id_paciente_historia_clinica = ?";
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
        String query = " SELECT COUNT(*) TotalReg FROM paciente_historia_clinica WHERE estado ='A'";
        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la
            while (rs.next()) {
                return rs.getInt(1); // TotalReg
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }

    public List<PacienteHistoriaClinicaDTO> readAllByPacienteId(Integer idPaciente) throws Exception {
        List<PacienteHistoriaClinicaDTO> lst = new ArrayList<>();
        String query = " SELECT "
                + "  id_paciente_historia_clinica "
                + " ,id_paciente "
                + " ,diagnostico "
                + " ,tratamiento "
                + " ,id_medico "
                + " ,estado "
                + " ,fecha_crea "
                + " ,fecha_modifica "
                + " FROM paciente_historia_clinica "
                + " WHERE estado='A'"
                + " AND id_paciente = " + idPaciente.toString();

        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la
            while (rs.next()) {
                PacienteHistoriaClinicaDTO s = new PacienteHistoriaClinicaDTO(
                        rs.getInt(1), // id_paciente_historia_clinica
                        rs.getInt(2), // id_paciente
                        rs.getString(3), // diagnostico
                        rs.getString(4), // tratamiento
                        rs.getInt(5), // id_medico
                        rs.getString(6), // estado
                        rs.getString(7), // fecha_crea
                        rs.getString(8) // fecha_modifica
                );
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PoliSaludException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

}
