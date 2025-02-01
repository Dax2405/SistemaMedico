package ui;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Turno.Turno;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.RecetaMedica.Medicamento;
import DataAccess.DAO.MedicamentoDAO;
import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.TurnoDAO;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.MedicamentoDTO;
import DataAccess.DTO.PacienteDTO;
import DataAccess.DTO.TurnoDTO;
import DataAccess.DTO.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel de selección de paciente y turno
        JPanel seleccionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints seleccionGbc = new GridBagConstraints();
        seleccionGbc.insets = new Insets(5, 5, 5, 5);
        seleccionGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel pacienteLabel = new JLabel("Paciente:");
        seleccionGbc.gridx = 0;
        seleccionGbc.gridy = 0;
        seleccionPanel.add(pacienteLabel, seleccionGbc);

        pacienteComboBox = new JComboBox<>();
        seleccionGbc.gridx = 1;
        seleccionPanel.add(pacienteComboBox, seleccionGbc);

        JLabel turnoLabel = new JLabel("Turno:");
        seleccionGbc.gridx = 0;
        seleccionGbc.gridy = 1;
        seleccionPanel.add(turnoLabel, seleccionGbc);

        turnoComboBox = new JComboBox<>();
        seleccionGbc.gridx = 1;
        seleccionPanel.add(turnoComboBox, seleccionGbc);

        seleccionarButton = new JButton("Seleccionar");
        seleccionGbc.gridx = 0;
        seleccionGbc.gridy = 2;
        seleccionGbc.gridwidth = 2;
        seleccionPanel.add(seleccionarButton, seleccionGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(seleccionPanel, gbc);

        // Panel de creación de historia clínica
        JPanel historiaClinicaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints historiaGbc = new GridBagConstraints();
        historiaGbc.insets = new Insets(5, 5, 5, 5);
        historiaGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel diagnosticoLabel = new JLabel("Diagnóstico:");
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
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 0;
        recetaPanel.add(medicamentoLabel, recetaGbc);

        medicamentoComboBox = new JComboBox<>();
        recetaGbc.gridx = 1;
        recetaPanel.add(medicamentoComboBox, recetaGbc);

        JLabel indicacionesLabel = new JLabel("Indicaciones:");
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 1;
        recetaPanel.add(indicacionesLabel, recetaGbc);

        indicacionesField = new JTextField(20);
        recetaGbc.gridx = 1;
        recetaPanel.add(indicacionesField, recetaGbc);

        recetarButton = new JButton("Recetar");
        recetaGbc.gridx = 0;
        recetaGbc.gridy = 2;
        recetaGbc.gridwidth = 2;
        recetaPanel.add(recetarButton, recetaGbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(recetaPanel, gbc);

        // Loading label
        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(loadingLabel, gbc);

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
                BLFactory<PacienteDTO> oPaciente = new BLFactory<>(PacienteDAO::new);
                return oPaciente.getAll();
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
                    return turnoDAO.readByPacienteId(paciente.getIdPaciente());
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

            BLFactory<UsuarioDTO> oUsuario = new BLFactory<>(UsuarioDAO::new);

            try {
                UsuarioDTO usuarioDTO = oUsuario.getBy(pacienteDTO.getIdPaciente());

                pacienteSeleccionado = new Paciente(pacienteDTO.getIdPaciente(), pacienteDTO.getNombre(),
                        pacienteDTO.getApellido(), pacienteDTO.getTelefono(), pacienteDTO.getCodigoUnico(),
                        pacienteDTO.getDireccion(), pacienteDTO.getFechaNacimiento(), usuarioDTO.getEmail());
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
            try {
                System.out.println("Creando historia clínica...");
                System.out.println(
                        "Paciente: " + pacienteSeleccionado.getNombre() + " " + pacienteSeleccionado.getApellido() + " "
                                + pacienteSeleccionado.getId().toString());
                System.out.println(
                        "Medico: " + medico.getNombre() + " " + medico.getApellido() + " id " + medico.getId());
                medico.generarHistoriaClinica(pacienteSeleccionado, turnoSeleccionado, diagnostico, tratamiento);
                JOptionPane.showMessageDialog(MedicoPanel.this, "Historia clínica creada con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MedicoPanel.this, ex.getMessage());
            }
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