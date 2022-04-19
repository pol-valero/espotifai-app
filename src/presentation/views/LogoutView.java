package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogoutView extends JFrame {
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETEACCOUNT = "BTN_DELETEACCOUNT" ;

    private JButton btnLogout;
    private JButton btnDeleteAccount;
    
    public LogoutView () {
        configureView();
        configureWindows();
    }

    private void configureWindows() {
        setTitle("Account Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1512,928);
        setLocationRelativeTo(null);
    }

    private void configureView() {
        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Dialog", Font.PLAIN, 36);
        Font text = new Font("Dialog", Font.PLAIN, 24);
        Dimension button_shape = new Dimension(371,56);

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,30,30,30); //Space between components

        //Title
        JLabel title = new JLabel();
        title.setText("Account Manager");
        title.setForeground(Color.white);
        title.setFont(titols);


        //Log out btn
        btnLogout = new JButton();
        btnLogout.setBackground(vermell);
        btnLogout.setForeground(Color.white);
        btnLogout.setText("Log out");
        btnLogout.setFont(text);
        btnLogout.setPreferredSize(button_shape);
        btnLogout.setFocusable(false);
        btnLogout.setActionCommand(BTN_LOGOUT);



        //Delete account btn
        btnDeleteAccount = new JButton();
        btnDeleteAccount.setBackground(vermell);
        btnDeleteAccount.setForeground(Color.white);
        btnDeleteAccount.setText("Delete account");
        btnDeleteAccount.setFont(text);
        btnDeleteAccount.setPreferredSize(button_shape);
        btnDeleteAccount.setFocusable(false);
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

        getContentPane().add(p);

    }
    public void start(){
        setVisible(true);
    }

    public void registerController (ActionListener logoutViewController){ //Todo crida aquesta funció on toqui
        btnLogout.addActionListener(logoutViewController);
        btnDeleteAccount.addActionListener(logoutViewController);
    }




}
