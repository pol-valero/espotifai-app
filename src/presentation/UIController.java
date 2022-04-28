package presentation;

import presentation.controllers.LoginViewController;
import presentation.controllers.LogoutViewController;
import presentation.controllers.SignupViewController;
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
    private SignupViewController signupViewController;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        signupViewController = new SignupViewController(this, topContainer, cardManager);
        logoutViewController = new LogoutViewController(this, topContainer, cardManager);

        showLogoutCard();
        //fer el mateix amb tots els altres controllers
    }


    public void showLoginCard() {
        loginViewController.showLoginCard();
    }

    public void showSignUpCard() {
        signupViewController.showSignupCard();
    }

    public void showLogoutCard () {
        logoutViewController.showLogoutCard();
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

    public boolean checkEqualPassword (String password, String rewritedPassword){
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



    public boolean loginRequest(String login, String password) { //todo

        return true;
    }
}
