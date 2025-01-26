package DataAccess.DTO;

public class UsuarioDTO {
    private Integer idUsuario;
    private String email;
    private String contrasena;
    private String tipoUsuario;
    private String estado;
    private String fechaCrea;
    private String fechaModifica;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer idUsuario, String email, String contrasena, String tipoUsuario, String estado,
            String fechaCrea, String fechaModifica) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        if (!tipoUsuario.equals("medico") && !tipoUsuario.equals("paciente")) {
            throw new IllegalArgumentException("El tipo de usuario debe ser 'medico' o 'paciente'");
        }
        this.tipoUsuario = tipoUsuario;
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
                + "\n IdUsuario: " + getIdUsuario()
                + "\n Email: " + getEmail()
                + "\n Contrasena: " + getContrasena()
                + "\n TipoUsuario: " + getTipoUsuario()
                + "\n Estado: " + getEstado()
                + "\n FechaCrea: " + getFechaCrea()
                + "\n FechaModifica: " + getFechaModifica();
    }
}
