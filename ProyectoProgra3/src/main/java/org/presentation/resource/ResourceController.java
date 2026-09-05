package org.presentation.resource;

import org.domain.Resource;
import org.logic.CategoryService;
import org.logic.ResourceService;

import javax.swing.*;
import java.util.List;


public class ResourceController {
    private ResourceView view;
    private ResourceModel model;
    private final ResourceService resourceService = new ResourceService();
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
            Resource c= resourceService.findByDesc(desc);
            model.setResources(c != null ? List.of(c) : List.of());
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
    public void saveResource(Resource r){
        try{
            resourceService.save(r);

        }
        catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }


}



