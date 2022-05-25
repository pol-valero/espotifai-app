import business.BusinessFacadelmpl;
import business.entities.Song;
import presentation.UIController;

/**
 * Clase que sirve de punto de partida para ejecutar el programa
 */
public class Main {
    public static void main(String[] args) {


        UIController uiController = new UIController();
        uiController.run();
        //BusinessFacadelmpl businessFacadelmpl = new BusinessFacadelmpl();
       /* businessFacadelmpl.createSong(new Song(0, "tetris", 0,"gamer",
                0, "gamertetris", 0, "anonimo", 8, "Uri",
                "Songs/cancion", 0));


        businessFacadelmpl.createSong(new Song("prueba" , "Artistaprueba",
                 "albumprueba", "generoprueba", "Uri",
                "Songs/cancion", "hola", 1, 1));

 */
    }
}
