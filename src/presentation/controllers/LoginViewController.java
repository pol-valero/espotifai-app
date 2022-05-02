package presentation.controllers;

import presentation.UIController;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {
    private final LoginView loginView;
    private final UIController controller;

    public LoginViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        loginView = new LoginView(topContainer, cardManager);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case LoginView.BTN_LOGIN:
                if(controller.loginRequest(loginView.getLoginField(), loginView.getPasswordField())){
                    controller.showHomescreenCard();
                }
                else{
                    loginView.showLoginErrorMessage();
                }
                break;
            case LoginView.BTN_FORGOTPASSWORD: //todo
                //Com que es una funcionalitat "opcional" de moment no ho farem
                //controller.showForgotPasswordCard();
                break;
            case LoginView.BTN_SINGUP: //todo
                controller.showSignUpCard();
                break;
        }

    }

    public void showLoginCard () {
        loginView.showCard();
    }
}
