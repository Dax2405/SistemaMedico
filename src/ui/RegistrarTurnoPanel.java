package ui;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Turno.Turno;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import DataAccess.DAO.MedicoDAO;
import DataAccess.DAO.MedicoEspecialidadDAO;
import DataAccess.DAO.PagoMetodoDAO;
import DataAccess.DTO.MedicoDTO;
import DataAccess.DTO.MedicoEspecialidadDTO;
import DataAccess.DTO.PagoMetodoDTO;
import ui.utils.DateLabelFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class RegistrarTurnoPanel extends JPanel {
    private Paciente paciente;
    private JComboBox<String> especialidadComboBox;
    private JComboBox<String> medicoComboBox;
    private JDatePickerImpl datePicker;
    private JButton generarTurnoButton;
    private JLabel loadingLabel;
    private List<MedicoEspecialidadDTO> especialidades;
    private List<MedicoDTO> medicos;
    private Turno turno;

    private JLabel metodoPagoLabel;
    private JComboBox<String> metodoPagoComboBox;
    private JButton pagarButton;
    private List<PagoMetodoDTO> metodosPago;

    public RegistrarTurnoPanel(Paciente paciente) {
        this.paciente = paciente;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar los márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel tituloLabel = new JLabel("Registrar Turno");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0); // Aumentar los márgenes
        add(tituloLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10); // Restablecer los márgenes

        JLabel especialidadLabel = new JLabel("Especialidad:");
        especialidadLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(especialidadLabel, gbc);

        especialidadComboBox = new JComboBox<>();
        especialidadComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 1;
        add(especialidadComboBox, gbc);

        JLabel medicoLabel = new JLabel("Médico:");
        medicoLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(medicoLabel, gbc);

        medicoComboBox = new JComboBox<>();
        medicoComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 1;
        add(medicoComboBox, gbc);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(fechaLabel, gbc);

        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        
        gbc.gridx = 1;
        add(datePicker, gbc);

        generarTurnoButton = new JButton("Generar Turno");
        generarTurnoButton.setFont(new Font("Arial", Font.BOLD, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(generarTurnoButton, gbc);

        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        loadingLabel.setVisible(false);
        gbc.gridy = 5;
        add(loadingLabel, gbc);

        metodoPagoLabel = new JLabel("Método de Pago:");
        metodoPagoLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(metodoPagoLabel, gbc);

        metodoPagoComboBox = new JComboBox<>();
        metodoPagoComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 1;
        add(metodoPagoComboBox, gbc);

        pagarButton = new JButton("Pagar");
        pagarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Aumentar el tamaño de la fuente
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(pagarButton, gbc);

        // Inicialmente ocultar el selector de método de pago y el botón de pagar
        metodoPagoLabel.setVisible(false);
        metodoPagoComboBox.setVisible(false);
        pagarButton.setVisible(false);

        cargarEspecialidades();

        especialidadComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarMedicos();
            }
        });

        generarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarTurno();
            }
        });

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagarTurno();
            }
        });
    }

    private void cargarEspecialidades() {
        mostrarCargando();
        new SwingWorker<List<MedicoEspecialidadDTO>, Void>() {
            @Override
            protected List<MedicoEspecialidadDTO> doInBackground() throws Exception {
                BLFactory<MedicoEspecialidadDTO> oMedicoEspecialidad = new BLFactory<>(MedicoEspecialidadDAO::new);
                return oMedicoEspecialidad.getAll();
            }

            @Override
            protected void done() {
                try {
                    especialidades = get();
                    especialidadComboBox.removeAllItems();
                    for (MedicoEspecialidadDTO especialidad : especialidades) {
                        especialidadComboBox.addItem(especialidad.getNombreEspecialidad());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegistrarTurnoPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void cargarMedicos() {
        mostrarCargando();
        new SwingWorker<List<MedicoDTO>, Void>() {
            @Override
            protected List<MedicoDTO> doInBackground() throws Exception {
                int selectedIndex = especialidadComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    MedicoEspecialidadDTO especialidad = especialidades.get(selectedIndex);
                    MedicoDAO medicoDAO = new MedicoDAO();
                    return medicoDAO.readAllByEspecialidadId(especialidad.getIdMedicoEspecialidad());
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    medicos = get();
                    medicoComboBox.removeAllItems();
                    if (medicos != null) {
                        for (MedicoDTO medico : medicos) {
                            medicoComboBox.addItem(medico.getNombre() + " " + medico.getApellido());
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegistrarTurnoPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void generarTurno() {
        mostrarCargando();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                int selectedMedicoIndex = medicoComboBox.getSelectedIndex();
                if (selectedMedicoIndex >= 0) {
                    MedicoDTO medicoDTO = medicos.get(selectedMedicoIndex);
                    Medico medico = new Medico(medicoDTO.getIdMedico(), medicoDTO.getNombre(),
                            medicoDTO.getApellido(), medicoDTO.getTelefono(),
                            medicoDTO.getIdMedicoEspecialidad(), medicoDTO.getIdMedicoRol());

                    Random random = new Random();
                    int sala = random.nextInt(3) + 1;
                    java.sql.Date fecha = (java.sql.Date) datePicker.getModel().getValue();

                    turno = paciente.registrarTurno(medico, fecha.toString(), sala);

                    JOptionPane.showMessageDialog(RegistrarTurnoPanel.this,
                            "Turno generado con éxito, se ha enviado un correo con los detalles del turno.");
                    cargarMetodosPago();
                }
                return null;
            }

            @Override
            protected void done() {
                ocultarCargando();
            }
        }.execute();
    }

    private void cargarMetodosPago() {
        mostrarCargando();
        new SwingWorker<List<PagoMetodoDTO>, Void>() {
            @Override
            protected List<PagoMetodoDTO> doInBackground() throws Exception {
                BLFactory<PagoMetodoDTO> oPagoMetodo = new BLFactory<>(PagoMetodoDAO::new);
                return oPagoMetodo.getAll();
            }

            @Override
            protected void done() {
                try {
                    metodosPago = get();
                    metodoPagoComboBox.removeAllItems();
                    for (PagoMetodoDTO metodo : metodosPago) {
                        metodoPagoComboBox.addItem(metodo.getNombreMetodo());
                    }
                    // Mostrar el selector de método de pago y el botón de pagar
                    metodoPagoLabel.setVisible(true);
                    metodoPagoComboBox.setVisible(true);
                    pagarButton.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegistrarTurnoPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void pagarTurno() {
        mostrarCargando();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                int selectedMetodoPagoIndex = metodoPagoComboBox.getSelectedIndex();
                if (selectedMetodoPagoIndex >= 0) {
                    PagoMetodoDTO metodoPagoDTO = metodosPago.get(selectedMetodoPagoIndex);
                    paciente.pagarTurno(turno, metodoPagoDTO.getIdPagoMetodo());
                    JOptionPane.showMessageDialog(RegistrarTurnoPanel.this,
                            "Pago realizado con éxito.");
                }
                return null;
            }

            @Override
            protected void done() {
                ocultarCargando();
            }
        }.execute();
    }

    private void mostrarCargando() {
        loadingLabel.setVisible(true);
    }

    private void ocultarCargando() {
        loadingLabel.setVisible(false);
    }
}