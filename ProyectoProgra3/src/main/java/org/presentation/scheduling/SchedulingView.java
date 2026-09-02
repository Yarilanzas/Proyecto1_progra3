package org.presentation.scheduling;

import com.github.lgooddatepicker.components.DatePicker;
import org.domain.CalendarData;
import org.domain.Category;
import org.logic.ReservationQueryService;
import org.presentation.CalendarTableModel;
import org.presentation.resource.ResourceController;
import org.presentation.resource.ResourceModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SchedulingView {
    private JPanel CalendarizacionPanel;
    private DatePicker fechaPicker;
    private JComboBox categoriaComboBox;
    private JButton cargarButton;
    private JTable Calendarizaciontable;
    private JPanel principalPanel;

    private final ReservationQueryService queryService = new ReservationQueryService();

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

                    CalendarData calendarData = queryService.getCalendar(fecha,categoria);
                    Calendarizaciontable.setModel(new CalendarTableModel(calendarData));
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(CalendarizacionPanel,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    private SchedulingModel model;
    private SchedulingController controller;



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
    }
}