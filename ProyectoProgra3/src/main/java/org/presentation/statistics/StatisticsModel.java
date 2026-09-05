package org.presentation.statistics;

import org.presentation.AbstractModel;
import java.util.List;
import java.util.ArrayList;

public class StatisticsModel extends AbstractModel {
    public static final String CATEGORIES = "categoryStats";
    public static final String ACTIVITIES = "activitiesStats";

    private List<CategoryStatistics> categoryStats = new ArrayList<>();
    private List<ActivityStatistics> activityStats = new ArrayList<>();

    public List<CategoryStatistics> getCategoryStats() {
        return categoryStats;
    }
    public void setCategoryStats(List<CategoryStatistics> categoryStats) {
        this.categoryStats = categoryStats;
        firePropertyChange(CATEGORIES);
    }

    public List<ActivityStatistics> getActivityStats() { return activityStats; }
    public void setActivityStats(List<ActivityStatistics> activityStats) {
        this.activityStats = activityStats;
        firePropertyChange(ACTIVITIES);
    }
}
