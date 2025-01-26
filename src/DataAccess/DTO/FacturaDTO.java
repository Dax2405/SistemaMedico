package DataAccess.DTO;

public class FacturaDTO {
    private Integer idFactura;
    private Integer idTurno;
    private Float montoTotal;
    private String estadoPago;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public FacturaDTO() {
    }

    public FacturaDTO(Integer idFactura, Integer idTurno, Float montoTotal, String estadoPago, String estado,
            String fechaCrea, String fechaModifica) {
        this.idFactura = idFactura;
        this.idTurno = idTurno;
        this.montoTotal = montoTotal;
        this.estadoPago = estadoPago;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
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
                + "\n IdFactura: " + getIdFactura()
                + "\n IdTurno: " + getIdTurno()
                + "\n MontoTotal: " + getMontoTotal()
                + "\n EstadoPago: " + getEstadoPago()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
