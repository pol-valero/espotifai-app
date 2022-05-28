package persistence;

import persistence.DAO.JsonDAO;
import java.sql.*;

public class SQLConnector {

    private static SQLConnector instance = null;

    private final String username;
    private final String password;
    private final String url;
    private Connection connection;
    private ConfigDAO configDAO =  new JsonDAO();

    public static SQLConnector getInstance(){
        if (instance == null ){
            instance = new SQLConnector();
            instance.connect();
        }
        return instance;
    }

    private SQLConnector(){
        this.username = configDAO.getUsuariobbdd();
        this.password = configDAO.getPassword();
        this.url = configDAO.getUrl();

    }

    /**
     * Method for connecting to the database
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException exception) {
            System.err.println("Couldn't connect to  " + url);
        }
    }

    /**
     * Method for inserting new information to the database
     * @param query String with the SQL command
     */
    public void insertQuery(String query){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(query);
        } catch (SQLException exception) {
            System.err.println("ERROR in = " + query + " (" + exception.getMessage() + ")");
        }
    }

    /**
     * Method to update already existing database information
     * @param query String with the SQL command
     */
    public void updateQuery(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            System.err.println("ERROR in = " + query);
        }
    }

    /**
     * Method to delete information from the database
     * @param query String with the SQL command
     */
    public void deleteQuery(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            System.err.println("ERROR in = " + query);
        }

    }

    /**
     *
     * @param query String with the SQL command
     * @return resultSet object of type ResultSet with database information
     */
    public ResultSet selectQuery(String query){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException exception) {
            System.err.println("ERROR in = " + query);
        }
        return resultSet;
    }

}
