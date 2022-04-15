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
            case SignupView.BTN_SIGNUP: //todo revisar el com trobem i avisem del errors.
                 /*int[] errors = controller.checkSingupInfo(signupView.getinfo());

                 if(lookForErrors(errors)){
                    controller.showHomescreenCard(controller.getPlaylist());
                }
                else{
                    signupView.showSignupErrorMessages(errors);
                }
                break;*/
        }
    }

    public boolean lookForErrors(int[] errors) { //todo podem utilitzar aquest mètode aquí?
        boolean flag = false;
        for (int error : errors) {
            if (error == 1) {
                flag = true;
            }
        }
        return flag;
    }
}
