package org.presentation.statistics;

import org.logic.ReservationQueryService;
import java.time.LocalDate;
import java.util.List;


public class StatisticsController {
    private StatisticsView view;
    private StatisticsModel model;
    private final ReservationQueryService queryService = new ReservationQueryService();

    public StatisticsController(StatisticsView view, StatisticsModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);
    }

    public void cargarRecursos(LocalDate desde, LocalDate hasta){
        try{
            model.setCategoryStats(queryService.getResourceStatistics(desde,hasta));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void cargarActividades(LocalDate desde, LocalDate hasta){
        try{
            model.setActivityStats(queryService.getActivityStatistics(desde,hasta));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}