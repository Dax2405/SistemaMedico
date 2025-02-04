package ui;

import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import java.awt.*;

public class GUI {
    private static GUI instance;
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private GUI() {
        FlatMaterialDeepOceanIJTheme.setup();
        FlatMaterialDeepOceanIJTheme.supportsNativeWindowDecorations();
        try {
            UIManager.setLookAndFeel(new FlatMaterialDeepOceanIJTheme());
        } catch (Exception e) {
            System.out.println("Error setting theme");
        }

        frame = new JFrame("SmartTurn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        frame.add(mainPanel, BorderLayout.CENTER);

        showLoginScreen();
        frame.setVisible(true); 
    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    public void showLoginScreen() {
        LoginPanel loginPanel = new LoginPanel();
        estilizarComponentes(loginPanel);
        mainPanel.add(loginPanel, "login");
        cardLayout.show(mainPanel, "login");
    }

    private void estilizarComponentes(JPanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(Color.WHITE); 
                c.setForeground(new Color(0, 100, 0)); 
                ((JButton) c).setFont(new Font("Segoe UI", Font.BOLD, 14)); 
            } else if (c instanceof JLabel) {
                c.setForeground(Color.WHITE); 
                ((JLabel) c).setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
            } else if (c instanceof JTextField || c instanceof JPasswordField) {
                c.setBackground(new Color(20, 40, 70));
                c.setForeground(Color.WHITE); 
                ((JTextField) c).setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
            }
        }
    }

    public void showOTPPanel() {
        OTPPanel otpPanel = new OTPPanel();
        estilizarComponentes(otpPanel);
        mainPanel.add(otpPanel, "otp");
        cardLayout.show(mainPanel, "otp");
    }

    public void showPacienteScreen(Paciente paciente) {
        PacientePanel pacientePanel = new PacientePanel(paciente);
        estilizarComponentes(pacientePanel);
        mainPanel.add(pacientePanel, "paciente");
        cardLayout.show(mainPanel, "paciente");
    }

    public void showRegistrarTurnoScreen(Paciente paciente) {
        RegistrarTurnoPanel registrarTurnoPanel = new RegistrarTurnoPanel(paciente);
        estilizarComponentes(registrarTurnoPanel);
        mainPanel.add(registrarTurnoPanel, "registrarTurno");
        cardLayout.show(mainPanel, "registrarTurno");
    }

    public void showHistoriasClinicasScreen(Paciente paciente) {
        HistoriasClinicasPanel historiasClinicasPanel = new HistoriasClinicasPanel(paciente);
        estilizarComponentes(historiasClinicasPanel);
        mainPanel.add(historiasClinicasPanel, "historiasClinicas");
        cardLayout.show(mainPanel, "historiasClinicas");
    }

    public void showMedicoScreen(Medico medico) {
        MedicoPanel medicoPanel = new MedicoPanel(medico);
        estilizarComponentes(medicoPanel);
        mainPanel.add(medicoPanel, "medico");
        cardLayout.show(mainPanel, "medico");
    }

    public void showRegistrarScreen() {
        RegistrarPanel registrarPanel = new RegistrarPanel();
        estilizarComponentes(registrarPanel);
        mainPanel.add(registrarPanel, "registrar");
        cardLayout.show(mainPanel, "registrar");
    }
}
