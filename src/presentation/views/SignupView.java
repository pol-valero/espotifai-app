package presentation.views;
import presentation.controllers.SignupViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

/**
 * Class representing the SignupView. This class contains all the methods and attributes
 * needed to use and show the  SignupView View
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class SignupView {

    private final JFrame topContainer;
    private final CardLayout jFrameCardManager;

    public static final String BTN_SIGNUP = "BTN_SIGNUP";
    private JButton jbSignup = new JButton();
    private JTextField usernameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField passwordField = new JTextField(20);
    private JTextField repeatedPasswordField = new JTextField(20);
    private JLabel jlExistingUsername = new JLabel();
    private JLabel jlExistingMailError = new JLabel();
    private JLabel jlMailFormatError = new JLabel();
    private JLabel jlNotEqualPasswordsError = new JLabel();
    private JLabel goBackImage = new JLabel();
    private JLabel jlPasswordFormat = new JLabel();
    private JLabel jlPasswordFormatError = new JLabel();


    /**
     * Constructor to create the SignupView
     * Creates the SignupView linking it to the UIController. This function
     * initializes the SignupView.
     *
     * @param topContainer this is the JPanel displayed in the Center of the mainView
     * @param jFrameCardManager the cardManager is the component that manages when to show each view
     */
    public SignupView (JFrame topContainer, CardLayout jFrameCardManager) {
        this.topContainer = topContainer;
        this.jFrameCardManager = jFrameCardManager;
        configureView();
        topContainer.pack();
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
        Font titols = new Font("Tahoma", Font.PLAIN, 28);
        Font text = new Font("Gulim", Font.PLAIN, 14);
        Font information = new Font("Gulim", Font.PLAIN, 14);
        Dimension button_shape = new Dimension(430,40);


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


        //Image

        ImageIcon Image_default = new ImageIcon("images/icona.png");
        Image imageIcon = Image_default.getImage();
        Image Image = getScaledImage(imageIcon, 200, 200);
        ImageIcon icon = new ImageIcon(Image);
        JLabel imatge_final = new JLabel();
        imatge_final.setIcon(icon);

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
        FillNORTH.setSize(50, 20);

        JPanel FillWEST = new JPanel();
        FillWEST.setBackground(negre);
        FillWEST.setSize(20, 50);

        BorderAdjustment.add(goBackImage, BorderLayout.CENTER);
        BorderAdjustment.add(FillNORTH, BorderLayout.NORTH);
        BorderAdjustment.add(FillWEST, BorderLayout.WEST);

        JPanel fill = new JPanel(new BorderLayout());

        JPanel FillPanel = new JPanel();
        FillPanel.setBackground(negre);
        fill.add(FillPanel, BorderLayout.CENTER);
        fill.add(BorderAdjustment, BorderLayout.WEST);

        //User name and name text


        JLabel name_Label = new JLabel();
        name_Label.setText("User name");
        name_Label.setForeground(Color.white);
        name_Label.setFont(titols);
        info_panel.add(name_Label);

        usernameField.setFont(text);
        info_panel.add(usernameField);

        jlExistingUsername.setText("The username is incorrect");
        jlExistingUsername.setForeground(vermell);
        jlExistingUsername.setFont(text);
        info_panel.add(jlExistingUsername);


        info_panel.setOpaque(false);

        //mail and mail text


        info_panel.setOpaque(false);
        JLabel mail = new JLabel();
        mail.setText("E-mail");
        mail.setForeground(Color.white);
        mail.setFont(titols);
        info_panel.add(mail);


        emailField.setFont(text);
        info_panel.add(emailField);


        jlExistingMailError.setText("This mail is already in use");
        jlExistingMailError.setForeground(vermell);
        jlExistingMailError.setFont(text);
        info_panel.add(jlExistingMailError);

        jlMailFormatError.setText("This is not a valid mail");
        jlMailFormatError.setForeground(vermell);
        jlMailFormatError.setFont(text);
        info_panel.add(jlMailFormatError);

        // Password and JTextField

        JLabel password = new JLabel();
        password.setText("Password");
        password.setForeground(Color.white);
        password.setFont(titols);
        info_panel.add(password);


        passwordField.setFont(text);
        info_panel.add(passwordField);


        jlNotEqualPasswordsError.setText("The passwords do not match");
        jlNotEqualPasswordsError.setForeground(vermell);
        jlNotEqualPasswordsError.setFont(text);
        info_panel.add(jlNotEqualPasswordsError);



        // Rewrite Password and JTextField


        JLabel password_2 = new JLabel();
        password_2.setText("Repeat password");
        password_2.setForeground(Color.white);
        password_2.setFont(titols);
        info_panel.add(password_2);


        repeatedPasswordField.setFont(text);
        info_panel.add(repeatedPasswordField);

        jlPasswordFormatError.setText("Password does not meet requirements");
        jlPasswordFormatError.setForeground(vermell);
        jlPasswordFormatError.setFont(text);
        info_panel.add(jlPasswordFormatError);


        jlPasswordFormat.setText("<html>The password must be at least 8 characters, have<br>uppercase and lowercase letters and a number</html>" );
        jlPasswordFormat.setForeground(Color.white);
        jlPasswordFormat.setFont(information);
        info_panel.add(jlPasswordFormat);

        //Sign up button

        jbSignup.setBackground(vermell);
        jbSignup.setForeground(Color.white);
        jbSignup.setText("Sign up");
        jbSignup.setFont(titols);
        jbSignup.setPreferredSize(button_shape);
        jbSignup.setFocusable(false);
        jbSignup.setOpaque(true);
        jbSignup.setBorderPainted(false);
        jbSignup.setActionCommand(BTN_SIGNUP);



        //Position and addition
        c.gridx = 0;
        c.gridy = 0;
        p.add(imatge_final,d);

        //Position and addition
        c.gridx = 0;
        c.gridy = 1;
        p.add(info_panel,c);

        //Position and addition
        c.gridx = 0;
        c.gridy = 3;
        p.add(jbSignup,c);

        panel.add(fill, BorderLayout.NORTH);
        panel.add(p, BorderLayout.CENTER);


        topContainer.getContentPane().add(panel, "signupCard");
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
     * registerController as it's name implies registers the two controllers
     * of the signupViewController in order to be accessed from the signupViewController class
     * @param signupViewController Is the parameter that will receive this class in order to
     *                              link the listeners of this view with the Controller
     */
    public void registerController (SignupViewController signupViewController){
        jbSignup.addActionListener(signupViewController);
        goBackImage.addMouseListener(signupViewController);
    }

   /**
    * existingUsernameErrorVisibility sets the visibility of jlExistingUsername
    * either true or false
    */
    public void existingUsernameErrorVisibility(boolean error){
        jlExistingUsername.setVisible(error);
        topContainer.revalidate();
    }

    /**
     * existingUsernameErrorVisibility sets the visibility of  jlExistingMailError
     * either true or false
     */
    public void existingMailErrorVisibility(boolean error){
        jlExistingMailError.setVisible(error);
        topContainer.revalidate();
    }

    /**
     * existingUsernameErrorVisibility sets the visibility of jlMailFormatError
     * either true or false
     */
    public void mailFormatErrorVisibility (boolean error) {
        jlMailFormatError.setVisible(error);
        topContainer.revalidate();
    }

    /**
     * existingUsernameErrorVisibility sets the visibility of jlNotEqualPasswordsError
     * either true or false
     */
    public void notEqualPasswordsErrorVisibility(boolean error){
        jlNotEqualPasswordsError.setVisible(error);
        topContainer.revalidate();
    }
    /**
     * existingUsernameErrorVisibility sets the visibility of jlPasswordFormatError
     * either true or false
     */
    public void passwordFormatErrorVisibility(boolean error){
        jlPasswordFormatError.setVisible(error);
        topContainer.revalidate();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getRepeatedPassword() {
        return repeatedPasswordField.getText();
    }

    /**
     * showCard  callas a method to
     * introduces the current state of the mainViewCenter Frame in the cardManager
     * and shows to the user the screen.
     *
     */
    public void showCard () {
        setErrorsInvisible();
        jFrameCardManager.show(topContainer.getContentPane(),"signupCard");
    }

    /**
     * setErrorsInvisibley sets the visibility of all errors invisible
     */
    private void setErrorsInvisible() {
        jlExistingUsername.setVisible(false);
        jlExistingMailError.setVisible(false);
        jlNotEqualPasswordsError.setVisible(false);
        jlPasswordFormatError.setVisible(false);
        jlMailFormatError.setVisible(false);
        topContainer.revalidate();
    }

    public void clearFields () {
        emailField.setText("");
        passwordField.setText("");
        repeatedPasswordField.setText("");
        usernameField.setText("");
    }
}
