package presentation.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class RemovePlaylistView {

    public static final String BTN_GO_BACK = "BTN_GO_BACK";
    public static final String BTN_REMOVE = "BTN_REMOVE";
    public static final String  BTN_CANCEL = "BTN_CANCEL";


    private final JFrame topContainer;
    private final CardLayout cardManager;
    private JButton jbManagement = new JButton();
    private JButton jbRemovePlaylist = new JButton();
    private JButton jbCancel = new JButton();
    private JLabel goBackImage = new JLabel();

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    JPanel panel;
    JPanel centralPanel;

    public RemovePlaylistView (JFrame topContainer, CardLayout cardManager){
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        configureView();
        topContainer.pack();
    }

    private void configureView() {

        //Colors, fonts and sizes
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel southernPanel = southernPanelConfiguration();

        centralPanel = centralPanelConfiguration("Temporal", 0);
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(southernPanel,BorderLayout.SOUTH);
        panel.add(centralPanel,BorderLayout.CENTER);

        topContainer.getContentPane().add(panel, "removePlaylistCard");
    }

    private JPanel centralPanelConfiguration(String playlistName, int songNumber) {

        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font subtitle = new Font("Trebuchet MS", Font.PLAIN, 30);
        Font titol = new Font("Trebuchet MS", Font.PLAIN, 38);
        Font button = new Font("Trebuchet MS", Font.PLAIN, 20);
        Font text = new Font("Gulim", Font.PLAIN, 14);
        Font information = new Font("Gulim", Font.PLAIN, 14);

        Dimension button_shape = new Dimension(245,40);
        Dimension CentralPanel = new Dimension(500,500);
        //   Dimension button_shape_2 = new Dimension(245,40);


        // Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(negre);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setBackground(negre);
        centralPanel.setMaximumSize(CentralPanel);


        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();


        c.insets = new Insets(10,30,10,30); //Space between components
        d.insets = new Insets(0,30,0,30); //Space between components


        JPanel info_panel = new JPanel();
        info_panel.setMaximumSize(CentralPanel);


        // tittle

        JPanel  title_panel = new JPanel();
        title_panel.setLayout(new BoxLayout( title_panel, BoxLayout.Y_AXIS));
        title_panel.setBackground(negre);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Remove playlist");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titol);

        JLabel explanation = new JLabel();
        explanation.setText("<html>You are about to remove the following playlist. Are you sure?</html>" );
        explanation.setForeground(Color.white);
        explanation.setFont(information);

        title_panel.add(titleLabel);
        //title_panel.add(Box.createRigidArea(new Dimension(50, 15)));

        title_panel.add(explanation);
        //  subtitle_panel.add(Box.createRigidArea(new Dimension(50, 15)));

        info_panel.add(title_panel);
        info_panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Playlist label

        JPanel label_panel = new JPanel();
        label_panel.setLayout(new BoxLayout(label_panel, BoxLayout.LINE_AXIS));
        label_panel.setBackground(negre);

        JLabel playListNameLabel = new JLabel();
        playListNameLabel.setText(playlistName);
        playListNameLabel.setForeground(Color.white);
        playListNameLabel.setFont(subtitle);

        label_panel.add( playListNameLabel);
        label_panel.add(Box.createRigidArea(new Dimension(50, 0)));

        String showNumber = null;
        JLabel songNumberLabel = new JLabel();
        songNumberLabel.setText( showNumber.valueOf(songNumber));
        songNumberLabel.setForeground(Color.white);
        songNumberLabel.setFont(subtitle);

        label_panel.add(songNumberLabel);
        info_panel.add(label_panel);
        info_panel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Remove and cancel playlist button

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.LINE_AXIS));
        button_panel.setBackground(negre);

        jbRemovePlaylist.setBackground(vermell);
        jbRemovePlaylist.setForeground(Color.white);
        jbRemovePlaylist.setText("Remove Playlist ");
        jbRemovePlaylist.setFont(subtitle);
        jbRemovePlaylist.setPreferredSize(button_shape);
        jbRemovePlaylist.setFocusable(false);
        jbRemovePlaylist.setOpaque(true);
        jbRemovePlaylist.setBorderPainted(false);
        jbRemovePlaylist.setActionCommand(BTN_REMOVE);

        button_panel.add( jbRemovePlaylist);
        button_panel.add(Box.createRigidArea(new Dimension(15, 0)));

        jbCancel.setBackground(vermell);
        jbCancel.setForeground(Color.white);
        jbCancel.setText("Cancel ");
        jbCancel.setFont(subtitle);
        jbCancel.setPreferredSize(button_shape);
        jbCancel.setFocusable(false);
        jbCancel.setOpaque(true);
        jbCancel.setBorderPainted(false);
        jbCancel.setActionCommand(BTN_CANCEL);

        button_panel.add(jbCancel);
        info_panel.add(button_panel);

        //Position and addition
        info_panel.setOpaque(false);
        info_panel.setLayout(new GridLayout(4,1));
        info_panel.setBackground(negre);


        //Position and addition

        c.gridx = 0;
        c.gridy = 0;
        centralPanel.add(info_panel,c);

        return centralPanel;
}

    private JPanel northernPanelConfiguration() {
        //Fonts, colours and sizes
        ImageIcon boto = new ImageIcon("src/edu/salleurl/boto.png");
        Image imageIcon_2 = boto.getImage();
        Image Image_2 = getScaledImage(imageIcon_2, 50, 50);
        ImageIcon new_Boto = new ImageIcon(Image_2);

        goBackImage.setIcon(new_Boto);
        goBackImage.addMouseListener(new MouseAdapter() {});

        JPanel BorderAdjustment = new JPanel(new BorderLayout());

        JPanel FillNORTH = new JPanel();
        FillNORTH.setBackground(negre);
        FillNORTH.setSize(20, 10);

        JPanel FillWEST = new JPanel();
        FillWEST.setBackground(negre);
        FillWEST.setSize(10, 20);

        BorderAdjustment.add(goBackImage, BorderLayout.CENTER);
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
        Font button = new Font("Trebuchet MS", Font.PLAIN, 20);
        Dimension button_shape_2 = new Dimension(245,40);


        jbManagement.setBackground(vermell);
        jbManagement.setForeground(Color.white);
        jbManagement.setText("Acount Management");
        jbManagement.setFont(button);
        jbManagement.setPreferredSize(button_shape_2);
        jbManagement.setFocusable(false);
        jbManagement.setOpaque(true);
        jbManagement.setBorderPainted(false);
        jbManagement.setActionCommand(BTN_GO_BACK);

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


    public void showCard(String playlistName, int songNumber) {
        panel.remove(centralPanel);
        centralPanel = centralPanelConfiguration(playlistName, songNumber);
        panel.add(centralPanel,BorderLayout.CENTER);
        topContainer.revalidate();
        cardManager.show(topContainer.getContentPane(),"removePlaylistCard");
    }
}
