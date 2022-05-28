package presentation.controllers;

import com.sun.tools.javac.Main;
import presentation.views.MainView;

import javax.swing.*;
import java.awt.*;

/**
 * Controller for the MainView, the connection between the MainView and the UIController.
 * Implementing an ActionListener and MouseListener.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class MainViewController {

    private final MainView mainView;

    /**
     * Constructor to create MainViewController
     * Creates the MainViewController linking it to the UIController. This function
     * links the elements of the class with the UIController.
     *
     * @param topContainer this is the JPanel displayed in the beginning of the program
     * @param jFrameCardManager the cardLayout is the component that will receive each view
     * @param cardManager the cardManager is the component that manages when to show each view
     * @param mainViewCenter this is the main part of the JPanel displayed with a reproductionBar in the south
     * @param reproductionBar this is the JPanel that contains the reproduction Bar to be shown in some views
     *
     */
    public MainViewController(JFrame topContainer, CardLayout jFrameCardManager, CardLayout cardManager, JPanel mainViewCenter, JPanel reproductionBar) {
         mainView = new MainView(topContainer, jFrameCardManager, cardManager, mainViewCenter, reproductionBar);
    }

    /**
     *  The function showMainViewCard calls the method showCard in mainView
     *
     */
    public void showMainViewCard() {
        mainView.showCard();
    }
}
