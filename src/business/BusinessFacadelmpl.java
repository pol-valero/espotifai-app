package business;

import business.entities.User;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para gestionar la informacion entre el controller y las clases de Business
 */
public class BusinessFacadelmpl implements BusinessFacade{

    private LoginManager loginManager = new LoginManager();

    @Override
    public boolean checkEmail(String email){
         return loginManager.checkEmail(email);
    }

    @Override
    public boolean sameString(String generalString1, String compareString) {
        return generalString1.equals(compareString);
    }

    @Override
    public boolean checkPassword(String password) {
        return loginManager.checkPassword(password);
    }

    public boolean loginRequest(String login, String password){
        return loginManager.loginRequest(login, password);
    }

    public boolean findUsernameMach(String name){
        return loginManager.findUsernameMach(name);
    }

    public boolean findEmailMach(String email){
        return loginManager.findEmailMach(email);
    }

    public void singUpRequest(User user){
        loginManager.singUpRequest(user);
    }

    public void logoutRequest(){
        loginManager.logoutRequest();
    }

    public void deleteAccountRequest(){
        loginManager.deleteAccountRequest();
    }

    public User getCurrentUser(){
        return loginManager.getCurrentUSer();
    }

}
