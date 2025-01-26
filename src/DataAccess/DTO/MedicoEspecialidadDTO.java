package DataAccess.DTO;

public class MedicoEspecialidadDTO {
    private Integer idMedicoEspecialidad;
    private String nombreEspecialidad;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public MedicoEspecialidadDTO() {
    }

    public MedicoEspecialidadDTO(Integer idMedicoEspecialidad, String nombreEspecialidad, String estado,
            String fechaCrea,
            String fechaModifica) {
        this.idMedicoEspecialidad = idMedicoEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdMedicoEspecialidad() {
        return idMedicoEspecialidad;
    }

    public void setIdMedicoEspecialidad(Integer idMedicoEspecialidad) {
        this.idMedicoEspecialidad = idMedicoEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
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
                + "\n IdMedicoEspecialidad: " + getIdMedicoEspecialidad()
                + "\n NombreEspecialidad: " + getNombreEspecialidad()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();

    }
}
