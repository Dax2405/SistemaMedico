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

        JLabel welcomeLabel = new JLabel("Bienvenido a Sistema Medico");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Nombre: " + paciente.getNombre() + " " + paciente.getApellido());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel codigoUnicoLabel = new JLabel("Cédula: " + paciente.getCodigoUnico());
        codigoUnicoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        codigoUnicoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cargar la imagen
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/ui/media/logo.png"));
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
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton registrarTurnoButton = new JButton("Registrar Turno");
        JButton verHistoriasClinicasButton = new JButton("Ver Historias Clínicas");

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

        buttonPanel.add(registrarTurnoButton);
        buttonPanel.add(verHistoriasClinicasButton);

        // Agregar los paneles al panel principal
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