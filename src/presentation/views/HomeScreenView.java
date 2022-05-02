package presentation.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class HomeScreenView {
    private final JFrame topContainer;
    private final CardLayout cardManager;

    JPanel panel;
    JPanel centralPanel;

    public HomeScreenView (JFrame topContainer, CardLayout cardManager){
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        configureView();
        topContainer.pack();
    }

    private void configureView() {

        //Creation of main panels
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        centralPanel = centralPanelConfiguration(new LinkedList<>(), new LinkedList<>());
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);
        panel.remove(centralPanel);

        topContainer.getContentPane().add(panel, "homescreenCard");
    }

    private JPanel centralPanelConfiguration(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {

        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);

        JPanel centralPanel = new JPanel();
        BoxLayout centralLayout = new BoxLayout(centralPanel,BoxLayout.Y_AXIS);
        centralPanel.setLayout(centralLayout);
        centralPanel.setBackground(negre);
        centralPanel.setBorder(new EmptyBorder(new Insets(50,50,40,80)));


        //Panel "Your Playlist" + New Playlist Button
        JPanel urPlaylistP = new JPanel();
        BoxLayout urPlaylistLayout = new BoxLayout(urPlaylistP,BoxLayout.X_AXIS);
        urPlaylistP.setLayout(urPlaylistLayout);
        urPlaylistP.setBackground(negre);

        JLabel urPlaylistLab = new JLabel("Your Playlist");
        urPlaylistLab.setFont(titols);
        urPlaylistLab.setForeground(Color.white);

        JButton newPlaylistBtn = new JButton("New Playlist");
        newPlaylistBtn.setFont(text);
        newPlaylistBtn.setForeground(Color.white);
        newPlaylistBtn.setBackground(vermell);
        newPlaylistBtn.setFocusable(false);
        newPlaylistBtn.setOpaque(true);
        newPlaylistBtn.setBorderPainted(false);

        urPlaylistP.add(urPlaylistLab);
        urPlaylistP.add(Box.createRigidArea(new Dimension(500,0)));
        urPlaylistP.add(newPlaylistBtn);

        //Panel ScrollPane Your Playlists
        JPanel urPlaylistScrollP = new JPanel();
        urPlaylistScrollP.setBackground(negre);
        urPlaylistScrollP.add(createScrollPane(usersPlaylists)); //Todo


        //Panel Explore
        JPanel exploreP = new JPanel();
        JLabel exploreLab = new JLabel("Explore");
        exploreLab.setFont(titols);
        exploreLab.setForeground(Color.white);

        exploreP.add(exploreLab);
        exploreP.add(Box.createRigidArea(new Dimension(900,0)));
        exploreP.setBackground(negre);

        //Panel ScrollPane Explore
        JPanel exploreScrollP = new JPanel();
        exploreScrollP.setBackground(negre);
        exploreScrollP.add(createScrollPane(publicPlaylists)); //todo


        centralPanel.add(urPlaylistP);
        centralPanel.add(urPlaylistScrollP);
        centralPanel.add(exploreP);
        centralPanel.add(exploreScrollP);

        return centralPanel;
    }

    private JPanel northernPanelConfiguration() {
        //Fonts, colours and sizes
        Color negre = new Color(48,48,48);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 65);

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(100, 300, 0, 50)));

        JLabel homeLabel = new JLabel("Home");
        homeLabel.setFont(titols);
        homeLabel.setForeground(Color.white);

        northernPanel.add(homeLabel);
        return northernPanel;
    }

    private JPanel westernPanelConfiguration() {
        //Fonts, colours and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);
        
        JPanel westernPanel = new JPanel();
        BoxLayout westernLayout = new BoxLayout(westernPanel,BoxLayout.Y_AXIS);
        westernPanel.setLayout(westernLayout);
        westernPanel.setBackground(negre);
        westernPanel.setBorder(new EmptyBorder(new Insets(50, 20, 40, 50)));

        //Search Panel
        JPanel searchPanel = new JPanel();
        BoxLayout searchLayout = new BoxLayout(searchPanel,BoxLayout.X_AXIS);
        searchPanel.setLayout(searchLayout);
        searchPanel.setBackground(negre);

        JTextField searchBar = new JTextField();
        searchBar.setMaximumSize(new Dimension(220,30));

        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(text);
        searchBtn.setForeground(Color.white);
        searchBtn.setBackground(vermell);
        searchBtn.setFocusable(false);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);

        searchPanel.add(searchBar);
        searchPanel.add(Box.createRigidArea(new Dimension(30,0))); //Create space between both buttons
        searchPanel.add(searchBtn);

        //Buttons Panel
        JPanel buttonsPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(buttonLayout);
        buttonsPanel.setBackground(negre);

        JButton stadisticBtn = new JButton("Stadistics");
        stadisticBtn.setFont(text);
        stadisticBtn.setForeground(Color.white);
        stadisticBtn.setBackground(vermell);
        stadisticBtn.setFocusable(false);
        stadisticBtn.setOpaque(true);
        stadisticBtn.setBorderPainted(false);

        JButton accManagBtn = new JButton("Account Manager");
        accManagBtn.setFont(text);
        accManagBtn.setForeground(Color.white);
        accManagBtn.setBackground(vermell);
        accManagBtn.setFocusable(false);
        accManagBtn.setOpaque(true);
        accManagBtn.setBorderPainted(false);

        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonsPanel.add(stadisticBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,450))); //Create space between both buttons
        buttonsPanel.add(accManagBtn);

        westernPanel.add(searchPanel);
        westernPanel.add(Box.createRigidArea(new Dimension(0,40)));
        westernPanel.add(buttonsPanel);

        return westernPanel;

    }

    private Component createScrollPane(LinkedList<String> playListNames){
        LinkedList<JButton> buttonLinkedList = new LinkedList<>();
        int nPlaylist = playListNames.size();

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,400));


        for(int i=0; i < nPlaylist; i++){

            //Panel which contains button and label
            JPanel miniPanel = new JPanel();
            BoxLayout miniPanelLayout = new BoxLayout(miniPanel,BoxLayout.Y_AXIS);
            miniPanel.setLayout(miniPanelLayout);

            //Label with the playlist name
            JLabel playlistTitle = new JLabel(playListNames.get(i));
            playlistTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


            //Button config
            buttonLinkedList.add(new JButton(playListNames.get(i)));
            buttonLinkedList.get(i).setPreferredSize(new Dimension(150,60));
            buttonLinkedList.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);

            //Adding components to panel
            miniPanel.add(buttonLinkedList.get(i));
            miniPanel.add(Box.createRigidArea(new Dimension(0,5)));
            miniPanel.add(playlistTitle);
            miniPanel.setBackground(Color.red);

            panel.add(miniPanel);
            //buttons.get(i).setActionCommand(); todo
        }

        JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800,300));
        scrollPane.setBackground(new Color(48,48,48));

        return scrollPane;
    }


    public void showCard(LinkedList<String> usersPlaylists, LinkedList<String> publicPlaylists) {
        //Afegir playlist a la vista ?¿
        panel.remove(centralPanel);
        centralPanel = centralPanelConfiguration(usersPlaylists, publicPlaylists);
        panel.add(centralPanel,BorderLayout.CENTER);
        topContainer.revalidate();
        cardManager.show(topContainer.getContentPane(),"homescreenCard");
    }
}
