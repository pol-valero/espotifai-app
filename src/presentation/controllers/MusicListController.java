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
import java.util.List;

/**
 * Controller for the MusicListView, the connection between the MusicListView and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class MusicListController implements ActionListener, MouseListener {
    private final MusicListView musicListView;
    private final UIController controller;

    LinkedList<String> selectedSongs = new LinkedList<>();

    /**
     * Constructor to create MusicListController
     * Creates the MusicListController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public MusicListController (UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        musicListView = new MusicListView(mainViewCenter, cardManager);
        musicListView.registerController(this);
        //la variacio que es carrega realment es fara desde la funcio "show", a on a partir del propietari de la playlist (el usuari logejat, un altre usuari, o null) carregarem la variacio que toqui. Tambe podem
        //jugar amb el fet que sabem el nom de les playlist "MySongs" i "AllSongs"?
        musicListView.userPlaylistVariation();
    }
    /**
     * Overrides the function actionPerformed to use the Card of different views
     * this function manages the main logic of this class
     * @param e Is the event that activates this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentPlaylist = controller.getCurrentPlaylist();

        switch (e.getActionCommand()){

            case MusicListView.BTN_SEARCH:
                String searhField = musicListView.getSearchBarText();
                if (!searhField.isBlank()) {
                    LinkedList<Song> filteredSongs = (LinkedList<Song>) controller.loadSearchMusic(searhField);
                    musicListView.showCard(filteredSongs, "AllSongs");
                }
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
                //Aqui en vez de mostrar todas las caciones del sistema directamente, mostraremos canciones del sistema - canciones de la playlist
                //musicListView.showCard((LinkedList<Song>) controller.loadAllMusic(), "AllSongs");
                musicListView.showCard(controller.loadAllNotAlreadyAddedSongs(currentPlaylist), "AllSongs");
                musicListView.addSongsVariation();
                break;

            case MusicListView.BTN_DELETE:
                controller.showRemovePlaylistCard(currentPlaylist, controller.loadPlaylistMusic(currentPlaylist).size());
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

            case MusicListView.BTN_ADD_SELECTED:
                controller.addSongPlaylist(currentPlaylist, selectedSongs);
                emptySelectedSongsList();
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                //musicListView.returnToPreviousVariation();
                break;

            case MusicListView.BTN_REMOVE_SELECTED_PLAYLIST_SONGS:
                //fer crida a business per eliminar
                controller.deleteSongPlaylist(currentPlaylist, selectedSongs);
                System.out.println("Linkedlist size: " + selectedSongs.size());
                emptySelectedSongsList();
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                break;

            case MusicListView.BTN_REMOVE_SELECTED_PERSONAL_SONGS:
                //fer crida a business per eliminar
                controller.deletePersonalSong(selectedSongs);
                System.out.println("Linkedlist size: " + selectedSongs.size());
                emptySelectedSongsList();
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                break;

            case MusicListView.BTN_CANCEL:
                emptySelectedSongsList();
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                //musicListView.returnToPreviousVariation();
                break;

            case MusicListView.BTN_UP:
                int selectedRow1 = musicListView.getRow();
                //String selectedSong = musicListView.getSongName(selectedRow);
                if(musicListView.getColumn() != 0 && musicListView.getRow() > 0){
                   controller.moveSongInPlaylist(currentPlaylist, selectedRow1, 1);
                   musicListView.moveUp();
                }
                break;

            case MusicListView.BTN_DOWN:
                int selectedRow2 = musicListView.getRow();
                if(musicListView.getColumn() != 0 && musicListView.getRow() < getSongListSize()){
                    controller.moveSongInPlaylist(currentPlaylist, selectedRow2, -1);
                    musicListView.moveDown();
                }
                break;
        }
    }


    /**
     * getSongListSize is a function that calls the method getSongListSize
     * in musicListView to now the number of songs of a given list.
     * @return the number of songs in a given list
     */
    private int getSongListSize() {
        return musicListView.getSongListSize();
    }

    public void showMusicListCard(LinkedList<Song> songList, String songListName){

        //Potser seria interessant fer un "if" aqui per si es dona el cas que el nom es "AllSongs" doncs no es canvii el nom de la
        //currentPlaylist?  (en canvi, si que es interessant que per a MySongs es canvii).
        if(!songListName.equals("AllSongs")) {
            controller.setCurrentPlaylist(songListName);
        }

        musicListView.showCard(songList,songListName);
        chooseVariation(songListName);
    }

    /**
     * The method chooseVariation determines witch kind of playlist the selected playlist is
     * @param playlistName is the name of the playlist and will be used to identify the playlist
     */
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

    /**
     * Overrides the function mouseClicked to call the class musicListView
     *  every time the mouse clicks in the corresponding area
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        /*int row = musicListView.getRowAtPoint(e.getPoint());
        int column = musicListView.getColumnAtPoint(e.getPoint());

        String songName = musicListView.getSongName(row);

        System.out.println(songName);
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

        }*/
    }

    /**
     * emptySelectedSongsList calls the method clearCheckBoxes of musicListView
     * and the attribute selectedSongs from this class
     */
    private void emptySelectedSongsList() {
        musicListView.clearCheckBoxes();
        selectedSongs.clear();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = musicListView.getRowAtPoint(e.getPoint());
        int column = musicListView.getColumnAtPoint(e.getPoint());

        String songName = musicListView.getSongName(row);

        System.out.println(songName);
        if (column != 6) {
            if (column == 0 || column == 1) {
                //mostrar vista detalls canço
                Song song = controller.findSong(songName);
                controller.setSelectedSongName(songName);
                controller.showSongDetailsCard(song);
            }
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
