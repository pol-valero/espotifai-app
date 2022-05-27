package presentation.controllers;

import presentation.UIController;
import presentation.views.AddToPlaylistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the AddToPlaylistView, the connection between the AddToPlaylistView and the UIController.
 * Implementing an ActionListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class AddToPlaylistViewController implements ActionListener {
    private final AddToPlaylistView addToPlaylistView;
    private final UIController controller;

    /**
     * Constructor to create AddToPlaylistViewController
     * Creates the AddToPlaylistViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param controller this is the UI controller parameter, shared among all Controllers
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public AddToPlaylistViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller=controller;
        addToPlaylistView=new AddToPlaylistView(mainViewCenter,cardManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     *  The function showAddToPlaylistView calls the method showCard in addToPlaylistView
     *
     */
    public void showAddToPlaylistView(){
        addToPlaylistView.showCard();
    }
}
