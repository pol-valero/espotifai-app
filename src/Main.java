import business.BusinessFacadelmpl;
import business.entities.Song;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import presentation.UIController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Clase que sirve de punto de partida para ejecutar el programa
 */
public class Main {
    public static void main(String[] args) {

        //UIController uiController = new UIController();
        //uiController.run();
        //BusinessFacadelmpl businessFacadelmpl = new BusinessFacadelmpl();
       /* businessFacadelmpl.createSong(new Song(0, "tetris", 0,"gamer",
                0, "gamertetris", 0, "anonimo", 8, "Uri",
                "Songs/cancion", 0));


        businessFacadelmpl.createSong(new Song("prueba" , "Artistaprueba",
                 "albumprueba", "generoprueba", "Uri",
                "Songs/cancion", "hola", 1, 1));

 */
        /*
        // en la clase business, antes de insertar canción, hay que llamar a la api para
        // recuperar la letra
        // con la letra, pasamos a clase todos los campos + letra recuperada de la api

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection conn = null;
        try{

            String lyric = "";

            URL url = new URL("https://api.lyrics.ovh/v1/melendi/saraluna");
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            conn.setReadTimeout(5000);

            //miramos is se ha podido ejecutar
            int status = conn.getResponseCode();

            //en caso de error , recupero el html de error
            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();

                //hay que decidir que se hace si hay error se guarda cancion = "" o se devuelve error
                System.out.println("response code: " + status);
                System.out.println(responseContent.toString());
            }
            // si no hay error,  normalmente code = 200, recupero el html correcto
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();

                // me guardo el resultado json
                String myjson = responseContent.toString();

                JSONParser parser = new JSONParser();
                //parseo el resultado
                Object obj = parser.parse(myjson);
                JSONObject configjson = (JSONObject) obj;

                //recupero y muestro lyrics
                lyric = (String) configjson.get("lyrics");

                //desactivar cuadno no se use
                System.out.println("cancion: " + lyric);

            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

         */
    }
}
