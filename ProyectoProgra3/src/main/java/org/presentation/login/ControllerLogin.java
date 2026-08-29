package org.presentation.login;
import org.logic.LoginService;
import org.domain.User;

public class ControllerLogin {
    Login view;
    ModelLogin model;

    public ControllerLogin(Login view, ModelLogin model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void readId(){

    }
    public void logIn(User user) throws Exception{

    }

    public void login(String id) {
        try {
            User usuarioLogueado = service.read(id);
            if (id.toUpperCase().startsWith("ADM")) {

                AdminView adminWindow = new AdminView();
                adminWindow.setVisible(true);

            } else if (id.toUpperCase().startsWith("FUN")) {
                FuncionarioView funcionarioWindow = new FuncionarioView();
                funcionarioWindow.setVisible(true);
            }
            Login.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, e.getMessage(), "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
