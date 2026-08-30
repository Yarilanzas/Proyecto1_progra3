package org.presentation.login;

import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Login extends JDialog  implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel PrincipalPanel;
    private JTextField idtextField1;
    private JTextField textField2;
    private JButton ingresarButton;
    private JButton cancelarButton;
    private JButton cambiarButton;

    public Login() {


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        idtextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateJTextField(idtextField1))
                    try{
                        controller.login(idtextField1.getText());
                        JOptionPane.showMessageDialog(contentPane, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
        });

    }
    ControllerLogin controller;
    ModelLogin model;

    public void setController(ControllerLogin controller) {
        this.controller = controller;
    }
    public JPanel getPanel() {
        return PrincipalPanel;
    }

    public void setModel(ModelLogin model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }



    private boolean validateJTextField(JTextField name) {
        boolean valid = true;
        if (name.getText().isEmpty()) {
            valid = false;
        }
        return valid;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
    /*public static void main(String[] args) {
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/



