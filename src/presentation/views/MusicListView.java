package presentation.views;

import business.entities.Song;
import presentation.controllers.MusicListController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;



public class MusicListView {
    private final JFrame topContainer;
    private final CardLayout cardManager;

    private JTable table;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    public MusicListView (JFrame topContainer, CardLayout cardManager){
        this.topContainer=topContainer;
        this.cardManager=cardManager;
        configureView();
        topContainer.pack();
    }

    private void configureView() {
        //Creation of main panels
        LinkedList<Song> songLinkedList = generateSong();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        JPanel centralPanel = centralPanelConfiguration(songLinkedList); //todo ha de rebre la linkedlist de songs
        JPanel northernPanel = northernPanelConfiguration(); //todo ha de rebre el nom de la playlist
        JPanel easternPanel = easternPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);
        panel.add(easternPanel,BorderLayout.EAST);


        topContainer.getContentPane().add(panel, "musicListCard");//todo ??

    }

    private LinkedList<Song> generateSong() {
        LinkedList<Song> linkedList = new LinkedList<>();
        for(int i=0; i < 200; i++){
            linkedList.add(new Song("songName",i,"album","albumName","singerName","You",i*2));
        }

        return linkedList;
    }

    private JPanel easternPanelConfiguration() {
        Color negre = new Color(48,48,48);

        JPanel easternPanel = new JPanel();
        GridBagLayout easternLayout = new GridBagLayout();
        easternPanel.setLayout(easternLayout);
        easternPanel.setBorder(new EmptyBorder(new Insets(10, 30, 400, 30)));
        easternPanel.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5); //Space between components

        JButton addSong = createButton("Add song");
        JButton removeSong = createButton("Remove song");
        JButton renamePlaylist = createButton("Rename Playlist");
        JButton deletePlaylist = createButton("Delete Playlist");
        JButton addPersonalSong = createButton("Add personal song");

        c.gridx = 0;
        c.gridy = 0;
        easternPanel.add(addSong,c);

        c.gridx = 0;
        c.gridy = 1;
        easternPanel.add(removeSong,c);

        c.gridx = 0;
        c.gridy = 2;
        easternPanel.add(renamePlaylist,c);

        c.gridx = 0;
        c.gridy = 3;
        easternPanel.add(deletePlaylist,c);

        c.gridx = 0;
        c.gridy = 4;
        easternPanel.add(addPersonalSong,c);

        return easternPanel;
    }

    private JPanel northernPanelConfiguration() {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 65);

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(50, 250, 0, 50)));

        JLabel homeLabel = new JLabel("Chill Out");
        homeLabel.setFont(titols);
        homeLabel.setForeground(Color.white);

        northernPanel.add(homeLabel);
        return northernPanel;
    }

    private JPanel centralPanelConfiguration(LinkedList<Song> songList) {
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 20);

        //Central panel configuration
        JPanel centraPanel = new JPanel();
        centraPanel.setBackground(negre);

        //Model creation and config
        String[] columnNames = {"","Name", "Author", "Album", "Genre", "Owner"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);

        //Table creation and config
        table = new JTable(model);
        table.setBackground(negre);
        table.setFont(titols);
        table.setForeground(Color.white);
        table.setFont(titols);
        table.setGridColor(negre);
        table.setRowHeight(30);

        //Scrollpane creation and config
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(1000, 670));
        scrollPane.setBackground(negre);

        for(int i=0; i < songList.size(); i++){
            Object[] aux = {new JButton("Button"),songList.get(i).getName(),songList.get(i).getSinger(),
                    songList.get(i).getAlbum(),songList.get(i).getGenre(),songList.get(i).getOwne()};

            model.addRow(aux);
        }


        centraPanel.add(scrollPane);

        return centraPanel;
    }

    private JPanel westernPanelConfiguration() {
        //Fonts, colours and sizes
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

    private Image getScaledImage(Image img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    private JButton createButton(String name) {
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

    public void registerController(MusicListController musicListController) {
        table.getSelectionModel().addListSelectionListener(musicListController);
    }

    public int getRow() {
        return table.getSelectedRow();
    }

    public void showCard() {
        cardManager.show(topContainer.getContentPane(),"musicListCard");
    }
}
