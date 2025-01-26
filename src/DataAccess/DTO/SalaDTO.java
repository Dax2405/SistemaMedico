package DataAccess.DTO;

public class SalaDTO {
    private Integer idSala;
    private Integer numeroSala;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public SalaDTO() {
    }

    public SalaDTO(Integer idSala, Integer numeroSala, String estado, String fechaCrea, String fechaModifica) {
        this.idSala = idSala;
        this.numeroSala = numeroSala;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
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
                + "\n IdSala: " + getIdSala()
                + "\n NumeroSala: " + getNumeroSala()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }

}
