package org.logic;


import org.domain.User;

public class SesionService {
    public static User user;

    public SesionService(User user) {
        this.user=user;
    }

    public static void setUser(User user) {
        SesionService.user = user;
    }
    public static void logOut(){
        user=null;
    }
    public static boolean isLoggedIn(){
        return user!=null;

    }
}
