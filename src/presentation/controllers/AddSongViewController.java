package presentation.controllers;
import business.entities.User;
import presentation.UIController;

import presentation.views.AddSongView;
import presentation.views.SignupView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Controller for the AddSongView, the connection between the AddSongView and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */

public class AddSongViewController implements ActionListener, MouseListener {

    private final AddSongView addSongView;
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

    public AddSongViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        addSongView = new AddSongView(mainViewCenter, cardManager);
        addSongView.registerController(this);
    }

    /**
     * Overrides the function actionPerformed to use the showAddSongCard
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case AddSongView.BTN_ADD:
                String currentPlaylist = controller.getCurrentPlaylist();
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                break;

            case AddSongView.BTN_MANAGEMENT:
                controller.showLogoutCard();
                break;
        }
    }

    /**
     *  The function showAddSongCard calls the method showCard in addSongView
     *
     */
    public void showAddSongCard () {
        addSongView.showCard();
    }

    /**
     * Overrides the function mouseClicked to call the method
     * getCurrentPlaylist every time the mouse clicks in the corresponding area
     *  and send the string with the current playlist to
     * the method loadPlaylistMusic within the controller of howMusicListCard
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        String currentPlaylist = controller.getCurrentPlaylist();
        controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
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
