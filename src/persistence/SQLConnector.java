package persistence;

import java.sql.*;

public class SQLConnector {

    private static SQLConnector instance = null;

    private final String username;
    private final String password;
    private final String url;
    private Connection connection;


    public static SQLConnector getInstance(){ //si la base no esta instanciada la instanciamos
        if (instance == null ){
            instance = new SQLConnector("spotify-lasalle", "3S8&jfd1", "www.spotify-lasalle.com", 3306, "spotifybbdd");
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
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException exception) {
            System.err.println("Couldn't connect to  " + url);
        }
    }

    /**
     * Metodo para insertar nueva informacion a la base de datos
     * @param query String con el comando para el SQL
     */
    public void insertQuery(String query){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(query);
        } catch (SQLException exception) {
            System.err.println("ERROR in = " + query);
        }
    }

    /**
     *  Metodo para actiualizar informacion ya existente de la  base de datos
     * @param query String con el comando para el SQL
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
     * Metodo para eliminar informacion de la base de datos
     * @param query String con el comando para el SQL
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
     * @param query String con el comando para el SQL
     * @return resulSet objeto de tipo ResultSet con ls informcion de la base de datos //todo preguntar por el puntero ya que en java no existen
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

/*
    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

 */

}
