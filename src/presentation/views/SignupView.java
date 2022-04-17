package presentation.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignupView extends JFrame{
    public static final String BTN_SIGNUP = "BTN_SIGNUP";
    private JButton btnSignup;

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
        Font text = new Font("Dialog", Font.PLAIN, 20);
        Dimension button_shape = new Dimension(371,56);

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(negre);

        //Image

        JLabel imatge = new JLabel(new ImageIcon("example png.png"));

        //User name

        JLabel name = new JLabel();
        name.setText("User name");
        name.setForeground(Color.white);
        name.setFont(titols);

        // User name Jtextfield

        JTextField name_text = new JTextField("enter the user name", 20);
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

            mail_text.setToolTipText(vermell + "Wrong User name " + text);
        }





        p.add(imatge);
        p.add(name);
        p.add(name_text);
        p.add(mail);
        p.add(mail_text);



    }
    public void start(){
        setVisible(true);
    }


    public String[] getinfo() { //todo mètode per recollir les strings del JlabelText
        String[] info = new String[4];


        return info;
    }
}
