package org.presentation.category;

import org.domain.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CategoryView implements PropertyChangeListener {
    private JPanel panel;
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
        guardarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateJTextField(descripcionCategoriaFld) && validateJTextField(idCategoriaFld)) {
                    try {
                        controller.saveCategory(takeCategory());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(principalPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(principalPanel, "Espacio vacio, asegurese de ingresar un ID y una decripcion", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        tableCategories.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableCategories.getSelectedRow();
                if (row >= 0 && controller != null) {
                    controller.edit(row);
                }
            }
        });

        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private boolean validateJTextField(JTextField field) {
        return field != null && !field.getText().trim().isEmpty();
    }

    private Category takeCategory() {
        Category cat = new Category();
        cat.setId(idCategoriaFld.getText().trim());
        cat.setDescription(descripcionCategoriaFld.getText().trim());
        return cat;
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