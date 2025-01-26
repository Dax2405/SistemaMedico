package DataAccess.DTO;

public class MedicamentoRecetadoDTO {
    private Integer idMedicamentoRecetado;
    private Integer idRecetaMedica;
    private Integer idMedicamento;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public MedicamentoRecetadoDTO() {
    }

    public MedicamentoRecetadoDTO(Integer idMedicamentoRecetado, Integer idRecetaMedica, Integer idMedicamento,
            String estado,
            String fechaCrea,
            String fechaModifica) {
        this.idMedicamentoRecetado = idMedicamentoRecetado;
        this.idRecetaMedica = idRecetaMedica;
        this.idMedicamento = idMedicamento;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdMedicamentoRecetado() {
        return idMedicamentoRecetado;
    }

    public void setIdMedicamentoRecetado(Integer idMedicamentoRecetado) {
        this.idMedicamentoRecetado = idMedicamentoRecetado;
    }

    public Integer getIdRecetaMedica() {
        return idRecetaMedica;
    }

    public void setIdRecetaMedica(Integer idRecetaMedica) {
        this.idRecetaMedica = idRecetaMedica;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
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
                + "\n IdMedicamentoRecetado: " + getIdMedicamentoRecetado()
                + "\n IdRecetaMedica: " + getIdRecetaMedica()
                + "\n IdMedicamento: " + getIdMedicamento()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
