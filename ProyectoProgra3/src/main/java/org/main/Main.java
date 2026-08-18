package org.main;
import javax.swing.JFrame;
import org.presentation.Login;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Login login = new Login();

        JFrame frame = new JFrame("SISTEMA DE RESERVAS");
        frame.setContentPane(login.getPrincipalPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}