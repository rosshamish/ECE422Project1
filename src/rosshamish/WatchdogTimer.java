package rosshamish;

import java.util.Timer;
import java.util.TimerTask;

public class WatchdogTimer implements Runnable {
    private Thread watched;
    private long timeout_ms;

    public WatchdogTimer(Thread watched, long timeout_ms) {
        this.watched = watched;
        this.timeout_ms = timeout_ms;
    }

    public WatchdogTimer(Thread watched, int timeout_s) {
        this.watched = watched;
        this.timeout_ms = ((long) timeout_s) * 1000;
    }

    @Override
    public void run() {
        Timer timer = new Timer("WatchdogTimer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                watched.stop();
            }
        }, this.timeout_ms);
    }
}
