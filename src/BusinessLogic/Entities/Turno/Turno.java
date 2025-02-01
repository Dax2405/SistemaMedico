package BusinessLogic.Entities.Turno;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import DataAccess.DAO.FacturaDAO;
import DataAccess.DAO.PagoDAO;
import DataAccess.DTO.FacturaDTO;
import DataAccess.DTO.PagoDTO;

public class Turno {
    private Integer id;
    private Paciente paciente;
    private Medico medico;
    private Integer idSala;
    private String fecha;
    private Integer idTurnoEstado;

    public Turno(Integer id, Paciente paciente, Medico medico, Integer idSala, String fecha, Integer idTurnoEstado) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.idSala = idSala;
        this.fecha = fecha;
        this.idTurnoEstado = idTurnoEstado;
    }

    public Float generarFactura() throws Exception {
        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setIdTurno(getId());
        Float montoTotal = 20 + (float) (Math.random() * ((100 - 20) + 1));
        facturaDTO.setMontoTotal(montoTotal);

        facturaDTO.setEstadoPago("Pendiente");

        BLFactory<FacturaDTO> oFactura = new BLFactory<>(FacturaDAO::new);
        oFactura.add(facturaDTO);
        System.out.println("Factura generada con exito");
        return montoTotal;
    }

    public void pagar(Integer idPagoMetodo) throws Exception {
        FacturaDAO facturaDAO = new FacturaDAO();
        FacturaDTO facturaDTO = facturaDAO.readByTurnoId(this.getId());

        PagoDTO pagoDTO = new PagoDTO(facturaDTO.getIdFactura(), idPagoMetodo);

        BLFactory<PagoDTO> oPago = new BLFactory<>(PagoDAO::new);
        oPago.add(pagoDTO);

        facturaDTO.setEstadoPago("Pagado");
        facturaDAO.update(facturaDTO);
        System.out.println("Pago realizado con exito");
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdTurnoEstado() {
        return idTurnoEstado;
    }

    public void setIdTurnoEstado(Integer idTurnoEstado) {
        this.idTurnoEstado = idTurnoEstado;
    }
}
