package presentation.views;

import presentation.controllers.RenamePlaylistViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Class representing the RenamePlaylistView. This class contains all the methods and attributes
 * needed to use and show the RenamePlaylist View.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class RenamePlaylistView {

    private final JPanel mainViewCenter;
    private final CardLayout cardManager;

    public static final String BTN_MANAGEMENT = "BTN_MANAGEMENT";
    public static final String BTN_DONE = "BTN_DONE";

    private JButton jbAdd = new JButton();
    private JButton jbManagement = new JButton();
    private JTextField nameField = new JTextField(40);

    private JLabel jlWrongNameError = new JLabel();
    private JLabel goBackImage = new JLabel();

    /**
     * Constructor to create CreatePlaylistView
     * Creates the CreatePlaylistView linking it to the UIController. This function
     * initializes the CreatePlaylistView.
     *
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public RenamePlaylistView (JPanel mainViewCenter, CardLayout cardManager) {
        this.mainViewCenter = mainViewCenter;
        this.cardManager = cardManager;
        configureView();
        //this.mainViewCenter.pack();
    }

    /**
     * configureView is the main method of this class , creating the view and
     * adding the view to the JPanel main view Center in order to be displayed.
     *
     */
    private void configureView() {

        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font subtitle = new Font("Gulim", Font.PLAIN, 30);
        Font titol = new Font("Tahoma", Font.PLAIN, 38);
        Font button = new Font("Gulim", Font.PLAIN, 20);
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
        titleLabel.setText("Name            ");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titol);


        JLabel explanation = new JLabel();
        explanation.setText("<html>Write the name of this new playlist</html>" );
        explanation.setForeground(Color.white);
        explanation.setFont(information);

        info_panel.add(titleLabel);
        info_panel.add(explanation);
        info_panel.add(Box.createRigidArea(new Dimension(5, 10)));

        //tittle_panel.add(titleLabel);
        //  tittle_panel.add(explanation);

        // Set go back Image and Imatge filler

        ImageIcon boto = new ImageIcon("images/home.png");
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


        //Playlist name text

        nameField.setFont(text);
        info_panel.add(nameField);

        jlWrongNameError.setText("This is not a valid name");
        jlWrongNameError.setForeground(vermell);
        jlWrongNameError.setFont(text);
        info_panel.add(jlWrongNameError);

        jbAdd.setBackground(vermell);
        jbAdd.setForeground(Color.white);
        jbAdd.setText("Done");
        jbAdd.setFont(subtitle);
        jbAdd.setPreferredSize(button_shape);
        jbAdd.setFocusable(false);
        jbAdd.setOpaque(true);
        jbAdd.setBorderPainted(false);
        jbAdd.setActionCommand(BTN_DONE);


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


        mainViewCenter.add(panel, "renamePlaylistCard");
    }

    /**
     * getScaledImage is a method that receives an image with a certain length and height
     * and resizes the image to mach the length and height.
     * @param Img Image to be resized
     * @param wt Width of the image to resize
     * @param ht Height of the image to resize
     * @return The image introduced in this method but resized to mach the Width and Height
     */
    private Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    /**
     *  The function wrongNameErrorVisibility calls the method setVisible of  the jlWrongNameError
     *  in order for them to not be visible at the start of the class and then revalidates the main JPanel
     *
     */
    public void wrongNameErrorVisibility(boolean error){
        jlWrongNameError.setVisible(error);
        mainViewCenter.revalidate();
    }

    /**
     *  The function getPlaylistName calls the method getText() of  the parameter nameField
     *  from this class
     *
     * @return The text introduced in the JTextField nameField
     *
     */
    public String getPlaylistName() {
        return nameField.getText();
    }

    /**
     * showCard  callas a method to set the error visibility no false
     * and introduces the current state of the mainViewCenter Frame in the cardManager
     * and shows to the user the screen
     *
     */
    public void showCreateCard() {
        cardManager.show(mainViewCenter,"renamePlaylistCard");
        wrongNameErrorVisibility(false);
    }

    /**
     * registerController as it's name implies registers the three controllers
     * of the CreatePlaylistView in order to be accessed from the CreatePlaylistViewController class
     * @param renamePlaylistViewController Is the parameter that will receive this class in order to
     *                              link the listeners of this view with the Controller
     */
    public void registerController(RenamePlaylistViewController renamePlaylistViewController) {
        jbAdd.addActionListener(renamePlaylistViewController);
        jbManagement.addActionListener(renamePlaylistViewController);
        goBackImage.addMouseListener(renamePlaylistViewController);
    }
}
