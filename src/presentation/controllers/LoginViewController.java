package presentation.controllers;

import presentation.UIController;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginViewController implements ActionListener, MouseListener {
    private final LoginView loginView;
    private final UIController controller;

    public LoginViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        loginView = new LoginView(topContainer, cardManager);
        loginView.registerController(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case LoginView.BTN_LOGIN:
                if(controller.loginRequest(loginView.getLoginField(), loginView.getPasswordField())){
                    controller.showHomescreenCard();
                }
                else{
                    loginView.loginErrorVisibility(true);
                }
                break;
            case LoginView.BTN_SIGNUP: //todo
                controller.showSignUpCard();
                break;
        }

    }

    public void showLoginCard () {
        loginView.showCard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.showSignUpCard();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
