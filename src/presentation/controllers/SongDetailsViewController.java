package presentation.controllers;

import presentation.UIController;
import presentation.views.HomeScreenView;
import presentation.views.SongDetailsView;

import javax.swing.*;
import java.awt.*;

public class SongDetailsViewController {

    private final UIController controller;
    private final SongDetailsView songDetailsView;

    public SongDetailsViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        songDetailsView = new SongDetailsView(mainViewCenter,cardManager);
        //songDetailsView.registerController(this);
    }
}
