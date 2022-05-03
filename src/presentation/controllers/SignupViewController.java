package presentation.controllers;

import presentation.UIController;

import presentation.views.SignupView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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
            signupView.userNameErrorVisibility(true);
            errorFlag=true;
        } else {
            signupView.userNameErrorVisibility(false);
        }

        if (controller.findEmailMatch(signupView.getEmail())){
            signupView.mailErrorVisibility(true);
            errorFlag=true;
        } else if (controller.checkEmailFormat(signupView.getEmail())){
            errorFlag=true;
        }



        if(controller.checkEqualPassword(signupView.getPassword(), signupView.getRewritedPassword())){
            signupView.passwordVisibility(true);
            errorFlag=true;
        } else if(controller.checkPasswordFormat(signupView.getPassword())){
            signupView.password_2_Visibility(true);
            errorFlag=true;
        }

        if(!errorFlag) {
            controller.showHomescreenCard();
        }

    }

    public void showSignupCard () {
        signupView.showCard();
    }

}
