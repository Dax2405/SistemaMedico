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

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Bienvenido a SmartTurn");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));
        welcomeLabel.setForeground(Color.WHITE); // Texto blanco
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Nombre: " + paciente.getNombre() + " " + paciente.getApellido());
        nameLabel.setFont(new Font("Cambria", Font.PLAIN, 35));
        nameLabel.setForeground(Color.WHITE); // Texto blanco
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel codigoUnicoLabel = new JLabel("Cédula: " + paciente.getCodigoUnico());
        codigoUnicoLabel.setFont(new Font("Cambria", Font.PLAIN, 35));
        codigoUnicoLabel.setForeground(Color.WHITE); // Texto blanco
        codigoUnicoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cargar la imagen
        ImageIcon logoIcon = new ImageIcon(new ImageIcon(getClass().getResource("/ui/media/SmartTurnLogo.png")).getImage().getScaledInstance(410, 400, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(logoLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(nameLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(codigoUnicoLabel);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar los botones

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton registrarTurnoButton = new JButton("Registrar Turno");
        registrarTurnoButton.setFont(new Font("Arial", Font.BOLD, 20));
        registrarTurnoButton.setBackground(Color.WHITE); // Fondo blanco
        registrarTurnoButton.setForeground(new Color(0, 100, 0)); // Texto verde oscuro
        buttonPanel.add(registrarTurnoButton, gbc);

        gbc.gridy = 1;
        JButton verHistoriasClinicasButton = new JButton("Ver Historias Clínicas");
        verHistoriasClinicasButton.setFont(new Font("Arial", Font.BOLD, 20));
        verHistoriasClinicasButton.setBackground(Color.WHITE); // Fondo blanco
        verHistoriasClinicasButton.setForeground(new Color(0, 100, 0)); // Texto verde oscuro
        buttonPanel.add(verHistoriasClinicasButton, gbc);

        gbc.gridy = 2;
        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Arial", Font.BOLD, 20));
        regresarButton.setBackground(Color.WHITE); // Fondo blanco
        regresarButton.setForeground(new Color(0, 100, 0)); // Texto verde oscuro
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
        GUI.getInstance().showRegitrarTurnoScreen(paciente);
    }

    private void showHistoriasClinicas() {
        GUI.getInstance().showHistoriasClinicasScreen(paciente);
    }
}
