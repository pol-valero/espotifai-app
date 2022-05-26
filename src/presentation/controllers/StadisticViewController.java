package presentation.controllers;

import business.entities.Genre;
import presentation.UIController;
import presentation.views.StadisticsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class StadisticViewController implements ActionListener {
    private final StadisticsView stadisticsView;
    private final UIController controller;

    public StadisticViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        stadisticsView=new StadisticsView(mainViewCenter,cardManager);
        stadisticsView.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showStadisticsView(LinkedList<Genre> genres){
        stadisticsView.showCard(genres);
    }
}
