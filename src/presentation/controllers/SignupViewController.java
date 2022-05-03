package presentation.controllers;

import presentation.UIController;

import presentation.views.SignupView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupViewController implements ActionListener  {

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

}
