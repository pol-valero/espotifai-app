package presentation.controllers;

import presentation.UIController;
import presentation.views.SignupView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupViewController implements ActionListener {

    private final SignupView signupView;
    private final UIController controller;

    public SignupViewController(UIController controller, SignupView signupView){
        this.signupView = signupView;
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case SignupView.BTN_SIGNUP:
                checkAllSignupFields();
                break;
        }
    }

    private void checkAllSignupFields() {//todo revisar el com trobem i avisem del errors.
        boolean errorFlag = false;
        //todo: posar les crides a la vista per mostrar els errors
        if(controller.findUserNameMatch(signupView.getUsername())){
            errorFlag=true;
        }

        if (controller.findEmailMatch(signupView.getEmail())){
            errorFlag=true;
        }

        if(controller.checkEmailFormat(signupView.getEmail())){
            errorFlag=true;
        }

        if(controller.checkEqualPassword(signupView.getPassword(), signupView.getRewritedPassword())){
            errorFlag=true;
        } else if(controller.checkPasswordFormat(signupView.getPassword())){
            errorFlag=true;
        }

        if(!errorFlag){
            controller.showHomescreenCard(controller.getPlaylistNames());
        }
    }

}
