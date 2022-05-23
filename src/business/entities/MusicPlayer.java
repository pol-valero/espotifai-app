package business.entities;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.InputStream;

public class MusicPlayer {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private boolean finishedSong;
    private int musicStatus;

    private final Player player;

    private final Object synchronizedThread = new Object();

    public MusicPlayer(final InputStream inputStream) throws JavaLayerException {
            this.player = new Player(inputStream);
    }


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

                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    musicStatus = PLAYING;
                    t.start();
                    break;

                case PAUSED:
                    resume();
                    break;

                default:
                    break;
            }
        }
    }


    public boolean pause() {
        synchronized (synchronizedThread) {
            if (musicStatus == PLAYING) {
                musicStatus = PAUSED;
            }
            return musicStatus == PAUSED;
        }
    }


    public boolean resume() {
        synchronized (synchronizedThread) {
            if (musicStatus == PAUSED) {
                musicStatus = PLAYING;
                synchronizedThread.notifyAll();
            }
            return musicStatus == PLAYING;
        }
    }


    public void stop() {
        synchronized (synchronizedThread) {
            musicStatus = FINISHED;
            synchronizedThread.notifyAll();
        }
    }

    private boolean playInternal() {
        while (musicStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
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
        close();
        return finishedSong = true;
    }


    private void close() {
        synchronized (synchronizedThread) {
            musicStatus = FINISHED;
        }
        try {
            player.close();

        } catch (final Exception e) {

        }
    }
}
