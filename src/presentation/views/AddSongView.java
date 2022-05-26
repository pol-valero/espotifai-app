package presentation.views;


import presentation.controllers.AddSongViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
public class AddSongView {

    private final JPanel mainViewCenter;
    private final CardLayout cardManager;

    public static final String BTN_ADD = "BTN_ADD";
    public static final String BTN_MANAGEMENT = "BTN_MANAGEMENT";


    private JButton jbManagement = new JButton();
    private JButton jbAdd = new JButton();
    private JTextField songNameField = new JTextField(40);
    private JTextField artistField = new JTextField(25);
    private JTextField albumField = new JTextField(25);
    private JTextField genreField = new JTextField(25);
    private JTextField filepathField = new JTextField(25);
    private JLabel jlExistingSong = new JLabel();
    private JLabel jlUnusableSong = new JLabel();
    private JLabel jlUnfoundArtistError = new JLabel();
    private JLabel jlWrongFilepathError = new JLabel();
    private JLabel jlAlbumNotFoundError = new JLabel();
    private JLabel goBackImage = new JLabel();
    private JLabel jlWrongGenreError = new JLabel();

    public AddSongView (JPanel mainViewCenter, CardLayout cardManager) {
        this.mainViewCenter = mainViewCenter;
        this.cardManager = cardManager;
        configureView();
        //this.mainViewCenter.pack();
    }

    private void configureView() {

        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font subtitle = new Font("Trebuchet MS", Font.PLAIN, 30);
        Font titol = new Font("Trebuchet MS", Font.PLAIN, 38);
        Font button = new Font("Trebuchet MS", Font.PLAIN, 20);
        Font text = new Font("Gulim", Font.PLAIN, 14);
        Font information = new Font("Gulim", Font.PLAIN, 14);

        Dimension button_shape = new Dimension(445,40);
        Dimension button_shape_2 = new Dimension(245,40);


        // Panel no bounds
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(negre);

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();


        c.insets = new Insets(10,30,10,30); //Space between components
        d.insets = new Insets(0,30,0,30); //Space between components


        JPanel info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.PAGE_AXIS));
        info_panel.setOpaque(false);

        // tittle

        JPanel tittle_panel = new JPanel();
        tittle_panel.setLayout(new BoxLayout(tittle_panel, BoxLayout.PAGE_AXIS));

        tittle_panel.setBackground(negre);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Add personal song           ");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titol);


        JLabel explanation = new JLabel();
        explanation.setText("<html>Fill the information requirements</html>" );
        explanation.setForeground(Color.white);
        explanation.setFont(information);

        tittle_panel.add(titleLabel);
        tittle_panel.add(explanation);

        // Set go back Image and Imatge filler

        ImageIcon boto = new ImageIcon("images/boto.png");
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

        JPanel fill = new JPanel(new BorderLayout());

        JPanel FillPanel = new JPanel();
        FillPanel.setBackground(negre);
        fill.add(FillPanel, BorderLayout.CENTER);
        fill.add(BorderAdjustment, BorderLayout.WEST);

        // Set go back Button


        jbManagement.setBackground(vermell);
        jbManagement.setForeground(Color.white);
        jbManagement.setText("Acount Management");
        jbManagement.setFont(button);
        jbManagement.setPreferredSize(button_shape_2);
        jbManagement.setFocusable(false);
        jbManagement.setOpaque(true);
        jbManagement.setBorderPainted(false);
        jbManagement.setActionCommand(BTN_MANAGEMENT);

        JPanel BorderAdjustment_2 = new JPanel(new BorderLayout());



        JPanel FillSOUTH = new JPanel();
        FillSOUTH.setBackground(negre);


        JPanel FillWEST_2 = new JPanel();
        FillWEST_2.setBackground(negre);


        BorderAdjustment_2.add(jbManagement, BorderLayout.CENTER);
        BorderAdjustment_2.add(FillSOUTH, BorderLayout.SOUTH);
        BorderAdjustment_2.add(FillWEST_2, BorderLayout.WEST);

        JPanel fill_2 = new JPanel(new BorderLayout());

        JPanel FillPanel_2 = new JPanel();
        FillPanel_2.setBackground(negre);
        fill_2.add(FillPanel_2, BorderLayout.CENTER);
        fill_2.add(BorderAdjustment_2, BorderLayout.WEST);

        //Song name and Song text

        JLabel songName_Label = new JLabel();
        songName_Label.setText("Song name");
        songName_Label.setForeground(Color.white);
        songName_Label.setFont(subtitle);
        info_panel.add(songName_Label);

        songNameField.setFont(text);
        info_panel.add(songNameField);

        jlExistingSong.setText("This song already exists");
        jlExistingSong.setForeground(vermell);
        jlExistingSong.setFont(text);
        info_panel.add(jlExistingSong);

        jlUnusableSong.setText("Not a valid song name");
        jlUnusableSong.setForeground(vermell);
        jlUnusableSong.setFont(text);
        info_panel.add(jlUnusableSong);

        //   info_panel.setOpaque(false);

        //Artist name and Artist text


        JLabel artist = new JLabel();
        artist.setText("Artist name");
        artist.setForeground(Color.white);
        artist.setFont(subtitle);
        info_panel.add(artist);

        artistField.setFont(text);
        info_panel.add(artistField);


        jlUnfoundArtistError.setText("We couldn't find this artist");
        jlUnfoundArtistError.setForeground(vermell);
        jlUnfoundArtistError.setFont(text);
        info_panel.add(jlUnfoundArtistError);



        // Album name and JTextField

        JLabel album = new JLabel();
        album.setText("Album name");
        album.setForeground(Color.white);
        album.setFont(subtitle);
        info_panel.add(album);


        albumField.setFont(text);
        info_panel.add(albumField);


        jlAlbumNotFoundError.setText("We couldn't find this album");
        jlAlbumNotFoundError.setForeground(vermell);
        jlAlbumNotFoundError.setFont(text);
        info_panel.add(jlAlbumNotFoundError);



        // Genre and JTextField


        JLabel genre = new JLabel();
        genre.setText("Genre");
        genre.setForeground(Color.white);
        genre.setFont(subtitle);
        info_panel.add(genre);


        genreField.setFont(text);
        info_panel.add(genreField);

        jlWrongGenreError.setText("We couldn't find this Genre");
        jlWrongGenreError.setForeground(vermell);
        jlWrongGenreError.setFont(text);
        info_panel.add(jlWrongGenreError);

        // Filepath and JTextField


        JLabel filepath = new JLabel();
        filepath.setText("Filepath");
        filepath.setForeground(Color.white);
        filepath.setFont(subtitle);
        info_panel.add(filepath);


        filepathField.setFont(text);
        info_panel.add(filepathField);

        jlWrongFilepathError.setText("This is not a valid Filepath");
        jlWrongFilepathError.setForeground(vermell);
        jlWrongFilepathError.setFont(text);
        info_panel.add(jlWrongFilepathError);
        //Sign up button

        jbAdd.setBackground(vermell);
        jbAdd.setForeground(Color.white);
        jbAdd.setText("Add ");
        jbAdd.setFont(subtitle);
        jbAdd.setPreferredSize(button_shape);
        jbAdd.setFocusable(false);
        jbAdd.setOpaque(true);
        jbAdd.setBorderPainted(false);
        jbAdd.setActionCommand(BTN_ADD);

        //   btnSignup.setActionCommand(BTN_LOGOUT);


        //Position and addition
        c.gridx = 0;
        c.gridy = 0;
        p.add(tittle_panel,c);



        //Position and addition
        c.gridx = 0;
        c.gridy = 1;
        p.add(info_panel,c);

        //Position and addition
        c.gridx = 0;
        c.gridy = 2;
        p.add(jbAdd,c);

        panel.add(fill, BorderLayout.NORTH);
        panel.add(p, BorderLayout.CENTER);
        panel.add(fill_2, BorderLayout.SOUTH);

        mainViewCenter.add(panel, "addSongCard");
    }
    private Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }
    public void existingSongErrorVisibility(boolean error){
        jlExistingSong.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void unusableSongErrorVisibility(boolean error){
        jlUnusableSong.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void unfoundArtistErrorVisibility(boolean error){
        jlUnfoundArtistError.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void WrongFilepathErrorErrorVisibility (boolean error) {
        jlWrongFilepathError.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void AlbumNotFoundErrorVisibility(boolean error){
        jlAlbumNotFoundError.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void wrongGenreErrorVisibility(boolean error){
        jlWrongGenreError.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void registerController (AddSongViewController addSongViewController){

        jbAdd.addActionListener(addSongViewController);
        jbManagement.addActionListener(addSongViewController);
        goBackImage.addMouseListener(addSongViewController);
    }
    public String getSongName() {
        return songNameField.getText();
    }

    public String getArtist() {
        return artistField.getText();
    }

    public String getAlbum() {
        return albumField.getText();
    }

    public String getGenre() {
        return genreField.getText();
    }

    public void showCard () {
        setErrorsInvisible();
        cardManager.show(mainViewCenter,"addSongCard");
    }

    private void setErrorsInvisible() {
        jlExistingSong.setVisible(false);
        jlUnfoundArtistError.setVisible(false);
        jlAlbumNotFoundError.setVisible(false);
        jlWrongGenreError.setVisible(false);
        jlWrongFilepathError.setVisible(false);
        mainViewCenter.revalidate();
    }
}
