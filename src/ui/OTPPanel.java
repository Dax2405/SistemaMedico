package ui;

import javax.swing.*;

import BusinessLogic.Entities.Autenticacion.AutenticacionOTP;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.Usuario.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OTPPanel extends JPanel {
    private JTextField otpField;

    public OTPPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel otpLabel = new JLabel("Codigo OTP:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(otpLabel, gbc);

        otpField = new JTextField(20);
        gbc.gridx = 1;
        add(otpField, gbc);

        JButton loginButton = new JButton("Validar Codigo OTP");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validarCodigoOTP();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(OTPPanel.this, ex.getMessage());
                }
            }
        });
    }

    private void validarCodigoOTP() throws Exception {
        String otp = otpField.getText();
        if (AutenticacionOTP.validarOTP(otp)) {
            Usuario usuario = AutenticacionOTP.obtenerUsuario();
            manejarAutenticacion(usuario);
        }
    }

    private void manejarAutenticacion(Usuario usuario) {
        if (usuario instanceof Paciente) {
            GUI.getInstance().showPacienteScreen((Paciente) usuario);
        } else if (usuario instanceof Medico) {
            // GUI.getInstance().showMedicoScreen((Medico) usuario);
        }
    }
}