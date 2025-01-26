package DataAccess.DTO;

public class TurnoDTO {
    private Integer idTurno;
    private Integer idPaciente;
    private Integer idMedico;
    private Integer idSala;
    private String fechaTurno;
    private Integer idTurnoEstado;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public TurnoDTO() {
    }

    public TurnoDTO(Integer idTurno, Integer idPaciente, Integer idMedico, Integer idSala, String fechaTurno,
            Integer idTurnoEstado, String estado, String fechaCrea, String fechaModifica) {
        this.idTurno = idTurno;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idSala = idSala;
        this.fechaTurno = fechaTurno;
        this.idTurnoEstado = idTurnoEstado;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(String fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public Integer getIdTurnoEstado() {
        return idTurnoEstado;
    }

    public void setIdTurnoEstado(Integer idTurnoEstado) {
        this.idTurnoEstado = idTurnoEstado;
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

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdTurno: " + getIdTurno()
                + "\n IdPaciente: " + getIdPaciente()
                + "\n IdMedico: " + getIdMedico()
                + "\n IdSala: " + getIdSala()
                + "\n FechaTurno: " + getFechaTurno()
                + "\n IdTurnoEstado: " + getIdTurnoEstado()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
