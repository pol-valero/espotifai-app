package persistence.DAO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import persistence.ConfigDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class JsonDAO implements ConfigDAO {
    private String usuariobbdd ;
    private String password ;
    private String url ;

    public String getUsuariobbdd() {
        return usuariobbdd;
    }

    public String getPassword() {

        return password;
    }

    public String getUrl() {

        return url;
    }

    public JsonDAO(){
        readJson();
    }
    public void readJson() {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("configJson/config.json"));
            JSONObject configjson = (JSONObject) obj;

            this.usuariobbdd = (String) configjson.get("userbbdd");
            this.password = (String) configjson.get("password");
            this.url = "jdbc:mysql://" + configjson.get("IP") + ":" + configjson.get("Port") + "/" + configjson.get("NomBase");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
