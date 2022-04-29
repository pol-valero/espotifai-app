package presentation.controllers;

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

    public HomescreenViewController(UIController controller, JFrame topContainer, CardLayout cardManager,
                                    LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists){
        this.controller = controller;
        homeScreenView = new HomeScreenView(topContainer,cardManager,usersPlaylists,publicPlaylists);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showHomescreenCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        homeScreenView.showCard(usersPlaylists,publicPlaylists);
    }
}
