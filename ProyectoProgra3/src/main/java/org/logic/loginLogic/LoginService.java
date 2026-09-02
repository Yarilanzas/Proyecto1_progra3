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
            File file = new File("data.xml");
            System.out.println("Ruta absoluta del XML: " + file.getAbsolutePath());
            System.out.println("¿Existe el archivo?: " + file.exists());

            data = XMLRepository.instance().load();

            System.out.println("Administradores cargados: " + (data != null && data.getAdministrators() != null ? data.getAdministrators().size() : 0));
            System.out.println("Funcionarios cargados: " + (data != null && data.getEmployees() != null ? data.getEmployees().size() : 0));

        } catch (Exception e) {
            System.err.println("Error al cargar la base de datos: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa para ver si falla la lectura XML
            data = new Data();
        }


        try {
            data = XMLRepository.instance().load();
        } catch (Exception e) {
            System.err.println("Error al cargar la base de datos: " + e.getMessage());
            data = new Data();
        }
    }

    public User read(String id, String password) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("EL ESPACIO DEL ID DEBE DE ESTAR COMPLETO");
        }

        String idClean = id.trim();
        String idUpper = idClean.toUpperCase();
        User result = null;

        if (idUpper.startsWith("ADM")) {
            result = data.getAdministrators().stream()
                    .filter(i -> i.getId() != null && i.getId().equalsIgnoreCase(idClean))
                    .findFirst()
                    .orElse(null);

            if (result == null) {
                throw new Exception("Administrador no existe");
            }

        } else if (idUpper.startsWith("FUN")) {
            result = data.getEmployees().stream()
                    .filter(i -> i.getId() != null && i.getId().equalsIgnoreCase(idClean))
                    .findFirst()
                    .orElse(null);

            if (result == null) {
                throw new Exception("Funcionario no existe");
            }

        } else {
            throw new Exception("El id debe iniciar con su distintivo (ADM o FUN)");
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

