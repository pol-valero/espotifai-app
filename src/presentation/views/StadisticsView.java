package presentation.views;

import business.entities.Genre;
import presentation.Components.RowIcon;
import presentation.controllers.StadisticViewController;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class StadisticsView {
    private final JPanel mainViewCenter;
    private final CardLayout cardManager;
    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    public StadisticsView (JPanel mainViewCenter, CardLayout cardManager){
        this.mainViewCenter=mainViewCenter;
        this.cardManager=cardManager;
        configureView();

    }

    private void configureView() {
        //Creation of main panels
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel westernPanel = westernPanelConfiguration();
        JPanel centralPanel = centralPanelConfiguration(new LinkedList<>());
        JPanel northernPanel = northernPanelConfiguration();

        panel.add(northernPanel,BorderLayout.NORTH);
        panel.add(westernPanel,BorderLayout.WEST);
        panel.add(centralPanel,BorderLayout.CENTER);

        mainViewCenter.add(panel,"stadisticsCard");

    }


    private JPanel northernPanelConfiguration() {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 65);

        JPanel northernPanel = new JPanel();
        BoxLayout northernLayout = new BoxLayout(northernPanel,BoxLayout.X_AXIS);
        northernPanel.setLayout(northernLayout);
        northernPanel.setBackground(negre);
        northernPanel.setBorder(new EmptyBorder(new Insets(50, 250, 0, 50)));

        JLabel homeLabel = new JLabel("Stadistics");
        homeLabel.setFont(titols);
        homeLabel.setForeground(Color.white);

        northernPanel.add(homeLabel);
        return northernPanel;
    }

    private JPanel centralPanelConfiguration(LinkedList<Genre> genres) {

        int maxValue =36;
        //int maxValue = genres.get(0).getAmount();

        //Central panel config
        JPanel centralPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        centralPanel.setLayout(borderLayout);
        centralPanel.setBorder(new EmptyBorder(50,50,50,50));
        centralPanel.setBackground(negre);

        //rowPanel config
        JPanel rowPanel = new JPanel();

        GridLayout rowLayout = new GridLayout(0,1,0,20);//change 10
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
        JPanel scalePanel =createScalePanel(maxValue);//todo passa el nombre màxim

        GridLayout scaleLayout = new GridLayout(1,0,0,0);
        scalePanel.setLayout(scaleLayout);
        scalePanel.setBackground(negre);

        scalePanel.setPreferredSize(new Dimension(1000,50));
        scalePanel.setBorder(new EmptyBorder(0,65,0,130));


        //todo treure valor de la linkedlist
        int maxWidth = 1000;
        int rowHeight = 50;

        for(int i=1; i < 10+1; i++){//Adding rows and their respective genre name

            String genreName = "Genre"+i;
            int value = (maxValue/10)*i;


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

    private JPanel createScalePanel(int amount) {
        JPanel scalePanel = new JPanel();
        scalePanel.setLayout(new FlowLayout());
        scalePanel.setBorder(new EmptyBorder(new Insets(0,30,0,80)));

        for(int i=1; i < amount+1;i++ ){
            JPanel panel1 = new JPanel();
            JLabel label = new JLabel(i+"");
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            panel1.add(label);
            panel1.setPreferredSize(new Dimension(100,40));
            panel1.setBackground(Color.white);
            panel1.setBorder(new LineBorder(Color.black));
            scalePanel.add(panel1);
        }

        return scalePanel;
    }


    private JPanel westernPanelConfiguration() {
        //Fonts, colours and sizes
        Font titols = new Font("Trebuchet MS", Font.PLAIN, 36);
        Font text = new Font("Gulim", Font.PLAIN, 24);

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

        JButton stadisticsBtn = createButton("Home");
        JButton accManBtn = createButton("Account Manager");

        constraints.gridx=0;
        constraints.gridy=0;
        buttonsPanel.add(stadisticsBtn,constraints);

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

    private Image getScaledImage(Image img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }

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

    public void registerController(StadisticViewController stadisticViewController) {

    }

    public void showCard(LinkedList<Genre> genres) {

        cardManager.show(mainViewCenter,"stadisticsCard");
    }
}

