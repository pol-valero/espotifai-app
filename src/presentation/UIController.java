package presentation;

import presentation.controllers.LoginViewController;
import presentation.controllers.LogoutViewController;
import presentation.views.LoginView;
import presentation.views.LogoutView;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que gestiona el funcionamiento del programa
 */
public class UIController {

    private LoginViewController loginViewController;
    private LogoutViewController logoutViewController;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        logoutViewController = new LogoutViewController(this, topContainer, cardManager);
        //fer el mateix amb tots els altres controllers
    }




}
