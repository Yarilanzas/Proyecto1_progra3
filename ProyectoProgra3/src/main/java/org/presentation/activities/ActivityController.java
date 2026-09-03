package org.presentation.activities;

import org.logic.ReservationQueryService;

import javax.swing.*;
import java.time.LocalDate;

public class ActivityController {
    private ActivityView view;
    private ActivityModel model;
    private final ReservationQueryService queryService = new ReservationQueryService();


    public ActivityController(ActivityView view, ActivityModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);
    }

    public void cargarHorario(LocalDate fecharef){
        try {
            model.setActivityData(queryService.getHorarioSemanal(fecharef));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
