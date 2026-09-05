package org.presentation.reservations;

import com.github.lgooddatepicker.components.DatePicker;
import org.presentation.resource.ResourceController;
import org.presentation.resource.ResourceModel;

import javax.swing.*;
import java.net.URL;

public class ReservationView {
    private JPanel Panel;
    private JTextField frasetextField;
    private JTextField ActividadtextField1;
    private JTextField horainiciotextField1;
    private JTextField horaFinaltextField1;
    private JButton extraerButton;
    private JPanel panel;
    private JButton horaInicioButton;
    private JButton horaFinButton;
    private JPanel categoriasPanel;
    private JButton reservarButton;
    private JButton cancelarReservaSeleccionadaButton;
    private JButton limpiarButton;
    private JTable table1;
    private JButton imprimirButton1;
    private JPanel PrincipalPanel;
    private DatePicker datePicker;

    public ReservationView() {
    }
    private ReservationModel model;
    private ReservationController controller;


    public JPanel getPanel() {
        return PrincipalPanel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ReservationController getController() {
        return controller;
    }

    public void setController(ReservationController controller) {
        this.controller = controller;
    }

    public ReservationModel getModel() {
        return model;
    }

    public void setModel(ReservationModel model) {
        this.model = model;
    }
    private ImageIcon getIcono(String ruta) {
        URL url = getClass().getResource(ruta);
        return (url != null) ? new ImageIcon(url) : null;
    }


}