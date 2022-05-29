package presentation.Components;

import presentation.controllers.PlayBarController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is the class ReproductionBar, this class enables access to a Reproduction Bar in multiple views.
 * Extends the functions of a JPanel.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class ReproductionBar extends JPanel {

    public static final String BTN_PLAY = "BTN_PLAY";
    public static final String BTN_PAUSE = "BTN_PAUSE";
    public static final String BTN_PREVIOUS_SONG = "BTN_PREVIOUS_SONG";
    public static final String BTN_NEXT_SONG = "BTN_NEXT_SONG";
    public static final String BTN_SONG_REPETITION = "BTN_SONG_REPETITION";
    public static final String BTN_PLAYLIST_REPETITION = "BTN_PLAYLIST_REPETITION";

    private final Color negre = new Color(48,48,48);

    private JProgressBar bar;

    private JButton playPauseBtn;
    private JButton previousSongBtn;
    private JButton nextSongBtn;

    private JButton songRepetition;
    private JButton playlistRepetition;

    private JLabel songNameLab;
    private JLabel artistNameLab;
    private JLabel currentTimeLabel;
    private JLabel endTimeLabel;

    /**
     * Constructor to create ReproductionBar
     * Creates the ReproductionBar calling the method configureBar
     *
     */
    public ReproductionBar(){
        configureBar();

    }

    /**
     * configureBar is the main method of this class , creating the Bar and
     * calling specific methods to add the components.
     *
     */
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

    /**
     * songNamePanelConfig configures the panel that contains all the elements of the title.
     *
     * @return JPanel with the elements that will be in the title
     */
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
    /**
     * barPanelConfig configures the panel that contains all the elements of the bar.
     *
     * @return JPanel with the elements that will be in the bar
     */
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

    /**
     * buttonsPanelConfig configures the panel that contains all the elements of the buttons.
     *
     * @return JPanel with the elements that will be in the buttons
     */
    private JPanel buttonsPanelConfig() {
        JPanel mainBtns = new JPanel();
        mainBtns.setLayout(new BoxLayout(mainBtns,BoxLayout.X_AXIS));
        mainBtns.setBackground(Color.gray);


        previousSongBtn = createRoundButton("images/iconGoBack.png",24,24);
        previousSongBtn.setActionCommand(BTN_PREVIOUS_SONG);

        playPauseBtn = createRoundButton("images/playButton.png",24,24);
        playPauseBtn.setActionCommand(BTN_PLAY);

        nextSongBtn =createRoundButton("images/iconGoNext.png",24,24);
        nextSongBtn.setActionCommand(BTN_NEXT_SONG);

        mainBtns.add(previousSongBtn);
        mainBtns.add(playPauseBtn);
        mainBtns.add(nextSongBtn);

        mainBtns.add(Box.createRigidArea(new Dimension(20,0)));

        songRepetition = new JButton();
        songRepetition.setBackground(Color.gray);
        songRepetition.setPreferredSize(new Dimension(25,25));
        songRepetition.setIcon(getScaledImage("images/individualRepeticion.png",25,25));
        songRepetition.setActionCommand(BTN_SONG_REPETITION);
        mainBtns.add(songRepetition);

        mainBtns.add(Box.createRigidArea(new Dimension(10,0)));

        playlistRepetition = new JButton();
        playlistRepetition.setBackground(Color.gray);
        playlistRepetition.setPreferredSize(new Dimension(25,25));
        playlistRepetition.setIcon(getScaledImage("images/globalRepeticionIcon.png",25,25));
        playlistRepetition.setActionCommand(BTN_PLAYLIST_REPETITION);
        playlistRepetition.setVisible(false);
        mainBtns.add(playlistRepetition);

        return mainBtns;
    }

    /**
     * createRoundButton is a generic method that  creates a RoundButton
     *
     * @param filepath the location of the image to use as a button
     * @param weidth width of the image that will be the RoundButton
     * @param height of the image that will be the RoundButton
     * @return A generic RoundButton with the correct attributes
     */
    private RoundButton createRoundButton(String filepath, int weidth, int height) {
        JButton button = new RoundButton("");
        button.setIcon(getScaledImage(filepath,weidth,height));
        button.setPreferredSize(new Dimension(25,25));
        return (RoundButton) button;
    }


    /**
     * getScaledImage is a method that receives an image with a certain length and height
     * and resizes the image to mach the length and height.
     * @param  filepath the location of the image to use as a button
     * @param wt Width of the image to resize
     * @param ht Height of the image to resize
     * @return The image introduced in this method but resized to mach the Width and Height
     */
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
        playPauseBtn.setIcon(getScaledImage("images/playButton.png",25,25));
        playPauseBtn.setActionCommand(BTN_PLAY);
        revalidate();

    }

    public void setPauseBtn(){
        playPauseBtn.setIcon(getScaledImage("images/pauseButton.png",25,25));
        playPauseBtn.setActionCommand(BTN_PAUSE);
        revalidate();
    }

    /**
     * reproduceNewSong prepares the ReproductionBar to change the parameters of a new song
     * @param songName name of the new song to play
     * @param totalMinutes length of the minute's duration of the song
     * @param totalSeconds length of the second's duration of the song
     */
    public void reproduceNewSong(String songName, int totalMinutes, int totalSeconds){
        bar.setValue(1);
        bar.setMaximum(totalMinutes*60+totalSeconds);

        songNameLab.setText(songName);

        currentTimeLabel.setText("0:01");
        endTimeLabel.setText(timeFormater(totalMinutes,totalSeconds));

        revalidate();
    }

    /**
     * update the ReproductionBar to show the current time of the song
     * @param minutes length of the minute's duration in the current moment
     * @param seconds length of the second's duration in the current moment
     */
    public void update (int minutes, int seconds){
        bar.setValue(minutes*60+seconds);
        currentTimeLabel.setText(timeFormater(minutes,seconds));

        revalidate();

    }


    public void showGlobalRepetitionBtn (boolean visible) {
        playlistRepetition.setVisible(visible);
        revalidate();
    }

    /**
     * timeFormater arranges the values of seconds and minutes to display them correctly
     * @param minutes length of the minute's duration of the song
     * @param seconds length of the second's duration of the song
     * @return
     */
    private String timeFormater(int minutes, int seconds){
        int secondsL = seconds/10;
        int secondsR = seconds - secondsL*10;

        return minutes+":"+secondsL+secondsR;

    }

    public void registerController(PlayBarController playBarController) {
        playPauseBtn.addActionListener(playBarController);
        nextSongBtn.addActionListener(playBarController);
        previousSongBtn.addActionListener(playBarController);
        playlistRepetition.addActionListener(playBarController);
        songRepetition.addActionListener(playBarController);
    }



}
