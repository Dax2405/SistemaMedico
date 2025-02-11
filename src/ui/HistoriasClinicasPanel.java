package ui;

import BusinessLogic.Entities.Turno.HistoriaClinica;
import BusinessLogic.Entities.Usuario.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HistoriasClinicasPanel extends JPanel {
    private Paciente paciente;
    private JTable table;
    private DefaultTableModel tableModel;

    public HistoriasClinicasPanel(Paciente paciente) {
        this.paciente = paciente;
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/ui/media/SmartTurnLogo.png").getImage().getScaledInstance(150, 140, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);

        JLabel titleLabel = new JLabel("SmartTurn");
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 50)); 
        titleLabel.setForeground(Color.WHITE); 

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);
        String[] columnNames = { "Diagnóstico", "Tratamiento", "Médico" };
        tableModel = new DefaultTableModel(columnNames, 0);
        
        table = new JTable(tableModel);
        table.setFont(new Font("Cambria", Font.PLAIN, 25));
        table.setRowHeight(30);
        table.setForeground(Color.WHITE); 
        table.setSelectionBackground(new Color(77, 153, 77)); 
        table.setSelectionForeground(Color.WHITE); 

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 30)); 
        header.setBackground(new Color(10, 25, 50)); 
        header.setForeground(Color.WHITE); 
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Se", Font.BOLD, 14));
        regresarButton.setBackground(Color.WHITE); 
        regresarButton.setForeground(new Color(0, 100, 0)); 
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().showPacienteScreen(paciente);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(regresarButton);
        add(buttonPanel, BorderLayout.SOUTH);
        cargarHistoriasClinicas();
    }

    private void cargarHistoriasClinicas() {
        ArrayList<HistoriaClinica> historiasClinica = paciente.getHistoriasClinica();
        for (HistoriaClinica historia : historiasClinica) {
            String diagnostico = historia.getDiagnostico();
            String tratamiento = historia.getTratamiento();
            String medico = historia.getMedico().getNombre() + " " + historia.getMedico().getApellido();
            Object[] rowData = { diagnostico, tratamiento, medico };
            tableModel.addRow(rowData);
        }
    }
}
