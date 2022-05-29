package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ForgotPasswordView {
    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_SEND = "BTN_SEND";

    private JButton btn_send = new JButton("Send");

    public ForgotPasswordView(JFrame topContainer, CardLayout cardManager){
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        configureView();
    }

    private void configureView() {
        //Fonts and colours
        Color negre = new Color(48,48,48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Tahoma", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);

        //Panel
        JPanel panel = new JPanel();
        panel.setBackground(negre);
        panel.setLayout(null);

        //Title
        JLabel title = new JLabel("Forgot password");
        title.setBounds(200,210,300,50);
        title.setFont(titols);
        title.setForeground(Color.white);
        panel.add(title);

        //Substitle
        JLabel subtitle = new JLabel("<html>Enter the email address assoaciated with your account. <br>" +
                "We will then send you the instructions.</html>");
        subtitle.setBounds(200,270,600,50);
        subtitle.setFont(text);
        subtitle.setForeground(Color.white);
        panel.add(subtitle);

        //Textbox's title
        JLabel textboxTitle = new JLabel("Email");
        textboxTitle.setBounds(200,380,70,30);
        textboxTitle.setFont(text);
        textboxTitle.setForeground(Color.white);
        panel.add(textboxTitle);

        //Textbox
        JTextField jTextField = new JTextField();
        jTextField.setBounds(200,430,490,45);
        jTextField.setFont(text);
        panel.add(jTextField);

        //Send button
        btn_send.setBounds(571,614,371,56);
        btn_send.setForeground(Color.white);
        btn_send.setFont(text);
        btn_send.setBackground(vermell);
        btn_send.setFocusable(false);
        btn_send.setOpaque(true);
        btn_send.setBorderPainted(false);
        btn_send.setActionCommand(BTN_SEND);
        panel.add(btn_send);

        topContainer.getContentPane().add(panel, "forgotPasswordView");
    }

    public void registerController (ActionListener ForgotPasswordViewController){ //Todo crida aquesta funció on toqui
        btn_send.addActionListener(ForgotPasswordViewController);
    }

    public void showCard () {
        cardManager.show(topContainer, "forgotPasswordView");
    }


}
