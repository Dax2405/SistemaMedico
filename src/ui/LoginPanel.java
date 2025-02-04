package ui;

import BusinessLogic.Entities.Autenticacion.AutenticacionCredenciales;
import BusinessLogic.Entities.Autenticacion.AutenticacionFacial;
import BusinessLogic.Entities.Autenticacion.AutenticacionOTP;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.Usuario.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel loadingLabel;

    public LoginPanel() {
        setLayout(new BorderLayout());

        // Panel para el logo y el título
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Margen superior e inferior

        // Logo
        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/ui/media/SmartTurnLogo.png").getImage().getScaledInstance(280, 250, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título
        JLabel titleLabel = new JLabel("SmartTurn");
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 50)); // Fuente más elegante
        titleLabel.setForeground(Color.WHITE); // Título en blanco
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar logo y título al panel
        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(5)); // Espacio entre el logo y el título
        headerPanel.add(titleLabel);

        // Agregar el panel de encabezado al norte
        add(headerPanel, BorderLayout.NORTH);

        // Panel para los campos de inicio de sesión
        JPanel loginFormPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        Font font = new Font("Cambria", Font.PLAIN, 25);
        Font font2 = new Font("Arial", Font.PLAIN, 20);

        // Campo de email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginFormPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(font);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginFormPanel.add(emailField, gbc);

        // Campo de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginFormPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(font);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginFormPanel.add(passwordField, gbc);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(font2);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(0, 100, 0));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginFormPanel.add(loginButton, gbc);

        // Botón de autenticación facial
        JButton facialButton = new JButton("Autenticación Facial");
        facialButton.setFont(font2);
        facialButton.setBackground(Color.WHITE);
        facialButton.setForeground(new Color(0, 100, 0));
        gbc.gridy = 3;
        loginFormPanel.add(facialButton, gbc);

        // Botón de autenticación OTP
        JButton otpButton = new JButton("Autenticación OTP");
        otpButton.setFont(font2);
        otpButton.setBackground(Color.WHITE);
        otpButton.setForeground(new Color(0, 100, 0));
        gbc.gridy = 4;
        loginFormPanel.add(otpButton, gbc);

        // Botón de registro
        JButton registrarButton = new JButton("Registrar Usuario");
        registrarButton.setFont(font2);
        registrarButton.setBackground(Color.WHITE);
        registrarButton.setForeground(new Color(0, 100, 0));
        gbc.gridy = 5;
        loginFormPanel.add(registrarButton, gbc);

        // Etiqueta de carga
        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setVisible(false);
        gbc.gridy = 6;
        loginFormPanel.add(loadingLabel, gbc);

        // Agregar el formulario de inicio de sesión al centro
        add(loginFormPanel, BorderLayout.CENTER);

        // Listeners
        loginButton.addActionListener(this::handleLogin);
        facialButton.addActionListener(this::handleFacial);
        otpButton.addActionListener(this::handleOTP);
        registrarButton.addActionListener(e -> GUI.getInstance().showRegistrarScreen());
    }

    private void handleLogin(ActionEvent e) {
        mostrarCargando();
        new SwingWorker<Usuario, Void>() {
            @Override
            protected Usuario doInBackground() throws Exception {
                return autenticarCredenciales();
            }

            @Override
            protected void done() {
                try {
                    Usuario usuario = get();
                    manejarAutenticacion(usuario);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void handleFacial(ActionEvent e) {
        mostrarCargando();
        new SwingWorker<Usuario, Void>() {
            @Override
            protected Usuario doInBackground() throws Exception {
                return autenticarFacial();
            }

            @Override
            protected void done() {
                try {
                    Usuario usuario = get();
                    manejarAutenticacion(usuario);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginPanel.this, ex.getMessage());
                } finally {
                    ocultarCargando();
                }
            }
        }.execute();
    }

    private void handleOTP(ActionEvent e) {
        mostrarCargando();
        try {
            autenticarOTP();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(LoginPanel.this, ex.getMessage());
        }
    }

    private void mostrarCargando() {
        loadingLabel.setVisible(true);
    }

    private void ocultarCargando() {
        loadingLabel.setVisible(false);
    }

    private Usuario autenticarCredenciales() throws Exception {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        if (email.isEmpty() || password.isEmpty()) {
            throw new Exception("Por favor, ingrese su email y contraseña.");
        }
        return AutenticacionCredenciales.autenticar(email, password);
    }

    private Usuario autenticarFacial() throws Exception {
        return AutenticacionFacial.autenticar();
    }

    private void autenticarOTP() throws Exception {
        String email = emailField.getText();
        if (email.isEmpty()) {
            throw new Exception("Por favor, ingrese su email.");
        }
        if (AutenticacionOTP.autenticar(email)) {
            GUI.getInstance().showOTPPanel();
        }
    }

    private void manejarAutenticacion(Usuario usuario) {
        if (usuario instanceof Paciente) {
            GUI.getInstance().showPacienteScreen((Paciente) usuario);
        } else if (usuario instanceof Medico) {
            GUI.getInstance().showMedicoScreen((Medico) usuario);
        }
    }
}
