package org.presentation.category;

import org.domain.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CategoryView implements PropertyChangeListener {
    private JPanel panel; // JPanel principal del form de Categorías
    private JTable tableCategories;
    private JTextField idCategoriaFld;
    private JTextField descripcionCategoriaFld;
    private JButton guardarButton1;
    private JButton borrarButton1;
    private JButton limpiarButton1;
    private JTextField descripcionFld;
    private JButton imprimirButton1;
    private JButton buscarButton1;
    private JPanel principalPanel;

    private CategoryModel model;
    private CategoryController controller;

    public CategoryView() {
        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setModel(CategoryModel model) {
        this.model = model;
        if (this.model != null) {
            this.model.addPropertyChangeListener(this);
        }
    }

    public void setController(CategoryController controller) {
        this.controller = controller;
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case CategoryModel.LIST:
                int[] colum = {CategoryTableModel.ID, CategoryTableModel.DESCRIPCION};
                tableCategories.setModel(new CategoryTableModel(colum, model.getList()));
                break;
            case CategoryModel.CURRENT:
                Category curr = model.getCurrent();
                idCategoriaFld.setText(curr.getId() == null ? "" : curr.getId());
                descripcionCategoriaFld.setText(curr.getDescription() == null ? "" : curr.getDescription());
                break;
        }
    }
}