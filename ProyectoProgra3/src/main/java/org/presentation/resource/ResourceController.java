package org.presentation.resource;

import org.domain.Category;
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
        list();

    }

    public void searchbyCategory(String cat) {
        try {
            List<Resource> list = resourceService.findByCategory(cat);
            model.setResources(list);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchbyDes(String desc) {
        try {
            List<Resource> list = resourceService.findByDesc(desc);
            model.setResources(list);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            model.setResources(resourceService.findAllResources());

        }
        catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    public void clear(){
        model.setCurrent(new Resource());
    }

    public void delete(String id){
        try{
            resourceService.delete(id);
            list();
            model.setCurrent(new Resource());

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void list() {
        try {
            model.setResources(resourceService.findAllResources());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void edit(int row) {
        Resource re = model.getResources().get(row);
        model.setCurrent(re);
    }
}



