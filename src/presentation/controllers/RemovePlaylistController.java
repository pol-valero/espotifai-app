package presentation.controllers;

import presentation.UIController;
import presentation.views.RemovePlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePlaylistController implements ActionListener {

    private UIController controller;
    private RemovePlaylistView removePlaylistView;

    public RemovePlaylistController (UIController controller, JFrame topContainer, CardLayout cardManager) {
        removePlaylistView = new RemovePlaylistView(topContainer, cardManager);
        removePlaylistView.registerController(this);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case RemovePlaylistView.BTN_REMOVE:
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

    public void showRemovePlaylistCard(String playlistName, int songNumber) {
        removePlaylistView.showCard(playlistName, songNumber);
    }
}
