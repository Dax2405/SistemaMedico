package DataAccess.DTO;

public class MedicoRolDTO {
    private Integer idMedicoRol;
    private String nombreRol;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public MedicoRolDTO() {
    }

    public MedicoRolDTO(Integer idMedicoRol, String nombreRol, String estado,
                        String fechaCrea,
                        String fechaModifica) {
        this.idMedicoRol = idMedicoRol;
        this.nombreRol = nombreRol;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdMedicoRol() {
        return idMedicoRol;
    }

    public void setIdMedicoRol(Integer idMedicoRol) {
        this.idMedicoRol = idMedicoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
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
                + "\n IdMedicoRol: " + getIdMedicoRol()
                + "\n NombreRol: " + getNombreRol()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();

    }

}
