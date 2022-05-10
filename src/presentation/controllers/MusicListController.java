package presentation.controllers;

import presentation.UIController;
import presentation.views.HomeScreenView;
import presentation.views.MusicListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicListController implements ActionListener {
    private final MusicListView musicListView;
    private final UIController controller;

    public MusicListController (UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        musicListView = new MusicListView(topContainer, cardManager);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showMusicListCard(){//todo ha de rebre paràmetres
        musicListView.showCard();
    }

}
