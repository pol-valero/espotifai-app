package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VerificationCodeView {

    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_CHECK = "BTN_CHECK";
    public static final String LBL_EMAIL = "LBL_EMAIL";
    private JLabel error_Label = new JLabel();
    private JButton btn_send = new JButton();
    private JLabel rewrite_email = new JLabel("Rewrite email adress");

    public VerificationCodeView(JFrame topContainer, CardLayout cardManager){
        this.topContainer = topContainer;
        this.cardManager = cardManager;
        configureWindows();
        configureView();

    }

    private void configureWindows(){
        topContainer.setSize(1512,928);
        topContainer.setLocationRelativeTo(null);
        error_Label.setVisible(false);
    }
    private void configureView() {
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Dialog", Font.PLAIN, 40);
        Font text = new Font("Dialog", Font.PLAIN, 20);
        Font mini_Text = new Font("Dialog", Font.PLAIN, 10);


        // Dimension button_shape = new Dimension(371,56);
        //Windows configuration

        JPanel panel = new JPanel();
        panel.setBackground(negre);
        panel.setLayout(null);

        JLabel title = new JLabel("Enter verification code");
        title.setBounds(200,210,500,50);
        title.setFont(titols);
        title.setForeground(Color.white);
        panel.add(title);

        JLabel subtitle = new JLabel("<html>Check your inbox email and look for our last email. " +
                "We have sent you a verification code. Please write it here.</html>");
        subtitle.setBounds(200,270,600,60);
        subtitle.setFont(text);
        subtitle.setForeground(Color.white);
        panel.add(subtitle);

        JLabel textboxTitle = new JLabel("Verification code");
        textboxTitle.setBounds(200,380,200,30);
        textboxTitle.setFont(text);
        textboxTitle.setForeground(Color.white);
        panel.add(textboxTitle);

        JTextField jTextField = new JTextField();
        panel.add(jTextField);
        jTextField.setForeground(negre);
        jTextField.setFont(text);
        jTextField.setBounds(200,430,490,45);


        btn_send.setText("Check");
        btn_send.setBounds(571,614,371,56);
        btn_send.setForeground(Color.white);
        btn_send.setFont(text);
        btn_send.setBackground(vermell);
        btn_send.setFocusable(false);
        // btn_send.setOpaque(false);
        btn_send.setBorderPainted(false);
        btn_send.setActionCommand(BTN_CHECK);
        panel.add(btn_send);

        error_Label.setText("Verification code only includes numbers");
        error_Label.setForeground(vermell);
        error_Label.setFont(mini_Text);
        error_Label.setBounds(200,430,371,156);
        panel.add(error_Label);


        Font font = rewrite_email.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rewrite_email.setFont(font.deriveFont(attributes));
        rewrite_email.setForeground(Color.white);
        rewrite_email.setBounds(200,460,371,156);

        rewrite_email.addMouseListener(new MouseAdapter() {});

        panel.add( rewrite_email);

        topContainer.getContentPane().add(panel);
    }
    /*
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Yay you clicked me");
    }
    */

    /**
     * This view shows there's been an error with the data if
     * @param error is true
     */
    public void ErrorVisibility (boolean error){ error_Label.setVisible(error);}

    public void registerController (ActionListener ForgotPasswordViewController, MouseListener rewriteEmailViewController){ //Todo crida aquesta funció on toqui
        btn_send.addActionListener(ForgotPasswordViewController);
        rewrite_email.addMouseListener(rewriteEmailViewController);

    }

}
