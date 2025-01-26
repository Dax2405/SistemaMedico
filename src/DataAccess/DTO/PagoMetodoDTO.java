package DataAccess.DTO;

public class PagoMetodoDTO {
    private Integer idPagoMetodo;
    private String nombreMetodo;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public PagoMetodoDTO() {
    }

    public PagoMetodoDTO(Integer idPagoMetodo, String nombreMetodo, String estado, String fechaCrea,
            String fechaModifica) {
        this.idPagoMetodo = idPagoMetodo;
        this.nombreMetodo = nombreMetodo;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdPagoMetodo() {
        return idPagoMetodo;
    }

    public void setIdPagoMetodo(Integer idPagoMetodo) {
        this.idPagoMetodo = idPagoMetodo;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
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
                + "\n IdPagoMetodo: " + getIdPagoMetodo()
                + "\n NombreMetodo: " + getNombreMetodo()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();

    }
}
