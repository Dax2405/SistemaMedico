package DataAccess.DTO;

public class PacienteHistoriaClinicaDTO {
    private Integer idPacienteHistoriaClinica;
    private Integer idPaciente;
    private String diagnostico;
    private String tratamiento;
    private Integer idMedico;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public PacienteHistoriaClinicaDTO() {
    }

    public PacienteHistoriaClinicaDTO(Integer idPacienteHistoriaClinica, Integer idPaciente, String diagnostico,
            String tratamiento, Integer idMedico, String estado, String fechaCrea, String fechaModifica) {
        this.idPacienteHistoriaClinica = idPacienteHistoriaClinica;
        this.idPaciente = idPaciente;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.idMedico = idMedico;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdPacienteHistoriaClinica() {
        return idPacienteHistoriaClinica;
    }

    public void setIdPacienteHistoriaClinica(Integer idPacienteHistoriaClinica) {
        this.idPacienteHistoriaClinica = idPacienteHistoriaClinica;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public String toString() {
        return getClass().getName()
                + "\n IdPacienteHistoriaClinica: " + getIdPacienteHistoriaClinica()
                + "\n IdPaciente: " + getIdPaciente()
                + "\n Diagnostico: " + getDiagnostico()
                + "\n Tratamiento: " + getTratamiento()
                + "\n IdMedico: " + getIdMedico()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }

}
