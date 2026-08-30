package org.logic.loginLogic;

import org.data.Data;

import org.data.XMLRepository;
import org.domain.User;

import java.io.File;
import java.util.Objects;

public class LoginService {
    private static LoginService theInstance;

    public static LoginService instance() {
        if (theInstance == null) theInstance = new LoginService();
        return theInstance;
    }

    private Data data;

    private LoginService() {
        try {
            data = XMLRepository.instance().load();
        } catch (Exception e) {
            System.err.println("Error al cargar la base de datos: " + e.getMessage());
            data = new Data();
        }
    }

    public User read(String id, String password) throws Exception {
        //System.out.println("Buscando el XML en: " + new File("data.xml").getAbsolutePath());
        if (id == null || id.isEmpty()) {
            throw new Exception("EL ESPACIO DEL ID DEBE DE ESTAR COMPLETO");
        }

        String idUpper = id.toUpperCase();
        User result = null;

        if (idUpper.startsWith("ADM")) {
            result = data.getAdministrators().stream()
                    .filter(i -> i.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (result == null) {
                throw new Exception("Administrador no existe");
            }

        } else if (idUpper.startsWith("FUN")) {
            result = data.getEmployees().stream()
                    .filter(i -> i.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (result == null) {
                throw new Exception("Funcionario no existe");
            }

        } else {
            throw new Exception("El id debe iniciar con su distintivo");
        }

        if (!result.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }

        return result;
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

