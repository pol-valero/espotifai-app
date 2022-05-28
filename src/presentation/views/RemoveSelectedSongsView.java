package presentation.views;

import presentation.Components.RoundButton;
import presentation.controllers.RemoveSelectedSongsController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RemoveSelectedSongsView {

    private final JPanel mainViewCenter;
    private final CardLayout cardManager;

    private JPanel panel;
    private JLabel cannotDeleteSongLabel;

    private final Color negre = new Color(48,48,48);
    private final Color vermell = new Color (232,74,77);

    public RemoveSelectedSongsView(JPanel mainViewCenter, CardLayout cardManager) {
        this.mainViewCenter= mainViewCenter;
        this.cardManager=cardManager;
        configureView();
    }

    private void configureView() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(270,200,200,200)));
        panel.setBackground(negre);

        JPanel titlePanel = titlePanelConfig();
        JPanel buttonPanel = buttonPanelConfig();
        JPanel errorPanel = errorMessageLabelConfig();

        panel.add(titlePanel);
        panel.add(buttonPanel);
        panel.add(errorPanel);

        mainViewCenter.add(panel, "removeSelectedSongsCard");
    }

    private JPanel errorMessageLabelConfig() {
        JPanel panel = new JPanel();
        panel.setBackground(negre);
        cannotDeleteSongLabel = new JLabel();
        cannotDeleteSongLabel.setText("The song that is currently being reproduced cannot be deleted");
        cannotDeleteSongLabel.setForeground(vermell);
        cannotDeleteSongLabel.setFont(new Font("Gulim", Font.PLAIN, 14));
        panel.add(cannotDeleteSongLabel);
        return panel;
    }

    private JPanel buttonPanelConfig() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(negre);
        buttonPanel.setBorder(new EmptyBorder(new Insets(100,60,0,0)));

        JButton confirmButton = createButton("Yes, remove songs");
        JButton cancelButton = createButton("Cancel");


        buttonPanel.add(confirmButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(30,0)));
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(vermell);
        button.setForeground(Color.white);
        button.setFont(new Font("Gulim", Font.PLAIN, 15));
        button.setPreferredSize(new Dimension(200,50));

        return button;
    }

    private JPanel titlePanelConfig() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(negre);
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Remove Selected Songs");
        title.setForeground(Color.white);
        title.setFont(new Font ("Tahoma",Font.PLAIN,30));

        JLabel subtitle = new JLabel("You are about to remove these songs. Are you sure?");
        subtitle.setForeground(Color.white);
        subtitle.setFont(new Font ("Gulim",Font.PLAIN,16));

        titlePanel.add(title);
        titlePanel.add(subtitle);

        return titlePanel;
    }
    public void cannotDeleteSongVisibility(boolean error){
        cannotDeleteSongLabel.setVisible(error);
        mainViewCenter.revalidate();
    }

    public void showCard() {
        cannotDeleteSongVisibility(false);
        cardManager.show(mainViewCenter,"removeSelectedSongsCard");
    }

    public void registerController(RemoveSelectedSongsController removeSelectedSongsController) {
    }
}
