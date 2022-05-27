package presentation.controllers;

import presentation.UIController;
import presentation.views.CreatePlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller for the CreatePlaylistView, the connection between the CreatePlaylistView and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */

public class CreatePlaylistViewController implements ActionListener, MouseListener {

    private final CreatePlaylistView createPlaylistView;
    private final UIController controller;

    /**
     * Constructor to create CreatePlaylistViewController
     * Creates the CreatePlaylistViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public CreatePlaylistViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager) {
        createPlaylistView = new CreatePlaylistView(mainViewCenter, cardManager);
        createPlaylistView.registerController(this);
        this.controller = controller;
    }

    /**
     * Overrides the function actionPerformed to use the Card of different views
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CreatePlaylistView.BTN_DONE:
                if(!controller.findPlaylistName(createPlaylistView.getPlaylistName()) && !createPlaylistView.getPlaylistName().isBlank()) {
                    createPlaylistView.wrongNameErrorVisibility(false);
                    controller.createPlaylist(createPlaylistView.getPlaylistName());
                    controller.showHomescreenCard();
                } else {
                    createPlaylistView.wrongNameErrorVisibility(true);
                }
                break;

            case CreatePlaylistView.BTN_MANAGEMENT:
                controller.showLogoutCard();
                break;
        }

    }

    /**
     *  The function showSetPlaylistNameCard calls the method showCard in createPlaylistView
     *
     */
    public void showCreatePlaylistCard() {
        createPlaylistView.showCreateCard();
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
