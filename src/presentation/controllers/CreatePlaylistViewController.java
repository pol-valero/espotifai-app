package presentation.controllers;

import presentation.UIController;
import presentation.views.CreatePlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreatePlaylistViewController implements ActionListener, MouseListener {

    private final CreatePlaylistView createPlaylistView;
    private final UIController controller;

    public CreatePlaylistViewController(UIController controller, JFrame topContainer, CardLayout cardManager) {
        createPlaylistView = new CreatePlaylistView(topContainer, cardManager);
        createPlaylistView.registerController(this);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CreatePlaylistView.BTN_DONE:
                if(!controller.findPlaylistName(e.getActionCommand())) {
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

    public void showSetPlaylistNameCard () {
        createPlaylistView.showCard();
    }

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
