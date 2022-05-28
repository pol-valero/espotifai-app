package presentation.controllers;

import presentation.UIController;
import presentation.views.RemovePlaylistView;
import presentation.views.RemoveSelectedSongsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSelectedSongsController implements ActionListener {
    private UIController controller;
    private RemoveSelectedSongsView removeSelectedSongsView;

    public RemoveSelectedSongsController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        removeSelectedSongsView= new RemoveSelectedSongsView(mainViewCenter,cardManager);
        removeSelectedSongsView.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showRemoveSelectedSongsCard(){
        removeSelectedSongsView.showCard();
    }
}
