package persistence;

import java.sql.*;

public class SQLConnector {

    private static SQLConnector instance = null; //empazamos sin instanciar la base

    private final String username;
    private final String password;
    private final String url;
    private Connection connection;


    public static SQLConnector getInstance(){ //si la base no esta instanciada la instanciamos
        if (instance == null ){
            instance = new SQLConnector("potify-lasalle", "3S8&jfd1", "www.spotify-lasalle.com", 3306, "spotifybbdd");
            instance.connect();
        }
        return instance;
    }

    private SQLConnector(String username, String password, String ip, int port, String database){
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    /**
     * Metodo para conectar a la base de datos
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password); //conecta a la base de datos usando la url
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
    }

    public void insertQuery(String query){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }


    public void updateQuery(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }


    public void deleteQuery(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }

    }


    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            Statement s = connection.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }


    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

}
