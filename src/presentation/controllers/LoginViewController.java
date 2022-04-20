package presentation.controllers;

import presentation.UIController;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {
    private LoginView loginView;
    private UIController controller;

    public LoginViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        loginView = new LoginView();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case LoginView.BTN_LOGIN:
                if(controller.findUserNameMatch(loginView.getUsername()) && controller.findEmailMatch(loginView.getEmail()) &&
                controller.checkPasswordFormat(loginView.getPassword()) && controller.checkEmailFormat(loginView.getEmail()) &&
                controller.checkEqualPassword(loginView.getBothPasswords())){

                    controller.showHomescreenCard(controller.getPlaylistNames());
                }
                else{
                    loginView.showLoginErrorMessage();
                }
                break;
            case LoginView.BTN_FORGOTPASSWORD: //todo
                controller.showForgotPasswordCard();

                break;
            case LoginView.BTN_SINGUP: //todo
                controller.showSignUpCard();
                break;
        }

    }
}
