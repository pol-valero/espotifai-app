package presentation.controllers;

import business.entities.Song;
import presentation.Components.ReproductionBar;
import presentation.UIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayBarController implements ActionListener {

    private ReproductionBar playBar;
    private UIController controller;

    public PlayBarController(UIController controller, ReproductionBar playBar) {
        this.playBar = playBar;
        this.controller = controller;
        playBar.registerController(this);
        initialSongConfiguration();
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ReproductionBar.BTN_PREVIOUS_SONG:
                controller.previusNextSong(-1);
                break;

            case ReproductionBar.BTN_PLAY:
                controller.pausedSong();
                playBar.setPauseBtn();
                break;

            case ReproductionBar.BTN_PAUSE:
                controller.pausedSong();
                playBar.setPlayBtn();
                break;

            case ReproductionBar.BTN_NEXT_SONG:
                controller.previusNextSong(1);
                break;
        }
    }

    public void reproduceNewSong(String songName, int totalMinutes, int totalSeconds){
        playBar.reproduceNewSong(songName,totalMinutes,totalSeconds);

    }

    public void update(int currentMinutes, int currentSeconds){
        playBar.update(currentMinutes,currentSeconds);

    }

    private void initialSongConfiguration () {
        Song firstSong =  controller.loadAllMusic().get(0);
        reproduceNewSong(firstSong.getName(), firstSong.getMinutes(), firstSong.getSeconds());
    }
}
