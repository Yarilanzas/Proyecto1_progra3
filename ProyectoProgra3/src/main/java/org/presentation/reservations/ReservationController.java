package org.presentation.reservations;

import org.presentation.resource.ResourceModel;
import org.presentation.resource.ResourceView;


public class ReservationController {
    private ReservationView view;
    private ReservationModel model;
    //private final CategoryService categoryService = new CategoryService();

    public ReservationController(ReservationView view, ReservationModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

    }
}