package BusinessLogic.Entities.Turno;

import BusinessLogic.Entities.Usuario.Medico;

public class HistoriaClinica {
    private Integer id;
    private String diagnostico;
    private String tratamiento;
    private Medico medico;

    public HistoriaClinica(Integer id, String diagnostico, String tratamiento, Medico medico) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medico = medico;
    }

    public HistoriaClinica(String diagnostico, String tratamiento, Medico medico) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medico = medico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
