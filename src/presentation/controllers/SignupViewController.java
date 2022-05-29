package presentation.controllers;

import business.entities.User;
import presentation.UIController;

import presentation.views.SignupView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller for the SignupView, the connection between SignupView and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class SignupViewController implements ActionListener, MouseListener {

    private final SignupView signupView;
    private final UIController controller;

    /**
     * Constructor to create SignupViewController
     * Creates the SignupViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param topContainer this is the JPanel displayed in the beginning of the application
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public SignupViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        signupView = new SignupView(topContainer, cardManager);
        signupView.registerController(this);
    }

    /**
     * Overrides the function actionPerformed to call the method checkAllSignupFields
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case SignupView.BTN_SIGNUP:
                checkAllSignupFields();
                break;
        }
    }

    /**
     * checkAllSignupFields checks if the information introduced in all the singu files is correct and
     * all the methods that will handle the logic
     */
    private void checkAllSignupFields() {
        boolean errorFlag = false;

        if(controller.findUserNameMatch(signupView.getUsername()) || signupView.getUsername().isBlank()){
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
            if(controller.checkPasswordFormat(signupView.getPassword()) || signupView.getPassword().isBlank()){
                signupView.passwordFormatErrorVisibility(true);
                errorFlag=true;
            } else {
                signupView.passwordFormatErrorVisibility(false);
            }
        }

        if(!errorFlag) {
            User user = new User(signupView.getUsername(), signupView.getEmail(), signupView.getPassword());
            controller.signUpRequest(user);
            controller.showHomescreenCard();
            signupView.clearFields();
        }

    }
    /**
     *  The function showSignupCard calls the method showCard in signupView
     *
     */
    public void showSignupCard () {
        signupView.showCard();
    }

    /**
     * Overrides the function mouseClicked to call the method
     * showLoginCard every time the mouse clicks in the corresponding area
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        controller.showLoginCard();
        signupView.clearFields();
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
