package presentation.controllers;

import presentation.UIController;
import presentation.views.LogoutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutViewController implements ActionListener {
    private UIController controller;
    private LogoutView logoutView;

    LogoutViewController(UIController controller, LogoutView logoutView){
        this.controller = controller;
        this.logoutView = logoutView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){ //todo revisar
            case logoutView.BTN_LOGOUT:
                controller.showLoginCard();
                break;
            case logoutView.BTN_DELETEACCOUNT:
                controller.deleteCurrentAccount();
                controller.showLoginCard();
                break;
        }
    }
}
