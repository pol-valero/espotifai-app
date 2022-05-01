package presentation.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

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
        generateName(usersPlaylists,publicPlaylists);
        
        //Creation of main panels
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        JPanel centralPanel = new JPanel();
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);

        topContainer.getContentPane().add(panel, "homescreenCard");
    }

    private JPanel northernPanelConfiguration() {
        //Fonts, colours and sizes
        Color negre = new Color(48,48,48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Eras Medium ITC", Font.PLAIN, 36);
        Font text = new Font("Segoe UI Light", Font.PLAIN, 20);

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(Color.blue);
        northernPanel.setBorder(new EmptyBorder(new Insets(100, 300, 0, 50)));

        JLabel homeLabel = new JLabel("Home");
        homeLabel.setFont(titols);
        homeLabel.setForeground(Color.white);

        northernPanel.add(homeLabel);
        return northernPanel;
    }

    private JPanel westernPanelConfiguration() {
        //Fonts, colours and sizes
        Color negre = new Color(48,48,48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Eras Medium ITC", Font.PLAIN, 36);
        Font text = new Font("Segoe UI Light", Font.PLAIN, 20);
        
        JPanel westernPanel = new JPanel();
        BoxLayout westernLayout = new BoxLayout(westernPanel,BoxLayout.Y_AXIS);
        westernPanel.setLayout(westernLayout);
        westernPanel.setBackground(Color.red);
        westernPanel.setBorder(new EmptyBorder(new Insets(50, 20, 20, 50)));

        //Search Panel
        JPanel searchPanel = new JPanel();
        BoxLayout searchLayout = new BoxLayout(searchPanel,BoxLayout.X_AXIS);
        searchPanel.setLayout(searchLayout);

        JTextField searchBar = new JTextField();
        searchBar.setMaximumSize(new Dimension(220,30));

        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(text);

        searchPanel.add(searchBar);
        searchPanel.add(Box.createRigidArea(new Dimension(30,0))); //Create space between both buttons
        searchPanel.add(searchBtn);

        //Buttons Panel
        JPanel buttonsPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(buttonLayout);

        JButton stadisticBtn = new JButton("Stadistics");
        stadisticBtn.setFont(text);

        JButton accManagBtn = new JButton("Account Manager");
        accManagBtn.setFont(text);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonsPanel.add(stadisticBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,450))); //Create space between both buttons
        buttonsPanel.add(accManagBtn);

        westernPanel.add(searchPanel);
        westernPanel.add(buttonsPanel);

        return westernPanel;

    }

    private void generateName(LinkedList<String> a, LinkedList<String> b) {
        a.add("Danamondi");
        a.add("ooyx");
        a.add("pucca and empresoa");
        a.add("fuji");
        a.add("imakiha");
        a.add("Vond den fragus");
        a.add("guck under this");
        a.add("Stagger");
        a.add("seketa");
        a.add("my ordinary life");
        a.add("seikatujo");
        a.add("way too much");
        a.add("No coming back");
        a.add("qqssDax");
        a.add("Never;)");
        a.add("Kaikai kitan");

        b.add("Danamondi");
        b.add("ooyx");
        b.add("pucca and empresoa");
        b.add("fuji");
        b.add("imakiha");
        b.add("Vond den fragus");
        b.add("guck under this");
        b.add("Stagger");
        b.add("seketa");
        b.add("my ordinary life");
        b.add("seikatujo");
        b.add("way too much");
        b.add("No coming back");
        b.add("qqssDax");
        b.add("Never;)");
        b.add("Kaikai kitan");
    } //Mètode auxiliar. S'haurà d'esborrar més endavant

    public void showCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        cardManager.show(topContainer.getContentPane(),"homescreenCard");
    }
}
