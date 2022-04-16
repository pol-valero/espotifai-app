package presentation;

import business.BusinessFacade;
import presentation.controllers.LoginViewController;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que gestiona el funcionamiento del programa
 */
public class UIController {

    private LoginViewController loginViewController;
    private BusinessFacade facade;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        //fer el mateix amb tots els controllers
    }




}
