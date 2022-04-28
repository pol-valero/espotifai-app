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
        //topContainer.setResizable(false);
    }

    private void configureLoginScreen() {
        JPanel jpLogin = new JPanel();
        jpLogin.setLayout(new BoxLayout(jpLogin, BoxLayout.Y_AXIS));

        JPanel imagePanel = new ImagePanel("images/logo.png");
        //imagePanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        imagePanel.setPreferredSize(new Dimension(50,50));

        JLabel jlAppName = new JLabel("Espotify");
        jlAppName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlAppName.setFont(new Font("Futura", Font.BOLD, 60));

        JButton jbutton2 = new JButton("prova2");
        JPanel panelaux = new JPanel();
        panelaux.setAlignmentX(Component.CENTER_ALIGNMENT);
        //jbutton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbutton2.setPreferredSize(new Dimension(40,40));
        panelaux.add(jbutton2);

        JButton jbutton3 = new JButton("prova3");
        jbutton3.setAlignmentX(Component.CENTER_ALIGNMENT);

        jpLogin.add(Box.createRigidArea(new Dimension(0, 100)));

        jpLogin.add(imagePanel);

        jpLogin.add(jlAppName);
        jpLogin.add(Box.createRigidArea(new Dimension(0, 50)));
        jpLogin.add(panelaux);
        jpLogin.add(jbutton3);


        topContainer.getContentPane().add(jpLogin, "loginCard");

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
