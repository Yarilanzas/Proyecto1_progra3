package org.presentation.resource;

import org.domain.Category;
import org.domain.Resource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResourceView implements PropertyChangeListener {
    private JButton imprimirButton2;
    private JButton buscarButton2;
    private JComboBox comboBoxCategorias;
    private JTextField textField4;
    private JTextField IDRecursofld;
    private JTextField DescripcionRecursofld;
    private JButton guardarButton2;
    private JButton borrarButton2;
    private JButton limpiarButton2;
    private JPanel panel;
    private JPanel principalPanel;
    private JTable ResourceTable;
    private JComboBox RecursoCategoriafld;


    //private JPanel ResourceTable;

    private ResourceModel model;
    private ResourceController controller;
    private JTable tableCategories;


    public ResourceView() {

        buscarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = textField4.getText().trim().toUpperCase();
                Category categoriaSeleccionada = (Category) comboBoxCategorias.getSelectedItem();

                if (descripcion.isEmpty() && categoriaSeleccionada == null) {
                    controller.list();
                    return;
                }

                if (!descripcion.isEmpty()) {
                    controller.searchbyDes(descripcion);
                }
                else if (categoriaSeleccionada != null) {
                    controller.searchbyCategory(categoriaSeleccionada.getDescription());
                }
                if (model.getResources().isEmpty()) {
                    JOptionPane.showMessageDialog(principalPanel, "No se encontraron recursos con los criterios especificados.");
                }
            }


        });
        guardarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(validateJTextField(IDRecursofld) && validateJTextField(DescripcionRecursofld)){

                        controller.saveResource(takeResoruce());
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(principalPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        limpiarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        borrarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = IDRecursofld.getText().trim();
                if (id.isEmpty()){
                    JOptionPane.showMessageDialog(principalPanel,"Debe ingresar el id para borrar");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(principalPanel,"Seguro/a que desea borrar la categoria " + id + "?",
                        "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;
                try{
                    controller.delete(id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(principalPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ResourceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = ResourceTable.getSelectedRow();
                if (row >= 0 && controller != null) {
                    controller.edit(row);
                }
            }
        });
    }
    public Resource takeResoruce(){
        Resource r= new Resource();
        r.setId(IDRecursofld.getText().trim());
        r.setCategory((Category) RecursoCategoriafld.getSelectedItem());
        r.setDescription(DescripcionRecursofld.getText().trim());
        return  r;
    }
    public JPanel getPanel() {
        return principalPanel;
    }

    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public ResourceController getController() {
        return controller;
    }

    public void setController(ResourceController controller) {
        this.controller = controller;
    }

    public ResourceModel getModel() {
        return model;
    }

    public void setModel(ResourceModel model) {
        this.model = model;
        this.model.addPropertyChangeListener(this);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case ResourceModel.CATEGORIES:
                DefaultComboBoxModel<Category> modelo = new DefaultComboBoxModel<>();
                DefaultComboBoxModel<Category> modelo2 = new DefaultComboBoxModel<>();

                for (Category c : model.getCategories()){
                    modelo.addElement(c);
                    modelo2.addElement(c);

                }
                comboBoxCategorias.setModel(modelo);
                RecursoCategoriafld.setModel(modelo2);
                comboBoxCategorias.setSelectedIndex(-1);

                break;

            case ResourceModel.RESOURCES:
                int[] colum = {ResourceTableModel.ID, ResourceTableModel.CATEGORIA, ResourceTableModel.DESCRIPCION};
                ResourceTable.setModel(new ResourceTableModel(colum, model.getResources()));
                break;
            case ResourceModel.CURRENT:
                Resource curr = model.getCurrent();
                IDRecursofld.setText(curr.getId() == null ? "" : curr.getId());
                DescripcionRecursofld.setText(curr.getDescription() == null ? "" : curr.getDescription());

                RecursoCategoriafld.setSelectedItem(curr.getCategory());


                if (curr.getCategory() != null) {
                    ComboBoxModel<Category> comboModel = RecursoCategoriafld.getModel();
                    for (int i = 0; i < comboModel.getSize(); i++) {
                        Category item = comboModel.getElementAt(i);
                        if (item.getId().equals(curr.getCategory().getId())) {
                            RecursoCategoriafld.setSelectedIndex(i);
                            break;
                        }
                    }
                } else {
                    RecursoCategoriafld.setSelectedIndex(-1);
                }

                break;
        }
        this.principalPanel.revalidate();
    }

    private boolean validateJTextField(JTextField field){
        return field != null && !field.getText().trim().isEmpty();
    }
}

