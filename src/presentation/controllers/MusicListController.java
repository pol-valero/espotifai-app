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
import javax.swing.table.DefaultTableModel;
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
                System.out.print("add song");
                musicListView.showCheckbox();

                break;

            case MusicListView.BTN_DELETE:
                System.out.print("delete");
                musicListView.hideCheckBox();

                break;

            case MusicListView.BTN_RENAMEPLAYLIST:

                break;

            case MusicListView.BTN_REMOVESONG:

                break;

            case MusicListView.BTN_ADDNEWSONG:

                break;

            case MusicListView.BTN_UP:
               if(musicListView.getColumn() != 0 && musicListView.getRow() > 0){
                   musicListView.moveUp();
               }
                break;

            case MusicListView.BTN_DOWN:
                if(musicListView.getColumn() != 0 && musicListView.getRow() < getSongListSize()){
                    musicListView.moveDown();
                }
                break;
        }
    }





    private int getSongListSize() {
        return musicListView.getSongListSize();
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
