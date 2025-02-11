package ui;

import javax.swing.*;
import BusinessLogic.Entities.Usuario.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacientePanel extends JPanel {
    private Paciente paciente;

    public PacientePanel(Paciente paciente) {
        this.paciente = paciente;
        setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.BOLD, 16);
        Font font2 = new Font("Cambria", Font.PLAIN, 20);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Bienvenido a SmartTurn");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Nombre: " + paciente.getNombre() + " " + paciente.getApellido());
        nameLabel.setFont(font2);
        nameLabel.setForeground(Color.WHITE); 
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel codigoUnicoLabel = new JLabel("Cédula: " + paciente.getCodigoUnico());
        codigoUnicoLabel.setFont(font2);
        codigoUnicoLabel.setForeground(Color.WHITE); 
        codigoUnicoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon logoIcon = new ImageIcon(new ImageIcon(getClass().getResource("/ui/media/SmartTurnLogo.png"))
                .getImage().getScaledInstance(400, 390, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(logoLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(nameLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(codigoUnicoLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton registrarTurnoButton = new JButton("Registrar Turno");
        registrarTurnoButton.setFont(font);
        registrarTurnoButton.setForeground(Color.WHITE);
        buttonPanel.add(registrarTurnoButton, gbc);

        gbc.gridy = 1;
        JButton verHistoriasClinicasButton = new JButton("Ver Historias Clínicas");
        verHistoriasClinicasButton.setFont(font);
        verHistoriasClinicasButton.setForeground(Color.WHITE);
        buttonPanel.add(verHistoriasClinicasButton, gbc);

        gbc.gridy = 2;
        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(font);
        regresarButton.setForeground(Color.WHITE);
        buttonPanel.add(regresarButton, gbc);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().showLoginScreen();
            }
        });

        registrarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegistrarTurno();
            }
        });

        verHistoriasClinicasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistoriasClinicas();
            }
        });

        add(welcomePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void showRegistrarTurno() {
        GUI.getInstance().showRegistrarTurnoScreen(paciente);
    }

    private void showHistoriasClinicas() {
        GUI.getInstance().showHistoriasClinicasScreen(paciente);
    }
}
