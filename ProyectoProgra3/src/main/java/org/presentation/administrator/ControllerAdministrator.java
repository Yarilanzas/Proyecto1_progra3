package org.presentation.administrator;

import org.domain.Category;
import org.logic.CategoryService;

import javax.swing.*;

public class ControllerAdministrator {
    AdministratorView view;
    ModelAdministrator model;
    private final CategoryService categoryService = new CategoryService();

    public ControllerAdministrator(AdministratorView view, ModelAdministrator model) {
        this.view = view;
        this.model = model;
        view.setControllerAdm(this);
        view.setModelAdm(model);

        this.cargarCategorias();
    }


    /*public void saveCategory(Category category){
        try {
        CayegoryService.instance().saveCategory(category);}
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/
    public void cargarCategorias() {
        try {
            model.setList(categoryService.findAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveCategory(Category category) {
        try {
             categoryService.save(category);
             this.cargarCategorias();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void edit(int row){
        Category cat = model.getList().get(row);
        model.setCurrent(cat);
    }
}
