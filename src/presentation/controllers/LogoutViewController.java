package presentation.controllers;

import presentation.UIController;
import presentation.views.LogoutView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutViewController implements ActionListener {
    private UIController controller;
    private LogoutView logoutView;

    public LogoutViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        logoutView = new LogoutView(topContainer,cardManager);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){ //todo revisar
            /*case LogoutView.BTN_LOGOUT:
                controller.showLoginCard();
                break;
            case LogoutView.BTN_DELETEACCOUNT:
                controller.deleteCurrentAccount();
                controller.showLoginCard();
                break;*/
        }
    }
}
