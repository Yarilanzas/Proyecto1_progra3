package org.presentation.resource;

import org.domain.Category;
import org.logic.CategoryService;
import org.logic.ResourceService;
import org.presentation.category.CategoryModel;
import org.presentation.category.CategoryView;

import javax.swing.*;
import java.util.List;


public class ResourceController {
    private ResourceView view;
    private ResourceModel model;
    private final ResourceService resourceServiceService = new ResourceService();
    private final CategoryService categoryService = new CategoryService();


    public ResourceController(ResourceView view, ResourceModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);
        cargarCategorias();

    }

    public void searchbyDescription(String desc) {
        try{
            Category c= categoryService.findByDesc(desc);
            model.setCategories(c != null ? List.of(c) : List.of());
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void cargarCategorias() {
        try {
            model.setCategories(categoryService.findAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



