package presentation.controllers;

import presentation.UIController;

import presentation.views.SignupView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignupViewController implements ActionListener, MouseListener {

    private final SignupView signupView;
    private final UIController controller;

    public SignupViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        signupView = new SignupView(topContainer, cardManager);
        signupView.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case SignupView.BTN_SIGNUP:
                checkAllSignupFields();
                break;
        }
    }

    private void checkAllSignupFields() {
        boolean errorFlag = false;

        if(controller.findUserNameMatch(signupView.getUsername())){
            signupView.existingUsernameErrorVisibility(true);
            errorFlag=true;
        } else {
            signupView.existingUsernameErrorVisibility(false);
        }

        if (controller.checkEmailFormat(signupView.getEmail())){
            signupView.mailFormatErrorVisibility(true);
            errorFlag=true;
        } else {
            signupView.mailFormatErrorVisibility(false);
            if (controller.findEmailMatch(signupView.getEmail())){
                signupView.existingMailErrorVisibility(true);
                errorFlag=true;
            } else {
                signupView.existingMailErrorVisibility(false);
            }
        }



        if(controller.checkEqualPassword(signupView.getPassword(), signupView.getRepeatedPassword())){
            signupView.notEqualPasswordsErrorVisibility(true);
            errorFlag=true;
        } else {
            signupView.notEqualPasswordsErrorVisibility(false);
            if(controller.checkPasswordFormat(signupView.getPassword())){
                signupView.passwordFormatErrorVisibility(true);
                errorFlag=true;
            } else {
                signupView.passwordFormatErrorVisibility(false);
            }
        }

        if(!errorFlag) {
            controller.showHomescreenCard();
        }

    }

    public void showSignupCard () {
        signupView.showCard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.showLoginCard();
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
