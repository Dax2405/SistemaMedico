package BusinessLogic.Entities.RecetaMedica;

public class Medicamento {
    private Integer id;

    private String nombreComercial;
    private String nombreQuimico;
    private Float concentracion;

    public Medicamento(Integer id, String nombreComercial, String nombreQuimico, Float concentracion) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreQuimico = nombreQuimico;
        this.concentracion = concentracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
