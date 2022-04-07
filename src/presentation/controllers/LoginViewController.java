package presentation.controllers;

import presentation.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {
    private LoginView loginView;

    public LoginViewController(LoginView loginView){
        this.loginView=loginView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case loginView.login: //todo

                break;
            case loginView.forgotPassword: //todo

                break;
            case loginView.signup: //todo
                break;
        }

    }
}
