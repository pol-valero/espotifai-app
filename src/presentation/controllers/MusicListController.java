package presentation.controllers;

import business.entities.Playlist;
import presentation.UIController;
import presentation.views.MusicListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        //la variacio que es carrega realment es fara desde la funcio "show", a on a partir del propietari de la playlist (el usuari logejat, un altre usuari, o null) carregarem la variacio que toqui. Tambe podem
        //jugar amb el fet que sabem el nom de les playlist "MySongs" i "AllSongs"?
        musicListView.userPlaylistVariation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case MusicListView.BTN_SEARCH:

                break;

            case MusicListView.BTN_STADISTICS:

                break;

            case MusicListView.BTN_ACCOUNTMANAGER:
                controller.showLogoutCard();
                break;

            case MusicListView.BTN_HOME:
                controller.showHomescreenCard();
                break;

            case MusicListView.BTN_ADDSONG:
                //falta posar aqui una funcio "refresh()" i que li passem una playlist amb totes les cançons del sistema
                musicListView.addSongsVariation();
                break;

            case MusicListView.BTN_DELETE:
                break;

            case MusicListView.BTN_RENAMEPLAYLIST:
                controller.showSetPlaylistNameCard();
                break;

            case MusicListView.BTN_REMOVESONG:
                musicListView.removeSongsVariation();
                break;

            case MusicListView.BTN_ADDPERSONALSONG:
                controller.showAddSongCard();
                break;

            case MusicListView.BTN_REMOVEPERSONALSONG:
                musicListView.removeSongsVariation();
                break;

            case MusicListView.BTN_REMOVE_SELECTED:

                break;

            case MusicListView.BTN_CANCEL:

                musicListView.returnToPreviousVariation();
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
        String playlistName = playlist.getName();
        musicListView.showCard(controller.loadPlaylistMusic(playlistName),playlistName);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {

            String songName = musicListView.getSongName(musicListView.getRow());
            System.out.println("Row: " + musicListView.getRow()+ " Song name: "+songName);
        }
    }

}
