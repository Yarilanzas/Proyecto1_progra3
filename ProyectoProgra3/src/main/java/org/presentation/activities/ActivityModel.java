package org.presentation.activities;

import org.domain.ActivityData;
import org.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

public class ActivityModel extends AbstractModel{
    public static final String HORARIO = "schedule";
    private ActivityData activityData;

    public ActivityModel() {
        activityData = null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
        super.addPropertyChangeListener(listener);
        //no hay datos hasta que se cargue
    }

    public ActivityData getActivityData() {
        return activityData;
    }

    public void setActivityData(ActivityData activityData) {
        this.activityData = activityData;
        firePropertyChange(HORARIO);
    }
}
