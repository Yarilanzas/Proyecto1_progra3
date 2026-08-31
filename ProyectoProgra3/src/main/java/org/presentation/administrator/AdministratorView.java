package org.presentation.administrator;

import com.github.lgooddatepicker.components.DatePicker;
import org.domain.CalendarData;
import org.domain.Category;
import org.domain.Employee;
import org.logic.ReservationQueryService;
import org.presentation.CalendarTableModel;
import org.presentation.employee.*;
import org.main.Main;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;


public class AdministratorView  implements PropertyChangeListener {
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
    private JTextField IdCategoriaFld;
    private JTextField DescripcionCategoriaFld;
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
    private DatePicker fechaPicker;
    private JPanel PanelCate;
    private JPanel CalendarizacionPanel;
    private JTable Calendarizaciontable;
    private JPanel JPCategoria;
    private JPanel adminPanel;

    private EmployeeModel model;
    private EmployeeController controller;
    private final ReservationQueryService queryService = new ReservationQueryService();


    //Atributos administrador
    private ControllerAdministrator controllerAdm;

    public void setModelAdm(ModelAdministrator modelAdm) {
        this.modelAdm = modelAdm;
    }

    private ModelAdministrator modelAdm;

    public void setControllerAdm(ControllerAdministrator controllerAdm) {
        this.controllerAdm = controllerAdm;
    }
    ///



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
        return adminPanel;
    }
    public JPanel getCalendarizacionPanel() { return CalendarizacionPanel; }

    public AdministratorView() {

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

        //Calendarizacion

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate fecha = fechaPicker.getDate();
                    Category categoria = (Category) categoriaComboBox.getSelectedItem();

                    if (fecha == null || categoria == null){
                        JOptionPane.showMessageDialog(CalendarizacionPanel,"Seleccione fecha y categoria");
                        return;
                    }

                    CalendarData calendarData = queryService.getCalendar(fecha,categoria);
                    Calendarizaciontable.setModel(new CalendarTableModel(calendarData));
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(CalendarizacionPanel,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //categoria
        guardarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateJTextField(IdCategoriaFld) && validateJTextField(DescripcionCategoriaFld) ) {
                    try {
                        controllerAdm.saveCategory(takeCategory());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(JPCategoria, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(JPCategoria, "Espacio vacio, asegurese de ingresar un ID y una decripcion", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private Category takeCategory(){
        Category cat = new Category();
        cat.setId(IdCategoriaFld.getText().trim());
        cat.setId(DescripcionCategoriaFld.getText().trim());
        return cat;
    }
    /////

    private boolean validateJTextField(JTextField name) {
        return !name.getText().trim().isEmpty();
    }
}
