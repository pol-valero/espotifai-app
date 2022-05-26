package presentation.controllers;

import presentation.UIController;
import presentation.views.AddToPlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToPlaylistViewController implements ActionListener {
    private final AddToPlaylistView addToPlaylistView;
    private final UIController controller;

    public AddToPlaylistViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        addToPlaylistView=new AddToPlaylistView(mainViewCenter,cardManager);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void showAddToPlaylistView(){
        addToPlaylistView.showCard();
    }
}
