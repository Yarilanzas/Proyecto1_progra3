package org.presentation.reservations;

import org.domain.Category;
import org.domain.Reservation;
import org.logic.CategoryService;
import org.logic.ReservationQueryService;
import org.logic.ReservationService;
import org.presentation.resource.ResourceModel;
import org.presentation.resource.ResourceView;

import javax.swing.*;
import java.util.List;


public class ReservationController {
    private ReservationView view;
    private ReservationModel model;
    private CategoryService categoryService= new CategoryService();
    private ReservationService reservationService= new ReservationService();


    private final ReservationQueryService queryService = new ReservationQueryService();

    public ReservationController(ReservationView view, ReservationModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

        this.model.addPropertyChangeListener(this.view::propertyChange);

        this.loadCategories();



    }

    public void loadCategories() {
        try {
            List<Category> list = categoryService.findAll();
            model.setCategories(list);
            List<Reservation> listRE = reservationService.findAllResources();
            model.setReservations(listRE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void clear(){
        Reservation r= new Reservation();
        model.setCurrent(r);
    }

    public void saveReservation(Reservation r, List<Category> categorias) throws Exception {
        reservationService.save(r, categorias);

        model.setReservations(reservationService.findAllResources());
        clear();
    }
    public void cancelReservation(String id) throws Exception {
        reservationService.cancel(id);
        model.setReservations(reservationService.findAllResources());
        clear();
    }

    public void delete(String id)throws Exception{
        reservationService.delete(id);
        list();
        model.setCurrent(new Reservation());
    }

    public void list() {
        try {
            model.setReservations(reservationService.findAllResources());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}