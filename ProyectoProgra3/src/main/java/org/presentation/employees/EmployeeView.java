package org.presentation.employees;

import javax.swing.*;
import java.net.URL;

public class EmployeeView {
    private JTabbedPane tabbedPane1;
    private JPanel Panel;
    private JTextField nombreFld;
    private JButton buscarButton;
    private JButton ImprimirButton; // Ojo con la 'I' mayúscula tal como sale en tu imagen
    private JTextField idFld;
    private JTextField idfuncionarios;
    private JTextField nombreFuncionarios;
    private JTextField telefono;
    private JTable table1;
    private JButton guardarButton;
    private JButton borrarButton;
    private JButton limpiarButton;
    private JPanel principalPanel;

    public EmployeeView() {
        // Inicialización básica y carga de íconos para las pestañas
        cargarIconosPestanias();
    }

    private ImageIcon getIcono(String ruta) {
        URL url = getClass().getResource(ruta);
        return (url != null) ? new ImageIcon(url) : null;
    }

    private void cargarIconosPestanias() {
        if (tabbedPane1 != null) {
            tabbedPane1.setIconAt(0, getIcono("/icons/reserva.png"));
            // tabbedPane1.setIconAt(1, getIcono("/icons/calendar.png"));
            // tabbedPane1.setIconAt(2, getIcono("/icons/actividades.png"));
            // tabbedPane1.setIconAt(3, getIcono("/icons/estadisticas.png"));
        }
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane1;
    }
}