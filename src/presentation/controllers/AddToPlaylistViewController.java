package presentation.controllers;

import business.entities.Song;
import presentation.UIController;
import presentation.views.AddToPlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * Controller for the AddToPlaylistView, the connection between the AddToPlaylistView and the UIController.
 * Implementing an ActionListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class AddToPlaylistViewController implements ActionListener, MouseListener {
    private final AddToPlaylistView addToPlaylistView;
    private final UIController controller;

    /**
     * Constructor to create AddToPlaylistViewController
     * Creates the AddToPlaylistViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public AddToPlaylistViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        addToPlaylistView = new AddToPlaylistView(mainViewCenter,cardManager);
        addToPlaylistView.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case AddToPlaylistView.BTN_ACCOUNT_MANAGEMENT:
                controller.showLogoutCard();
                break;

            default:
                String playlistName = e.getActionCommand();
                String selectedSongName = controller.getSelectedSongName();

                LinkedList<String> songToAdd = new LinkedList<>();
                songToAdd.add(selectedSongName);

                if (!controller.songExistsInPlaylist(playlistName, selectedSongName)) {
                    controller.addSongPlaylist(playlistName, songToAdd);
                    controller.showMusicListCard(controller.loadPlaylistMusic(playlistName), playlistName);
                } else {
                    addToPlaylistView.songExistsErrorVisibility(true);
                }
                break;
        }
    }

    /**
     *  The function showAddToPlaylistView calls the method showCard in addToPlaylistView
     *
     */
    public void showAddToPlaylistView(LinkedList<String> userPlaylist){
        addToPlaylistView.showCard(userPlaylist);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        String selectedSongName = controller.getSelectedSongName();
        Song selectedSong = controller.findSong(selectedSongName);
        controller.showSongDetailsCard(selectedSong);
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
