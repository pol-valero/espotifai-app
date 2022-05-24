package presentation.controllers;

import presentation.UIController;
import presentation.views.StadisticsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StadisticViewController implements ActionListener {
    private final StadisticsView stadisticsView;
    private final UIController controller;

    public StadisticViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller=controller;
        stadisticsView=new StadisticsView(topContainer,cardManager);
        stadisticsView.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showStadisticsView(){
        String[][] data = new String[0][];
        stadisticsView.showCard(data);
    }
}
