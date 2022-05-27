package presentation.views;

import business.entities.Song;
import presentation.Components.ImagePanel;
import presentation.controllers.MusicListController;
import presentation.Components.SimpleHeaderRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class MusicListView {
    private final JPanel mainViewCenter;
    private final CardLayout cardManager;

    public static final String BTN_ACCOUNTMANAGER = "BTN_ACCOUNTMANAGER";
    public static final String BTN_STADISTICS = "BTN_STADISTICS";
    public static final String BTN_SEARCH = "BTN_SEARCH";
    public static final String BTN_ADDSONG = "BTN_ADDSONG";
    public static final String BTN_REMOVESONG = "BTN_REMOVESONG";
    public static final String BTN_RENAMEPLAYLIST = "BTN_RENAMEPLAYLIST";
    public static final String BTN_DELETE = "BTN_DELETE";
    public static final String BTN_ADDPERSONALSONG = "BTN_ADDPERSONALSONG";
    public static final String BTN_REMOVEPERSONALSONG = "BTN_REMOVEPERSONALSONG";
    public static final String BTN_UP = "BTN_UP";
    public static final String BTN_DOWN = "BTN_DOWN";
    public static final String BTN_REMOVE_SELECTED_PLAYLIST_SONGS = "BTN_REMOVE_SELECTED_PLAYLIST_SONGS";
    public static final String BTN_REMOVE_SELECTED_PERSONAL_SONGS = "BTN_REMOVE_SELECTED_PERSONAL_SONGS";
    public static final String BTN_ADD_SELECTED = "BTN_ADD_SELECTED";
    public static final String BTN_CANCEL = "BTN_CANCEL";
    public static final String BTN_HOME = "BTN_HOME";

    private JButton addSong;
    private JButton removeSong;
    private JButton renamePlaylist;
    private JButton deletePlaylist;
    private JButton addPersonalSong;
    private JButton removePersonalSong;
    private JButton removeSelectedPlaylistSongs;
    private JButton removeSelectedPersonalSongs;
    private JButton addSelectedSongs;
    private JButton cancel;
    private JButton upBtn ;
    private JButton downBtn;
    private JButton searchBtn;
    private JTextField searchBar;
    private JButton stadisticsBtn;
    private JButton jbHome;
    private JButton accManBtn;

    private JLabel playlistTitle;

    private DefaultTableModel model;

    private JTable table;
    private Object[][] data;

    private ImagePanel imagePanel;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    private JPanel panel;
    private JPanel centralPanel;
    private JPanel northernPanel;

    private boolean userPersonalSongsVariationActive;
    private boolean userPlaylistVariationActive;
    private boolean publicPlaylistVariationActive;
    private boolean allSongsVariationActive;

    public MusicListView (JPanel mainViewCenter, CardLayout cardManager){
        this.mainViewCenter =mainViewCenter;
        this.cardManager=cardManager;
        configureTable();
        configureView();
        //topContainer.pack();
    }

    private void configureTable () {
        table = new JTable(model){
            public Class getColumnClass(int column) {//Specifing class for column 0 and 6
                switch(column){
                    case 0 -> {
                        return Icon.class;
                    }
                    case 6 -> {
                        return Boolean.class;
                    }
                    default -> {return String.class;}
                }
            }
        };
    }

    private void configureView() {
        //Creation of main panels
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

        mainViewCenter.add(panel, "musicListCard");//todo ??

    }

    private JPanel easternPanelConfiguration() {
        Color negre = new Color(48,48,48);

        JPanel easternPanel = new JPanel();
        BoxLayout easternLayout = new BoxLayout(easternPanel,BoxLayout.Y_AXIS);
        easternPanel.setLayout(easternLayout);
        easternPanel.setBackground(Color.blue);
        easternPanel.setBorder(new EmptyBorder(new Insets(50, 30, 40, 30)));
        easternPanel.setPreferredSize(new Dimension(270,700));
        easternPanel.setBackground(negre);

        //Upper buttons config
        JPanel upperButtons = new JPanel();
        GridBagLayout upperButtonsLayout = new GridBagLayout();
        upperButtons.setLayout(upperButtonsLayout);
        upperButtons.setMaximumSize(new Dimension(200,400));
        upperButtons.setBackground(negre);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5); //Space between components

        addSong = createButton("Add song");
        addSong.setActionCommand(BTN_ADDSONG);

        removeSong = createButton("Remove song");
        removeSong.setActionCommand(BTN_REMOVESONG);

        renamePlaylist = createButton("Rename Playlist");
        renamePlaylist.setActionCommand(BTN_RENAMEPLAYLIST);

        deletePlaylist = createButton("Delete Playlist");
        deletePlaylist.setActionCommand(BTN_DELETE);

        addPersonalSong = createButton("Add personal song");
        addPersonalSong.setActionCommand(BTN_ADDPERSONALSONG);

        removePersonalSong = createButton("Remove personal song");
        removePersonalSong.setActionCommand(BTN_REMOVEPERSONALSONG);

        cancel = createButton("Cancel");
        cancel.setActionCommand(BTN_CANCEL);

        removeSelectedPlaylistSongs = createButton("Remove selected");
        removeSelectedPlaylistSongs.setActionCommand(BTN_REMOVE_SELECTED_PLAYLIST_SONGS);

        removeSelectedPersonalSongs = createButton("Remove selected");
        removeSelectedPersonalSongs.setActionCommand(BTN_REMOVE_SELECTED_PERSONAL_SONGS);





        addSelectedSongs = createButton("Add selected");
        addSelectedSongs.setActionCommand(BTN_ADD_SELECTED);




        c.gridx = 0;
        c.gridy = 0;
        upperButtons.add(addSong,c);

        c.gridx = 0;
        c.gridy = 1;
        upperButtons.add(removeSong,c);

        c.gridx = 0;
        c.gridy = 2;
        upperButtons.add(renamePlaylist,c);

        c.gridx = 0;
        c.gridy = 3;
        upperButtons.add(deletePlaylist,c);

        c.gridx = 0;
        c.gridy = 4;
        upperButtons.add(addPersonalSong,c);

        c.gridx = 0;
        c.gridy = 5;
        upperButtons.add(removePersonalSong,c);

        c.gridx = 0;
        c.gridy = 6;
        upperButtons.add(removeSelectedPlaylistSongs,c);

        c.gridx = 0;
        c.gridy = 7;
        upperButtons.add(removeSelectedPersonalSongs, c);

        c.gridx = 0;
        c.gridy = 8;
        upperButtons.add(addSelectedSongs, c);

        c.gridx = 0;
        c.gridy = 9;
        upperButtons.add(cancel, c);

        //Sorting Panel Config
        JPanel sortingPanel = new JPanel();
        BoxLayout sortingLayout = new BoxLayout(sortingPanel,BoxLayout.X_AXIS);
        sortingPanel.setLayout(sortingLayout);
        sortingPanel.setBackground(negre);

        upBtn = createButton("Up");
        upBtn.setActionCommand(BTN_UP);

        downBtn = createButton("Down");
        downBtn.setActionCommand(BTN_DOWN);

        sortingPanel.add(upBtn);
        sortingPanel.add(Box.createRigidArea(new Dimension(20,0)));
        sortingPanel.add(downBtn);

        easternPanel.add(upperButtons);
        easternPanel.add(Box.createRigidArea(new Dimension(0,40)));
        easternPanel.add(sortingPanel);



        return easternPanel;

    }

    private JPanel northernPanelConfiguration(String playlistName) {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 65);

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(50, 250, 0, 50)));

        playlistTitle = new JLabel();
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
        String[] columnNames = {"","Name", "Author", "Album", "Genre", "Owner",""};
        model = new DefaultTableModel(data, columnNames);

        //Table creation and config
        table.setModel(model);

        table.setFillsViewportHeight(true);
        /*table = new JTable(model){
        table {
            public Class getColumnClass(int column) {//Specifing class for column 0 and 6
                switch(column){
                    case 0 -> {
                        return Icon.class;
                    }
                    case 6 -> {
                        return Boolean.class;
                    }
                    default -> {return String.class;}
                }
            }
        };*/

        table.setBackground(negre);
        table.setFont(titols);
        table.setForeground(Color.white);
        table.setFont(titols);
        table.setGridColor(negre);
        table.setRowHeight(50);
        table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        table.setDefaultEditor(Object.class, null);
        table.getColumnModel().getColumn(6).setMinWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(0);
        //table.removeColumn(table.getColumnModel().getColumn(6)); todo mètode per "amagar" una playlist

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
        westernPanel.setPreferredSize(new Dimension(270,900));
        westernPanel.setBackground(negre);

        //Search Panel
        JPanel searchPanel = new JPanel();
        BoxLayout searchLayout = new BoxLayout(searchPanel,BoxLayout.X_AXIS);
        searchPanel.setLayout(searchLayout);
        searchPanel.setBackground(negre);
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchBar = new JTextField();
        searchBar.setMaximumSize(new Dimension(300,30));

        ImageIcon magLens = new ImageIcon("images/LUPA AI.png");
        Image magLensIcon = magLens.getImage();
        Image magLensIconScaled = getScaledImage(magLensIcon,20,20);
        ImageIcon magnifyingLens = new ImageIcon(magLensIconScaled);

        searchBtn = new JButton();
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

        jbHome = createButton("Home");
        jbHome.setActionCommand(BTN_HOME);

        stadisticsBtn = createButton("Stadistics");
        stadisticsBtn.setActionCommand(BTN_STADISTICS);

        accManBtn = createButton("Account Manager");
        accManBtn.setActionCommand(BTN_ACCOUNTMANAGER);

        constraints.insets = new Insets(5,5,5,5);

        constraints.gridx=0;
        constraints.gridy=0;
        buttonsPanel.add(stadisticsBtn,constraints);

        constraints.gridx=0;
        constraints.gridy=1;
        buttonsPanel.add(jbHome, constraints);

        constraints.gridx=0;
        constraints.gridy=2;
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,400)),constraints);

        constraints.gridx=0;
        constraints.gridy=3;
        buttonsPanel.add(accManBtn,constraints);


        westernPanel.add(searchPanel);
        westernPanel.add(Box.createRigidArea(new Dimension(0,40)));
        westernPanel.add(buttonsPanel);


        return westernPanel;
    }

    private Object[][] songConversor(LinkedList<Song> songList) { //todo descomentar quan calgui
        //Object[][] data = new Object[songList.size()][6];
        Object[][] data = new Object[songList.size()][7];

        ImageIcon songCover = new ImageIcon("images/musicCoverMusicList.png");
        Image songCoverImage = songCover.getImage();
        Image songCoverScaled = getScaledImage(songCoverImage,40,40);
        ImageIcon songCoverDone = new ImageIcon(songCoverScaled);

        /*for(int i=0; i < 200; i++){
            data[i][0] = songCoverDone;
            data[i][1] = "songName"+i;
            data[i][2] = "Singer"+i;
            data[i][3] = "Album"+i;
            data[i][4] = "Genre"+i;
            data[i][5] = "Owner"+i;
            data[i][6] = false;
        }*/

        for(int i=0; i < songList.size(); i++){
            data[i][0] = songCoverDone;
            data[i][1] = songList.get(i).getName();
            data[i][2] = songList.get(i).getSinger();
            data[i][3] = songList.get(i).getAlbum();
            data[i][4] = songList.get(i).getGenre();
            data[i][5] = songList.get(i).getOwne();
            data[i][6] = false;
        }

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
        Dimension button_shape = new Dimension(170,40);
        Font text = new Font("Gulim", Font.PLAIN, 17);

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
        table.addMouseListener(musicListController);
        addSong.addActionListener(musicListController);
        removeSong.addActionListener(musicListController);
        renamePlaylist.addActionListener(musicListController);
        deletePlaylist.addActionListener(musicListController);
        addPersonalSong.addActionListener(musicListController);
        removePersonalSong.addActionListener(musicListController);
        removeSelectedPlaylistSongs.addActionListener(musicListController);
        removeSelectedPersonalSongs.addActionListener(musicListController);
        addSelectedSongs.addActionListener(musicListController);
        cancel.addActionListener(musicListController);
        upBtn.addActionListener(musicListController);
        downBtn.addActionListener(musicListController);
        searchBtn.addActionListener(musicListController);
        stadisticsBtn.addActionListener(musicListController);
        jbHome.addActionListener(musicListController);
        accManBtn.addActionListener(musicListController);
    }

    public int getRow() {
        return table.getSelectedRow();
    }

    public int getColumn() {
        return table.getSelectedColumn();
    }

    public int getRowAtPoint(Point point) {
        return table.rowAtPoint(point);
    }

    public int getColumnAtPoint(Point point) {
        return table.columnAtPoint(point);
    }

    public String getSongName(int row){
        return (String) table.getValueAt(row, 1);
    }

    private void hideCheckBox(){
        table.getColumnModel().getColumn(6).setMinWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(0);
        table.getColumnModel().getColumn(6).setMinWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(0);
    }

    public void showCheckbox(){
        table.getColumnModel().getColumn(6).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMaxWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMaxWidth(100);
    }

    public void moveDown() {
        int index = table.getSelectedRow();
        model.moveRow(index, index,index+1);
        table.setRowSelectionInterval(index+1,index+1);
    }

    public void moveUp() {
        int index = table.getSelectedRow();
        model.moveRow(index, index,index-1);
        table.setRowSelectionInterval(index-1,index-1);
    }

    public void showCard(LinkedList<Song> musicList, String playlistName) {

        panel.remove(centralPanel);
        panel.remove(northernPanel);

        centralPanel = centralPanelConfiguration(musicList);
        northernPanel = northernPanelConfiguration(playlistName);

        panel.add(centralPanel,BorderLayout.CENTER);
        panel.add(northernPanel,BorderLayout.NORTH);

        mainViewCenter.revalidate();
        cardManager.show(mainViewCenter,"musicListCard");
    }

    public int getSongListSize() {
        return table.getRowCount()-1; //todo revisar
    }

    public void removePlaylistSongsVariation() {
        showCheckbox();
        addSong.setVisible(false);
        removeSong.setVisible(false);
        renamePlaylist.setVisible(false);
        deletePlaylist.setVisible(false);
        addPersonalSong.setVisible(false);
        removePersonalSong.setVisible(false);
        removeSelectedPlaylistSongs.setVisible(true);
        removeSelectedPersonalSongs.setVisible(false);
        addSelectedSongs.setVisible(false);
        cancel.setVisible(true);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        searchBtn.setVisible(false);
        searchBar.setVisible(false);
        mainViewCenter.revalidate();
    }

    public void removePersonalSongsVariation() {
        showCheckbox();
        addSong.setVisible(false);
        removeSong.setVisible(false);
        renamePlaylist.setVisible(false);
        deletePlaylist.setVisible(false);
        addPersonalSong.setVisible(false);
        removePersonalSong.setVisible(false);
        removeSelectedPlaylistSongs.setVisible(false);
        removeSelectedPersonalSongs.setVisible(true);
        addSelectedSongs.setVisible(false);
        cancel.setVisible(true);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        searchBtn.setVisible(false);
        searchBar.setVisible(false);
        mainViewCenter.revalidate();
    }

    public void addSongsVariation() {
        showCheckbox();
        addSong.setVisible(false);
        removeSong.setVisible(false);
        renamePlaylist.setVisible(false);
        deletePlaylist.setVisible(false);
        addPersonalSong.setVisible(false);
        removePersonalSong.setVisible(false);
        removeSelectedPlaylistSongs.setVisible(false);
        removeSelectedPersonalSongs.setVisible(false);
        addSelectedSongs.setVisible(true);
        cancel.setVisible(true);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        searchBtn.setVisible(false);
        searchBar.setVisible(false);
        mainViewCenter.revalidate();
    }

    public void userPersonalSongsVariation() {
        hideCheckBox();
        addSong.setVisible(false);
        removeSong.setVisible(false);
        renamePlaylist.setVisible(false);
        deletePlaylist.setVisible(false);
        addPersonalSong.setVisible(true);
        removePersonalSong.setVisible(true);
        removeSelectedPlaylistSongs.setVisible(false);
        removeSelectedPersonalSongs.setVisible(false);
        addSelectedSongs.setVisible(false);
        cancel.setVisible(false);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        searchBtn.setVisible(false);
        searchBar.setVisible(false);
        mainViewCenter.revalidate();
        setVariationsToInactive();
        userPersonalSongsVariationActive = true;
    }

    public void userPlaylistVariation() {
        hideCheckBox();
        addSong.setVisible(true);
        removeSong.setVisible(true);
        renamePlaylist.setVisible(true);
        deletePlaylist.setVisible(true);
        addPersonalSong.setVisible(false);
        removePersonalSong.setVisible(false);
        removeSelectedPlaylistSongs.setVisible(false);
        removeSelectedPersonalSongs.setVisible(false);
        addSelectedSongs.setVisible(false);
        cancel.setVisible(false);
        upBtn.setVisible(true);
        downBtn.setVisible(true);
        searchBtn.setVisible(false);
        searchBar.setVisible(false);
        mainViewCenter.revalidate();
        setVariationsToInactive();
        userPlaylistVariationActive = true;
    }

    public void publicPlaylistVariation() {
        hideCheckBox();
        addSong.setVisible(false);
        removeSong.setVisible(false);
        renamePlaylist.setVisible(false);
        deletePlaylist.setVisible(false);
        addPersonalSong.setVisible(false);
        removePersonalSong.setVisible(false);
        removeSelectedPlaylistSongs.setVisible(false);
        removeSelectedPersonalSongs.setVisible(false);
        addSelectedSongs.setVisible(false);
        cancel.setVisible(false);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        searchBtn.setVisible(false);
        searchBar.setVisible(false);
        mainViewCenter.revalidate();
        setVariationsToInactive();
        publicPlaylistVariationActive = true;
    }

    public void publicSongsVariation(){
        hideCheckBox();
        addSong.setVisible(false);
        removeSong.setVisible(false);
        renamePlaylist.setVisible(false);
        deletePlaylist.setVisible(false);
        addPersonalSong.setVisible(false);
        removePersonalSong.setVisible(false);
        removeSelectedPlaylistSongs.setVisible(false);
        removeSelectedPersonalSongs.setVisible(false);
        addSelectedSongs.setVisible(false);
        cancel.setVisible(false);
        upBtn.setVisible(false);
        downBtn.setVisible(false);
        searchBtn.setVisible(true);
        searchBar.setVisible(true);
        mainViewCenter.revalidate();
        setVariationsToInactive();
        allSongsVariationActive = true;
    }

    public void returnToPreviousVariation() {

        if (userPersonalSongsVariationActive) {
            userPersonalSongsVariation();
        }

        if (userPlaylistVariationActive) {
            userPlaylistVariation();
        }

        if (publicPlaylistVariationActive) {
            publicPlaylistVariation();
        }

        if (allSongsVariationActive) {
            publicSongsVariation();
        }
    }

    public void setVariationsToInactive() {
        //aquesta funcio posara totes les variacions a false. Cridarem aquesta funcio dins del procediment de cada variacio (just abans d'activar el boolean de la variacio)
        userPersonalSongsVariationActive = false;
        userPlaylistVariationActive = false;
        publicPlaylistVariationActive = false;
        allSongsVariationActive = false;
    }

    public String getDisplayedPlaylistName() {
        return playlistTitle.getText();
    }

    public void clearCheckBoxes () {
        System.out.println("prova");

        for (int i = 0; i < data.length; i++) {
            //data[i][6] = false;
            //table.setModel();
            table.setValueAt(false, i, 6);
        }
        table.revalidate();

    }

}
