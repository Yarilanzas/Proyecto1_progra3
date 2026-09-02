package org.presentation.scheduling;

import com.github.lgooddatepicker.components.DatePicker;
import org.domain.Category;
import org.presentation.CalendarTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;

public class SchedulingView implements PropertyChangeListener{
    private JPanel CalendarizacionPanel;
    private DatePicker fechaPicker;
    private JComboBox categoriaComboBox;
    private JButton cargarButton;
    private JTable Calendarizaciontable;
    private JPanel principalPanel;

    private SchedulingModel model;
    private SchedulingController controller;


    public SchedulingView() {
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate fecha = fechaPicker.getDate();
                    Category categoria = (Category) categoriaComboBox.getSelectedItem();

                    if (fecha == null || categoria == null){
                        JOptionPane.showMessageDialog(CalendarizacionPanel,"Seleccione fecha y categoria");
                        return;
                    }
                    controller.cargarCalendar(fecha,categoria);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(CalendarizacionPanel,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        switch(evt.getPropertyName()){
            case SchedulingModel.CATEGORIES:
                DefaultComboBoxModel<Category> modelo = new DefaultComboBoxModel<>();
                for (Category c : model.getCategories()){
                    modelo.addElement(c);
                }
                categoriaComboBox.setModel(modelo);
                break;
            case SchedulingModel.CALENDAR:
                if (model.getCalendarData() != null) {
                    Calendarizaciontable.setModel(new CalendarTableModel(model.getCalendarData()));
                }
                break;
        }
    }

    public JPanel getPanel() {
        return principalPanel;
    }
    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public SchedulingController getController() {
        return controller;
    }
    public void setController(SchedulingController controller) {
        this.controller = controller;
    }

    public SchedulingModel getModel() {
        return model;
    }
    public void setModel(SchedulingModel model) {
        this.model = model;
        if (this.model != null){
            this.model.addPropertyChangeListener(this);
        }
    }
}