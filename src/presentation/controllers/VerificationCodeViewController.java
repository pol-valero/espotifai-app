package presentation.controllers;
import presentation.UIController;
import presentation.views.VerificationCodeView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class VerificationCodeViewController implements ActionListener, MouseListener {

    private final VerificationCodeView verificationCodeView;
    private final UIController controller;

    public VerificationCodeViewController(UIController controller, JFrame topContainer, CardLayout cardManager){
        this.controller = controller;
        verificationCodeView = new VerificationCodeView(topContainer, cardManager);
        verificationCodeView.registerController(this);
    }

    public void showVerificationCodeViewCard () {

        verificationCodeView.showCard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
