package org.presentation.activities;

import org.presentation.resource.ResourceModel;
import org.presentation.resource.ResourceView;


public class ActivityController {
    private ActivityView view;
    private ActivityModel model;
    //private final CategoryService categoryService = new CategoryService();

    public ActivityController(ActivityView view, ActivityModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

    }
}
