package presentation.controllers;

import business.entities.Song;
import presentation.UIController;
import presentation.views.HomeScreenView;
import presentation.views.SongDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SongDetailsViewController implements ActionListener, MouseListener {

    private final UIController controller;
    private final SongDetailsView songDetailsView;

    public SongDetailsViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        songDetailsView = new SongDetailsView(mainViewCenter,cardManager);
        songDetailsView.registerController(this);
    }

    public void showSongDetailsCard(Song song) {
        songDetailsView.showCard(song);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentPlaylist = controller.getCurrentPlaylist();

        switch (e.getActionCommand()) {
            case SongDetailsView.BTN_ADDPLAYLIST:
                controller.showAddToPlaylistCard();
                break;

            case SongDetailsView.BTN_ACCOUNT_MANAGEMENT:
                controller.showLogoutCard();
                break;

            case SongDetailsView.BTN_GO_BACK:
                controller.showMusicListCard(controller.loadPlaylistMusic(currentPlaylist), currentPlaylist);
                break;

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //canviar currentSong a la "selectedSong"
        //cridar a la funcio reproduir
        System.out.println("reproduceSong");
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
