package presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing the MainView. This class contains all the methods and attributes
 * needed to use and show the Main View
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class MainView {

    private final JFrame topContainer;
    private final CardLayout jFrameCardManager;

    /**
     * Constructor to create the MainView
     * Creates the MainView linking it to the UIController. This function
     * initializes the MainView.
     *
     * @param topContainer this is the JPanel displayed in the beginning of the program
     * @param jFrameCardManager the cardLayout is the component that will receive each view
     * @param cardManager the cardManager is the component that manages when to show each view
     * @param mainViewCenter this is the main part of the JPanel displayed with a reproductionBar in the south
     * @param reproductionBar this is the JPanel that contains the reproduction Bar to be shown in some views
     */
    public MainView (JFrame topContainer, CardLayout jFrameCardManager, CardLayout cardManager, JPanel mainViewCenter, JPanel reproductionBar) {
        this.topContainer = topContainer;
        this.jFrameCardManager = jFrameCardManager;
        oneTimeConfiguration(cardManager, mainViewCenter, reproductionBar);
    }

    /**
     * oneTimeConfiguration function applies the following lines only need to be added to this class as it is
     * the first class whose object is created. All the other view classes must not have these three lines.
     */
    private void oneTimeConfiguration (CardLayout cardManager, JPanel mainViewCenter, JPanel reproductionBar) {
        JPanel mainView = new JPanel();
        mainView.setLayout(new BorderLayout());

        mainViewCenter.setLayout(cardManager);

        mainView.add(mainViewCenter, BorderLayout.CENTER);
        mainView.add(reproductionBar, BorderLayout.SOUTH);

        topContainer.getContentPane().add(mainView, "mainView");
    }

    /**
     * showCard introduces the current state of the mainView Frame in the cardManager
     * and shows to the user the screen.
     *
     */
    public void showCard() {
        jFrameCardManager.show(topContainer.getContentPane(), "mainView");
    }
}
