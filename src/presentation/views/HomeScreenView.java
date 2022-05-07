package presentation.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        usersPlaylists.addFirst("MySongs");
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
        publicPlaylists.addFirst("AllSongs");
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
        northernPanel.setBorder(new EmptyBorder(new Insets(100, 250, 0, 50)));

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
        westernPanel.setBackground(Color.white);
        westernPanel.setBorder(new EmptyBorder(new Insets(50, 30, 40, 30)));
        westernPanel.setMinimumSize(new Dimension(200,900));
        westernPanel.setBackground(negre);

        //Search Panel
        JPanel searchPanel = new JPanel();
        BoxLayout searchLayout = new BoxLayout(searchPanel,BoxLayout.X_AXIS);
        searchPanel.setLayout(searchLayout);
        searchPanel.setBackground(negre);
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField searchBar = new JTextField();
        searchBar.setMaximumSize(new Dimension(300,30));

        ImageIcon magLens = new ImageIcon("images/LUPA AI.png");
        Image magLensIcon = magLens.getImage();
        Image magLensIconScaled = getScaledImage(magLensIcon, 20, 20);
        ImageIcon magnifyingLens = new ImageIcon(magLensIconScaled);

        JButton searchBtn = new JButton();
        searchBtn.setIcon(magnifyingLens);
        searchBtn.setBackground(vermell);
        searchBtn.setFocusable(false);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);
        searchBtn.setMaximumSize(new Dimension(30,30));

        searchPanel.add(searchBar);
        searchPanel.add(Box.createRigidArea(new Dimension(20,0))); //Create space between both buttons
        searchPanel.add(searchBtn);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(negre);

        GridBagConstraints constraints = new GridBagConstraints();

        JButton stadisticsBtn = createButton("Stadistics");
        JButton accManBtn = createButton("Account Manager");

        constraints.gridx=0;
        constraints.gridy=0;
        buttonsPanel.add(stadisticsBtn,constraints);

        constraints.gridx=0;
        constraints.gridy=1;
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,400)),constraints);

        constraints.gridx=0;
        constraints.gridy=2;
        buttonsPanel.add(accManBtn,constraints);


        westernPanel.add(searchPanel);
        westernPanel.add(Box.createRigidArea(new Dimension(0,40)));
        westernPanel.add(buttonsPanel);


        return westernPanel;

    }

    private JButton createButton(String name) {
        Color vermell = new Color (232,74,77);
        Dimension button_shape = new Dimension(200,40);
        Font text = new Font("Gulim", Font.PLAIN, 19);

        JButton btn = new JButton(name);

        btn.setPreferredSize(button_shape);
        btn.setBackground(vermell);
        btn.setForeground(Color.white);
        btn.setFocusable(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFont(text);

        return btn;
    }

    private Image getScaledImage(Image img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    private Component createScrollPane(LinkedList<String> playListNames){

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,400));

        for(int i=0; i < playListNames.size(); i++){

            //Panel which contains button and label
            JPanel miniPanel = new JPanel();
            BoxLayout miniPanelLayout = new BoxLayout(miniPanel,BoxLayout.Y_AXIS);
            miniPanel.setLayout(miniPanelLayout);

            //Label with the playlist name
            JLabel playlistTitle = new JLabel(playListNames.get(i));
            playlistTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Button config
            JButton jbPlaylist = new JButton(playListNames.get(i));
            jbPlaylist.setPreferredSize(new Dimension(150,60));
            jbPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Adding components to panel
            miniPanel.add(jbPlaylist);
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
