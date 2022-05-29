package business.entities;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import presentation.listeners.PlayBarListener;

import java.io.InputStream;

/**
 * Class which executes methods related to the performance of song reproduction
 */
public class MusicPlayer {

    /**
     * reference object to synchronize the thread of the class
     */
    private final Object synchronizedThread = new Object();

    /**
     * Thread object that is used in the class MusicPlayer
     */
    private Thread thread;
    /**
     * Int indicates that the song is not playing
     */
    private final static int NOTSTARTEDSONG = 0;

    /**
     * Int indicates that the song is playing
     */
    private final static int PLAYINGMUSIC = 1;

    /**
     * Int indicates that the song is paused
     */
    private final static int PAUSED = 2;

    /**
     * Int  indicates that the song is finished
     */
    private final static int FINISHED = 3;

    /**
     * Boolean that indicates if the song has finished (true)
     */
    private boolean finishedSong;

    /**
     * Int indicates the current status of the song
     */
    private int musicStatus;

    /**
     * Object of the player class, from the Jlayer library
     */
    private final Player player;
    /**
     * Object bar listener
     */
    private PlayBarListener playBarListener;


    /**
     * Constructor MusicPlayer
     * @param inputStream Object InputSteam , with the information of FileInputStream
     * @param playBarListener Object playBarListener
     * @throws JavaLayerException Jlayer class exception
     */
    public MusicPlayer(final InputStream inputStream, PlayBarListener playBarListener) throws JavaLayerException {
            this.player = new Player(inputStream);
            this.playBarListener = playBarListener;
    }

    /**
     * Method to begin the thread for playing a song or restart in case of pause
     */
    public void play() {
        finishedSong = false;
        musicStatus = NOTSTARTEDSONG;

        synchronized (synchronizedThread) {
            switch (musicStatus) {
                case NOTSTARTEDSONG:
                    final Runnable r = new Runnable() {
                        public void run() {
                            playMusic();
                        }
                    };

                    thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setPriority(Thread.MAX_PRIORITY);
                    musicStatus = PLAYINGMUSIC;
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
            if (musicStatus == PLAYINGMUSIC) {
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
                musicStatus = PLAYINGMUSIC;
                synchronizedThread.notifyAll();
            }
            return musicStatus == PLAYINGMUSIC;
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
     * @return boolean that indicates if the song has finished
     */
    private boolean playMusic() {
        double temps = 0;
        int segons = 0;
        int minutes = 0;
        while (musicStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }

                temps = temps + 2.42;

                if (temps > 100 && temps < 103) {
                    temps = 0;
                    segons++;
                    if (segons == 60) {
                        segons = 0;
                        minutes++;
                    }
                    playBarListener.updateBar(minutes, segons);
                }

            } catch (final JavaLayerException e) {
                break;
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
        finishedSong = true;
        close();
        return finishedSong;
    }

    /**
     * Method that sets song status to finished and stop the thread
     */
    public void close() {
        synchronized (synchronizedThread) {
            musicStatus = FINISHED;
        }
        try {
            player.close();

        } catch (final Exception e) {
        }
    }

    /**
     * method to check if the song has finished
     * @return boolean that indicates if the song has finished (true)
     */
    public boolean getfinisehedSong() {
        return finishedSong;
    }
}

