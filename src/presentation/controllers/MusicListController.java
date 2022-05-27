package presentation.controllers;

import business.entities.Song;
import presentation.UIController;
import presentation.views.MusicListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class MusicListController implements ActionListener, MouseListener {
    private final MusicListView musicListView;
    private final UIController controller;

    LinkedList<String> selectedSongs = new LinkedList<>();

    public MusicListController (UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        musicListView = new MusicListView(mainViewCenter, cardManager);
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
                //fer crida a business per afegir
                emptySelectedSongsList();
                musicListView.addSongsVariation();
                break;

            case MusicListView.BTN_DELETE:
                String playlistName = controller.getCurrentPlaylist();
                controller.showRemovePlaylistCard(playlistName, controller.loadPlaylistMusic(playlistName).size());
                break;

            case MusicListView.BTN_RENAMEPLAYLIST:
                controller.showRenamePlaylistCard();
                break;

            case MusicListView.BTN_REMOVESONG:
                musicListView.removePlaylistSongsVariation();
                break;

            case MusicListView.BTN_ADDPERSONALSONG:
                controller.showAddSongCard();
                break;

            case MusicListView.BTN_REMOVEPERSONALSONG:
                musicListView.removePersonalSongsVariation();
                break;

            case MusicListView.BTN_REMOVE_SELECTED_PLAYLIST_SONGS:
                //fer crida a business per eliminar
                emptySelectedSongsList();
                break;

            case MusicListView.BTN_REMOVE_SELECTED_PERSONAL_SONGS:
                //fer crida a business per eliminar
                emptySelectedSongsList();
                break;

            case MusicListView.BTN_CANCEL:
                emptySelectedSongsList();
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

    public void showMusicListCard(LinkedList<Song> songList, String songListName){
        controller.setCurrentPlaylist(songListName);
        musicListView.showCard(songList,songListName);
        chooseVariation(songListName);
    }


    private void chooseVariation(String playlistName) {
        switch (playlistName) {
            case "MySongs":
                musicListView.userPersonalSongsVariation();
                break;
            case "AllSongs":
                musicListView.publicSongsVariation();
                break;
            default:
                if (controller.isPublicPlaylist(playlistName)) {
                    musicListView.publicPlaylistVariation();
                } else {
                    musicListView.userPlaylistVariation();
                }
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = musicListView.getRowAtPoint(e.getPoint());
        int column = musicListView.getColumnAtPoint(e.getPoint());

        String songName = musicListView.getSongName(row);

        if (column != 6) {
            //mostrar vista detalls canço
        } else {
            if (selectedSongs.contains(songName)) {
                selectedSongs.remove(songName);
                //System.out.println("Song borrada: " + songName);
            } else {
                selectedSongs.add(musicListView.getSongName(musicListView.getRow()));
                //System.out.println("Song afegida: " + songName);
            }

        }
    }

    private void emptySelectedSongsList() {
        musicListView.clearCheckBoxes();
        selectedSongs.clear();
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
