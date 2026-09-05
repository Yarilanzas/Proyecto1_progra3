package org.presentation.resource;

import org.domain.Category;
import org.domain.Resource;
import org.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ResourceModel extends AbstractModel {
    Resource current;
    List<Category> categories;


    public static final String CATEGORIES = "categories";
    public static final String CURRENT= "current";

    public ResourceModel() {
        current = new Resource();
        categories = new ArrayList<Category>();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(CATEGORIES);
    }

    public Resource getCurrent() {
        return current;
    }

    public void setCurrent(Resource current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        firePropertyChange(CATEGORIES);
    }





}
