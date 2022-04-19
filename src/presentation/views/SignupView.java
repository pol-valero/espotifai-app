package presentation.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SignupView extends JFrame{

    public static final String BTN_SIGNUP = "BTN_SIGNUP";
    private JButton btnSignup;
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
        Font text = new Font("Dialog", Font.PLAIN, 24);
        Dimension button_shape = new Dimension(371,56);

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(negre);

        //Image

        JLabel imatge = new JLabel(new ImageIcon("icona.png"));

        //User name

        JLabel name = new JLabel();
        name.setText("User name");
        name.setForeground(Color.white);
        name.setFont(titols);

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
        }

        //Sign up button

        btnSignup.setBackground(vermell);
        btnSignup.setForeground(Color.white);
        btnSignup.setText("Log out");
        btnSignup.setFont(text);
        btnSignup.setPreferredSize(button_shape);
        btnSignup.setFocusable(false);
        btnSignup.setActionCommand(BTN_SIGNUP);

     //   btnSignup.setActionCommand(BTN_LOGOUT);


        p.add(imatge);
        p.add(name);
        p.add(name_text);
        p.add(mail);
        p.add(mail_text);
        p.add(password);
        p.add(password_text);
        p.add(password_2);
        p.add(password_2_text);
        p.add(btnSignup);

        getContentPane().add(p);


    }
    public void start(){
        setVisible(true);
    }

    /**

       return null si les strings del password no son iguals

    */
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
    public void registerController (ActionListener SignupViewController){ //Todo crida aquesta funció on toqui
        btnSignup.addActionListener(SignupViewController);

    }
}
