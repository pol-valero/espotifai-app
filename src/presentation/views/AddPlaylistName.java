package presentation.views;

import presentation.controllers.AddSongViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import presentation.controllers.AddSongViewController;
import presentation.controllers.SignupViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;


public class AddPlaylistName {

    private final JFrame topContainer;
    private final CardLayout cardManager;

    public static final String BTN_ADD = "BTN_ADD";

    private JButton jbAdd = new JButton();
    private JButton jbManagement = new JButton();
    private JTextField nameField = new JTextField(40);

    private JLabel jlWrongNameError = new JLabel();
    private JLabel goBackImage = new JLabel();

        public AddPlaylistName (JFrame topContainer, CardLayout cardManager) {
            this.topContainer = topContainer;
            this.cardManager = cardManager;
            configureView();
            topContainer.pack();
        }

        private void configureView() {

            //Colors, fonts and sizes
            Color negre = new Color(48, 48, 48);
            Color vermell = new Color (232,74,77);
            Font subtitle = new Font("Trebuchet MS", Font.PLAIN, 30);
            Font titol = new Font("Trebuchet MS", Font.PLAIN, 38);
            Font button = new Font("Trebuchet MS", Font.PLAIN, 20);
            Font text = new Font("Gulim", Font.PLAIN, 14);
            Font information = new Font("Gulim", Font.PLAIN, 14);

            Dimension button_shape = new Dimension(445,40);
            Dimension button_shape_2 = new Dimension(245,40);


            // Panel no bounds
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(negre);

            JPanel p = new JPanel(new GridBagLayout());
            p.setBackground(negre);

            GridBagConstraints c = new GridBagConstraints();
            GridBagConstraints d = new GridBagConstraints();


            c.insets = new Insets(10,30,10,30); //Space between components
            d.insets = new Insets(0,30,0,30); //Space between components


            JPanel info_panel = new JPanel();
            info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.PAGE_AXIS));
            info_panel.setOpaque(false);

            // tittle

            JPanel tittle_panel = new JPanel();
            tittle_panel.setLayout(new BoxLayout(tittle_panel, BoxLayout.PAGE_AXIS));

            tittle_panel.setBackground(negre);

            JLabel titleLabel = new JLabel();
            titleLabel.setText("Name            ");
            titleLabel.setForeground(Color.white);
            titleLabel.setFont(titol);


            JLabel explanation = new JLabel();
            explanation.setText("<html>Write the name of this new playlist</html>" );
            explanation.setForeground(Color.white);
            explanation.setFont(information);

            info_panel.add(titleLabel);
            info_panel.add(explanation);
            info_panel.add(Box.createRigidArea(new Dimension(5, 10)));

            //tittle_panel.add(titleLabel);
            //  tittle_panel.add(explanation);

            // Set go back Image and Imatge filler

            ImageIcon boto = new ImageIcon("src/edu/salleurl/boto.png");
            Image imageIcon_2 = boto.getImage();
            Image Image_2 = getScaledImage(imageIcon_2, 50, 50);
            ImageIcon new_Boto = new ImageIcon(Image_2);

            goBackImage.setIcon(new_Boto);
            goBackImage.addMouseListener(new MouseAdapter() {});

            JPanel BorderAdjustment = new JPanel(new BorderLayout());

            JPanel FillNORTH = new JPanel();
            FillNORTH.setBackground(negre);
            FillNORTH.setSize(20, 10);

            JPanel FillWEST = new JPanel();
            FillWEST.setBackground(negre);
            FillWEST.setSize(10, 20);

            BorderAdjustment.add(goBackImage, BorderLayout.CENTER);
            BorderAdjustment.add(FillNORTH, BorderLayout.NORTH);
            BorderAdjustment.add(FillWEST, BorderLayout.WEST);

            JPanel fill = new JPanel(new BorderLayout());

            JPanel FillPanel = new JPanel();
            FillPanel.setBackground(negre);
            fill.add(FillPanel, BorderLayout.CENTER);
            fill.add(BorderAdjustment, BorderLayout.WEST);

            // Set go back Button


            jbManagement.setBackground(vermell);
            jbManagement.setForeground(Color.white);
            jbManagement.setText("Acount Management");
            jbManagement.setFont(button);
            jbManagement.setPreferredSize(button_shape_2);
            jbManagement.setFocusable(false);
            jbManagement.setOpaque(true);
            jbManagement.setBorderPainted(false);
            jbManagement.setActionCommand(BTN_ADD);

            JPanel BorderAdjustment_2 = new JPanel(new BorderLayout());



            JPanel FillSOUTH = new JPanel();
            FillSOUTH.setBackground(negre);


            JPanel FillWEST_2 = new JPanel();
            FillWEST_2.setBackground(negre);


            BorderAdjustment_2.add(jbManagement, BorderLayout.CENTER);
            BorderAdjustment_2.add(FillSOUTH, BorderLayout.SOUTH);
            BorderAdjustment_2.add(FillWEST_2, BorderLayout.WEST);

            JPanel fill_2 = new JPanel(new BorderLayout());

            JPanel FillPanel_2 = new JPanel();
            FillPanel_2.setBackground(negre);
            fill_2.add(FillPanel_2, BorderLayout.CENTER);
            fill_2.add(BorderAdjustment_2, BorderLayout.WEST);


            //Playlist name text

            nameField.setFont(text);
            info_panel.add(nameField);

            jlWrongNameError.setText("This is not a valid name");
            jlWrongNameError.setForeground(vermell);
            jlWrongNameError.setFont(text);
            info_panel.add(jlWrongNameError);
            //Sign up button

            jbAdd.setBackground(vermell);
            jbAdd.setForeground(Color.white);
            jbAdd.setText("Add ");
            jbAdd.setFont(subtitle);
            jbAdd.setPreferredSize(button_shape);
            jbAdd.setFocusable(false);
            jbAdd.setOpaque(true);
            jbAdd.setBorderPainted(false);
            jbAdd.setActionCommand(BTN_ADD);

            //   btnSignup.setActionCommand(BTN_LOGOUT);


            //Position and addition
            c.gridx = 0;
            c.gridy = 0;
            p.add(tittle_panel,c);



            //Position and addition
            c.gridx = 0;
            c.gridy = 1;
            p.add(info_panel,c);

            //Position and addition
            c.gridx = 0;
            c.gridy = 2;
            p.add(jbAdd,c);

            panel.add(fill, BorderLayout.NORTH);
            panel.add(p, BorderLayout.CENTER);
            panel.add(fill_2, BorderLayout.SOUTH);


            topContainer.getContentPane().add(panel, "addPlaylistNameCard");
        }
        private Image getScaledImage(Image Img, int wt, int ht) {
            BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(Img, 0, 0, wt, ht, null);
            g2.dispose();

            return resizedImg;
        }
    public void wrongNameErrorVisibility(boolean error){
        jlWrongNameError.setVisible(error);
        topContainer.revalidate();
    }

    public String getPlaylistName() {
        return nameField.getText();
    }

    public void showCard () {
        setErrorsInvisible();
        cardManager.show(topContainer.getContentPane(),"addPlaylistNameCard");
    }

    private void setErrorsInvisible() {

        topContainer.revalidate();
    }
    }

