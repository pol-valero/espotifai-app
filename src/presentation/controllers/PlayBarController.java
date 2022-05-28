package presentation.controllers;

import presentation.Components.ReproductionBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayBarController implements ActionListener {
    ReproductionBar playBar;
    public void actionPerformed(ActionEvent e) {

    }

    public void reproduceNewSong(String songName, int totalMinutes, int totalSeconds){
        playBar.reproduceNewSong(songName,totalMinutes,totalSeconds);

    }

    public void update(int currentMinutes, int currentSeconds){
        playBar.update(currentMinutes,currentSeconds);

    }
}
