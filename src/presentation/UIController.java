package presentation;

import presentation.controllers.HomescreenViewController;
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
    private HomescreenViewController homescreenViewController;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        signupViewController = new SignupViewController(this, topContainer, cardManager);
        logoutViewController = new LogoutViewController(this, topContainer, cardManager);
        homescreenViewController = new HomescreenViewController(this,topContainer,cardManager);


        showSignUpCard();
        //fer el mateix amb tots els altres controllers
    }

    public LinkedList<String> loadPublicPlaylists() {
        LinkedList<String> publicPlaylists = new LinkedList<String>();

        return publicPlaylists;
    }

    public LinkedList<String> loadUsersPlaylists() {
        LinkedList<String> usersPlaylists = new LinkedList<String>();

        return usersPlaylists;
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

        return false;
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


    public void showHomescreenCard() {
        LinkedList<String> prova1 = new LinkedList<>();
        prova1.add("Adria");
        prova1.add("se't");
        prova1.add("dona");
        prova1.add("molt");
        prova1.add("be");
        prova1.add("fer");
        prova1.add("vistes");
        prova1.add(":-)");

        LinkedList<String> prova2 = new LinkedList<>();
        prova2.add("Quim");
        prova2.add("dolent");
        homescreenViewController.showHomescreenCard(prova1,prova2);
    }

    public boolean loginRequest(String login, String password) { //todo

        return true;
    }
}
