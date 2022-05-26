package presentation.controllers;

import presentation.UIController;
import presentation.views.LogoutView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogoutViewController implements ActionListener, MouseListener{
    private UIController controller;
    private LogoutView logoutView;

    public LogoutViewController(UIController controller, JPanel mainViewCenter, CardLayout cardManager){
        this.controller = controller;
        logoutView = new LogoutView(mainViewCenter,cardManager);
        logoutView.registerController(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case LogoutView.BTN_LOGOUT:
                controller.showLoginCard();
                break;
            case LogoutView.BTN_DELETEACCOUNT:
                controller.deleteAccountRequest();
                controller.showLoginCard();
                break;
        }
    }

    public void showLogoutCard () {
        logoutView.showCard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.showHomescreenCard();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
