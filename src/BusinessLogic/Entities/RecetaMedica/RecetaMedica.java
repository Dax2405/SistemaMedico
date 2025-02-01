package BusinessLogic.Entities.RecetaMedica;

public class RecetaMedica {
    private Integer id;
    private String indicaciones;

    public RecetaMedica(Integer id, String indicaciones) {
        this.id = id;
        this.indicaciones = indicaciones;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
