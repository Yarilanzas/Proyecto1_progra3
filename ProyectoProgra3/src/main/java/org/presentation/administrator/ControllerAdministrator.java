package org.presentation.administrator;

import org.domain.Category;
import org.logic.CayegoryService;
import org.presentation.login.Login;
import org.presentation.login.ModelLogin;

public class ControllerAdministrator {
    AdministratorView view;
    ModelAdministrator model;

    public ControllerAdministrator(AdministratorView view, ModelAdministrator model) {
        this.view = view;
        this.model = model;
        view.setControllerAdm(this);
        view.setModelAdm(model);
    }

    public void saveCategory(Category category){
        CayegoryService.instance().saveCategory(category);
    }
}
