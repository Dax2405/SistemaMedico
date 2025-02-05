package ui;

import BusinessLogic.Entities.Autenticacion.AutenticacionOTP;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import BusinessLogic.Entities.Usuario.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OTPPanel extends JPanel {
    private JTextField otpField;

    public OTPPanel() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes
        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/ui/media/SmartTurnLogo.png").getImage().getScaledInstance(150, 140, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        
        JLabel titleLabel = new JLabel("SmartTurn");
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 50)); // Tamaño de fuente más pequeño
        titleLabel.setForeground(Color.WHITE); // Texto en blanco

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel otpLabel = new JLabel("Codigo OTP:");
        otpLabel.setForeground(Color.WHITE); // Texto en blanco
        otpLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        otpLabel.setForeground(Color.WHITE); // Texto en blanco
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(otpLabel, gbc);

        otpField = new JTextField(20);
        otpField.setFont(new Font("Cambria", Font.PLAIN, 25));
        gbc.gridx = 1;
        mainPanel.add(otpField, gbc);

        JButton loginButton = new JButton("Validar Codigo OTP");
        loginButton.setBackground(Color.WHITE); // Fondo blanco
        loginButton.setForeground(new Color(0, 128, 0)); // Letras verdes
        loginButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

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

        add(mainPanel, BorderLayout.CENTER);
    }

    private void validarCodigoOTP() throws Exception {
        String otp = otpField.getText();
        if (otp.isEmpty()) {
            throw new Exception("Codigo OTP no puede estar vacio");
        }
        if (AutenticacionOTP.validarOTP(otp)) {
            Usuario usuario = AutenticacionOTP.obtenerUsuario();
            manejarAutenticacion(usuario);
        } else {
            JOptionPane.showMessageDialog(this, "Codigo OTP invalido");
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
