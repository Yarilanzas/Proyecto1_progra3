package org.presentation.login;

import org.domain.User;
import org.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
public class ModelLogin extends AbstractModel  {
    User current;
    public static final String CURRENT = "current";

    public Model() {
        current = new User() {
        };
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
    }
}
