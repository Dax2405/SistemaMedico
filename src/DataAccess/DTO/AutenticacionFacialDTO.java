package DataAccess.DTO;

public class AutenticacionFacialDTO {
    private Integer idAutenticacion;
    private Integer idUsuario;
    private byte[] encodingFacial;
    private String fechaCrea;

    public AutenticacionFacialDTO() {
    }

    public AutenticacionFacialDTO(Integer idAutenticacion, Integer idUsuario, byte[] encodingFacial,
            String fechaCrea) {
        this.idAutenticacion = idAutenticacion;
        this.idUsuario = idUsuario;
        this.encodingFacial = encodingFacial;
        this.fechaCrea = fechaCrea;
    }

    public Integer getIdAutenticacion() {
        return idAutenticacion;
    }

    public void setIdAutenticacion(Integer idAutenticacion) {
        this.idAutenticacion = idAutenticacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public byte[] getEncodingFacial() {
        return encodingFacial;
    }

    public void setEncodingFacial(byte[] encodingFacial) {
        this.encodingFacial = encodingFacial;
    }

    public String getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdAutenticacion: " + getIdAutenticacion()
                + "\n IdUsuario: " + getIdUsuario()
                + "\n encodingFacial: " + getEncodingFacial()
                + "\n FechaCrea: " + getFechaCrea();
    }

}
