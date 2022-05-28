package presentation.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ReproductionBar extends JPanel {

    private final Color negre = new Color(48,48,48);

    JProgressBar bar;
    JButton playPauseBtn;
    JLabel songNameLab;
    JLabel artistNameLab;
    JLabel currentTimeLabel;
    JLabel endTimeLabel;

    public ReproductionBar(){
        configureBar();

    }

    private void configureBar() {

        setLayout(new FlowLayout());
        setBorder(new EmptyBorder(new Insets(20,20,20,20)));
        setBackground(negre);


        JPanel reproductionPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(15,15);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;

                //Draws the rounded panel with borders.
                graphics.setColor(Color.gray);
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background

            }
        };

        reproductionPanel.setBackground(this.getBackground());

        JPanel songNamePanel = songNamePanelConfig();
        JPanel barPanel = barPanelConfig();
        JPanel buttonsPanel = buttonsPanelConfig();

        reproductionPanel.add(songNamePanel);
        reproductionPanel.add(Box.createRigidArea(new Dimension(50,0)));
        reproductionPanel.add(barPanel);
        reproductionPanel.add(Box.createRigidArea(new Dimension(50,0)));
        reproductionPanel.add(buttonsPanel);

        add(reproductionPanel);



    }

    private JPanel songNamePanelConfig() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.gray);

        songNameLab = new JLabel();
        songNameLab.setForeground(Color.white);

        artistNameLab = new JLabel();
        artistNameLab.setForeground(Color.white);

        titlePanel.add(songNameLab);
        titlePanel.add(artistNameLab);

        return titlePanel;

    }

    private JPanel barPanelConfig() {

        JPanel barPanel = new JPanel();
        barPanel.setLayout(new BoxLayout(barPanel,BoxLayout.X_AXIS));
        barPanel.setBackground(Color.gray);


        currentTimeLabel = new JLabel();
        currentTimeLabel.setForeground(Color.white);
        endTimeLabel = new JLabel();
        endTimeLabel.setForeground(Color.white);

        bar = new JProgressBar(SwingConstants.HORIZONTAL);
        bar.setBackground(Color.lightGray);

        barPanel.add(currentTimeLabel);
        barPanel.add(Box.createRigidArea(new Dimension(5,0)));
        barPanel.add(bar);
        barPanel.add(Box.createRigidArea(new Dimension(5,0)));
        barPanel.add(endTimeLabel);

        return barPanel;
    }
    private JPanel buttonsPanelConfig() {
        JPanel mainBtns = new JPanel();
        mainBtns.setLayout(new BoxLayout(mainBtns,BoxLayout.X_AXIS));
        mainBtns.setBackground(Color.gray);


        JButton previousSongBtn = createRoundButton("images/iconGoBack.png",24,24);
        playPauseBtn = createRoundButton("images/whenPlaying.png",24,24);
        JButton nextSongBtn =createRoundButton("images/iconGoNext.png",24,24);

        mainBtns.add(previousSongBtn);
        mainBtns.add(playPauseBtn);
        mainBtns.add(nextSongBtn);

        mainBtns.add(Box.createRigidArea(new Dimension(20,0)));

        JButton indRepBtn = new JButton("IRB");
        indRepBtn.setBackground(Color.gray);
        indRepBtn.setPreferredSize(new Dimension(25,25));
        indRepBtn.setIcon(getScaledImage("images/individualRepeticion.png",25,25));
        mainBtns.add(indRepBtn);

        mainBtns.add(Box.createRigidArea(new Dimension(10,0)));

        JButton globalRepBtn = new JButton("Next");
        globalRepBtn.setBackground(Color.gray);
        globalRepBtn.setPreferredSize(new Dimension(25,25));
        globalRepBtn.setIcon(getScaledImage("images/globalRepeticionIcon.png",25,25));
        mainBtns.add(globalRepBtn);

        return mainBtns;
    }

    private RoundButton createRoundButton(String filepath, int weidth, int height) {
        JButton button = new RoundButton("");
        button.setIcon(getScaledImage(filepath,weidth,height));
        button.setPreferredSize(new Dimension(25,25));
        return (RoundButton) button;
    }


    private ImageIcon getScaledImage(String filepath, int wt, int ht) {
        ImageIcon imageIcon = new ImageIcon(filepath);
        Image image = imageIcon.getImage();

        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, wt, ht, null);
        g2.dispose();

        return new ImageIcon(resizedImg);


    }

    public void setNameLabels(String songName, String artistName){
        songNameLab.setText(songName);
        artistNameLab.setText(artistName);
        revalidate();
    }

    public void setCurrentTime(int minutes, int seconds){
        int secondsL = seconds/10;
        int secondsR = seconds - secondsL*10;

        currentTimeLabel.setText(minutes+":"+secondsL+""+secondsR);
        bar.setValue(minutes*60+seconds);
        revalidate();

    }


    public void setPlayBtn(){
        playPauseBtn.setIcon(getScaledImage("images/whenPlaying.png",25,25));
        revalidate();

    }

    public void setPauseBtn(){
        playPauseBtn.setIcon(getScaledImage("images/whenPause.png",25,25));
        revalidate();
    }

    public void reproduceNewSong(String songName, int totalMinutes, int totalSeconds){
        bar.setValue(0);
        bar.setMaximum(totalMinutes*60+totalSeconds);

        songNameLab.setText(songName);

        currentTimeLabel.setText("0:00");
        endTimeLabel.setText(timeFormater(totalMinutes,totalSeconds));

        revalidate();
    }

    public void update (int minutes, int seconds){
        bar.setValue(minutes*60+seconds);
        currentTimeLabel.setText(timeFormater(minutes,seconds));

        revalidate();

    }

    private String timeFormater(int minutes, int seconds){
        int secondsL = seconds/10;
        int secondsR = seconds - secondsL*10;

        return minutes+":"+secondsL+secondsR;

    }



}
