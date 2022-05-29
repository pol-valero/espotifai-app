package presentation.controllers;

import presentation.UIController;
import presentation.views.RemovePlaylistView;
import presentation.views.RemoveSelectedSongsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class RemoveSelectedSongsController implements ActionListener {
    private UIController controller;
    private RemoveSelectedSongsView removeSelectedSongsView;
    private LinkedList<String> songsToRemove;

    public RemoveSelectedSongsController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        removeSelectedSongsView= new RemoveSelectedSongsView(mainViewCenter,cardManager);
        removeSelectedSongsView.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentPlaylist = controller.getCurrentPlaylist();
        String currentSong = controller.getCurrentSong().getName();
        switch (e.getActionCommand()) {
            case RemoveSelectedSongsView.BTN_CONFIRM_REMOVE:
                if (!currentPlaylist.equals("MySongs")) {
                    controller.deleteSongPlaylist(currentPlaylist, songsToRemove);
                    controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                } else {
                    if (songsToRemove.contains(currentSong)) {
                        removeSelectedSongsView.cannotDeleteSongVisibility(true);
                    } else {
                        controller.deletePersonalSong(songsToRemove);
                        controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                    }
                }
                break;

            case RemoveSelectedSongsView.BTN_CANCEL:
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                break;
        }
    }

    public void showRemoveSelectedSongsCard(LinkedList<String> songsToRemove){
        this.songsToRemove = new LinkedList<>(songsToRemove);
        removeSelectedSongsView.cannotDeleteSongVisibility(false);
        removeSelectedSongsView.showCard();
    }
}
