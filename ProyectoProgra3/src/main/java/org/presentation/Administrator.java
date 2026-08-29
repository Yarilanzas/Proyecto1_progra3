package org.presentation;

import org.domain.Employee;
import org.presentation.employee.*;
import org.main.Main;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class Administrator implements PropertyChangeListener {
    private JTabbedPane tabbedPane1;
    private JPanel BusquedaPanel;
    private JTextField nombreFld;
    private JButton buscarButton;
    private JButton ImprimirButton;
    private JTextField idFld;
    private JLabel ID;
    private JTextField idfuncionarios;
    private JLabel Nombre;
    private JTextField nombreFuncionarios;
    private JTextField telefono;
    private JButton guardarButton;
    private JButton borrarButton;
    private JButton limpiarButton;
    private JTable table1;
    private JTextField fechaFld;
    private JButton button1;
    private JComboBox categoriaComboBox;
    private JButton cargarButton;
    private JButton imprimirButton;
    private JTextField desdeFld;
    private JTextField hastaFld;
    private JButton button2;
    private JTextField desdeAFld;
    private JButton button3;
    private JTextField hastaAFld;
    private JButton cargarButtonA;
    private JTable table2;
    private JTextField descripcionFld;
    private JButton buscarButton1;
    private JButton imprimirButton1;
    private JTextField IDfld;
    private JTextField DescripcionFld2;
    private JButton guardarButton1;
    private JButton borrarButton1;
    private JButton limpiarButton1;
    private JTable table3;
    private JButton imprimirButton2;
    private JButton buscarButton2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton2;
    private JButton borrarButton2;
    private JButton limpiarButton2;
    private JTable table4;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JPanel Panel;

    private EmployeeModel model;
    private EmployeeController controller;

    private Employee take(){
        Employee emp = new Employee();
        emp.setId(idfuncionarios.getText().trim());
        emp.setPassword(idfuncionarios.getText().trim());
        emp.setName(nombreFuncionarios.getText().trim());
        emp.setPhone(telefono.getText().trim());
        return emp;
    }


    private boolean validate(){
        boolean valid = true;

        if (idfuncionarios.getText().trim().isEmpty()){
            valid = false;
            idfuncionarios.setBackground(Main.BACKGROUND_ERROR);
            idfuncionarios.setToolTipText("Se requiere el campo de ID");
        }else {
            idfuncionarios.setBackground(null);
            idfuncionarios.setToolTipText(null);
        }

        if (nombreFuncionarios.getText().trim().isEmpty()){
            valid = false;
            nombreFuncionarios.setBackground(Main.BACKGROUND_ERROR);
            nombreFuncionarios.setToolTipText("Se requiere el campo de Nombre");
        }else {
            nombreFuncionarios.setBackground(null);
            nombreFuncionarios.setToolTipText(null);
        }

        if (nombreFuncionarios.getText().trim().isEmpty()){
            valid = false;
            nombreFuncionarios.setBackground(Main.BACKGROUND_ERROR);
            nombreFuncionarios.setToolTipText("Se requiere el campo de Nombre");
        }else {
            nombreFuncionarios.setBackground(null);
            nombreFuncionarios.setToolTipText(null);
        }

        return valid;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        switch (evt.getPropertyName()){
            case EmployeeModel.LIST:
                int[] cols = {EmployeeTableModel.ID,EmployeeTableModel.NOMBRE, EmployeeTableModel.TELEFONO};
                table1.setModel(new EmployeeTableModel(cols, model.getList()));
                break;
            case EmployeeModel.CURRENT:
                Employee curr = model.getCurrent();
                idfuncionarios.setText(curr.getId() == null ? "" : curr.getId());
                nombreFuncionarios.setText(curr.getName() == null ? "" : curr.getName());
                telefono.setText(curr.getPhone() == null ? "" : curr.getPhone());
                break;
        }
    }

    public JPanel getPanel() {
        return Panel;
    }

    public Administrator() {

        model = new EmployeeModel();
        controller = new EmployeeController(model);
        model.addPropertyChangeListener(this);

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();
                if (row >= 0){
                    controller.edit(row);
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idFld.getText().trim();
                String nombre = nombreFld.getText().trim();

                if (id.isEmpty() && nombre.isEmpty()){
                    JOptionPane.showMessageDialog(Panel,"Debe ingresar nombre o ID");
                    return;
                }

                if (!id.isEmpty()){
                    controller.search(id,true);
                }else{
                    controller.search(nombre,false);
                }

                if (model.getList().isEmpty()){
                    JOptionPane.showMessageDialog(Panel,"No se encontro el funcionario");
                }
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()){
                    Employee emp = take();
                    try {
                        controller.create(emp);
                        JOptionPane.showMessageDialog(Panel,"Funcionario guardado correctamente");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String id = idfuncionarios.getText().trim();
                if (id.isEmpty()){
                    JOptionPane.showMessageDialog(Panel,"Seleccionar Funcionario");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(Panel,"Seguro/a que desea borrar al funcionario " + id + "?",
                        "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;
                try{
                    controller.delete(id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });

    }
}
