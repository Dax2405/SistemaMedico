package DataAccess.DTO;

public class MedicamentoTipoDTO {
    private Integer idMedicamentoTipo;
    private String nombreTipo;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public MedicamentoTipoDTO() {
    }

    public MedicamentoTipoDTO(Integer idMedicamentoTipo, String nombreTipo, String estado,
                              String fechaCrea,
                              String fechaModifica) {
        this.idMedicamentoTipo = idMedicamentoTipo;
        this.nombreTipo = nombreTipo;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdMedicamentoTipo() {
        return idMedicamentoTipo;
    }

    public void setIdMedicamentoTipo(Integer idMedicamentoTipo) {
        this.idMedicamentoTipo = idMedicamentoTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
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
                + "\n IdMedicamentoTipo: " + getIdMedicamentoTipo()
                + "\n NombreTipo: " + getNombreTipo()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
