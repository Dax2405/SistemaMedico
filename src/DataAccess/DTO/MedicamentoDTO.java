package DataAccess.DTO;

public class MedicamentoDTO {
    private Integer idMedicamento;
    private String nombreComercial;
    private String nombreQuimico;
    private Float concentracion;
    private Integer idMedicamentoTipo;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(Integer idMedicamento, String nombreComercial, String nombreQuimico, Float concentracion,
                          Integer idMedicamentoTipo, String estado, String fechaCrea, String fechaModifica) {
        this.idMedicamento = idMedicamento;
        this.nombreComercial = nombreComercial;
        this.nombreQuimico = nombreQuimico;
        this.concentracion = concentracion;
        this.idMedicamentoTipo = idMedicamentoTipo;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreQuimico() {
        return nombreQuimico;
    }

    public void setNombreQuimico(String nombreQuimico) {
        this.nombreQuimico = nombreQuimico;
    }

    public Float getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(Float concentracion) {
        this.concentracion = concentracion;
    }

    public Integer getIdMedicamentoTipo() {
        return idMedicamentoTipo;
    }

    public void setIdMedicamentoTipo(Integer idMedicamentoTipo) {
        this.idMedicamentoTipo = idMedicamentoTipo;
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
                + "\n IdMedicamento: " + getIdMedicamento()
                + "\n NombreComercial: " + getNombreComercial()
                + "\n NombreQuimico: " + getNombreQuimico()
                + "\n Concentracion: " + getConcentracion()
                + "\n IdMedicamentoTipo: " + getIdMedicamentoTipo()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }

}
