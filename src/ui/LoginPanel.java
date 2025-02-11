package ui;

import BusinessLogic.Entities.Autenticacion.AutenticacionCredenciales;
import BusinessLogic.Entities.Autenticacion.AutenticacionFacial;
import BusinessLogic.Entities.Autenticacion.AutenticacionOTP;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.Usuario.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel loadingLabel;

    public LoginPanel() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/ui/media/SmartTurnLogo.png").getImage()
                .getScaledInstance(280, 250, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("SmartTurn");
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel loginFormPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        Font font = new Font("Cambria", Font.PLAIN, 17);
        Font font2 = new Font("Arial", Font.PLAIN, 15);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        emailLabel.setFont(font2);
        loginFormPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginFormPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        passwordLabel.setFont(font2);
        loginFormPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginFormPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.setFont(font);
        loginButton.setForeground(Color.WHITE);
        loginFormPanel.add(loginButton, gbc);

        JButton facialButton = new JButton("Autenticación Facial");
        facialButton.setFont(font);
        facialButton.setForeground(Color.WHITE);

        gbc.gridy = 3;
        loginFormPanel.add(facialButton, gbc);

        JButton otpButton = new JButton("Autenticación OTP");
        otpButton.setFont(font);
        otpButton.setForeground(Color.WHITE);

        gbc.gridy = 4;
        loginFormPanel.add(otpButton, gbc);

        JButton registrarButton = new JButton("Registrar Usuario");
        registrarButton.setFont(font);
        registrarButton.setForeground(Color.WHITE);
        gbc.gridy = 5;
        loginFormPanel.add(registrarButton, gbc);

        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setVisible(false);
        gbc.gridy = 6;
        loginFormPanel.add(loadingLabel, gbc);
        add(loginFormPanel, BorderLayout.CENTER);

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
        if (!validarCorreo(email)) {
            throw new Exception("El email no es valido");
        }
        if (email.isEmpty() || password.isEmpty()) {
            throw new Exception("Por favor, ingrese su email y contraseña.");
        }
        try {
            return AutenticacionCredenciales.autenticar(email, password);
        } catch (Exception e) {
            throw new Exception("Credenciales incorrectas");
        }
    }

    private Usuario autenticarFacial() throws Exception {
        return AutenticacionFacial.autenticar();
    }

    private void autenticarOTP() throws Exception {
        String email = emailField.getText();
        if (!validarCorreo(email)) {
            throw new Exception("El email no es valido");
        }
        if (email.isEmpty()) {
            throw new Exception("Por favor, ingrese su email.");
        }
        if (AutenticacionOTP.autenticar(email)) {
            GUI.getInstance().showOTPPanel();
        } else {
            throw new Exception("El correo ingresado no es valido");
        }
        ocultarCargando();
    }

    private void manejarAutenticacion(Usuario usuario) {
        if (usuario instanceof Paciente) {
            GUI.getInstance().showPacienteScreen((Paciente) usuario);
        } else if (usuario instanceof Medico) {
            GUI.getInstance().showMedicoScreen((Medico) usuario);
        }
    }

    public boolean validarCorreo(String correo) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
