package org.presentation.statistics;

import org.presentation.resource.ResourceModel;
import org.presentation.resource.ResourceView;


public class StatisticsController {
    private StatisticsView view;
    private StatisticsModel model;
    //private final CategoryService categoryService = new CategoryService();

    public StatisticsController(StatisticsView view, StatisticsModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

    }
}