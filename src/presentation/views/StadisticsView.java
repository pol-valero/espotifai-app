package presentation.views;

import business.entities.Genre;
import presentation.Components.RowIcon;
import presentation.controllers.StadisticViewController;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing the StadisticsView. This class contains all the methods and attributes
 * needed to use and show the Stadistics View.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class StadisticsView {
    private final JPanel mainViewCenter;
    private final CardLayout cardManager;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    private JPanel centralPanel;
    private JPanel panel;

    public static final String BTN_HOME = "BTN_HOME";
    public static final String BTN_ACCOUNTMANAGER = "BTN_ACCOUNTMANAGER";

    private JButton homeBtn;
    private JButton accManBtn;

    private ActionListener actionListener; //todo ?¿

    /**
     * Constructor to create StadisticsView
     * Creates the LogoutView linking it to the UIController. This function
     * initializes the LogoutView.
     *
     * @param mainViewCenter this is the JPanel displayed in the Center of the mainView
     * @param cardManager the cardManager is the component that manages when to show each view
     */
    public StadisticsView (JPanel mainViewCenter, CardLayout cardManager){
        this.mainViewCenter=mainViewCenter;
        this.cardManager=cardManager;
        configureView();

    }

    /**
     * configureView is the main method of this class , creating the view and
     * adding the view to the JPanel main view Center in order to be displayed.
     *
     */
    private void configureView() {
        //Creation of main panels
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        centralPanel = centralPanelConfiguration(new LinkedList<>());
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);

        mainViewCenter.add(panel,"stadisticsCard");

    }

    /**
     * northernPanelConfiguration configures the panel located at the north of the main panel
     * of this view.
     *
     * @return JPanel with the elements that will be in the north of this view added
     */
    private JPanel northernPanelConfiguration() {

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(50, 250, 0, 50)));

        JLabel homeLabel = new JLabel("Stadistics");
        homeLabel.setFont( new Font("Tahoma", Font.PLAIN, 65));
        homeLabel.setForeground(Color.white);

        northernPanel.add(homeLabel);
        return northernPanel;
    }

    /**
     * centralPanelConfiguration configures the panel located at the center of the main panel
     * of this view.
     *
     * @param genres a linked list with  different song genres
     *
     * @return JPanel with the elements that will be in the center of this view added
     */
    private JPanel centralPanelConfiguration(LinkedList<Genre> genres) {

        int maxValue;
        int maxWidth = 1000;
        int rowHeight = 50;

        if (genres.size() > 0) {
            maxValue = genres.get(0).getAmount();
        } else {
            maxValue = 1;
        }

        //Central panel config
        JPanel centralPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        centralPanel.setLayout(borderLayout);
        centralPanel.setBorder(new EmptyBorder(50,50,50,50));
        centralPanel.setBackground(negre);

        //rowPanel config
        JPanel rowPanel = new JPanel();

        GridLayout rowLayout = new GridLayout(0,1,0,20);
        Border outsideBorder= new MatteBorder(1, 1, 1, 1, vermell);
        Border insideBorder = new EmptyBorder(10, 10, 0, 10);
        Border compund = new CompoundBorder(outsideBorder,insideBorder);

        rowPanel.setBorder(compund);
        rowPanel.setLayout(rowLayout);
        rowPanel.setBackground(negre);

        //GenrePanel config
        JPanel genrePanel = new JPanel();
        GridLayout genreLayout = new GridLayout(0,1,0,20);
        genrePanel.setLayout(genreLayout);
        genrePanel.setBorder(new EmptyBorder(5,10,0,10));
        genrePanel.setBackground(negre);

        //Scale panel x-axis numbers
        JPanel scalePanel = createScalePanel(maxValue,maxWidth);

        GridLayout scaleLayout = new GridLayout(1,0,0,0);
        scalePanel.setLayout(scaleLayout);
        scalePanel.setBackground(negre);

        scalePanel.setPreferredSize(new Dimension(1000,50));
        scalePanel.setBorder(new EmptyBorder(0,65,0,130));


        for(int i=0; i < genres.size(); i++){//Adding rows and their respective genre name

            String genreName = genres.get(i).getGenre();
            int value = genres.get(i).getAmount();

            JLabel valueLabel = new JLabel(String.valueOf(value));
            valueLabel.setHorizontalTextPosition(JLabel.RIGHT);
            valueLabel.setHorizontalAlignment(JLabel.LEFT);
            valueLabel.setVerticalTextPosition(JLabel.CENTER);
            valueLabel.setVerticalAlignment(JLabel.CENTER);
            valueLabel.setForeground(Color.white);

            int rowWidth = (value*maxWidth)/maxValue;
            Icon rowIcon = new RowIcon(rowWidth,rowHeight,vermell);
            valueLabel.setIcon(rowIcon);
            rowPanel.add(valueLabel);

            JLabel titleLabel = new JLabel(genreName);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            titleLabel.setForeground(Color.white);
            titleLabel.setFont( new Font("Gulim", Font.PLAIN, 17));
            genrePanel.add(titleLabel);

        }


        centralPanel.add(rowPanel,BorderLayout.CENTER);
        centralPanel.add(genrePanel,BorderLayout.WEST);
        centralPanel.add(scalePanel,BorderLayout.NORTH);


        return centralPanel;
    }

    /**
     * createScalePanel receives a LinkedList with the name of the playlist and creates a specific scroll panel
     * with the correct size to fit all playlist.
     *
     * @param amount amount of elements introduced in the panel
     *
     * @return the specific JPanel in which each playlist fits
     */
    private JPanel createScalePanel(int amount, int maxWidth) {
        int idealWidth = maxWidth/amount;

        JPanel scalePanel = new JPanel();
        scalePanel.setLayout(new FlowLayout());
        scalePanel.setBorder(new EmptyBorder(new Insets(0,30,0,70)));

        for(int i=0; i < amount+1;i++ ){
            JPanel panel = new JPanel();
            JLabel label = new JLabel(String.valueOf(i));
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            panel.add(label);
            panel.setPreferredSize(new Dimension(idealWidth,40));
            panel.setBackground(negre);
            scalePanel.add(panel);
        }

        return scalePanel;
    }


    /**
     * westernPanelConfiguration the panel located at the west of the main panel
     * of this view.
     *
     * @return JPanel with the elements that will be in the west of this view added
     */
    private JPanel westernPanelConfiguration() {

        JPanel westernPanel = new JPanel();
        BoxLayout westernLayout = new BoxLayout(westernPanel,BoxLayout.Y_AXIS);
        westernPanel.setLayout(westernLayout);
        westernPanel.setBackground(Color.white);
        westernPanel.setBorder(new EmptyBorder(new Insets(50, 30, 40, 30)));
        westernPanel.setMinimumSize(new Dimension(200,900));
        westernPanel.setBackground(negre);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(negre);

        GridBagConstraints constraints = new GridBagConstraints();

        homeBtn = createButton("Home");
        homeBtn.setActionCommand(BTN_HOME);
        accManBtn = createButton("Account Manager");
        accManBtn.setActionCommand(BTN_ACCOUNTMANAGER);

        constraints.gridx=0;
        constraints.gridy=0;
        buttonsPanel.add(homeBtn,constraints);

        constraints.gridx=0;
        constraints.gridy=1;
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,400)),constraints);

        constraints.gridx=0;
        constraints.gridy=2;
        buttonsPanel.add(accManBtn,constraints);


        westernPanel.add(Box.createRigidArea(new Dimension(0,40)));
        westernPanel.add(buttonsPanel);


        return westernPanel;
    }

    /**
     * createButton is a method that generic creates a JButton
     *
     * @param name the name of the button to create
     * @return A generic JButton with the correct name
     */
    private JButton createButton(String name) {
        Dimension button_shape = new Dimension(200,40);
        Font text = new Font("Gulim", Font.PLAIN, 19);

        JButton btn = new JButton(name);

        btn.setPreferredSize(button_shape);
        btn.setBackground(vermell);
        btn.setForeground(Color.white);
        btn.setFocusable(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFont(text);

        return btn;
    }

    /**
     * registerController as it's name implies registers the three controllers
     * of the stadisticView order to be accessed from the stadisticViewController class
     * @param stadisticViewController Is the parameter that will receive this class in order to
     *                              link the listeners of this view with the Controller
     */
    public void registerController(StadisticViewController stadisticViewController) {
        homeBtn.addActionListener(stadisticViewController);
        accManBtn.addActionListener(stadisticViewController);
        actionListener= stadisticViewController; //todo ?¿
    }

    /**
     * showCard  calls a method to set the error visibility no false
     * and introduces the current state of the mainViewCenter Frame in the cardManager
     * and shows to the user the screen.
     *
     * @param genres These are the different music genres.
     *
     */
    public void showCard(LinkedList<Genre> genres) {
        panel.remove(centralPanel);
        centralPanel = centralPanelConfiguration(genres);
        panel.add(centralPanel,BorderLayout.CENTER);
        mainViewCenter.revalidate();
        cardManager.show(mainViewCenter,"stadisticsCard");
    }
}

