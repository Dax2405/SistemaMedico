package DataAccess.DTO;

public class MedicoDTO {
    private Integer idMedico;
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String idMedicoEspecialidad;
    private String idMedicoRol;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public MedicoDTO() {
    }

    public MedicoDTO(Integer idMedico, Integer idUsuario, String nombre, String apellido, String telefono,
            String idMedicoEspecialidad, String idMedicoRol, String estado, String fechaCrea, String fechaModifica) {
        this.idMedico = idMedico;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.idMedicoEspecialidad = idMedicoEspecialidad;
        this.idMedicoRol = idMedicoRol;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdMedicoEspecialidad() {
        return idMedicoEspecialidad;
    }

    public void setIdMedicoEspecialidad(String idMedicoEspecialidad) {
        this.idMedicoEspecialidad = idMedicoEspecialidad;
    }

    public String getIdMedicoRol() {
        return idMedicoRol;
    }

    public void setIdMedicoRol(String idMedicoRol) {
        this.idMedicoRol = idMedicoRol;
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
                + "\n IdMedico: " + getIdMedico()
                + "\n IdUsuario: " + getIdUsuario()
                + "\n Nombre: " + getNombre()
                + "\n Apellido: " + getApellido()
                + "\n Telefono: " + getTelefono()
                + "\n IdMedicoEspecialidad: " + getIdMedicoEspecialidad()
                + "\n IdMedicoRol: " + getIdMedicoRol()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
