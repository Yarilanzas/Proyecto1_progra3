package org.presentation.administrator;

import org.domain.Category;
import org.logic.CayegoryService;
import org.presentation.login.Login;
import org.presentation.login.ModelLogin;

import javax.swing.*;

public class ControllerAdministrator {
    AdministratorView view;
    ModelAdministrator model;

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
            model.setList(CayegoryService.instance().getAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveCategory(Category category) {
        try {
            CayegoryService.instance().saveCategory(category);
            this.cargarCategorias();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
