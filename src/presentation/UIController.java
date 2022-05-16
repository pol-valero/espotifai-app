package presentation;

import business.BusinessFacade;
import business.BusinessFacadelmpl;
import business.entities.Playlist;
import business.entities.User;
import presentation.controllers.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

/**
 * Clase que gestiona el funcionamiento del programa
 */
public class UIController {

    private LoginViewController loginViewController;
    private LogoutViewController logoutViewController;
    private SignupViewController signupViewController;
    private HomescreenViewController homescreenViewController;
    private BusinessFacade businessFacade = new BusinessFacadelmpl();
    private VerificationCodeViewController verificationCodeViewController;
    private MusicListController musicListController;

    public void run () {
        JFrame topContainer = new JFrame();
        CardLayout cardManager = new CardLayout();

        loginViewController = new LoginViewController(this, topContainer, cardManager);
        signupViewController = new SignupViewController(this, topContainer, cardManager);
        logoutViewController = new LogoutViewController(this, topContainer, cardManager);
        homescreenViewController = new HomescreenViewController(this,topContainer,cardManager);
        verificationCodeViewController = new VerificationCodeViewController(this,topContainer,cardManager);
        musicListController = new MusicListController(this, topContainer, cardManager);
        //fer el mateix amb tots els altres controllers
        //showMusicListCard();
        //showSignUpCard();
        //showHomescreenCard();
        //showLoginCard();
        //showSignUpCard();
        //showHomescreenCard();
        //showMusicListCard();
    }

    private void showMusicListCard() {
        //todo cal fer un mètode per obtenir una string (songname)
        musicListController.showMusicListCard(new Playlist(122,"Chill out testing",2323,"You"));
    }

    public LinkedList<String> loadPublicPlaylists() {
        LinkedList<String> publicPlaylists = new LinkedList<String>();

        return publicPlaylists;
    }

    public LinkedList<String> loadUsersPlaylists() {
        LinkedList<String> usersPlaylists = new LinkedList<String>();

        return usersPlaylists;
    }

    public void showHomescreenCard() {
        LinkedList<String> prova1 = new LinkedList<>();
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("test2");
        prova1.add("test1");
        prova1.add("final");

        LinkedList<String> prova2 = new LinkedList<>();
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("test3");
        prova2.add("final");
        homescreenViewController.showHomescreenCard(prova1,prova2);
    }

    public void showLoginCard() {
        loginViewController.showLoginCard();
    }

    public void showSignUpCard() {
        signupViewController.showSignupCard();
    }
    public void showVerificationCodeViewCard() {

        verificationCodeViewController.showVerificationCodeViewCard();
    }

    public void showLogoutCard () {
        logoutViewController.showLogoutCard();
    }

    public boolean findUserNameMatch(String username){//todo

        return businessFacade.findUsernameMach(username);
    }

    public boolean findEmailMatch(String email){ //todo

        return businessFacade.findEmailMach(email);
    }

    public boolean checkPasswordFormat(String password){ //todo

        return !businessFacade.checkPassword(password);
    }

    public boolean checkEmailFormat (String email){

        return businessFacade.checkEmail(email);
    }

    public boolean checkEqualPassword (String password, String rewritedPassword){

        return !businessFacade.sameString(password, rewritedPassword);
    }

    public boolean loginRequest(String login, String password) { //todo

        return businessFacade.loginRequest(login, password);
    }

    public void signUpRequest (User user) {
        businessFacade.singUpRequest(user);
    }



    public void deleteAccountRequest() {
    }


}
