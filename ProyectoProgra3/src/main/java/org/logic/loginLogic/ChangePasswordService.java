package org.logic.loginLogic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.User;

public class ChangePasswordService {

    public void changePassword(String id, String actual, String nueva, String confirmacion) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("El ID de usuario no es válido");
        }
        if (actual.isEmpty() || nueva.isEmpty() || confirmacion.isEmpty()) {
            throw new Exception("Todos los campos de contraseña son obligatorios");
        }

        if (!nueva.equals(confirmacion)) {
            throw new Exception("La contraseña nueva y la confirmación no coinciden");
        }

        Data data = XMLRepository.instance().load();
        String idUpper = id.toUpperCase();
        User userActualizado = null;

        if (idUpper.startsWith("ADM")) {
            userActualizado = data.getAdministrators().stream()
                    .filter(a -> a.getId().equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(null);
        } else if (idUpper.startsWith("FUN")) {
            userActualizado = data.getEmployees().stream()
                    .filter(e -> e.getId().equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(null);
        }

        if (userActualizado == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (!userActualizado.getPassword().equals(actual)) {
            throw new Exception("La contraseña actual es incorrecta");
        }

        userActualizado.setPassword(nueva);
        XMLRepository.instance().store(data);
    }
}