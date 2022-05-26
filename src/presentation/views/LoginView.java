package presentation.views;

import presentation.controllers.LoginViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginView {

    private final JFrame topContainer;
    private final CardLayout jFrameCardManager;

    public static final String BTN_LOGIN = "BTN_LOGING";
    public static final String BTN_SIGNUP = "BTN_SIGNUP";


    private JButton btnLogin = new JButton();
    private JTextField loginField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JLabel loginError = new JLabel();
    private JLabel signUpLabel = new JLabel();

    public LoginView (JFrame topContainer, CardLayout jFrameCardManager) {
        this.topContainer = topContainer;
        this.jFrameCardManager = jFrameCardManager;
        oneTimeConfiguration();
        configureView();
        topContainer.pack();
    }

    public void registerController (LoginViewController loginViewController){ //Todo crida aquesta funció on toqui
        btnLogin.addActionListener(loginViewController);
        signUpLabel.addMouseListener(loginViewController);
    }

    private void oneTimeConfiguration () {
        //The following three lines only need to be added to this class as it is the first class whose object is created. All the other view classes must not have these three lines.
        topContainer.setTitle("Spotifai");
        topContainer.getContentPane().setLayout(jFrameCardManager);
        topContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topContainer.setPreferredSize(new Dimension(1600,900));
        topContainer.setResizable(false);
        topContainer.pack();
        topContainer.setVisible(true);
        topContainer.setLocationRelativeTo(null);

    }

    private void configureView() {
        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 20);
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


        //User name
        JLabel name_Label = new JLabel();
        name_Label.setText("Username or email address");
        name_Label.setForeground(Color.white);
        name_Label.setFont(titols);
        info_panel.add(name_Label);

        loginField.setFont(text);
        info_panel.add(loginField);
        info_panel.setOpaque(false);

        //blank space
        info_panel.add(Box.createRigidArea(new Dimension(0,20)));

        //password
        info_panel.setOpaque(false);
        JLabel mail = new JLabel();
        mail.setText("Password");
        mail.setForeground(Color.white);
        mail.setFont(titols);
        info_panel.add(mail);


        passwordField.setFont(text);
        info_panel.add(passwordField);


        loginError.setText("Username or password are incorrect");
        loginError.setForeground(vermell);
        loginError.setFont(text);
        loginError.setVisible(false);
        info_panel.add(loginError);

        //blank space
        info_panel.add(Box.createRigidArea(new Dimension(0,20)));

        //login button

        btnLogin.setBackground(vermell);
        btnLogin.setForeground(Color.white);
        btnLogin.setText("Log in");
        btnLogin.setFont(button);
        btnLogin.setPreferredSize(button_shape);
        btnLogin.setFocusable(false);
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setActionCommand(BTN_LOGIN);

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
        p.add(btnLogin,c);



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


    private Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    public void loginErrorVisibility(boolean error) {
        loginError.setVisible(error);
        topContainer.revalidate();
    }

    public String getLoginField() {

        return loginField.getText();
    }

    public String getPasswordField() {

        return passwordField.getText();
    }

    public void showCard () {
        loginErrorVisibility(false);
        jFrameCardManager.show(topContainer.getContentPane(),"loginCard");
    }



}
