package presentation.controllers;

import presentation.UIController;
import presentation.views.SetPlaylistNameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetPlaylistNameController implements ActionListener, MouseListener {

    private final SetPlaylistNameView setPlaylistNameView;
    private final UIController controller;

    public SetPlaylistNameController (UIController controller, JFrame topContainer, CardLayout cardManager) {
        setPlaylistNameView = new SetPlaylistNameView(topContainer, cardManager);
        setPlaylistNameView.registerController(this);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SetPlaylistNameView.BTN_DONE:
                System.out.print(setPlaylistNameView.getPlaylistName());
                controller.createPlaylist(setPlaylistNameView.getPlaylistName());
                controller.showHomescreenCard();
                break;

            case SetPlaylistNameView.BTN_MANAGEMENT:
                controller.showLogoutCard();
                break;
        }

    }

    public void showSetPlaylistNameCard () {
        setPlaylistNameView.showCard();
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
