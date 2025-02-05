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
import Framework.PoliSaludException;
import ui.utils.DateLabelFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;

public class RegistrarTurnoPanel extends JPanel {
    private Paciente paciente;
    private JComboBox<String> especialidadComboBox;
    private JComboBox<String> medicoComboBox;
    private JDatePickerImpl datePicker;
    private JSpinner timeSpinner;
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
        setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        Font font = new Font("Cambria", Font.PLAIN, 20);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setOpaque(false);
        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/ui/media/SmartTurnLogo.png").getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);

        JLabel titleLabel = new JLabel("SmartTurn");
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);
        logoPanel.add(titleLabel);
        headerPanel.add(logoPanel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloLabel = new JLabel("Registrar Turno");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 70));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        formPanel.add(tituloLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel especialidadLabel = new JLabel("Especialidad:");
        especialidadLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(especialidadLabel, gbc);

        especialidadComboBox = new JComboBox<>();
        especialidadComboBox.setFont(new Font("Cambria", Font.PLAIN, 14));
        gbc.gridx = 1;
        formPanel.add(especialidadComboBox, gbc);

        JLabel medicoLabel = new JLabel("Médico:");
        medicoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(medicoLabel, gbc);

        medicoComboBox = new JComboBox<>();
        medicoComboBox.setFont(new Font("Cambria", Font.PLAIN, 14));
        gbc.gridx = 1;
        formPanel.add(medicoComboBox, gbc);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(fechaLabel, gbc);

        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setFont(new Font("Cambria", Font.PLAIN, 14));

        gbc.gridx = 1;
        formPanel.add(datePicker, gbc);

        JLabel horaLabel = new JLabel("Hora:");
        horaLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(horaLabel, gbc);

        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setFont(new Font("Cambria", Font.PLAIN, 14));
        timeSpinner.setValue(new Date());
        gbc.gridx = 1;
        formPanel.add(timeSpinner, gbc);

        generarTurnoButton = new JButton("Generar Turno");
        generarTurnoButton.setFont(new Font("Arial", Font.BOLD, 14));
        generarTurnoButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(generarTurnoButton, gbc);

        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
        loadingLabel.setVisible(false);
        gbc.gridy = 6;
        formPanel.add(loadingLabel, gbc);

        metodoPagoLabel = new JLabel("Método de Pago:");
        metodoPagoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(metodoPagoLabel, gbc);

        metodoPagoComboBox = new JComboBox<>();
        metodoPagoComboBox.setFont(new Font("Cambria", Font.PLAIN, 14));
        gbc.gridx = 1;
        formPanel.add(metodoPagoComboBox, gbc);

        pagarButton = new JButton("Pagar");
        pagarButton.setFont(new Font("Cambria", Font.BOLD, 20));
        pagarButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        formPanel.add(pagarButton, gbc);

        metodoPagoLabel.setVisible(false);
        metodoPagoComboBox.setVisible(false);
        pagarButton.setVisible(false);

        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Arial", Font.PLAIN, 15));
        regresarButton.setForeground(Color.WHITE); // Texto verde
        GridBagConstraints gbcRegresar = new GridBagConstraints();
        gbcRegresar.gridx = 0;
        gbcRegresar.gridy = 9;
        gbcRegresar.anchor = GridBagConstraints.NORTHWEST;
        gbcRegresar.insets = new Insets(10, 10, 10, 10);
        formPanel.add(regresarButton, gbcRegresar);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().showPacienteScreen(paciente);
            }
        });

        add(formPanel, BorderLayout.CENTER);

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
                    Date time = (Date) timeSpinner.getValue();

                    if (fecha == null) {
                        JOptionPane.showMessageDialog(RegistrarTurnoPanel.this, "Seleccione una fecha.");
                        return null;
                    }
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);
                        Calendar timeCalendar = Calendar.getInstance();
                        timeCalendar.setTime(time);
                        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
                        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));

                        java.sql.Timestamp fechaHora = new java.sql.Timestamp(calendar.getTimeInMillis());

                        LocalDateTime currentDateTime = LocalDateTime.now();
                        LocalDateTime selectedDateTime = fechaHora.toLocalDateTime();
                        if (selectedDateTime.isBefore(currentDateTime)) {
                            JOptionPane.showMessageDialog(RegistrarTurnoPanel.this,
                                    "No se puede seleccionar una fecha y hora pasadas.");
                            return null;
                        }
                        turno = paciente.registrarTurno(medico, fechaHora.toString(), sala);

                        JOptionPane.showMessageDialog(RegistrarTurnoPanel.this,
                                "Turno generado con éxito, se ha enviado un correo con los detalles del turno.");
                        cargarMetodosPago();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(RegistrarTurnoPanel.this, e.getMessage());
                    }
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
