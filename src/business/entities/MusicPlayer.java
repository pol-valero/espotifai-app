package business.entities;
import com.sun.tools.javac.Main;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import presentation.listeners.PlayBarListener;

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
    private PlayBarListener playBarListener;


    public MusicPlayer(final InputStream inputStream, PlayBarListener playBarListener) throws JavaLayerException {
            this.player = new Player(inputStream);
            this.playBarListener = playBarListener;
    }

    /**
     * Method to begin the thread for playing a song or restart in case of pause
     */
    public void play() {
        finishedSong = false;
        musicStatus = NOTSTARTED;

        synchronized (synchronizedThread) {
            switch (musicStatus) {
                case NOTSTARTED:
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
        return finishedSong = true;
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

    public boolean getfinisehedSong() {
        return finishedSong;
    }
}

