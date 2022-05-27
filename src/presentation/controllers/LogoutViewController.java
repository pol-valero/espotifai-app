package presentation.controllers;

import presentation.UIController;
import presentation.views.LogoutView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller for the LogoutViewController, the connection between the LogoutViewController and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class LogoutViewController implements ActionListener, MouseListener{
    private UIController controller;
    private LogoutView logoutView;

    /**
     * Constructor to create LogoutViewController
     * Creates the LogoutViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public LogoutViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        logoutView = new LogoutView(mainViewCenter,cardManager);
        logoutView.registerController(this);
    }

    /**
     * Overrides the function actionPerformed to use the  showLoginCard
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case LogoutView.BTN_LOGOUT:
                controller.showLoginCard();
                break;
            case LogoutView.BTN_DELETEACCOUNT:
                controller.deleteAccountRequest();
                controller.showLoginCard();
                break;
        }
    }

    /**
     *  The function showLogoutCard calls the method showCard in logoutView
     *
     */
    public void showLogoutCard () {
        logoutView.showCard();
    }

    /**
     * Overrides the function mouseClicked to call the method
     * showHomescreenCard every time the mouse clicks in the corresponding area
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        controller.showHomescreenCard();
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
