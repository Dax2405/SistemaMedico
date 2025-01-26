package DataAccess.DTO;

public class RecetaMedicaDTO {
    private Integer idRecetaMedica;
    private Integer idTurno;
    private String indicaciones;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public RecetaMedicaDTO() {
    }

    public RecetaMedicaDTO(Integer idRecetaMedica, Integer idTurno, String indicaciones, String estado,
            String fechaCrea,
            String fechaModifica) {
        this.idRecetaMedica = idRecetaMedica;
        this.idTurno = idTurno;
        this.indicaciones = indicaciones;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdRecetaMedica() {
        return idRecetaMedica;
    }

    public void setIdRecetaMedica(Integer idRecetaMedica) {
        this.idRecetaMedica = idRecetaMedica;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
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
                + "\n IdRecetaMedica: " + getIdRecetaMedica()
                + "\n IdTurno: " + getIdTurno()
                + "\n Indicaciones: " + getIndicaciones()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();

    }

}
