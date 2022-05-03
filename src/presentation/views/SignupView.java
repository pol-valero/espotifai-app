package presentation.views;
import presentation.controllers.SignupViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;


public class SignupView {

    private final JFrame topContainer;
    private final CardLayout cardManager;

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
    private JLabel jlPasswordFormatError = new JLabel();



    public SignupView (JFrame topContainer, CardLayout cardManager) {
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        configureView();
        topContainer.pack();
    }

    private void configureView() {
        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 20);
        Font button = new Font("Gulim", Font.PLAIN, 30);
        Font information = new Font("Gulim", Font.PLAIN, 14);
        // Dimension button_shape = new Dimension(371,56);
        Dimension button_shape = new Dimension(505,40);


        // Panel no bounds
        JPanel panel = new JPanel();
        panel.setBackground(negre);
        panel.setLayout(null);


        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,30,10,30); //Space between components

        JPanel info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.PAGE_AXIS));


        //Image

        ImageIcon Image_default = new ImageIcon("images/icona.png");
        Image imageIcon = Image_default.getImage();
        Image Image = getScaledImage(imageIcon, 200, 200);
        ImageIcon icon = new ImageIcon(Image);
        JLabel imatge_final = new JLabel();
        imatge_final.setIcon(icon);

        // Set go back Image

        ImageIcon boto = new ImageIcon("images/boto.png");
        Image imageIcon_2 = boto.getImage();
        Image Image_2 = getScaledImage(imageIcon_2, 50, 50);
        ImageIcon new_Boto = new ImageIcon(Image_2);

        goBackImage.setIcon(new_Boto);
        goBackImage.setBounds(30,30,50,50);
        goBackImage.addMouseListener(new MouseAdapter() {});

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




        jlPasswordFormatError.setText("<html>The password must be at least 8 characters, have<br>uppercase and lowercase letters and a number</html>" );
        jlPasswordFormatError.setForeground(vermell);
        jlPasswordFormatError.setFont(information);
        info_panel.add(jlPasswordFormatError);

        //Sign up button

        jbSignup.setBackground(vermell);
        jbSignup.setForeground(Color.white);
        jbSignup.setText("Sign up");
        jbSignup.setFont(button);
        jbSignup.setPreferredSize(button_shape);
        jbSignup.setFocusable(false);
        jbSignup.setOpaque(true);
        jbSignup.setBorderPainted(false);
        jbSignup.setActionCommand(BTN_SIGNUP);

        //   btnSignup.setActionCommand(BTN_LOGOUT);


        //Position and addition
        c.gridx = 0;
        c.gridy = 0;
        p.add(imatge_final,c);

        //Position and addition
        c.gridx = 0;
        c.gridy = 1;
        p.add(info_panel,c);

        //Position and addition
        c.gridx = 0;
        c.gridy = 3;
        p.add(jbSignup,c);

        p.setBounds(450,0,600,800);

        panel.add(goBackImage);
        panel.add(p);

        topContainer.getContentPane().add(panel, "signupCard");
    }


    private Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    public void registerController (SignupViewController signupViewController){ //Todo crida aquesta funció on toqui
        jbSignup.addActionListener(signupViewController);
        goBackImage.addMouseListener(signupViewController);
    }

   /**
    * Aquesta funcio permet canviar si es visible o no , per tant s'ha de utilitzar
    * tant per si s'ha de fer visible com invisible.
    */
    public void existingUsernameErrorVisibility(boolean error){
        jlExistingUsername.setVisible(error);
        topContainer.revalidate();
    }

    public void existingMailErrorVisibility(boolean error){
        jlExistingMailError.setVisible(error);
        topContainer.revalidate();
    }

    public void mailFormatErrorVisibility (boolean error) {
        jlMailFormatError.setVisible(error);
        topContainer.revalidate();
    }

    public void notEqualPasswordsErrorVisibility(boolean error){
        jlNotEqualPasswordsError.setVisible(error);
        topContainer.revalidate();
    }

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

    public void showCard () {
        setErrorsInvisible();
        cardManager.show(topContainer.getContentPane(),"signupCard");
    }

    private void setErrorsInvisible() {
        jlExistingUsername.setVisible(false);
        jlExistingMailError.setVisible(false);
        jlNotEqualPasswordsError.setVisible(false);
        jlPasswordFormatError.setVisible(false);
        jlMailFormatError.setVisible(false);
        topContainer.revalidate();
    }
}
