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

public class AddSongViewController implements ActionListener, MouseListener {

    private final AddSongView addSongView;
    private final UIController controller;

    public AddSongViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        addSongView = new AddSongView(topContainer, cardManager);
        addSongView.registerController(this);
    }

    /**
     * S'ha de fer la funció que rep els parametres i fica o treu els errors
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case AddSongView.BTN_ADD:
                controller.showMusicListCard();
                break;

            case AddSongView.BTN_MANAGEMENT:
                controller.showLogoutCard();
                break;
        }
    }


    public void showAddSongCard () {
        addSongView.showCard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.showMusicListCard();
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
