package persistence.json;

//import com.google.gson.Gson;
import persistence.ConfigDAO;
import java.io.FileNotFoundException;
//import org.json.simple.JSONArray;


public class ConfigJsonDAO implements ConfigDAO {

    private static final String filename = "configJson/config.json";
    //private final Gson gson;

    private int puerto;
    private String ip;
    private String nombreBase;
    private String usuario;
    private String contraseña;

    //otro con la libreria de google
    /*
    public void readFile(){

        JsonElement fichero = null;

        try {
            //fichero = Json.parseReader(new Filereader(filename));
            //JsonArray informacion;

            this.puerto = informacion.get(0).getAsInt;
            this.ip = informacion.get(1).getAsString/;
            this.nombreBasa = informacion.get(2).getAsString/;
            this.usuario = informacion.get(3).getAsString/;
            this.contaseña =  informacion.get(4).getAsString/;

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        */
    }
}