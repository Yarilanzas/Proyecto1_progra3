package org.logic.loginLogic;


import org.domain.User;

public class SesionService{
    private static User usuario;

    public static User getUsuario() { return usuario;}
    public static void setUsuario(User usuario) { SesionService.usuario = usuario;}
    public static void logout(){SesionService. usuario = null;}
    public static boolean isLoggedIn() { return usuario != null; }

}
