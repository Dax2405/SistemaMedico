package ui;

import BusinessLogic.Entities.Turno.HistoriaClinica;
import BusinessLogic.Entities.Usuario.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class HistoriasClinicasPanel extends JPanel {
    private Paciente paciente;
    private JTable table;
    private DefaultTableModel tableModel;

    public HistoriasClinicasPanel(Paciente paciente) {
        this.paciente = paciente;
        setLayout(new BorderLayout());

        // Crear el modelo de tabla
        String[] columnNames = { "Diagnóstico", "Tratamiento", "Médico" };
        tableModel = new DefaultTableModel(columnNames, 0);

        // Crear la tabla
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);

        // Estilo moderno con FlatLaf
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(new Color(0xD6EAF8));
        table.setSelectionForeground(Color.BLACK);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar las historias clínicas
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