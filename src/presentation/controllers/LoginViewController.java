package presentation.controllers;

import presentation.UIController;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller for the LoginView, the connection between theLoginView and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class LoginViewController implements ActionListener, MouseListener {

    private final LoginView loginView;
    private final UIController controller;

    /**
     * Constructor to create CreatePlaylistViewController
     * Creates the CreatePlaylistViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param topContainer this is the JPanel displayed in the beginning of the application
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public LoginViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        loginView = new LoginView(topContainer, cardManager);
        loginView.registerController(this);
    }
    /**
     * Overrides the function actionPerformed to use the Card of different views
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
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

    /**
     *  The function showLoginCard calls the method showCard in loginView
     *
     */
    public void showLoginCard () {
        loginView.showCard();
    }

    /**
     * Overrides the function mouseClicked to call the method
     * showSignUpCard every time the mouse clicks in the corresponding area
     *
     */
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
