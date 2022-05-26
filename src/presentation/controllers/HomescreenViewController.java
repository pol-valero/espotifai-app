package presentation.controllers;

import business.entities.Playlist;
import presentation.UIController;
import presentation.views.HomeScreenView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class HomescreenViewController implements ActionListener {
    private final HomeScreenView homeScreenView;
    private final UIController controller;

    public HomescreenViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        homeScreenView = new HomeScreenView(mainViewCenter,cardManager);
        homeScreenView.registerController(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case HomeScreenView.BTN_STATISTICS:
                break;
            case HomeScreenView.BTN_ACCOUNTMANAGER:
                controller.showLogoutCard();
                break;
            case HomeScreenView.BTN_CREATEPLAYLIST:
                controller.showSetPlaylistNameCard();
                break;
            default:
                String playlistName = e.getActionCommand();
                controller.showMusicListCard(controller.loadPlaylistMusic(playlistName), playlistName);
                break;
        }
    }

    public void showHomescreenCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        homeScreenView.showCard(usersPlaylists,publicPlaylists);
    }
}
