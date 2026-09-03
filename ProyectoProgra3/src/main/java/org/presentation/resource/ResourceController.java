package org.presentation.resource;

import org.domain.Category;
import org.logic.CategoryService;
import org.logic.ResourceService;
import org.presentation.category.CategoryModel;
import org.presentation.category.CategoryView;

import javax.swing.*;


public class ResourceController {
    private ResourceView view;
    private ResourceModel model;
    private final ResourceService resourceServiceService = new ResourceService();

    public ResourceController(ResourceView view, ResourceModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);
        try {
            model.setCategories(resourceServiceService.findAllCategories());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
}



