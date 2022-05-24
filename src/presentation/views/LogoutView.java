package presentation.views;

import presentation.controllers.LogoutViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class LogoutView {
    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETEACCOUNT = "BTN_DELETEACCOUNT" ;

    private JButton btnLogout = new JButton("Log out");
    private JButton btnDeleteAccount = new JButton("Delete Account");
    private JLabel goBackImage = new JLabel();
    
    public LogoutView (JFrame topContainer, CardLayout cardManager) {
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
        Font text = new Font("Gulim", Font.PLAIN, 24);
        Dimension button_shape = new Dimension(371,56);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(negre);

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,30,30,30); //Space between components

        // Set go back Image and Imatge filler

        ImageIcon boto = new ImageIcon("images/boto.png");
        Image imageIcon_2 = boto.getImage();
        Image Image_2 = getScaledImage(imageIcon_2, 50, 50);
        ImageIcon new_Boto = new ImageIcon(Image_2);

        goBackImage.setIcon(new_Boto);

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


        //Title
        JLabel title = new JLabel();
        title.setText("Account Manager");
        title.setForeground(Color.white);
        title.setFont(titols);


        //Log out btn
        btnLogout.setBackground(vermell);
        btnLogout.setForeground(Color.white);
        btnLogout.setFont(text);
        btnLogout.setPreferredSize(button_shape);
        btnLogout.setFocusable(false);
        btnLogout.setOpaque(true);
        btnLogout.setBorderPainted(false);
        btnLogout.setActionCommand(BTN_LOGOUT);



        //Delete account btn
        btnDeleteAccount.setBackground(vermell);
        btnDeleteAccount.setForeground(Color.white);
        btnDeleteAccount.setFont(text);
        btnDeleteAccount.setPreferredSize(button_shape);
        btnDeleteAccount.setFocusable(false);
        btnDeleteAccount.setOpaque(true);
        btnDeleteAccount.setBorderPainted(false);
        btnDeleteAccount.setActionCommand(BTN_DELETEACCOUNT);

        //Position and addition
        c.gridx = 0;
        c.gridy = 1;
        p.add(title,c);

        c.gridx = 0;
        c.gridy = 2;
        p.add(btnLogout,c);

        c.gridx = 0;
        c.gridy = 3;
        p.add(btnDeleteAccount,c);

        panel.add(fill, BorderLayout.NORTH);
        panel.add(p, BorderLayout.CENTER);

        topContainer.getContentPane().add(panel, "logoutCard");
    }
    private Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }
    public void registerController (LogoutViewController logoutViewController){ //Todo crida aquesta funció on toqui
        btnLogout.addActionListener(logoutViewController);
        btnDeleteAccount.addActionListener(logoutViewController);
        goBackImage.addMouseListener(logoutViewController);
    }

    public void showCard () {
        cardManager.show(topContainer.getContentPane(),"logoutCard");
    }

}
