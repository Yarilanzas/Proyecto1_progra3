package org.presentation.activities;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;

public class ActivityView implements PropertyChangeListener{
    private ActivityModel model;
    private ActivityController controller;
    private JPanel panel;
    private JPanel principalPanel;
    private DatePicker fechapicker;
    private JButton cargarButton;
    private JButton imprimirbutton;
    private JTable Actividadestable;


    public ActivityView() {
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LocalDate fecha = fechapicker.getDate();

                    if (fecha == null){
                        JOptionPane.showMessageDialog(principalPanel,"Seleccione fecha de referencia");
                        return;
                    }
                    controller.cargarHorario(fecha);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(principalPanel,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
       if (ActivityModel.HORARIO.equals(evt.getPropertyName())){
           Actividadestable.setModel(new ActivityTableModel(model.getActivityData()));
       }
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public ActivityController getController() {
        return controller;
    }

    public void setController(ActivityController controller) {
        this.controller = controller;
    }

    public ActivityModel getModel() {
        return model;
    }

    public void setModel(ActivityModel model) {
        this.model = model;
        if (this.model != null){
            this.model.addPropertyChangeListener(this);
        }
    }
}
