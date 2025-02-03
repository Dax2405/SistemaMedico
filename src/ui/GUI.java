package ui;

import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import BusinessLogic.Entities.Usuario.Medico;
import BusinessLogic.Entities.Usuario.Paciente;
import java.awt.*;

public class GUI {
    private static GUI instance;
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private GUI() {
        FlatMaterialDarkerIJTheme.setup();
        FlatMaterialDarkerIJTheme.supportsNativeWindowDecorations();
        try {
            UIManager.setLookAndFeel(new FlatMaterialDarkerIJTheme());
        } catch (Exception e) {
            System.out.println("Error setting theme");
        }

        frame = new JFrame("SmartTurn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(10, 25, 50));
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

    public void showOTPPanel() {
        OTPPanel otpPanel = new OTPPanel();
        mainPanel.add(otpPanel, "otp");
        cardLayout.show(mainPanel, "otp");
    }

    public void showPacienteScreen(Paciente paciente) {
        PacientePanel pacientePanel = new PacientePanel(paciente);
        mainPanel.add(pacientePanel, "paciente");
        cardLayout.show(mainPanel, "paciente");
    }

    public void showRegitrarTurnoScreen(Paciente paciente) {
        RegistrarTurnoPanel registrarTurnoPanel = new RegistrarTurnoPanel(paciente);
        mainPanel.add(registrarTurnoPanel, "registrarTurno");
        cardLayout.show(mainPanel, "registrarTurno");
    }

    public void showHistoriasClinicasScreen(Paciente paciente) {
        HistoriasClinicasPanel historiasClinicasPanel = new HistoriasClinicasPanel(paciente);
        mainPanel.add(historiasClinicasPanel, "historiasClinicas");
        cardLayout.show(mainPanel, "historiasClinicas");
    }

    public void showMedicoScreen(Medico medico) {
        MedicoPanel medicoPanel = new MedicoPanel(medico);
        mainPanel.add(medicoPanel, "medico");
        cardLayout.show(mainPanel, "medico");
    }

    public void showRegistrarScreen() {
        RegistrarPanel registrarPanel = new RegistrarPanel();
        mainPanel.add(registrarPanel, "registrar");
        cardLayout.show(mainPanel, "registrar");
        frame.setVisible(true);
    }
}
