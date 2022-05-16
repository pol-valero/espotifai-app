package presentation.views;

import business.entities.Playlist;
import business.entities.Song;
import presentation.controllers.MusicListController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;



public class MusicListView {
    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_ACCOUNTMANAGER = "BTN_ACCOUNTMANAGER";
    public static final String BTN_STADISTICS = "BTN_STADISTICS";
    public static final String BTN_SEARCH = "BTN_SEARCH";
    public static final String BTN_ADDSONG = "BTN_ADDSONG";
    public static final String BTN_REMOVESONG = "BTN_REMOVESONG";
    public static final String BTN_RENAMEPLAYLIST = "BTN_RENAMEPLAYLIST";
    public static final String BTN_DELETE = "BTN_DELETE";
    public static final String BTN_ADDNEWSONG = "BTN_ADDNEWSONG";

    private JTable table;
    private Object[][] data;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    JPanel panel;
    JPanel centralPanel;
    JPanel northernPanel;

    public MusicListView (JFrame topContainer, CardLayout cardManager){
        this.topContainer=topContainer;
        this.cardManager=cardManager;
        configureView();
        topContainer.pack();
    }

    private void configureView() {
        //Creation of main panels
        //LinkedList<Song> songLinkedList = generateSong();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        centralPanel = centralPanelConfiguration(new LinkedList<>());
        northernPanel = northernPanelConfiguration(new String());
        JPanel easternPanel = easternPanelConfiguration();


        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(easternPanel,BorderLayout.EAST);
        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(centralPanel,BorderLayout.CENTER);
        panel.remove(centralPanel);
        panel.remove(northernPanel);

        topContainer.getContentPane().add(panel, "musicListCard");//todo ??

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
        addSong.setActionCommand(BTN_ADDSONG);

        JButton removeSong = createButton("Remove song");
        removeSong.setActionCommand(BTN_REMOVESONG);

        JButton renamePlaylist = createButton("Rename Playlist");
        renamePlaylist.setActionCommand(BTN_RENAMEPLAYLIST);

        JButton deletePlaylist = createButton("Delete Playlist");
        deletePlaylist.setActionCommand(BTN_DELETE);

        JButton addNewSong = createButton("Add new song");
        addNewSong.setActionCommand(BTN_ADDNEWSONG);

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
        easternPanel.add(addNewSong,c);

        return easternPanel;
    }

    private JPanel northernPanelConfiguration(String playlistName) {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 65);
        playlistName = "Chill out";

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(50, 250, 0, 50)));

        JTextField playlistTitle = new JTextField();
        playlistTitle.setFont(titols);
        playlistTitle.setText(playlistName);
        playlistTitle.setForeground(Color.white);
        playlistTitle.setBackground(negre);
        playlistTitle.setBorder(BorderFactory.createLineBorder(negre));

        northernPanel.add(playlistTitle);
        return northernPanel;
    }

    private JPanel centralPanelConfiguration(LinkedList<Song> songList) {
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 20);

        //Central panel configuration
        JPanel centralPanel = new JPanel();
        centralPanel.setBackground(negre);

        //Data conversion from Linkedlist to 2D Matrix
        data = songConversor(songList);

        //Model creation and config
        String[] columnNames = {"","Name", "Author", "Album", "Genre", "Owner"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        //Table creation and config
        table = new JTable(model){
            public Class getColumnClass(int column) { //Specifing that for first column, class is Icon
                return (column == 0) ? Icon.class : Object.class;
            }
        };
        table.setBackground(negre);
        table.setFont(titols);
        table.setForeground(Color.white);
        table.setFont(titols);
        table.setGridColor(negre);
        table.setRowHeight(50);
        table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.setDefaultEditor(Object.class, null);

        //Scrollpane creation and config
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(1000, 670));
        scrollPane.setBackground(negre);

        centralPanel.add(scrollPane);

        return centralPanel;
    }

    private JPanel westernPanelConfiguration() {

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
        Image magLensIconScaled = getScaledImage(magLensIcon,20,20);
        ImageIcon magnifyingLens = new ImageIcon(magLensIconScaled);

        JButton searchBtn = new JButton();
        searchBtn.setIcon(magnifyingLens);
        searchBtn.setBackground(vermell);
        searchBtn.setFocusable(false);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);
        searchBtn.setMaximumSize(new Dimension(30,30));
        searchBtn.setActionCommand(BTN_SEARCH);

        searchPanel.add(searchBar);
        searchPanel.add(Box.createRigidArea(new Dimension(20,0))); //Create space between both buttons
        searchPanel.add(searchBtn);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(negre);

        GridBagConstraints constraints = new GridBagConstraints();

        JButton stadisticsBtn = createButton("Stadistics");
        stadisticsBtn.setActionCommand(BTN_STADISTICS);

        JButton accManBtn = createButton("Account Manager");
        accManBtn.setActionCommand(BTN_ACCOUNTMANAGER);

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

    private Object[][] songConversor(LinkedList<Song> songList) { //todo descomentar quan calgui
        //Object[][] data = new Object[songList.size()][6];
        Object[][] data = new Object[201][6];

        ImageIcon songCover = new ImageIcon("images/musicCoverMusicList.png");
        Image songCoverImage = songCover.getImage();
        Image songCoverScaled = getScaledImage(songCoverImage,40,40);
        ImageIcon songCoverDone = new ImageIcon(songCoverScaled);

        for(int i=0; i < 200; i++){
            data[i][0] = songCoverDone;
            data[i][1] = "songName"+i;
            data[i][2] = "Singer"+i;
            data[i][3] = "Album"+i;
            data[i][4] = "Genre"+i;
            data[i][5] = "Owner"+i;
        }

        /*for(int i=0; i < songList.size(); i++){
            data[i][0] = songList.get(i).getName();
            data[i][1] = songList.get(i).getSinger();
            data[i][2] = songList.get(i).getAlbum();
            data[i][3] = songList.get(i).getGenre();
            data[i][4] = songList.get(i).getOwne();
        }*/

        return data;
    }

    private Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
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

    public String getSongName(int row){
        return (String)data[row][0];
    }

    public void showCard(Playlist playlist) {

        //LinkedList<Song> musicList = playlist.getSonglist(); //todo obtenir de la playlist
        String playlistName = playlist.getName();

        panel.remove(centralPanel);
        panel.remove(northernPanel);

        //centralPanel = centralPanelConfiguration(musicList);
        northernPanel = northernPanelConfiguration(playlistName);

        panel.add(centralPanel,BorderLayout.CENTER);
        panel.add(northernPanel,BorderLayout.NORTH);

        topContainer.revalidate();
        cardManager.show(topContainer.getContentPane(),"musicListCard");
    }

}

