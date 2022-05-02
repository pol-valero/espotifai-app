package presentation.views;

import presentation.Components.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginView {

    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_LOGIN = "BTN_LOGING";
    public static final String BTN_FORGOTPASSWORD = "BTN_FORGOTPASSWORD";
    public static final String BTN_SIGNUP = "BTN_SIGNUP";


    private JButton btnSignup = new JButton();
    private JTextField name_text = new JTextField();
    private JTextField mail_text = new JTextField();
    private JLabel error_Label_mail = new JLabel();
    private JLabel signUpLabel = new JLabel();

    public LoginView (JFrame topContainer, CardLayout cardManager) {
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        oneTimeConfiguration();
        configureView();
        topContainer.pack();
    }

    private void oneTimeConfiguration () {
        //The following three lines only need to be added to this class as it is the first class whose object is created. All the other view classes must not have these three lines.
        topContainer.setTitle("Spotifai");
        topContainer.getContentPane().setLayout(cardManager);
        topContainer.setVisible(true);
        topContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topContainer.setLocationRelativeTo(null);
        topContainer.setPreferredSize(new Dimension(1600,900));
        topContainer.setResizable(false);
    }

    private void configureView() {
        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);
        Font button = new Font("Gulim", Font.PLAIN, 30);
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


        //User name and name text


        JLabel name_Label = new JLabel();
        name_Label.setText("Username or email address");
        name_Label.setForeground(Color.white);
        name_Label.setFont(titols);
        info_panel.add(name_Label);

        name_text.setFont(text);
        info_panel.add(name_text);
        info_panel.setOpaque(false);

        //blank space
        info_panel.add(Box.createRigidArea(new Dimension(0,20)));


        //mail and mail text
        info_panel.setOpaque(false);
        JLabel mail = new JLabel();
        mail.setText("E-mail");
        mail.setForeground(Color.white);
        mail.setFont(titols);
        info_panel.add(mail);


        mail_text.setFont(text);
        info_panel.add(mail_text);


        error_Label_mail.setText("The mail is incorrect");
        error_Label_mail.setForeground(vermell);
        error_Label_mail.setFont(text);
        info_panel.add(error_Label_mail);

        //blank space
        info_panel.add(Box.createRigidArea(new Dimension(0,20)));

        //Sign up button

        btnSignup.setBackground(vermell);
        btnSignup.setForeground(Color.white);
        btnSignup.setText("Log in");
        btnSignup.setFont(button);
        btnSignup.setPreferredSize(button_shape);
        btnSignup.setFocusable(false);
        btnSignup.setOpaque(true);
        btnSignup.setBorderPainted(false);
        btnSignup.setActionCommand(BTN_SIGNUP);


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
        c.gridy = 2;
        p.add(btnSignup,c);



        //blank space
        c.gridx = 0;
        c.gridy = 3;
        p.add(Box.createRigidArea(new Dimension(0,10)), c);

        //label signup
        c.gridx = 0;
        c.gridy = 4;

        signUpLabel.setText("Don’t have an account? Sign up");
        signUpLabel.setForeground(Color.white);
        signUpLabel.setFont(text);
        p.add(signUpLabel, c);

        p.setBounds(450,0,600,800);

        panel.add(p);



        topContainer.getContentPane().add(panel, "loginCard");
    }


    Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    public void showLoginErrorMessage() {
    }

    public String getLoginField() {

        return null;
    }

    public String getPasswordField() {
        return null;
    }

    public void showCard () {
        cardManager.show(topContainer.getContentPane(),"loginCard");
    }

}
