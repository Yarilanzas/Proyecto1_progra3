package org.presentation.scheduling;

import org.domain.Category;
import org.domain.CalendarData;
import org.presentation.AbstractModel;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.List;

public class SchedulingModel extends AbstractModel {
    public static final String CATEGORIES = "categories";
    public static final String CALENDAR = "calendar";

    private List<Category> categories;
    private CalendarData calendarData;

    public SchedulingModel() {
        categories = new ArrayList<>();
        calendarData = null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
        super.addPropertyChangeListener(listener);
        firePropertyChange(CATEGORIES);
        //firePropertyChange(CALENDAR);
    }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories){
        this.categories = categories;
        firePropertyChange(CATEGORIES);
    }

    public CalendarData getCalendarData() { return calendarData; }
    public void setCalendarData(CalendarData calendarData){
        this.calendarData = calendarData;
        firePropertyChange(CALENDAR);
    }
}
