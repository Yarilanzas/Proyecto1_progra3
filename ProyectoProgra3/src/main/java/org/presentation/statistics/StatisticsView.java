package org.presentation.statistics;

import com.github.lgooddatepicker.components.DatePicker;
import org.domain.Category;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.presentation.activities.ActivityTableModel;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;


public class StatisticsView implements PropertyChangeListener{
    private JButton cargarButtonA;
    private JTable tableActividades;
    private JPanel panel;
    private JPanel principalPanel;
    private DatePicker desdeRecursos;
    private DatePicker hastaRecursos;
    private JButton cargarButton;
    private DatePicker desdeActividades;
    private DatePicker hastaActividades;
    private JTable tableCategorias;
    private JPanel graficoRecursos;
    private JPanel graficoActividades;

    private StatisticsModel model;
    private StatisticsController controller;

    public StatisticsView() {
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate desde = desdeRecursos.getDate();
                LocalDate hasta = hastaRecursos.getDate();
                if (desde  == null || hasta == null){
                    JOptionPane.showMessageDialog(principalPanel,"Seleccionar ambas fechas");
                    return;
                }
                controller.cargarRecursos(desde,hasta);
            }
        });
        cargarButtonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate desde = desdeActividades.getDate();
                LocalDate hasta = hastaActividades.getDate();
                if (desde  == null || hasta == null){
                    JOptionPane.showMessageDialog(principalPanel,"Seleccionar ambas fechas");
                    return;
                }
                controller.cargarActividades(desde,hasta);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        switch (evt.getPropertyName()){
            case StatisticsModel.CATEGORIES:
                int [] colsCate = {CategoriesTableModel.CATEGORIA,CategoriesTableModel.CANTIDAD};
                tableCategorias.setModel(new CategoriesTableModel(colsCate,model.getCategoryStats()));
                mostrargraficoRecursos();
                break;
            case StatisticsModel.ACTIVITIES:
                int [] colsAct = {ActivitiesTableModel.SEMANA,ActivitiesTableModel.CANTIDAD};
                tableCategorias.setModel(new ActivitiesTableModel(colsAct,model.getActivityStats()));
                mostrargraficoActividades();
                break;
        }
    }

    public void mostrargraficoRecursos(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (CategoryStatistics ce : model.getCategoryStats()){
            dataset.addValue(ce.getCantidad(),"Recurso",ce.getCategory().getDescription());
        }

        JFreeChart chart = ChartFactory.createLineChart
                ("Recursos usados", "Recurso", "Cantidad",
                        dataset,PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultLinesVisible(true);
        ChartPanel chartPanel = new ChartPanel(chart);
        graficoRecursos.removeAll();
        graficoRecursos.add(chartPanel);
    }

    public void mostrargraficoActividades(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ActivityStatistics ae : model.getActivityStats()){
            dataset.addValue(ae.getCantidad(),"Semana", ae.getSemana());
        }

        JFreeChart chart = ChartFactory.createLineChart
                ("Actividades realizadas", "Semana", "Cantidad",
                        dataset,PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultLinesVisible(true);
        ChartPanel chartPanel = new ChartPanel(chart);
        graficoActividades.removeAll();
        graficoActividades.add(chartPanel);
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public StatisticsController getController() {
        return controller;
    }

    public void setController(StatisticsController controller) {
        this.controller = controller;
    }

    public StatisticsModel getModel() {
        return model;
    }

    public void setModel(StatisticsModel model) {
        this.model = model;
    }
}
