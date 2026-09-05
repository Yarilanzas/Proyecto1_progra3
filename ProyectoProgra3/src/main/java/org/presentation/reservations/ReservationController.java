package org.presentation.reservations;

import org.logic.ReservationQueryService;
import org.presentation.resource.ResourceModel;
import org.presentation.resource.ResourceView;


public class ReservationController {
    private ReservationView view;
    private ReservationModel model;
    private final ReservationQueryService queryService = new ReservationQueryService();

    public ReservationController(ReservationView view, ReservationModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

    }


}