package DataAccess.DTO;

public class TurnoEstadoDTO {
    private Integer idTurnoEstado;
    private String nombreEstado;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public TurnoEstadoDTO() {
    }

    public TurnoEstadoDTO(Integer idTurnoEstado, String nombreEstado, String estado, String fechaCrea,
                          String fechaModifica) {
        this.idTurnoEstado = idTurnoEstado;
        this.nombreEstado = nombreEstado;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdTurnoEstado() {
        return idTurnoEstado;
    }

    public void setIdTurnoEstado(Integer idTurnoEstado) {
        this.idTurnoEstado = idTurnoEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
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
                + "\n IdTurnoEstado: " + getIdTurnoEstado()
                + "\n NombreEstado: " + getNombreEstado()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }

}
