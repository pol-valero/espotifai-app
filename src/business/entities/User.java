package business.entities;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class in charged of managing information related to User objects
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = pwdHash(password);
    }


    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Method to get the user's id
     * @return Integer corresponding to the user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the user's id
     * @param id Integer corresponding to the new id of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the name of the user
     * @return String corresponding to the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the user's email
     * @return String corresponding to the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to get the user's password
     * @return String corresponding to the user's passwords
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method that codifies a string (password) in hash. This will be used for security purposes.
     * @param password password to codify
     * @return return the codified password.
     */
    private String pwdHash(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.reset();
        try {
            digest.update(password.getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        password = String.format("%064x", new BigInteger(1, digest.digest()));

        return password;
    }
}

