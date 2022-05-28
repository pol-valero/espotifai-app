package business.entities;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.InputStream;

/**
 * Class which executes methods related to the performance of song reproduction
 */
public class MusicPlayer {

    private final Object synchronizedThread = new Object();
    private Thread thread;

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private boolean finishedSong;
    private int musicStatus;

    private final Player player;


    public MusicPlayer(final InputStream inputStream) throws JavaLayerException {
            this.player = new Player(inputStream);
    }

    /**
     * Method to begin the thread for playing a song or restart in case of pause
     */
    public void play() {
        System.out.println("se acaba vale = "+ finishedSong);
        finishedSong = false;
        musicStatus = NOTSTARTED;

        synchronized (synchronizedThread) {
            switch (musicStatus) {

                case NOTSTARTED: //todo preguntar si asi es correcto
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal();
                        }
                    };

                    thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setPriority(Thread.MAX_PRIORITY);
                    musicStatus = PLAYING;
                    thread.start();
                    break;

                case PAUSED:
                    resume();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Method that pauses the thread in case of playing status
     * @return returns true in case the previous status was Playing. In other cases, NotStarted or Finished;
     * returns false.
     */
    public boolean pause() {
        synchronized (synchronizedThread) {
            if (musicStatus == PLAYING) {
                musicStatus = PAUSED;
            }
            return musicStatus == PAUSED;
        }
    }

    /**
     * Method that restrats the thread in case of pause status
     * @return returns true in case the previous status was Pause. In other cases, NotStarted or Finished;
     * returns false.
     */
    public boolean resume() {
        synchronized (synchronizedThread) {
            if (musicStatus == PAUSED) {
                musicStatus = PLAYING;
                synchronizedThread.notifyAll();
            }
            return musicStatus == PLAYING;
        }
    }

    /**
     * Method that sets the status to finished
     */
    public void stop() {
        synchronized (synchronizedThread) {
            musicStatus = FINISHED;
            synchronizedThread.notifyAll();
        }
    }

    /**
     * Method that manages the reproduction of the
     * @return
     */
    private boolean playInternal() {
        while (musicStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
                thread.sleep(100);
            } catch (final JavaLayerException e) {
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (synchronizedThread) {
                while (musicStatus == PAUSED) {
                    try {
                        synchronizedThread.wait();

                    } catch (final InterruptedException e) {
                        break;
                    }
                }
            }
        }
        close();
        return finishedSong = true;
    }

    /**
     * Method that sets song status to finished and stop the thread
     */
    private void close() {
        synchronized (synchronizedThread) {
            musicStatus = FINISHED;
        }
        try {
            player.close();

        } catch (final Exception e) {

        }
    }

    public boolean getfinisehedSong() {
        return finishedSong;
    }
}

