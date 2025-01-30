package DataAccess.DTO;

public class PagoDTO {
    private Integer idPago;
    private Integer idFactura;
    private Integer idPagoMetodo;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public PagoDTO() {
    }

    public PagoDTO(Integer idFactura, Integer idPagoMetodo) {
        this.idFactura = idFactura;
        this.idPagoMetodo = idPagoMetodo;
    }

    public PagoDTO(Integer idPago, Integer idFactura, Integer idPagoMetodo, String estado, String fechaCrea,
                   String fechaModifica) {
        this.idPago = idPago;
        this.idFactura = idFactura;
        this.idPagoMetodo = idPagoMetodo;
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

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
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
                + "\n IdPago: " + getIdPago()
                + "\n IdFactura: " + getIdFactura()
                + "\n IdPagoMetodo: " + getIdPagoMetodo()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
