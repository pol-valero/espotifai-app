package presentation.views;

import presentation.Components.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginView {

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_FORGOTPASSWORD = "BTN_FORGOTPASSWORD";
    public static final String BTN_SINGUP = "BTN_SIGNUP";

    private final JFrame topContainer;
    private final CardLayout cardManager;


    public LoginView (JFrame topContainer, CardLayout cardManager) {
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        oneTimeConfiguration();
        configureLoginScreen();
        configureWindow();
    }

    private void oneTimeConfiguration () {
        //The following three lines only need to be added to this class as it is the first class whose object is created. All the other view classes must not have these three lines.
        topContainer.setTitle("Spotifai");
        topContainer.getContentPane().setLayout(cardManager);
        topContainer.setVisible(true);
    }

    private void configureWindow () {
        topContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topContainer.pack();
        topContainer.setLocationRelativeTo(null);
        topContainer.setSize(1600, 900);
    }

    private void configureLoginScreen() {
        JPanel jpLogin = new JPanel();
        jpLogin.setLayout(new BoxLayout(jpLogin, BoxLayout.Y_AXIS));

        JPanel imagePanel = new ImagePanel("images/logo.png");
        //imagePanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        imagePanel.setPreferredSize(new Dimension(50,50));

        JLabel jlAppName = new JLabel("Espotify");
        jlAppName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlAppName.setFont(new Font("Futura", Font.BOLD, 40));

        JButton jbutton2 = new JButton("prova2");
        jbutton2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbutton3 = new JButton("prova3");
        jbutton3.setAlignmentX(Component.CENTER_ALIGNMENT);

        jpLogin.add(Box.createRigidArea(new Dimension(0, 100)));

        jpLogin.add(imagePanel);

        jpLogin.add(jlAppName);
        jpLogin.add(Box.createRigidArea(new Dimension(0, 50)));
        jpLogin.add(jbutton2);
        jpLogin.add(jbutton3);

        topContainer.add(jpLogin, "login");
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
        //cardmanager.show()
    }
}
