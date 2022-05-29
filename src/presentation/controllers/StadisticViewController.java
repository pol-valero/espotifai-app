package presentation.controllers;

import business.entities.Genre;
import presentation.UIController;
import presentation.views.StadisticsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Controller for the StadisticView, the connection between Stadistic View and the UIController.
 * Implementing an ActionListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class StadisticViewController implements ActionListener {
    private final StadisticsView stadisticsView;
    private final UIController controller;

    /**
     * Constructor to create StadisticViewController
     * Creates the StadisticViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the beginning of the application
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public StadisticViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        stadisticsView=new StadisticsView(mainViewCenter,cardManager);
        stadisticsView.registerController(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case StadisticsView.BTN_HOME:
                controller.showHomescreenCard();
                break;
            case StadisticsView.BTN_ACCOUNTMANAGER:
                controller.showLogoutCard();
                break;
        }
    }

    public void showStadisticsView(LinkedList<Genre> genres){
        stadisticsView.showCard(genres);
    }
}
