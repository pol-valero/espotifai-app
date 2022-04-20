package persistence;

import java.sql.*;

public class SQLConnector {

    private static SQLConnector instance = null;

    private final String username;
    private final String password;
    private final String url;
    private Connection conn;
/* todo copiado del study cambiar a nuestra info

    public static SQLConnector getInstance(){
        if (instance == null ){
            // NOT a good practice to hardcode connection data! Be aware of this for your project delivery ;)
            instance = new SQLConnector("root", "swain", "localhost", 3306, "oopd_studentsdb"); //todo esta linea
            instance.connect();
        }
        return instance;
    }
 */
    private SQLConnector(String username, String password, String ip, int port, String database){
        this.username = username;
        this.password = password;
        this.url = "https://spotify-lasalle.com:8443/";
    }
}
