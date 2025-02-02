package ui;

import javax.swing.*;

import BusinessLogic.Entities.Autenticacion.AutenticacionCredenciales;
import BusinessLogic.Entities.Autenticacion.AutenticacionFacial;
import BusinessLogic.Entities.Autenticacion.AutenticacionOTP;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.Usuario.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel loadingLabel;

    public LoginPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.PLAIN, 14);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(font);
        gbc.gridx = 1;
        add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(font);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        JButton facialButton = new JButton("Autenticación Facial");
        facialButton.setFont(font);
        gbc.gridy = 3;
        add(facialButton, gbc);

        JButton otpButton = new JButton("Autenticación OTP");
        otpButton.setFont(font);
        gbc.gridy = 4;
        add(otpButton, gbc);

        JButton registrarButton = new JButton("Registrar Usuario");
        gbc.gridy = 5;
        add(registrarButton, gbc);

        loadingLabel = new JLabel("Cargando...");
        loadingLabel.setForeground(Color.BLUE);
        loadingLabel.setVisible(false);
        gbc.gridy = 6;
        add(loadingLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        facialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        otpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCargando();
                try {
                    autenticarOTP();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginPanel.this, ex.getMessage());
                }
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().showRegistrarScreen();
            }
        });
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