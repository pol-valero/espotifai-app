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
        Font titols = new Font("Dialog", Font.PLAIN, 30);
        Font text = new Font("Dialog", Font.PLAIN, 10);
        // Dimension button_shape = new Dimension(371,56);
        Dimension button_shape = new Dimension(250,50);

        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(negre);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,30,10,30); //Space between components

        //Image

        ImageIcon Image_default = new ImageIcon("images/logo.png");
        Image imageIcon = Image_default.getImage();
        Image Image = getScaledImage(imageIcon, 200, 200);
        ImageIcon icon = new ImageIcon(Image);
        JLabel imatge_final = new JLabel();
        imatge_final.setIcon(icon);


        //User name

        JLabel name_Label = new JLabel();
        name_Label.setText("User name");
        name_Label.setForeground(Color.white);
        name_Label.setFont(titols);

        JPanel name = new JPanel();
        name.setLayout( new BorderLayout());
        name.setSize(500, 300);
        name.setOpaque(false);
        name.add(name_Label, BorderLayout.SOUTH);
        // User name Jtextfield

        name_text.setFont(text);

        if(error_name){

            name_text.setToolTipText(vermell + "Wrong User name " + text);
        }

        // E-mail

        JLabel mail = new JLabel();
        mail.setText("E-mail");
        mail.setForeground(Color.white);
        mail.setFont(titols);

        // mail Jtextfield

        JTextField mail_text = new JTextField("enter the E-mail", 20);
        mail_text.setFont(text);

        if(error_mail){

            mail_text.setToolTipText(vermell + "Wrong E-mail " + text);
            mail.setText("malament ");
        }

        // Password

        JLabel password = new JLabel();
        password.setText("Password");
        password.setForeground(Color.white);
        password.setFont(titols);

        // password Jtextfield

        JTextField password_text = new JTextField("enter the Password", 20);
        password_text.setFont(text);

        if(error_password){

            password_text.setToolTipText(vermell + "Wrong Password " + text);
            password_text.setText("malament ");
        }

        // Rewrite Password

        JLabel password_2 = new JLabel();
        password_2.setText("Rewrite Password");
        password_2.setForeground(Color.white);
        password_2.setFont(titols);

        // password_2 Jtextfield

        JTextField password_2_text = new JTextField("enter the Password again", 20);
        password_2_text.setFont(text);

        if(error_password_2){

            password_2_text.setToolTipText(vermell + "Wrong Password " + text);
            password_2_text.setText("malament ");
        }

        //Sign up button

        btnSignup.setBackground(vermell);
        btnSignup.setForeground(Color.white);
        btnSignup.setText("Sing Up");
        btnSignup.setFont(titols);
        btnSignup.setPreferredSize(button_shape);
        btnSignup.setFocusable(false);
        btnSignup.setActionCommand(BTN_SIGNUP);

        //   btnSignup.setActionCommand(BTN_LOGOUT);


        //Position and addition
        c.gridx = 0;
        c.gridy = 0;
        p.add(imatge_final,c);

        c.gridx = 0;
        c.gridy = 1;
        p.add(name,c);

        c.gridx = 0;
        c.gridy = 2;
        p.add(name_text,c);
        //Position and addition
        c.gridx = 0;
        c.gridy = 3;
        p.add(mail,c);

        c.gridx = 0;
        c.gridy = 4;
        p.add(mail_text,c);

        c.gridx = 0;
        c.gridy = 5;
        p.add(password,c);

        //Position and addition
        c.gridx = 0;
        c.gridy = 6;
        p.add(password_text,c);

        c.gridx = 0;
        c.gridy = 7;
        p.add(password_2,c);

        c.gridx = 0;
        c.gridy = 8;
        p.add(password_2_text,c);
        //Position and addition
        c.gridx = 0;
        c.gridy = 9;
        p.add(btnSignup,c);

        getContentPane().add(p);


    }
    public void start(){
        setVisible(true);
    }


    /*
    public String[] getinfo() { //todo mètode per recollir les strings del JlabelText

        String[] info = new String[4];
        String name_info = name_text.getText();
        String mail_info = mail_text.getText();
        String password_info = password_text.getText();
        String password_2_info = password_2_text.getText();

        if (Objects.equals(password_info, password_2_info)){

            info[0] = name_info;
            info[1] = mail_info;
            info[2] = password_info;
            info[3] = password_2_info;

            return info;
        }

        return null;
    }
    */

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
