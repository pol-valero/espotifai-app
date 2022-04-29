package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class HomeScreenView {
    private final JFrame topContainer;
    private final CardLayout cardManager;

    public HomeScreenView (JFrame topContainer, CardLayout cardManager,
                           LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists){
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        configureView(usersPlaylists,publicPlaylists);
        topContainer.pack();
    }

    private void configureView(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {

    }

    public void showCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        cardManager.show(topContainer.getContentPane(),"homescreenCard");
    }
}
