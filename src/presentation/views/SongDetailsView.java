package presentation.views;

import business.entities.Song;
import presentation.controllers.SongDetailsViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;


public class SongDetailsView {

    public static final String BTN_ACCOUNT_MANAGEMENT= "BTN_ACCOUNT_MANAGEMENT";
    public static final String BTN_ADDPLAYLIST = "BTN_ADDPLAYLIST";
    public static final String BTN_GO_BACK = "BTN_GO_BACK";


    private final JPanel mainViewCenter;
    private final CardLayout cardManager;
    private JButton jbManagement = new JButton();
    private JButton jbAddPlaylist = new JButton();

    private JButton jbGoBackImage = new JButton();

    private JLabel playImage = new JLabel();
    private JLabel albumImage = new JLabel();

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    JPanel panel;
    JPanel centralPanel;
    Song innitializeSong = new Song(0, "", 0, "", 0,
            "", 0, "", 0, "", "", 0, 0, 0,"" );

    public SongDetailsView (JPanel mainViewCenter, CardLayout cardManager) {
        this.mainViewCenter = mainViewCenter;
        this.cardManager = cardManager;
        configureView();
        //this.mainView.pack();
    }

    private void configureView() {
        //Colors, fonts and sizes
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel southernPanel = southernPanelConfiguration();

        centralPanel = new JPanel();
        //centralPanel = centralPanelConfiguration(" ", innitializeSong,0,0);
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(southernPanel,BorderLayout.SOUTH);
        panel.add(centralPanel,BorderLayout.CENTER);

        mainViewCenter.add(panel, "songDetailsCard");

    }
    private JPanel centralPanelConfiguration(Song song) {



        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font subtitle = new Font("Gulim", Font.PLAIN, 30);
        Font titol = new Font("Tahoma", Font.PLAIN, 38);
        Font text = new Font("Gulim", Font.PLAIN, 20);
        Font word = new Font("Gulim", Font.PLAIN, 14);
        Font information = new Font("Gulim", Font.PLAIN, 14);

        Dimension button_shape = new Dimension(325,40);
        Dimension CentralPanel = new Dimension(1000,500);
        Dimension PanelShape = new Dimension(500,250);


        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();
        GridBagConstraints z = new GridBagConstraints();

        z.insets = new Insets(10,10,10,10); //Space between components

        c.insets = new Insets(10,30,10,30); //Space between components
        d.insets = new Insets(10,5,10,5); //Space between components

        // Panel

        JPanel info_panel = new JPanel();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(negre);

        JPanel subpanel_1 = new JPanel(new GridBagLayout());
        subpanel_1.setBackground(negre);


        JPanel  subpanel_2 = new JPanel();
        subpanel_2.setLayout(new GridBagLayout());
        subpanel_2.setBackground(negre);


        JPanel subpanel_3 = new JPanel(new GridBagLayout());
        subpanel_3.setBackground(negre);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setBackground(negre);
        centralPanel.setMaximumSize(CentralPanel);


        // tittle

        JPanel  title_panel = new JPanel();
        title_panel.setLayout(new BoxLayout( title_panel, BoxLayout.Y_AXIS));
        title_panel.setBackground(negre);

        JLabel songName = new JLabel();
        songName.setText(song.getName());
        songName.setForeground(Color.white);
        songName.setFont(titol);

        JLabel songSinger = new JLabel();
        songSinger.setText(song.getSinger());
        songSinger.setForeground(Color.white);
        songSinger.setFont(subtitle);

        title_panel.add(songName);
        title_panel.add(songSinger);
        title_panel.setBackground(negre);
        d.gridx = 0;
        d.gridy = 0;
        subpanel_1.add(title_panel,d);

        // Details panel

        JPanel details_panel = new JPanel();
        details_panel.setLayout(new BoxLayout(details_panel, BoxLayout.LINE_AXIS));
        details_panel.setBackground(negre);
        //album

        JPanel  album_panel = new JPanel();
        album_panel.setLayout(new BoxLayout( album_panel, BoxLayout.Y_AXIS));
        album_panel.setBackground(negre);

        JLabel albumLabel = new JLabel();
        albumLabel.setText("Album");
        albumLabel.setForeground(Color.white);
        albumLabel.setFont(subtitle);

        JLabel albumSong = new JLabel();
        albumSong.setText(song.getAlbum());
        albumSong.setForeground(Color.white);
        albumSong.setFont(text);

        album_panel.add(albumLabel);
        album_panel.add(Box.createRigidArea(new Dimension(5, 10)));
        album_panel.add(albumSong);

        details_panel.add(album_panel);
        details_panel.add(Box.createRigidArea(new Dimension(20, 20)));

        //  Owner

        JPanel  owner_panel = new JPanel();
        owner_panel.setLayout(new BoxLayout( owner_panel, BoxLayout.Y_AXIS));
        owner_panel.setBackground(negre);

        JLabel ownerLabel = new JLabel();
        ownerLabel.setText("Owner");
        ownerLabel.setForeground(Color.white);
        ownerLabel.setFont(subtitle);

        JLabel ownerSong = new JLabel();
        ownerSong.setText(song.getOwne());
        ownerSong.setForeground(Color.white);
        ownerSong.setFont(text);

        owner_panel.add(ownerLabel);
        owner_panel.add(Box.createRigidArea(new Dimension(5, 10)));
        owner_panel.add(ownerSong);

        details_panel.add(owner_panel);
        details_panel.add(Box.createRigidArea(new Dimension(20, 20)));
        //  Genre

        JPanel  genre_panel = new JPanel();
        genre_panel.setLayout(new BoxLayout( genre_panel, BoxLayout.Y_AXIS));
        genre_panel.setBackground(negre);

        JLabel genreLabel = new JLabel();
        genreLabel.setText("Genre");
        genreLabel.setForeground(Color.white);
        genreLabel.setFont(subtitle);

        JLabel genreSong = new JLabel();
        genreSong.setText(song.getGenre());
        genreSong.setForeground(Color.white);
        genreSong.setFont(text);

        genre_panel.add(genreLabel);
        genre_panel.add(Box.createRigidArea(new Dimension(5, 10)));
        genre_panel.add(genreSong);

        details_panel.add(genre_panel);
        details_panel.add(Box.createRigidArea(new Dimension(20, 20)));

        // Duration
        JPanel  duration_panel = new JPanel();
        duration_panel.setLayout(new BoxLayout( duration_panel, BoxLayout.Y_AXIS));
        duration_panel.setBackground(negre);

        JLabel durationLabel = new JLabel();
        durationLabel.setText("Duration");
        durationLabel.setForeground(Color.white);
        durationLabel.setFont(subtitle);

        JLabel durationSong = new JLabel();

        durationSong.setText(song.getMinutes() + "m  " + song.getSeconds() + "s");
        durationSong.setForeground(Color.white);
        durationSong.setFont(text);

        duration_panel.add(durationLabel);
        duration_panel.add(Box.createRigidArea(new Dimension(5, 10)));
        duration_panel.add(durationSong);

        details_panel.add(duration_panel);
        details_panel.setBackground(negre);

        d.gridx = 0;
        d.gridy = 1;
        subpanel_1.add(details_panel,d);


        // Album image

        ImageIcon a = new ImageIcon("images/mySongCover.png");
        Image b = a.getImage();
        Image t = getScaledImage(b, 300, 350);
        ImageIcon dale = new ImageIcon(t);

        albumImage.setIcon(dale);
        albumImage.addMouseListener(new MouseAdapter() {});

        z.gridx = 0;
        z.gridy = 0;
        subpanel_2.add(albumImage,z);

        subpanel_2.add(Box.createRigidArea(new Dimension(20, 20)));


        // Play and Add playlist button

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.LINE_AXIS));
        button_panel.setBackground(negre);

        ImageIcon boto = new ImageIcon("images/playButton.png");
        Image imageIcon_2 = boto.getImage();
        Image Image_2 = getScaledImage(imageIcon_2, 50, 50);
        ImageIcon new_Boto = new ImageIcon(Image_2);

        playImage.setIcon(new_Boto);
        playImage.addMouseListener(new MouseAdapter() {});

        button_panel.add(playImage);
        button_panel.add(Box.createRigidArea(new Dimension(40, 20)));

        jbAddPlaylist.setBackground(vermell);
        jbAddPlaylist.setForeground(Color.white);
        jbAddPlaylist.setText("Add to playlist");
        jbAddPlaylist.setFont(subtitle);
        jbAddPlaylist.setPreferredSize(button_shape);
        jbAddPlaylist.setFocusable(false);
        jbAddPlaylist.setOpaque(true);
        jbAddPlaylist.setBorderPainted(false);
        jbAddPlaylist.setActionCommand(BTN_ADDPLAYLIST);

        button_panel.add(jbAddPlaylist);

        z.gridx = 0;
        z.gridy = 1;
        subpanel_2.add(button_panel,z);


        //  Lyrics

        JLabel songLyricsLabel = new JLabel();
        songLyricsLabel.setText("Lyrics");
        songLyricsLabel.setForeground(Color.white);
        songLyricsLabel.setFont(subtitle);

        c.gridx = 0;
        c.gridy = 0;
        subpanel_3.add(songLyricsLabel,c);
        subpanel_3.add(Box.createRigidArea(new Dimension(10, 10)));

        String data = song.getLyrics();
        JTextArea textArea = new JTextArea(data,18,30);
        textArea.setFont(word);
        textArea.setForeground(Color.white);
        textArea.setBackground(negre);
        textArea.setEditable(false);

        JScrollPane js = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        // scrollPane.setBackground(negre);
        //scrollPane.add(BorderAdjustment);
        c.gridx = 0;
        c.gridy = 1;
        subpanel_3.add(js,c);



        //Position and addition
        info_panel.setLayout(new GridBagLayout());
        info_panel.setBackground(negre);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        d.gridx = 0; d.gridy = 0;
        info_panel.add(subpanel_1, d);
        gbc.gridx = 1; gbc.gridy = 1;
        info_panel.add(subpanel_2, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        info_panel.add(subpanel_3, gbc);


        //Position and addition

        c.gridx = 0;
        c.gridy = 0;
        centralPanel.add(info_panel,c);
        centralPanel.setBackground(negre);

        return  centralPanel;
    }
    private JPanel northernPanelConfiguration() {

        //Fonts, colours and sizes
        ImageIcon boto = new ImageIcon("images/boto.png");
        Image imageIcon_2 = boto.getImage();
        Image Image_2 = getScaledImage(imageIcon_2, 50, 50);
        ImageIcon new_Boto = new ImageIcon(Image_2);

        jbGoBackImage.setIcon(new_Boto);
        jbGoBackImage.setPreferredSize(new Dimension(50,50));
        jbGoBackImage.setActionCommand(BTN_GO_BACK);
        //goBackImage.setIcon(new_Boto);
        //goBackImage.addMouseListener(new MouseAdapter() {});

        JPanel BorderAdjustment = new JPanel(new BorderLayout());

        JPanel FillNORTH = new JPanel();
        FillNORTH.setBackground(negre);
        FillNORTH.setSize(20, 10);

        JPanel FillWEST = new JPanel();
        FillWEST.setBackground(negre);
        FillWEST.setSize(10, 20);

        //BorderAdjustment.add(goBackImage, BorderLayout.CENTER);
        BorderAdjustment.add(jbGoBackImage, BorderLayout.CENTER);
        BorderAdjustment.add(FillNORTH, BorderLayout.NORTH);
        BorderAdjustment.add(FillWEST, BorderLayout.WEST);

        JPanel northernPanel = new JPanel(new BorderLayout());

        JPanel FillPanel = new JPanel();
        FillPanel.setBackground(negre);
        northernPanel.add(FillPanel, BorderLayout.CENTER);
        northernPanel.add(BorderAdjustment, BorderLayout.WEST);

        return northernPanel;
    }

    private JPanel southernPanelConfiguration() {

        //Fonts, colours and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font button = new Font("Gulim", Font.PLAIN, 20);
        Dimension button_shape_2 = new Dimension(245,40);


        jbManagement.setBackground(vermell);
        jbManagement.setForeground(Color.white);
        jbManagement.setText("Acount Management");
        jbManagement.setFont(button);
        jbManagement.setPreferredSize(button_shape_2);
        jbManagement.setFocusable(false);
        jbManagement.setOpaque(true);
        jbManagement.setBorderPainted(false);
        jbManagement.setActionCommand(BTN_ACCOUNT_MANAGEMENT);

        JPanel BorderAdjustment_2 = new JPanel(new BorderLayout());



        JPanel FillSOUTH = new JPanel();
        FillSOUTH.setBackground(negre);


        JPanel FillWEST_2 = new JPanel();
        FillWEST_2.setBackground(negre);


        BorderAdjustment_2.add(jbManagement, BorderLayout.CENTER);
        BorderAdjustment_2.add(FillSOUTH, BorderLayout.SOUTH);
        BorderAdjustment_2.add(FillWEST_2, BorderLayout.WEST);

        JPanel southernPanel = new JPanel(new BorderLayout());

        JPanel FillPanel_2 = new JPanel();
        FillPanel_2.setBackground(negre);
        southernPanel.add(FillPanel_2, BorderLayout.CENTER);
        southernPanel.add(BorderAdjustment_2, BorderLayout.WEST);


        return southernPanel;

    }
    private Image getScaledImage(Image img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    public void registerController (SongDetailsViewController songDetailsViewController) {
        jbAddPlaylist.addActionListener(songDetailsViewController);
        jbManagement.addActionListener(songDetailsViewController);
        jbGoBackImage.addActionListener(songDetailsViewController);
        playImage.addMouseListener(songDetailsViewController);
    }


    public void showCard(Song song) {

        panel.remove(centralPanel);
        centralPanel = centralPanelConfiguration(song);
        panel.add(centralPanel,BorderLayout.CENTER);
        //mainViewCenter.add(panel, "songDetailsCard");
        mainViewCenter.revalidate();
        cardManager.show(mainViewCenter, "songDetailsCard");
        //mainView.setVisible(true);
    }
}
