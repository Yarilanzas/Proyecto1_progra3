package org.presentation.scheduling;

import org.domain.Category;
import org.logic.CategoryService;
import org.logic.ReservationQueryService;

import javax.swing.*;
import java.time.LocalDate;


public class SchedulingController {
    private SchedulingView view;
    private SchedulingModel model;
    private final CategoryService categoryService = new CategoryService();
    private final ReservationQueryService queryService = new ReservationQueryService();

    public SchedulingController(SchedulingView view, SchedulingModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

        cargarCategorias();

    }

    public void cargarCategorias() {
        try {
            model.setCategories(categoryService.findAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarCalendar(LocalDate fecha, Category categoria) {
        try {
            model.setCalendarData(queryService.getCalendar(fecha, categoria));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}