package org.main;
import javax.swing.*;

import org.presentation.Login;

import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Login login = new Login();

        JFrame frame = new JFrame("SISTEMA DE RESERVAS");
        frame.setIconImage(new ImageIcon("src/main/resources/logoReservas.png").getImage());
        frame.setContentPane(login.getPrincipalPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("Probando");
        System.out.println("Probando1");

    }
}