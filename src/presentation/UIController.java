package presentation;

import presentation.controllers.LoginViewController;
import presentation.controllers.LogoutViewController;
import presentation.views.LoginView;
import presentation.views.LogoutView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

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


    public void showLoginCard() {
    }

    public boolean findUserNameMatch(String username){//todo

        return true;
    }

    public boolean findEmailMatch(String email){ //todo

        return true;
    }

    public boolean checkPasswordFormat(String password){ //todo

        return true;
    }

    public boolean checkEmailFormat (String email){
        return true;
    }

    public boolean checkEqualPassword (String[] passwords){
        return true;
    }


    public void deleteAccountRequest() {
    }

    public LinkedList getPlaylistNames() {
        LinkedList playlistNames = new LinkedList();
        
        return playlistNames;
    }

    public void showHomescreenCard(LinkedList playlistNames) {
    }

    public void showForgotPasswordCard() {
    }

    public void showSignUpCard() {
    }
}
