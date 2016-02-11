package rosshamish;

import java.util.Timer;
import java.util.TimerTask;

public class WatchdogTimer {
    private Thread watched;
    private long timeout_ms;
    private Timer timer = null;

    public WatchdogTimer(Thread watched, long timeout_ms) {
        this.watched = watched;
        this.timeout_ms = timeout_ms;
    }

    public WatchdogTimer(Thread watched, int timeout_s) {
        this.watched = watched;
        this.timeout_ms = ((long) timeout_s) * 1000;
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void start() {
        timer = new Timer("WatchdogTimer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (watched.isAlive()) {
                    System.out.println(String.format("Watchdog killing thread %s after %dms",
                            watched.toString(), timeout_ms));
                    watched.stop();
                }
            }
        }, timeout_ms);
    }
}
