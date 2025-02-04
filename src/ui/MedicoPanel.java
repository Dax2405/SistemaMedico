package ui;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.RecetaMedica.Medicamento;
import BusinessLogic.Entities.Turno.Turno;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import DataAccess.DAO.MedicamentoDAO;
import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.TurnoDAO;
import DataAccess.DTO.MedicamentoDTO;
import DataAccess.DTO.PacienteDTO;
import DataAccess.DTO.TurnoDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class MedicoPanel extends JPanel {
    private Medico medico;
    private JComboBox<String> pacienteComboBox;
    private JComboBox<String> turnoComboBox;
    private JComboBox<String> medicamentoComboBox;
    private JTextField diagnosticoField;
    private JTextField tratamientoField;
    private JTextField indicacionesField;
    private JButton seleccionarButton;
    private JButton crearHistoriaClinicaButton;
    private JButton recetarButton;
    private JLabel loadingLabel;
    private List<PacienteDTO> pacientes;
    private List<TurnoDTO> turnos;
    private List<MedicamentoDTO> medicamentos;
    private Paciente pacienteSeleccionado;
    private Turno turnoSeleccionado;

    public MedicoPanel(Medico medico) {
        this.medico = medico;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        // Cargar el logo y escalarlo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/ui/media/SmartTurnLogo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH); // Escalar a 200px de ancho
        logoIcon = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(logoIcon);
    
        // Configurar GridBagConstraints para el logo
        gbc.gridx = 0;
        gbc.gridy = 0; // Fila 0 para el logo
        gbc.gridwidth = 3; // Ocupa 3 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el logo
        add(logoLabel, gbc);

        // Panel de selección de paciente y turno
        JPanel seleccionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints seleccionGbc = new GridBagConstraints();
        seleccionGbc.insets = new Insets(5, 5, 5, 5);
        seleccionGbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel pacienteLabel = new JLabel("Paciente:");
        pacienteLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        seleccionGbc.gridx = 0;
        seleccionGbc.gridy = 0;
        seleccionPanel.add(pacienteLabel, seleccionGbc);
        pacienteComboBox = new JComboBox<>();
        seleccionGbc.gridx = 1;
        seleccionPanel.add(pacienteComboBox, seleccionGbc);
        
        JLabel turnoLabel = new JLabel("Turno:");
        turnoLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        seleccionGbc.gridx = 0;
        seleccionGbc.gridy = 1;
        seleccionPanel.add(turnoLabel, seleccionGbc);
        turnoComboBox = new JComboBox<>();
        seleccionGbc.gridx = 1;
        seleccionPanel.add(turnoComboBox, seleccionGbc);
        seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        seleccionarButton.setBackground(Color.WHITE);
        seleccionarButton.setForeground(new Color(0, 128, 0)); // Letras verdes
        seleccionGbc.gridx = 0;
        seleccionGbc.gridy = 2;
        seleccionGbc.gridwidth = 2;
        seleccionPanel.add(seleccionarButton, seleccionGbc);
        gbc.gridx = 0;
        gbc.gridy = 2; // Fila 2 para el panel de selección (debajo del título)
        gbc.gridwidth = 1; // Restablecer gridwidth
        add(seleccionPanel, gbc);

        // Panel de creación de historia clínica
        JPanel historiaClinicaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints historiaGbc = new GridBagConstraints();
        historiaGbc.insets = new Insets(5, 5, 5, 5);
        historiaGbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel diagnosticoLabel = new JLabel("Diagnóstico:");
        diagnosticoLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        historiaGbc.gridx = 0;
        historiaGbc.gridy = 0;
        historiaClinicaPanel.add(diagnosticoLabel, historiaGbc);
        diagnosticoField = new JTextField(20);
        historiaGbc.gridx = 1;
        historiaClinicaPanel.add(diagnosticoField, historiaGbc);

        JLabel tratamientoLabel = new JLabel("Tratamiento:");
        historiaGbc.gridx = 0;
        historiaGbc.gridy = 1;
        historiaClinicaPanel.add(tratamientoLabel, historiaGbc);

        tratamientoField = new JTextField(20);
        historiaGbc.gridx = 1;
        historiaClinicaPanel.add(tratamientoField, historiaGbc);

        crearHistoriaClinicaButton = new JButton("Crear Historia Clínica");
        historiaGbc.gridx = 0;
        historiaGbc.gridy = 2;
        historiaGbc.gridwidth = 2;
        historiaClinicaPanel.add(crearHistoriaClinicaButton, historiaGbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(historiaClinicaPanel, gbc);

       // Panel de receta de medicamentos
        JPanel recetaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints recetaGbc = new GridBagConstraints();
        recetaGbc.insets = new Insets(5, 5, 5, 5);
        recetaGbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel medicamentoLabel = new JLabel("Medicamento:");
        medicamentoLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 0;
        recetaPanel.add(medicamentoLabel, recetaGbc);
    
        medicamentoComboBox = new JComboBox<>();
        recetaGbc.gridx = 1;
        recetaPanel.add(medicamentoComboBox, recetaGbc);

         JLabel indicacionesLabel = new JLabel("Indicaciones:");
        indicacionesLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 1;
        recetaPanel.add(indicacionesLabel, recetaGbc);
    
        indicacionesField = new JTextField(20);
        recetaGbc.gridx = 1;
        recetaPanel.add(indicacionesField, recetaGbc);
        recetarButton = new JButton("Recetar");
        recetarButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        recetarButton.setBackground(Color.WHITE);
        recetarButton.setForeground(new Color(0, 128, 0)); // Letras verdes
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 2;
        recetaGbc.gridwidth = 2;
        recetaPanel.add(recetarButton, recetaGbc);
    
        gbc.gridx = 2;
        gbc.gridy = 2; // Fila 2 para el panel de receta (debajo del título)
        add(recetaPanel, gbc);
        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 3; // Fila 3 para el loading label
        add(loadingLabel, gbc);
    
        recetarButton = new JButton("Recetar");
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 2;
        recetaGbc.gridwidth = 2;
        recetaPanel.add(recetarButton, recetaGbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(recetaPanel, gbc);
        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(loadingLabel, gbc);

                // Botón de regresar
        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        regresarButton.setBackground(Color.WHITE);
        regresarButton.setForeground(new Color(0, 128, 0)); // Letras verdes
        regresarButton.setPreferredSize(seleccionarButton.getPreferredSize()); // Igualar el tamaño al botón "Seleccionar"

        GridBagConstraints gbcRegresar = new GridBagConstraints();
        gbcRegresar.gridx = 0;
        gbcRegresar.gridy = 0;
        gbcRegresar.anchor = GridBagConstraints.NORTHWEST;
        gbcRegresar.insets = new Insets(10, 10, 10, 10);
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(regresarButton);

        gbc.gridx = 0;
        gbc.gridy = 4; // Fila 4 para el botón de regresar
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(panelBoton, gbc);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().showLoginScreen();
            }
        });
        
        cargarPacientes();
        cargarMedicamentos();

        pacienteComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTurnos();
            }
        });

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarPacienteYTurno();
            }
        });

        crearHistoriaClinicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearHistoriaClinica();
            }
        });

        recetarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recetarMedicamento();
            }
        });
    }
    private void cargarPacientes() {
        mostrarCargando();
        new SwingWorker<List<PacienteDTO>, Void>() {
            @Override
            protected List<PacienteDTO> doInBackground() throws Exception {
                PacienteDAO oPaciente = new PacienteDAO();

                return oPaciente.readPacientesByMedicoId(medico.getId());
            }

            @Override
            protected void done() {
                try {
                    pacientes = get();
                    pacienteComboBox.removeAllItems();
                    for (PacienteDTO paciente : pacientes) {
                        pacienteComboBox.addItem(paciente.getNombre() + " " + paciente.getApellido());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void cargarTurnos() {
        mostrarCargando();
        new SwingWorker<List<TurnoDTO>, Void>() {
            @Override
            protected List<TurnoDTO> doInBackground() throws Exception {
                int selectedIndex = pacienteComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    PacienteDTO paciente = pacientes.get(selectedIndex);
                    TurnoDAO turnoDAO = new TurnoDAO();
                    return turnoDAO.readByPacienteAndMedicoId(paciente.getIdPaciente(), medico.getId());
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    turnos = get();
                    turnoComboBox.removeAllItems();
                    if (turnos != null) {
                        for (TurnoDTO turno : turnos) {
                            turnoComboBox.addItem(turno.getFechaTurno());
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void seleccionarPacienteYTurno() {
        int selectedPacienteIndex = pacienteComboBox.getSelectedIndex();
        int selectedTurnoIndex = turnoComboBox.getSelectedIndex();
        if (selectedPacienteIndex >= 0 && selectedTurnoIndex >= 0) {
            PacienteDTO pacienteDTO = pacientes.get(selectedPacienteIndex);
            TurnoDTO turnoDTO = turnos.get(selectedTurnoIndex);

            try {
                PacienteDAO pacienteDAO = new PacienteDAO();

                pacienteSeleccionado = new Paciente(pacienteDTO.getIdPaciente(), pacienteDTO.getNombre(),
                        pacienteDTO.getApellido(), pacienteDTO.getTelefono(), pacienteDTO.getCodigoUnico(),
                        pacienteDTO.getDireccion(), pacienteDTO.getFechaNacimiento(),
                        pacienteDAO.readEmail(pacienteDTO.getIdPaciente()));
                turnoSeleccionado = new Turno(turnoDTO.getIdTurno(), pacienteSeleccionado, medico,
                        turnoDTO.getIdSala(), turnoDTO.getFechaTurno(), turnoDTO.getIdTurnoEstado());

                JOptionPane.showMessageDialog(MedicoPanel.this, "Paciente y turno seleccionados con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
            }
        }
    }

    private void crearHistoriaClinica() {
        String diagnostico = diagnosticoField.getText();
        String tratamiento = tratamientoField.getText();

        if (pacienteSeleccionado != null && turnoSeleccionado != null) {
            if (diagnostico.isEmpty() || tratamiento.isEmpty()) {
                JOptionPane.showMessageDialog(MedicoPanel.this,
                        "Debe completar los campos de diagnóstico y tratamiento.");
                return;
            }
            try {
                System.out.println("Creando historia clínica...");
                System.out.println(
                        "Paciente: " + pacienteSeleccionado.getNombre() + " " + pacienteSeleccionado.getApellido() + " "
                                + pacienteSeleccionado.getId().toString() + "" + pacienteSeleccionado.getEmail());
                System.out.println(
                        "Medico: " + medico.getNombre() + " " + medico.getApellido() + " id " + medico.getId());
                medico.generarHistoriaClinica(pacienteSeleccionado, turnoSeleccionado, diagnostico, tratamiento);
                JOptionPane.showMessageDialog(MedicoPanel.this, "Historia clínica creada con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(MedicoPanel.this, "Debe seleccionar un turno y un paciente.");
        }
    }

    private void cargarMedicamentos() {
        mostrarCargando();
        new SwingWorker<List<MedicamentoDTO>, Void>() {
            @Override
            protected List<MedicamentoDTO> doInBackground() throws Exception {
                BLFactory<MedicamentoDTO> oMedicamento = new BLFactory<>(MedicamentoDAO::new);
                return oMedicamento.getAll();
            }

            @Override
            protected void done() {
                try {
                    medicamentos = get();
                    medicamentoComboBox.removeAllItems();
                    for (MedicamentoDTO medicamento : medicamentos) {
                        medicamentoComboBox
                                .addItem(medicamento.getNombreComercial() + "-" + medicamento.getConcentracion());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void recetarMedicamento() {
        String indicaciones = indicacionesField.getText();
        int selectedMedicamentoIndex = medicamentoComboBox.getSelectedIndex();
        if (pacienteSeleccionado != null && turnoSeleccionado != null && selectedMedicamentoIndex >= 0) {
            if (indicaciones.isEmpty()) {
                JOptionPane.showMessageDialog(MedicoPanel.this, "Debe completar el campo de indicaciones.");
                return;
            }
            MedicamentoDTO medicamentoDTO = medicamentos.get(selectedMedicamentoIndex);
            Medicamento medicamento = new Medicamento(medicamentoDTO.getIdMedicamento(),
                    medicamentoDTO.getNombreComercial(),
                    medicamentoDTO.getNombreQuimico(), medicamentoDTO.getConcentracion());
            try {
                medico.recetar(pacienteSeleccionado, turnoSeleccionado, indicaciones, medicamento);
                JOptionPane.showMessageDialog(MedicoPanel.this, "Medicamento recetado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(MedicoPanel.this, "Debe seleccionar un turno y un paciente.");
        }
    }

    private void mostrarCargando() {
        loadingLabel.setVisible(true);
    }

    private void ocultarCargando() {
        loadingLabel.setVisible(false);
    }
}
