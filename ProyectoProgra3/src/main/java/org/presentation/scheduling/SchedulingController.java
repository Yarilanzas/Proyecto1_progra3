package org.presentation.scheduling;

import org.logic.CategoryService;
import org.presentation.resource.ResourceModel;
import org.presentation.resource.ResourceView;


public class SchedulingController {
    private SchedulingView view;
    private SchedulingModel model;

    public SchedulingController(SchedulingView view, SchedulingModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

    }
}