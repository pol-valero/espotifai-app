package presentation.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.awt.image.BufferedImage;


public class SignupView extends JFrame{

    public static final String BTN_SIGNUP = "BTN_SIGNUP";
    private JButton btnSignup = new JButton("Sing Up");
    private JTextField name_text = new JTextField("enter the user name", 20);
    private JTextField mail_text = new JTextField("enter the E-mail", 20);
    private JTextField password_text = new JTextField("enter the Password", 20);
    private JTextField password_2_text = new JTextField("enter the Password again", 20);



    /**
     Singup view ha de rebre si hi ha un error en algun parametre a
     l'hora de fer el display perque en funció d'aixo mostrara un misstage de error o no

     */
    public SignupView (boolean error_name, boolean error_mail, boolean error_password , boolean error_password_2) {
        configureView( error_name,  error_mail,  error_password , error_password_2);
        configureWindows();
    }

    private void configureWindows() {
        setTitle("Account Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1512,928);
        setLocationRelativeTo(null);
    }
    private void configureView( boolean error_name, boolean error_mail, boolean error_password , boolean error_password_2) {
        //Colors, fonts and sizes
        Color negre = new Color(48, 48, 48);
        Color vermell = new Color (232,74,77);
        Font titols = new Font("Dialog", Font.PLAIN, 40);
        Font text = new Font("Dialog", Font.PLAIN, 20);
        Font button = new Font("Dialog", Font.PLAIN, 30);
        Font information = new Font("Dialog", Font.PLAIN, 12);
        // Dimension button_shape = new Dimension(371,56);
        Dimension button_shape = new Dimension(505,40);

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,30,10,30); //Space between components

        JPanel info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.PAGE_AXIS));


        //Image

        ImageIcon Image_default = new ImageIcon("images/logo.png");
        Image imageIcon = Image_default.getImage();
        Image Image = getScaledImage(imageIcon, 200, 200);
        ImageIcon icon = new ImageIcon(Image);
        JLabel imatge_final = new JLabel();
        imatge_final.setIcon(icon);


        //User name and name text


        JLabel name_Label = new JLabel();
        name_Label.setText("User name");
        name_Label.setForeground(Color.white);
        name_Label.setFont(titols);
        info_panel.add(name_Label);

        name_text.setFont(text);
        info_panel.add(name_text);

        if(error_name) {

            JLabel Error_Label = new JLabel();
            Error_Label.setText("The username is incorrect");
            Error_Label.setForeground(vermell);
            Error_Label.setFont(text);
            info_panel.add(Error_Label);

        }

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

        if(error_mail){

            JLabel Error_Label = new JLabel();
            Error_Label.setText("The mail is incorrect");
            Error_Label.setForeground(vermell);
            Error_Label.setFont(text);
            info_panel.add(Error_Label);
        }



        // Password and JTextField

        JLabel password = new JLabel();
        password.setText("Password");
        password.setForeground(Color.white);
        password.setFont(titols);
        info_panel.add(password);


        password_text.setFont(text);
        info_panel.add(password_text);

        if(error_password){

            JLabel Error_Label = new JLabel();
            Error_Label.setText("The password is incorrect");
            Error_Label.setForeground(vermell);
            Error_Label.setFont(text);
            info_panel.add(Error_Label);
        }


        // Rewrite Password and JTextField


        JLabel password_2 = new JLabel();
        password_2.setText("Rewrite Password");
        password_2.setForeground(Color.white);
        password_2.setFont(titols);
        info_panel.add(password_2);


        password_2_text.setFont(text);
        info_panel.add(password_2_text);

        if(error_password_2){

            JLabel Error_Label = new JLabel();
            Error_Label.setText("The password is incorrect");
            Error_Label.setForeground(vermell);
            Error_Label.setFont(text);
            info_panel.add(Error_Label);
        }

        JLabel message = new JLabel();
        message.setText("The password must contain at least 8 characters with ");
        message.setForeground(Color.white);
        message.setFont(information);
        info_panel.add( message);
        JLabel message_2 = new JLabel();
        message_2.setText("1 lower case letter, 1 upper case letter and 1 number");
        message_2.setForeground(Color.white);
        message_2.setFont(information);
        info_panel.add( message_2);

        //Sign up button

        btnSignup.setBackground(vermell);
        btnSignup.setForeground(Color.white);
        btnSignup.setText("Sing Up");
        btnSignup.setFont(button);
        btnSignup.setPreferredSize(button_shape);
        btnSignup.setFocusable(false);
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

        getContentPane().add(p);

    }
    public void start(){
        setVisible(true);
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
}
