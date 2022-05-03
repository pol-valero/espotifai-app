package presentation.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;


public class SignupView {

    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_SIGNUP = "BTN_SIGNUP";
    private JButton btnSignup = new JButton();
    private JTextField name_text = new JTextField();
    private JTextField mail_text = new JTextField();
    private JTextField password_text = new JTextField();
    private JTextField password_2_text = new JTextField();
    private JLabel error_Label_name = new JLabel();
    private JLabel error_Label_mail = new JLabel();
    private JLabel error_Label_password = new JLabel();
    private JLabel go_Back_Image = new JLabel();
    private JLabel jlPasswordFormat = new JLabel();



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

        go_Back_Image.setIcon(new_Boto);
        go_Back_Image.setBounds(30,30,50,50);
        go_Back_Image.addMouseListener(new MouseAdapter() {});

        //User name and name text


        JLabel name_Label = new JLabel();
        name_Label.setText("User name");
        name_Label.setForeground(Color.white);
        name_Label.setFont(titols);
        info_panel.add(name_Label);

        name_text.setFont(text);
        info_panel.add(name_text);

        error_Label_name.setText("The username is incorrect");
        error_Label_name.setForeground(vermell);
        error_Label_name.setFont(text);
        info_panel.add(error_Label_name);


        info_panel.setOpaque(false);

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




        // Password and JTextField

        JLabel password = new JLabel();
        password.setText("Password");
        password.setForeground(Color.white);
        password.setFont(titols);
        info_panel.add(password);


        password_text.setFont(text);
        info_panel.add(password_text);


        error_Label_password.setText("The passwords do not match");
        error_Label_password.setForeground(vermell);
        error_Label_password.setFont(text);
        info_panel.add(error_Label_password);



        // Rewrite Password and JTextField


        JLabel password_2 = new JLabel();
        password_2.setText("Rewrite Password");
        password_2.setForeground(Color.white);
        password_2.setFont(titols);
        info_panel.add(password_2);


        password_2_text.setFont(text);
        info_panel.add(password_2_text);




        jlPasswordFormat.setText("<html>The password must be at least 8 characters, have<br>uppercase and lowercase letters and a number</html>" );
        jlPasswordFormat.setForeground(Color.white);
        jlPasswordFormat.setFont(information);
        info_panel.add( jlPasswordFormat);

        //Sign up button

        btnSignup.setBackground(vermell);
        btnSignup.setForeground(Color.white);
        btnSignup.setText("Sign up");
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
        c.gridy = 3;
        p.add(btnSignup,c);

        p.setBounds(450,0,600,800);

        panel.add(go_Back_Image);
        panel.add(p);

        topContainer.getContentPane().add(panel, "signupCard");
    }


    Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

    public void registerController (ActionListener SignupViewController){ //Todo crida aquesta funció on toqui
        btnSignup.addActionListener(SignupViewController);
    }

   /**
    * Aquesta funcio permet canviar si es visible o no , per tant s'ha de utilitzar
    * tant per si s'ha de fer visible com invisible.
    */
    public void userNameErrorVisibility (boolean error){
        error_Label_name.setVisible(error);
        topContainer.revalidate();
    }

    public void mailErrorVisibility (boolean error){
        error_Label_mail.setVisible(error);
        topContainer.revalidate();
    }

    public void notEqualPasswordsError(boolean error){
        error_Label_password.setVisible(error);
        topContainer.revalidate();
    }

    public void passwordFormatError(boolean error){
        jlPasswordFormat.setVisible(error);
        topContainer.revalidate();
    }


    public String getUsername() {
        return name_text.getText();
    }

    public String getEmail() {
        return mail_text.getText();
    }

    public String getPassword() {
        return password_text.getText();
    }

    public String getRewritedPassword () {
        return password_2_text.getText();
    }

    public void showCard () {
        setErrorsInvisible();
        cardManager.show(topContainer.getContentPane(),"signupCard");
    }

    private void setErrorsInvisible() {
        error_Label_name.setVisible(false);
        error_Label_mail.setVisible(false);
        error_Label_password.setVisible(false);
        jlPasswordFormat.setVisible(false);
        topContainer.revalidate();
    }
}
