package presentation.controllers;

import com.sun.tools.javac.Main;
import presentation.views.MainView;

import javax.swing.*;
import java.awt.*;

public class MainViewController {

    private final MainView mainView;

    public MainViewController(JFrame topContainer, CardLayout jFrameCardManager, CardLayout cardManager, JPanel mainViewCenter, JPanel reproductionBar) {
         mainView = new MainView(topContainer, jFrameCardManager, cardManager, mainViewCenter, reproductionBar);
    }

    public void showMainViewCard() {
        mainView.showCard();
    }
}
