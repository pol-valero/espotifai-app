package presentation.controllers;

import business.entities.Song;
import presentation.UIController;
import presentation.views.HomeScreenView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Controller for the HomescreenView, the connection between the HomescreenView and the UIController.
 * Implementing an ActionListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class HomescreenViewController implements ActionListener {
    private final HomeScreenView homeScreenView;
    private final UIController controller;

    /**
     * Constructor to create AddSongViewController
     * Creates the AddSongViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public HomescreenViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        homeScreenView = new HomeScreenView(mainViewCenter,cardManager);
        homeScreenView.registerController(this);
    }

    /**
     * Overrides the function actionPerformed to use the  showHomescreenViewCard
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case HomeScreenView.BTN_STATISTICS:
                controller.showStadisticsCard();
                break;
            case HomeScreenView.BTN_ACCOUNTMANAGER:
                controller.showLogoutCard();
                break;
            case HomeScreenView.BTN_CREATEPLAYLIST:
                controller.showCreatePlaylistCard();
                break;
            default:
                String playlistName = e.getActionCommand();
                controller.showMusicListCard(controller.loadPlaylistMusic(playlistName), playlistName);

                break;
        }
    }

    /**
     *  The function showHomescreenCard calls the method  showHomescreen in addSongView
     *
     */
    public void showHomescreenCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        homeScreenView.showCard(usersPlaylists,publicPlaylists);
    }
}
