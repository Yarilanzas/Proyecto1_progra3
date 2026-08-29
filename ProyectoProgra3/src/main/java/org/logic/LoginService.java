package org.logic;

import org.data.Data;

import org.domain.User;
import org.presentation.login.ControllerLogin;

import java.awt.*;

public class LoginService {
    private static LoginService theInstance;

    public static LoginService instance() {
        if (theInstance == null) theInstance = new LoginService();
        return theInstance;
    }

    private Data data;

    private LoginService() {
        data = new Data();
    }

    public User read(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("EL ESPACIO DEL ID DEBE DE ESTAR COMPLETO");
        }

        String idUpper = id.toUpperCase();

        if (idUpper.startsWith("ADM")) {
            User result = data.getAdministrators().stream()
                    .filter(i -> i.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (result != null) {
                return result;
            } else {
                throw new Exception("Administrador no existe");
            }
        } else if (idUpper.startsWith("FUN")) {
            User result = data.getEmployees().stream()
                    .filter(i -> i.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (result != null) {
                return result;
            } else {
                throw new Exception("Funcionario no existe");
            }
        } else {
            throw new Exception("El id debe iniciar con su distintivo");
        }
    }


}

    /*public static User user;

    public LoginService(User user) {
        this.user=user;
    }

    public static void setUser(User user) {
        LoginService.user = user;
    }
    public static void logOut(){
        user=null;
    }
    public static boolean isLoggedIn(){
        return user!=null;

    }*/

