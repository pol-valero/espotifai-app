package presentation.views;

import business.entities.Playlist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class AddToPlaylistView {
    private final JPanel mainViewCenter;
    private final CardLayout cardManager;
    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    public AddToPlaylistView(JPanel mainViewCenter, CardLayout cardManager){
        this.mainViewCenter = mainViewCenter;
        this.cardManager=cardManager;
        configureView();
        //topContainer.pack();
    }

    private void configureView() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        JPanel centralPanel = centralPanelConfiguration(new LinkedList<>());

        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);

        mainViewCenter.add(panel,"addToPlaylistCard");

    }

    private JPanel westernPanelConfiguration() {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);

        JPanel westernPanel = new JPanel();
        BoxLayout westernLayout = new BoxLayout(westernPanel,BoxLayout.Y_AXIS);
        westernPanel.setLayout(westernLayout);
        westernPanel.setBorder(new EmptyBorder(new Insets(750, 30, 40, 30)));
        westernPanel.setMinimumSize(new Dimension(200,900));
        westernPanel.setBackground(negre);

        JButton accMBtn = createButton("Account Manager");

        westernPanel.add(accMBtn);


        return westernPanel;
    }

    private JPanel centralPanelConfiguration(LinkedList<String> playlists){
        playlists = generateLinkedlist(playlists);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 54);
        Font text = new Font("Trebuchet MS", Font.PLAIN, 24);

        JPanel centralPanel = new JPanel();
        BoxLayout centralLayout = new BoxLayout(centralPanel,BoxLayout.Y_AXIS);
        centralPanel.setLayout(centralLayout);
        centralPanel.setBackground(negre);
        centralPanel.setBorder(new EmptyBorder(new Insets(100,50,100,50)));



        //Title Panel config
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        titlePanel.setBackground(negre);

        JLabel title = new JLabel("Add to Playlist");
        title.setFont(titols);
        title.setForeground(Color.white);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Chose one of the playlist to add the song");
        subtitle.setFont(text);
        subtitle.setForeground(Color.white);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(title);
        titlePanel.add(subtitle);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Panel ScrollPane Playlist
        playlists.addFirst("AllSongs");
        playlists.addLast("Final");

        JPanel playlistScrollPanel = new JPanel();
        playlistScrollPanel.setBackground(negre);
        JScrollPane scrollPane = (JScrollPane) createScrollPane(playlists);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        playlistScrollPanel.add(scrollPane); //todo
        playlistScrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel donePanel = new JPanel();
        donePanel.setBorder(new EmptyBorder(new Insets(0,850,0,0)));
        donePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        donePanel.setBackground(negre);
        donePanel.add(createButton("Done"));

        centralPanel.add(titlePanel);
        centralPanel.add(playlistScrollPanel);
        centralPanel.add(donePanel);

        return centralPanel;
    }

    private Component createScrollPane(LinkedList<String> playListNames) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,getIdealHeight(playListNames.size()))); // calcular la height
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,40,5));
        panel.setBackground(negre);

        for(int i=0; i < playListNames.size(); i++){

            String playListName = playListNames.get(i);

            //Panel which contains button and label
            JPanel miniPanel = new JPanel();
            BoxLayout miniPanelLayout = new BoxLayout(miniPanel,BoxLayout.Y_AXIS);
            miniPanel.setLayout(miniPanelLayout);

            //Label with the playlist name
            JLabel playlistTitle = new JLabel(playListName);
            playlistTitle.setForeground(Color.white);
            playlistTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


            ImageIcon defaultCover = new ImageIcon("images/albumCoverDefault.png");
            Image defaultCoverIcon = defaultCover.getImage();
            Image defaultCoverIconScaled = getScaledImage(defaultCoverIcon, 160, 160);
            ImageIcon albumCoverDefault = new ImageIcon(defaultCoverIconScaled);

            //Button config
            JButton jbPlaylist = new JButton();
            jbPlaylist.setPreferredSize(new Dimension(160,160));
            jbPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);
            jbPlaylist.setIcon(albumCoverDefault);

            //Adding components to panel
            miniPanel.add(jbPlaylist);
            miniPanel.add(Box.createRigidArea(new Dimension(0,5)));
            miniPanel.add(playlistTitle);
            miniPanel.setBackground(negre);

            panel.add(miniPanel);
            //buttons.get(i).setActionCommand(); todo
        }

        JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(panel);
        scrollPane.setPreferredSize(new Dimension(1060,500));
        scrollPane.setBackground(negre);

        return scrollPane;
    }

    private int getIdealHeight(int size) {
       double residu = size%5;
       System.out.print("Size: "+size+" Residu: "+residu+"\n");

        if (residu == 1) {
            size += 4;
        }
        else if (residu == 2) {
            size += 3;

        }
        else if (residu == 3) {
            size += 2;

        }
        else if (residu == 4) {
            size += 1;
        }
        return size*38;


    }
    private LinkedList<String> generateLinkedlist(LinkedList<String> playlists) {
        for(int i=0; i < 20; i++){
            playlists.add("Playlist"+i);
        }

        return playlists;
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


    private Image getScaledImage(Image img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    public void showCard(){
        cardManager.show(mainViewCenter,"addToPlaylistCard");
    }
}
