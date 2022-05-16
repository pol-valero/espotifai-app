package presentation.controllers;

import business.entities.Playlist;
import presentation.UIController;
import presentation.views.HomeScreenView;
import presentation.views.MusicListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicListController implements ActionListener, ListSelectionListener {
    private final MusicListView musicListView;
    private final UIController controller;

    public MusicListController (UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        musicListView = new MusicListView(topContainer, cardManager);
        musicListView.registerController(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case MusicListView.BTN_SEARCH:

                break;

            case MusicListView.BTN_STADISTICS:

                break;

            case MusicListView.BTN_ACCOUNTMANAGER:

                break;

            case MusicListView.BTN_ADDSONG:

                break;

            case MusicListView.BTN_DELETE:

                break;

            case MusicListView.BTN_RENAMEPLAYLIST:

                break;

            case MusicListView.BTN_REMOVESONG:

                break;

            case MusicListView.BTN_ADDNEWSONG:

                break;

        }
    }

    public void showMusicListCard(Playlist playlist){
        musicListView.showCard(playlist);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {

            String songName = musicListView.getSongName(musicListView.getRow());
            System.out.println("Row: " + musicListView.getRow()+ " Song name: "+songName);
        }
    }

}
