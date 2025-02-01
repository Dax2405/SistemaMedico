package BusinessLogic.Entities.Usuario;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.RecetaMedica.Medicamento;
import BusinessLogic.Entities.RecetaMedica.RecetaMedica;
import BusinessLogic.Entities.Turno.HistoriaClinica;
import BusinessLogic.Entities.Turno.Turno;
import BusinessLogic.Entities.Utils.EmailUtils;
import DataAccess.DAO.MedicamentoRecetadoDAO;
import DataAccess.DAO.PacienteHistoriaClinicaDAO;
import DataAccess.DAO.RecetaMedicaDAO;
import DataAccess.DTO.MedicamentoRecetadoDTO;
import DataAccess.DTO.PacienteHistoriaClinicaDTO;
import DataAccess.DTO.RecetaMedicaDTO;

public class Medico extends Usuario {
    private Integer idMedicoEspecialidad;
    private Integer idMedicoRol;

    public Medico(Integer id, String nombre, String apellido, String telefono,
            Integer idMedicoEspecialidad, Integer idMedicoRol) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);

        this.idMedicoEspecialidad = idMedicoEspecialidad;
        this.idMedicoRol = idMedicoRol;
    }

    public void recetar(Paciente paciente, Turno turno, String indicaciones, Medicamento medicamento) throws Exception {
        BLFactory<RecetaMedicaDTO> oRecetaMedica = new BLFactory<>(RecetaMedicaDAO::new);
        RecetaMedicaDTO recetaMedicaDTO = new RecetaMedicaDTO(turno.getId(), indicaciones);
        oRecetaMedica.add(recetaMedicaDTO);
        RecetaMedica recetaMedica = new RecetaMedica(recetaMedicaDTO.getIdRecetaMedica(), indicaciones);

        BLFactory<MedicamentoRecetadoDTO> oMedicamentoRecetado = new BLFactory<>(MedicamentoRecetadoDAO::new);
        MedicamentoRecetadoDTO medicamentoRecetadoDTO = new MedicamentoRecetadoDTO(recetaMedicaDTO.getIdRecetaMedica(),
                medicamento.getId());

        oMedicamentoRecetado.add(medicamentoRecetadoDTO);

        System.out.println("Receta generada con exito");
        EmailUtils.enviarEmail(paciente.getEmail(),
                "SISTEMA MEDICO - Medicamento recetado para el turno del " + turno.getFecha(),
                "SISTEMA MEDICO \n " + paciente.getNombre() + " " + paciente.getApellido()
                        + "\nEl medicamento recetado es: " + medicamento.getNombreComercial()
                        + " o con su nombre Quimico: "
                        + medicamento.getNombreQuimico() + " con concentracion: "
                        + medicamento.getConcentracion() + " y las indicaciones son: "
                        + recetaMedica.getIndicaciones());

    }

    public void generarHistoriaClinica(Paciente paciente, Turno turno, String diagnostico, String tratamiento)
            throws Exception {
        BLFactory<PacienteHistoriaClinicaDTO> oHistoriaClinica = new BLFactory<>(PacienteHistoriaClinicaDAO::new);
        PacienteHistoriaClinicaDTO historiaClinicaDTO = new PacienteHistoriaClinicaDTO(paciente.getId(), diagnostico,
                tratamiento, this.getId());
        HistoriaClinica historiaClinica = new HistoriaClinica(diagnostico, tratamiento, this);
        oHistoriaClinica.add(historiaClinicaDTO);
        paciente.addHistoriaClinica(historiaClinica);

        System.out.println("Historia clinica generada con exito");
        EmailUtils.enviarEmail(paciente.getEmail(), "Diagnostico para el turno del " + turno.getFecha(),
                "SISTEMA MEDICO \n " + paciente.getNombre() + " " + paciente.getApellido() +
                        "\nSu diagnóstico es: " + diagnostico + " y su tratamiento es: " + tratamiento);

    }

    public Integer getIdMedicoEspecialidad() {
        return idMedicoEspecialidad;
    }

    public void setIdMedicoEspecialidad(Integer idMedicoEspecialidad) {
        this.idMedicoEspecialidad = idMedicoEspecialidad;
    }

    public Integer getIdMedicoRol() {
        return idMedicoRol;
    }

    public void setIdMedicoRol(Integer idMedicoRol) {
        this.idMedicoRol = idMedicoRol;
    }

}
