package org.presentation.resource;

import org.logic.CategoryService;
import org.presentation.category.CategoryModel;
import org.presentation.category.CategoryView;


public class ResourceController {
    private ResourceView view;
    private ResourceModel model;
    //private final CategoryService categoryService = new CategoryService();

    public ResourceController(ResourceView view, ResourceModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

    }
}



