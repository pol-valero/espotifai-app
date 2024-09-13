package presentation.controllers;

import presentation.UIController;
import presentation.views.RemovePlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the RemovePlaylist, the connection between the RemovePlaylist and the UIController.
 * Implementing an ActionListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class RemovePlaylistController implements ActionListener {

    private UIController controller;
    private RemovePlaylistView removePlaylistView;

    /**
     * Constructor to create RemovePlaylistController
     * Creates the AddToPlaylistViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public RemovePlaylistController (UIController controller, JPanel mainViewCenter, CardLayout cardManager) {
        removePlaylistView = new RemovePlaylistView(mainViewCenter, cardManager);
        removePlaylistView.registerController(this);
        this.controller = controller;
    }

    /**
     * Overrides the function actionPerformed to use several view Cards
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case RemovePlaylistView.BTN_REMOVE:
                controller.deletePlaylist(removePlaylistView.getPlaylistName());
                controller.showHomescreenCard();
                break;
            case RemovePlaylistView.BTN_CANCEL:
                String playlistName = controller.getCurrentPlaylist();
                controller.showMusicListCard(controller.loadPlaylistMusic(playlistName), playlistName);
                break;
            case RemovePlaylistView.BTN_MANAGEMENT:
                controller.showLogoutCard();
                break;
        }
    }

    /**
     *  The function showRemovePlaylistCard calls the method showCard in removePlaylistView
     *
     */
    public void showRemovePlaylistCard(String playlistName, int songNumber) {
        removePlaylistView.showCard(playlistName, songNumber);
    }
}
