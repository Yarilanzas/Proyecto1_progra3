package org.presentation.employee;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.net.URL;

public class EmployeeView {
    private JTabbedPane tabbedPane1;
    private JTextField frasetextField;
    private JTextField ActividadtextField1;
    private JTextField FechatextField1;
    private JButton button1;
    private JPanel Panel;
    private JTextField horainiciotextField1;
    private JTextField horaFinaltextField1;
    private JButton button2;
    private JButton button3;
    private JPanel categoriasPanel;
    private JButton extraerButton;
    private JButton reservarButton;
    private JButton cancelarReservaSeleccionadaButton;
    private JButton limpiarButton;
    private JTextField fechaFld;
    private JComboBox categoriaComboBox;
    private JButton cargarButton;
    private JButton imprimirButton;
    private JTextField desdeFld;
    private JTextField hastaFld;
    private JTextField desdeAFld;
    private JTextField hastaAFld;
    private JButton cargarButtonA;
    private JTable table2;
    private JTable table1;
    private JButton imprimirButton1;

    private ImageIcon getIcono(String ruta) {
        URL url = getClass().getResource(ruta);
        return (url != null) ? new ImageIcon(url) : null;
    }
    private void cargarIconosPestanias() {
        // Método aux para cargar la imagen rápido
        tabbedPane1.setIconAt(0, getIcono("/icons/reserva.png"));
        //tabbedPane1.setIconAt(1, getIcono("/icons/calendar.png"));
        //tabbedPane1.setIconAt(2, getIcono("/icons/actividades.png"));
        //tabbedPane1.setIconAt(3, getIcono("/icons/estadisticas.png"));
    }

    public JPanel getPanel() {
        return Panel;
    }

}
