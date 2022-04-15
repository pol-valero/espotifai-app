package presentation.controllers;

import presentation.UIController;
import presentation.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {
    private LoginView loginView;
    private UIController controller;

    public LoginViewController(UIController controller, LoginView loginView){
        this.controller = controller;
        this.loginView=loginView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case LoginView.BTN_LOGIN: //todo
                if(controller.checkLoginInfo(loginView.getInfo())){
                    controller.showHomescreenCard(controller.getPlaylist());
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
