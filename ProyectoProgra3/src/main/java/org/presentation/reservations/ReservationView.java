package org.presentation.reservations;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import org.domain.Category;
import org.domain.Reservation;
import org.presentation.category.CategoryTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;

public class ReservationView {
    private JPanel Panel;
    private JTextField frasetextField;
    private JTextField ActividadtextField1;
    private JButton extraerButton;
    private JPanel panel;
    private JPanel categoriasPanel;
    private JButton reservarButton;
    private JButton cancelarReservaSeleccionadaButton;
    private JButton limpiarButton;
    private JTable reservastable1;
    private JButton imprimirButton1;
    private JPanel PrincipalPanel;
    private DatePicker datePicker;
    private TimePicker horaInicio;
    private TimePicker horaFin;
    private JTable categoriastable2;
    private Category categoriaActual;

    public ReservationView() {
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoriaActual = null; //
                if (controller != null) {
                    controller.clear();
                }
            }
        });


        categoriastable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = categoriastable2.getSelectedRow();
                if (selectedRow >= 0 && model != null && model.getCategories() != null) {
                    categoriaActual = model.getCategories().get(selectedRow);
                }
            }
        });
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.saveReservation(takeReservation());
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(PrincipalPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private ReservationModel model;
    private ReservationController controller;


    public JPanel getPanel() {
        return PrincipalPanel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ReservationController getController() {
        return controller;
    }

    public void setController(ReservationController controller) {
        this.controller = controller;
    }

    public ReservationModel getModel() {
        return model;
    }
    private void createUIComponents() {
        categoriastable2 = new JTable();
        datePicker = new DatePicker();
        horaInicio = new TimePicker();
        horaFin = new TimePicker();
    }
    public void setModel(ReservationModel model) {
        this.model = model;
    }
    private ImageIcon getIcono(String ruta) {
        URL url = getClass().getResource(ruta);
        return (url != null) ? new ImageIcon(url) : null;
    }
    private void seleccionarCategoriaEnTabla(Category categoriaBuscada) {
        List<Category> lista = model.getCategories();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(categoriaBuscada.getId())) {
                categoriastable2.setRowSelectionInterval(i, i);
                break;
            }
        }
    }
    public void propertyChange(PropertyChangeEvent evt) {
       switch (evt.getPropertyName()) {
            case ReservationModel.CURRENT:
                Reservation r = model.getCurrent();
                ActividadtextField1.setText(r.getId() == null ? "" : r.getId());
                datePicker.setDate(r.getDate());

                if (r.getStartTime() != null && !r.getStartTime().isEmpty()) {
                    horaInicio.setTime(LocalTime.parse(r.getStartTime()));
                } else {
                    horaInicio.setTime(null);
                }

                if (r.getEndTime() != null && !r.getEndTime().isEmpty()) {
                    horaFin.setTime(LocalTime.parse(r.getEndTime()));
                } else {
                    horaFin.setTime(null);
                }
                if (r.getCategory() != null) {
                    seleccionarCategoriaEnTabla(r.getCategory());
                } else {
                    categoriastable2.clearSelection();
                }
                break;
           case ReservationModel.CATEGORIES:
               int[] colsCat = {CategoryTableModel.ID, CategoryTableModel.DESCRIPCION};

               categoriastable2.setModel(new CategoryTableModel(colsCat, model.getCategories()));
               break;
           case ReservationModel.RESERVATIONS:
               int[] colum = {ReservationTableModel.ID, ReservationTableModel.ACTIVIDAD, ReservationTableModel.FECHA, ReservationTableModel.RECURSO, ReservationTableModel.ESTADO};
               reservastable1.setModel(new ReservationTableModel(colum, model.getReservations()));
               break;

//ID, ACTIVIDAD, FECHA , HORARIO, RECURSO, ESTADO




       }
        this.PrincipalPanel.revalidate();

    }
    public Reservation takeReservation() {
        Reservation r = new Reservation();
        r.setActivity(ActividadtextField1.getText().trim());
        r.setDate(datePicker.getDate());
        r.setCategory(categoriaActual);

        if (horaInicio.getTime() != null) {
            r.setStartTime(horaInicio.getTime().toString());
        }
        if (horaFin.getTime() != null) {
            r.setEndTime(horaFin.getTime().toString());
        }

        return r;
    }
}