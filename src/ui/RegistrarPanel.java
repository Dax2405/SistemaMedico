package ui;

import BusinessLogic.BLFactory;
import BusinessLogic.Entities.Autenticacion.Autenticacion;
import DataAccess.DAO.MedicoEspecialidadDAO;
import DataAccess.DAO.MedicoRolDAO;
import DataAccess.DTO.MedicoEspecialidadDTO;
import DataAccess.DTO.MedicoRolDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class RegistrarPanel extends JPanel {
    private JComboBox<String> tipoUsuarioComboBox;
    private JPanel camposPanel;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField codigoUnicoField;
    private JTextField telefonoField;
    private JTextField fechaNacimientoField;
    private JTextField direccionField;
    private JTextField emailField;
    private JPasswordField contraseñaField;
    private JComboBox<String> especialidadComboBox;
    private JComboBox<String> rolComboBox;
    private JButton registrarButton;
    private JLabel loadingLabel;
    private JButton regresarButton;
    private List<MedicoEspecialidadDTO> especialidades;
    private List<MedicoRolDTO> roles;

    public RegistrarPanel() {
        setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        Font font = new Font("Cambria", Font.PLAIN, 20);
        Font font2 = new Font("Arial", Font.PLAIN, 15);

        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/ui/media/SmartTurnLogo.png").getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("SmartTurn");
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel tipoUsuarioPanel = new JPanel();
        tipoUsuarioPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel tipoUsuarioLabel = new JLabel("Tipo de Usuario:");
        tipoUsuarioComboBox = new JComboBox<>(new String[] { "Paciente", "Medico" });
        tipoUsuarioPanel.add(tipoUsuarioLabel);
        tipoUsuarioPanel.add(tipoUsuarioComboBox);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(tipoUsuarioPanel);
        add(headerPanel, BorderLayout.NORTH);

        camposPanel = new JPanel();
        camposPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        camposPanel.add(nombreLabel, gbc);

        nombreField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(nombreField, gbc);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        camposPanel.add(apellidoLabel, gbc);

        apellidoField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(apellidoField, gbc);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        camposPanel.add(telefonoLabel, gbc);

        telefonoField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(telefonoField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        camposPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(emailField, gbc);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        camposPanel.add(contraseñaLabel, gbc);

        contraseñaField = new JPasswordField(20);
        gbc.gridx = 1;
        camposPanel.add(contraseñaField, gbc);

        JLabel codigoUnicoLabel = new JLabel("Código Único:");
        codigoUnicoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 5;
        camposPanel.add(codigoUnicoLabel, gbc);

        codigoUnicoField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(codigoUnicoField, gbc);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 6;
        camposPanel.add(fechaNacimientoLabel, gbc);

        fechaNacimientoField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(fechaNacimientoField, gbc);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 7;
        camposPanel.add(direccionLabel, gbc);

        direccionField = new JTextField(20);
        gbc.gridx = 1;
        camposPanel.add(direccionField, gbc);

        JLabel especialidadLabel = new JLabel("Especialidad:");
        especialidadLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 8;
        camposPanel.add(especialidadLabel, gbc);

        especialidadComboBox = new JComboBox<>();
        gbc.gridx = 1;
        camposPanel.add(especialidadComboBox, gbc);

        JLabel rolLabel = new JLabel("Rol:");
        rolLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 9;
        camposPanel.add(rolLabel, gbc);

        rolComboBox = new JComboBox<>();
        gbc.gridx = 1;
        camposPanel.add(rolComboBox, gbc);

        registrarButton = new JButton("Registrar");
        registrarButton.setFont(font2);
        registrarButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        camposPanel.add(registrarButton, gbc);
        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setVisible(false);
        gbc.gridy = 11;
        camposPanel.add(loadingLabel, gbc);

        regresarButton = new JButton("Regresar");
        regresarButton.setFont(font2);
        regresarButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        camposPanel.add(regresarButton, gbc);
        add(camposPanel, BorderLayout.CENTER);
        tipoUsuarioComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCampos();
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().showLoginScreen();
            }
        });

        actualizarCampos();
    }

    private void actualizarCampos() {
        String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();
        boolean esPaciente = tipoUsuario.equals("Paciente");

        codigoUnicoField.setVisible(esPaciente);
        fechaNacimientoField.setVisible(esPaciente);
        direccionField.setVisible(esPaciente);

        especialidadComboBox.setVisible(!esPaciente);
        rolComboBox.setVisible(!esPaciente);

        if (!esPaciente) {
            cargarEspecialidades();
            cargarRoles();
        }
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
                    JOptionPane.showMessageDialog(RegistrarPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void cargarRoles() {
        mostrarCargando();
        new SwingWorker<List<MedicoRolDTO>, Void>() {
            @Override
            protected List<MedicoRolDTO> doInBackground() throws Exception {
                BLFactory<MedicoRolDTO> oMedicoRol = new BLFactory<>(MedicoRolDAO::new);
                return oMedicoRol.getAll();
            }

            @Override
            protected void done() {
                try {
                    roles = get();
                    rolComboBox.removeAllItems();
                    for (MedicoRolDTO rol : roles) {
                        rolComboBox.addItem(rol.getNombreRol());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegistrarPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    public Boolean validarContrasena(String contraseña) throws Exception {
        String regex = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contraseña);

        return matcher.matches();
    }

    private void registrarUsuario() {
        mostrarCargando();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();
                String contraseña = new String(contraseñaField.getPassword());

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty()
                        || contraseña.isEmpty()) {
                    throw new Exception("Todos los campos son obligatorios.");
                }
                // if (validarContrasena(contraseña)) {
                // throw new Exception(
                // "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una
                // letra minúscula, un número y un caracter especial.");
                // }
                try {
                    if (tipoUsuario.equals("Paciente")) {
                        String codigoUnico = codigoUnicoField.getText();
                        String fechaNacimiento = fechaNacimientoField.getText();
                        String direccion = direccionField.getText();
                        if (codigoUnico.isEmpty() || fechaNacimiento.isEmpty() || direccion.isEmpty()) {
                            throw new Exception("Todos los campos son obligatorios.");
                        }
                        Autenticacion.registrarPaciente(nombre, apellido, codigoUnico, telefono, fechaNacimiento,
                                direccion,
                                email, contraseña);
                    } else {
                        int selectedEspecialidadIndex = especialidadComboBox.getSelectedIndex();
                        int selectedRolIndex = rolComboBox.getSelectedIndex();
                        if (selectedEspecialidadIndex >= 0 && selectedRolIndex >= 0) {
                            MedicoEspecialidadDTO especialidad = especialidades.get(selectedEspecialidadIndex);
                            MedicoRolDTO rol = roles.get(selectedRolIndex);
                            Autenticacion.registrarUsuario(nombre, apellido, telefono,
                                    especialidad.getIdMedicoEspecialidad(), rol.getIdMedicoRol(), email, contraseña);
                        }
                    }
                } catch (Exception ex) {
                    throw new Exception("Error al registrar usuario: " + ex.getMessage());
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(RegistrarPanel.this, "Usuario registrado con éxito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegistrarPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
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
