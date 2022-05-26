package presentation.views;

import javax.swing.*;
import java.awt.*;

public class MainView {

    private final JFrame topContainer;
    private final CardLayout jFrameCardManager;

    public MainView (JFrame topContainer, CardLayout jFrameCardManager, CardLayout cardManager, JPanel mainViewCenter, JPanel reproductionBar) {
        this.topContainer = topContainer;
        this.jFrameCardManager = jFrameCardManager;
        oneTimeConfiguration(cardManager, mainViewCenter, reproductionBar);
    }

    private void oneTimeConfiguration (CardLayout cardManager, JPanel mainViewCenter, JPanel reproductionBar) {
        JPanel mainView = new JPanel();
        mainView.setLayout(new BorderLayout());

        mainViewCenter.setLayout(cardManager);

        mainView.add(mainViewCenter, BorderLayout.CENTER);
        mainView.add(reproductionBar, BorderLayout.SOUTH);

        topContainer.getContentPane().add(mainView, "mainView");
    }

    public void showCard() {
        jFrameCardManager.show(topContainer.getContentPane(), "mainView");
    }
}
