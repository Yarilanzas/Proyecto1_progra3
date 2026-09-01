package org.presentation.administrator;

import org.domain.Category;
import org.presentation.AbstractModel;

import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.List;

public class ModelAdministrator extends AbstractModel {
    Category current;
    List<Category> list;
    public static final String CURRENT = "currentCategory";
    public static final String LISTCAT = "listCat";


    public ModelAdministrator() {
        current = new Category();
        list = new ArrayList<Category>();

    }
    public void setCurrent(Category current) {
        this.current = current;
        firePropertyChange(CURRENT);

    }

    public List<Category> getList() {
        return list;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(LISTCAT);

    }
    public void setList(List<Category> list) {
        this.list = list;
        firePropertyChange(LISTCAT);
    }

    public Category getCurrent(){
        return current;
    }
}



