package BusinessLogic.Entities.Usuario;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Turno.HistoriaClinica;
import BusinessLogic.Entities.Turno.Turno;
import BusinessLogic.Entities.Utils.EmailUtils;
import DataAccess.DAO.MedicoDAO;
import DataAccess.DAO.TurnoDAO;
import DataAccess.DTO.MedicoDTO;
import DataAccess.DTO.PacienteHistoriaClinicaDTO;
import DataAccess.DTO.TurnoDTO;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario {
    private String codigoUnico;
    private String direccion;
    private String fechaNacimiento;
    private Float costo;
    private ArrayList<HistoriaClinica> historiasClinica;

    public Paciente(Integer id, String nombre, String apellido, String telefono,
            String codigoUnico, String direccion, String fechaNacimiento, String email) throws Exception {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setEmail(email);
        this.codigoUnico = codigoUnico;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.historiasClinica = new ArrayList<>();
    }

    public Paciente(Integer id, String nombre, String apellido, String telefono,
            String codigoUnico, String direccion, String fechaNacimiento,
            List<PacienteHistoriaClinicaDTO> historiasClinica, String email) throws Exception {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setEmail(email);
        this.codigoUnico = codigoUnico;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.historiasClinica = new ArrayList<>();
        for (PacienteHistoriaClinicaDTO historiaClinicaDTO : historiasClinica) {
            BLFactory<MedicoDTO> oMedico = new BLFactory<>(MedicoDAO::new);
            MedicoDTO medicoDTO = oMedico.getBy(historiaClinicaDTO.getIdMedico());
            Medico medico = new Medico(medicoDTO.getIdMedico(), medicoDTO.getNombre(), medicoDTO.getApellido(),
                    medicoDTO.getTelefono(), medicoDTO.getIdMedicoEspecialidad(), medicoDTO.getIdMedicoRol());
            this.historiasClinica.add(new HistoriaClinica(historiaClinicaDTO.getIdPacienteHistoriaClinica(),
                    historiaClinicaDTO.getDiagnostico(), historiaClinicaDTO.getTratamiento(),
                    medico));
        }

    }

    public Turno registrarTurno(Medico medico, String fecha, Integer sala) throws Exception {

        BLFactory<TurnoDTO> oTurno = new BLFactory<>(TurnoDAO::new);

        TurnoDTO turnoDTO = new TurnoDTO(this.getId(), medico.getId(), sala, fecha, 1);
        oTurno.add(turnoDTO);
        Turno turno = new Turno(turnoDTO.getIdTurno(), this, medico, sala, fecha, 1);
        setCosto(turno.generarFactura());
        EmailUtils.enviarEmail(this.getEmail(), "SmartTurn - Turno ",
                "Turno registrado con exito\n" + "Fecha: " + fecha + "\nDoctor: " + medico.getNombre() + " "
                        + medico.getApellido() + "\n"
                        + "Sala: " + sala + "\n" + "Costo: " + getCosto());

        System.out.println("Turno registrado con exito");
        return turno;
    }

    public void pagarTurno(Turno turno, Integer idPagoMetodo) throws Exception {
        turno.pagar(idPagoMetodo);
        EmailUtils.enviarEmail(this.getEmail(), "SmartTurn - Factura Pago Turno",
                "Pago realizado con exito\n" + "Fecha: " + turno.getFecha() + "\nDoctor: "
                        + turno.getMedico().getNombre() + " "
                        + turno.getMedico().getApellido() + "\n"
                        + "Sala: " + turno.getIdSala() + "\n" + "Costo: " + this.getCosto());

    }

    public ArrayList<HistoriaClinica> getHistoriasClinica() {
        return historiasClinica;
    }

    public void setHistoriasClinica(ArrayList<HistoriaClinica> historiasClinica) {
        this.historiasClinica = historiasClinica;
    }

    public void addHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiasClinica.add(historiaClinica);
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
