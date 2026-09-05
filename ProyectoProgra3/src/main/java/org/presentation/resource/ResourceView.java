package org.presentation.resource;

import org.domain.Category;
import org.domain.Resource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                String descripcion = (textField4.getText().trim()).toUpperCase();

                if (descripcion.isEmpty()){
                    JOptionPane.showMessageDialog(principalPanel,"Debe una descripcion");
                    return;
                }else {
                    controller.searchbyDescription(descripcion);
                }


                if (model.getCategories().isEmpty()){
                    JOptionPane.showMessageDialog(principalPanel,"No se encontro ninguna categoria con esa descripcion");
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

                break;

            case ResourceModel.RESOURCES:
                int[] colum = {ResourceTableModel.ID, ResourceTableModel.DESCRIPCION};
                ResourceTable.setModel(new ResourceTableModel(colum, model.getResources()));
                break;
        }
        this.principalPanel.revalidate();
    }

    private boolean validateJTextField(JTextField field){
        return field != null && field.getText().trim().isEmpty();
    }
}

